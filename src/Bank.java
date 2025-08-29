import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank  implements BankOperation{
    List<Customer> customers = new ArrayList<>();
    Map<Integer,Account> accounts = new HashMap<>();
    Map<Integer,List<Transaction>> transactions = new HashMap<>();
    final double intrestRateSavingAccount=4.00;
    final double intrestRateCurrentAccount=2.00;

    public void mergeAccounts(int AccountNumber1, int AccountNumber2) {
        accounts.get(AccountNumber1).deposit(accounts.get(AccountNumber2).getBalance());
        accounts.remove(AccountNumber2);
        transactions.get(AccountNumber1).add(new Transaction(transactions.size(), AccountNumber1, "Merge", accounts.get(AccountNumber2).getBalance()));
    }

    public void exportAccountsToFile(String fileName, int AccountNumber, String AccountType, String CustomerId) throws Exception{
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write("AccountNumber,AccountType,CustomerId\n");
        myWriter.write(AccountNumber+","+AccountType+","+CustomerId+"\n");
        try{
            myWriter.write("Balance,"+accounts.get(AccountNumber).getBalance()+"\n");
            myWriter.write("Interest,"+calculateInterest(AccountNumber)+"\n");
            myWriter.write("Transactions\n");
            for(Transaction transaction:transactions.get(AccountNumber)){
                myWriter.write(transaction.toString()+"\n");
            }
        }catch (Exception e){
            throw new Exception("Account not found");
        }
        myWriter.close();
    }

    public double calculateInterest(int AccountNumber) {
        double intrest=0;
        if(accounts.get(AccountNumber) instanceof SavingAccount){
            intrest=(intrestRateSavingAccount*accounts.get(AccountNumber).getBalance())/100;
        }else if(accounts.get(AccountNumber) instanceof CurrentAccount){
            intrest=(intrestRateCurrentAccount*accounts.get(AccountNumber).getBalance())/100;
        }
        return intrest;
    }


    //createCustomer(), openAccount(), deposit(), withdraw(), transfer(), showBalance(), showTransactions()
    public void createCustomer(int id, String name, String email, int phone) throws CustomerAlreadyExists,AccountNumberAlreadyExistsException {
        if(customers.stream().anyMatch(customer -> customer.getId() == id) || customers.stream().anyMatch(customer -> customer.getPhone()==phone)){
            throw new CustomerAlreadyExists("Customer already exists");
        }else{
            customers.add(new Customer(id, name, email, phone));
        }
    }

    public void openAccount(int AccountNumber, double Balance, String CustomerId, String AccountType) {
        if (AccountType.equals("Saving")) {
            accounts.put(AccountNumber,(new SavingAccount(AccountNumber, Balance, CustomerId)));
        } else if (AccountType.equals("Current")) {
            accounts.put(AccountNumber,(new CurrentAccount(AccountNumber, Balance, CustomerId)));
        }

    }

    public synchronized void deposit(int AccountNumber, double amount, int customerId) throws AccountNumberNotFound{
        Account account = accounts.get(AccountNumber);

        if (account ==null){
            throw new AccountNumberNotFound("Account not found");
        }else if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }else{
            accounts.get(AccountNumber).deposit(amount);
        }
        System.out.println(Thread.currentThread().getName()+" Deposited "+amount+" Balance "+ accounts.get(AccountNumber).getBalance());
        transactions.get(AccountNumber).add(new Transaction(transactions.get(AccountNumber).size(), AccountNumber, "Deposit", amount));
        addTransaction(AccountNumber, "Deposit", amount); }

    public synchronized void withdraw(int AccountNumber, double amount,int customerId) throws BalanceIsLow{
         double balance = accounts.get(AccountNumber).getBalance();
         if(amount < 0){
             throw new IllegalArgumentException("Amount cannot be negative");
         }else if(balance < 500){
             throw new BalanceIsLow("Balance is low in your Account");
         }else {
             accounts.get(AccountNumber).withdrawal(amount);
         }
         System.out.println(Thread.currentThread().getName()+" Withdraw "+amount+" Balance "+ accounts.get(AccountNumber).getBalance());

        addTransaction(AccountNumber, "Deposit", amount);
    }

    public void transfer(int fromAcc, int toAcc, double amount,int customerId) throws AccountNumberNotFound{
        Account sender=accounts.get(fromAcc);
        Account reciever=accounts.get(toAcc);
        if(sender==null){
            throw new AccountNumberNotFound("Account not found");
        }else if(reciever==null){
            throw new AccountNumberNotFound("Account not found");
        }
        synchronized (sender) {  // lock sender first
            sender.withdrawal(amount);
            synchronized (reciever) { // lock receiver
                reciever.deposit(amount);
            }
        }
        addTransaction(fromAcc, "Transfer-Out", amount);
        addTransaction(toAcc, "Transfer-In", amount);
    }

    private void addTransaction(int accountNumber, String type, double amount) {
        transactions.putIfAbsent(accountNumber, new ArrayList<>());
        List<Transaction> accountTransactions = transactions.get(accountNumber);

        accountTransactions.add(
                new Transaction(accountTransactions.size(), accountNumber, type, amount)
        );
    }

    public void showBalance(int AccountNumber){
        System.out.println(accounts.get(AccountNumber).getBalance());
    }
    public void showTransactions(int AccountNumber){
        System.out.println(transactions.get(AccountNumber));
    }
}

