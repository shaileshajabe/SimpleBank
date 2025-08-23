public class CurrentAccount extends Account {
 private int minimumBalance = 500;
 private double overdraftLimit = 0.0;
    public CurrentAccount(int accountNumber, double initialBalance, String customerId) {
        super(accountNumber, initialBalance, customerId);
    }
    @Override
    public double minimumBalance(){
        return minimumBalance;
    }

    @Override
    public double overdraftLimit(){
        return overdraftLimit;
    }
}
