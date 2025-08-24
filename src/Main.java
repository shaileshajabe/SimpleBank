import javax.security.auth.login.AccountNotFoundException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws AccountNumberNotFound, BalanceIsLow {
        Bank bank = new Bank();
        bank.createCustomer(1, "John Doe", "j@j.com", 1234567890);
        //bank.createCustomer(1, "John Doe", "j@j.com", 1234567890, 54321);
        bank.openAccount(12, 1000, "1", "Saving");
        bank.openAccount(21, 1000, "1", "Current");
        bank.deposit(12, 1000, 1);

        bank.deposit(21, 1000, 1);
        bank.withdraw(12, 1000,1);

        bank.withdraw(21, 1000,1);
        bank.transfer(12, 21, 1000,1);
        bank.showBalance(12);
        bank.showBalance(21);

        bank.showTransactions(12);

    }
}