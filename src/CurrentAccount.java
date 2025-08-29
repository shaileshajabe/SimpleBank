public class CurrentAccount extends Account {
 private int minimumBalance = 500;
 private double overdraftLimit = 0.0;
    public CurrentAccount(int accountNumber, double initialBalance, String customerId) {
        super( initialBalance, customerId,accountNumber);
    }
}














