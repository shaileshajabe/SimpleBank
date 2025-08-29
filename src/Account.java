abstract class  Account {
    private int AccountNumber;
    private double Balance;
    private String CustomerId;


    //Getters
    public double getBalance() {
        return Balance;
    }
    public String getCustomerId() {
        return CustomerId;
    }
    public int getAccountNumber() {
        return AccountNumber;
    }

    public Account(double Balance, String CustomerId, int AccountNumber) {
        this.Balance = Balance;
        this.CustomerId = CustomerId;
        this.AccountNumber = AccountNumber;
    }

    public Account(int AccountNumber, double Balance) {
        this.AccountNumber = AccountNumber;
        this.Balance = Balance;
    }
    public void deposit(double amount){
        Balance += amount;
    }
    public void withdrawal(double amount){
        Balance -= amount;
    }



}
