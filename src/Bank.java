import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;



public class Bank  implements BankOperation{
    List<Customer> customers = new ArrayList<>();
    Map<Integer,Account> accounts = new HashMap<>();
    List<Transaction> transactions = new ArrayList<>();


    //createCustomer(), openAccount(), deposit(), withdraw(), transfer(), showBalance(), showTransactions()
    public void createCustomer(int id, String name, String email, int phone) {
        customers.add(new Customer(id, name, email, phone));
    }

    public void openAccount(int AccountNumber, double Balance, String CustomerId, String AccountType) {
        if (AccountType.equals("Saving")) {
            accounts.put(AccountNumber,(new SavingAccount(AccountNumber, Balance, CustomerId)));
        } else if (AccountType.equals("Current")) {
            accounts.put(AccountNumber,(new CurrentAccount(AccountNumber, Balance, CustomerId)));
        }

    }

    public void deposit(int AccountNumber, double amount, int customerId) throws AccountNumberNotFound{
        Account account = accounts.get(AccountNumber);
        if (account ==null){
            throw new AccountNumberNotFound("Account not found");
        }else{
            accounts.get(AccountNumber).deposit(amount);
        }
        transactions.add(new Transaction(transactions.size(), AccountNumber, "Deposit", amount));
    }

    public void withdraw(int AccountNumber, double amount,int customerId) throws BalanceIsLow{
         double balance = accounts.get(AccountNumber).getBalance();
         if(balance < 500){
             throw new BalanceIsLow("Balance is low in your Account");
         }else {
             accounts.get(AccountNumber).withdrawal(amount);
         }

        transactions.add(new Transaction(transactions.size(), AccountNumber, "Withdrawal", amount));
    }

    public void transfer(int AccountNumber1, int AccountNumber2, double amount,int customerId) {
        accounts.get(AccountNumber1).withdrawal(amount);
        accounts.get(AccountNumber2).deposit(amount);
    }

    public void showBalance(int AccountNumber){
        System.out.println(accounts.get(AccountNumber).getBalance());
    }

    public void showTransactions(int AccountNumber){
        transactions.forEach(transaction -> {
            if(transaction.getAccountNo() == AccountNumber){
                System.out.println(transaction);
            }
        });
    }

}

