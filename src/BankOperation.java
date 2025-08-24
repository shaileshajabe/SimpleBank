import javax.security.auth.login.AccountNotFoundException;

public interface BankOperation {
   void createCustomer(int id, String name, String email, int phone);
   void deposit(int AccountNumber, double amount, int customerId) throws AccountNumberNotFound;
   void withdraw(int AccountNumber, double amount, int customerId) throws BalanceIsLow;
   void transfer(int AccountNumber1,int AccountNumber2, double amount, int customerIdOfAccount);
}
