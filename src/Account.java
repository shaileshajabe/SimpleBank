abstract class  Account {
    private int AccountNumber;
    private double Balance;
    private String CustomerId;

    //Getters
    public int getAccountNumber() {
        return AccountNumber;
    }
    public double getBalance() {
        return Balance;
    }
    public String getCustomerId() {
        return CustomerId;
    }

    public Account(double Balance, String CustomerId) {
        this.Balance = Balance;
        this.CustomerId = CustomerId;
    }
    public void deposit(double amount){
        Balance += amount;
    }
    public void withdrawal(double amount){
        Balance -= amount;
    }

    abstract double minimumBalance();

    abstract double overdraftLimit();

}
