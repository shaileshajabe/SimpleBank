import java.util.ArrayList;
import java.util.List;

public class Bank {
    List<Customer> customers = new ArrayList<>();
    List<Account> accounts = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();


    //createCustomer(), openAccount(), deposit(), withdraw(), transfer(), showBalance(), showTransactions()
    public void createCustomer(int id,String name,String email,int phone) {
        customers.add(new Customer(id,name,email,phone));
    }

    public void openAccount(int AccountNumber, double Balance, String CustomerId,String AccountType) {
        if (AccountType.equals("Saving")) {
            accounts.add(new SavingAccount(AccountNumber, Balance, CustomerId));
        } else if (AccountType.equals("Current")) {
            accounts.add(new CurrentAccount(AccountNumber, Balance, CustomerId));
        }

    }

}

