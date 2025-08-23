import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    List<Customer> customers = new ArrayList<>();
    Map<Integer,Account> accounts = new HashMap<>();
    List<Transaction> transactions = new ArrayList<>();

    public void storeTransaction(int transactionId, long accountNo, String type, double amount, String date) {
        transactions.add(new Transaction(transactionId, accountNo, type, amount, date));
    }

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

    public void deposit(int AccountNumber, double amount) {
//        for (Account accounts : accounts) {
//            if (accounts.getAccountNumber() == AccountNumber) {
//                accounts.deposit(amount);
//            }
//        }
        accounts.get(AccountNumber).deposit(amount);
    }

    public void withdraw(int AccountNumber, double amount) {
//        for (Account accounts : accounts) {
//            accounts.withdrawal(amount);
//        }
        accounts.get(AccountNumber).withdrawal(amount);
    }

    public void transfer(int AccountNumber1, int AccountNumber2, double amount) {
//        for (Account accounts : accounts) {
//            if (accounts.getAccountNumber() == AccountNumber1) {
//                accounts.withdrawal(amount);
//            } else if (accounts.getAccountNumber() == AccountNumber2) {
//                accounts.deposit(amount);
//            }
//        }
        accounts.get(AccountNumber1).withdrawal(amount);
        accounts.get(AccountNumber2).deposit(amount);
    }

    public void showBalance(int AccountNumber){
//        for (Account accounts : accounts) {
//            if (accounts.getAccountNumber() == AccountNumber) {
//                System.out.println(accounts.getBalance());
//            }
//        }
        System.out.println(accounts.get(AccountNumber).getBalance());
    }

//    public void showTransactions(int AccountNumber){
//        for (Account accounts : accounts) {
//            if (accounts.getAccountNumber() == AccountNumber) {
//                System.out.println(accounts.getBalance());
//            }
//        }
//    }

}

