public class SavingAccount extends Account {
    // Minimum balance required for a savings account
    private static final double MIN_SAVINGS_BALANCE = 500.0;
    
    // Overdraft limit for savings account (typically 0 for savings)
    private static final double SAVINGS_OVERDRAFT_LIMIT = 0.0;

    public SavingAccount(int accountNumber, double initialBalance, String customerId) {
        super(accountNumber, initialBalance, customerId);
    }

    @Override
    public double minimumBalance() {
        return MIN_SAVINGS_BALANCE;
    }

    @Override
    public double overdraftLimit() {
        return SAVINGS_OVERDRAFT_LIMIT;
    }
}