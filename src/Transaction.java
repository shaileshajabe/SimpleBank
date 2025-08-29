public class Transaction {
    private int transactionId;
    private long accountNo;
    private String type; // "Deposit" or "Withdraw" or "Transfer"
    private double amount;
   // private String date;

    // Constructor
    public Transaction(int transactionId, long accountNo, String type, double amount) {
        this.transactionId = transactionId;
        this.accountNo = accountNo;
        this.type = type;
        this.amount = amount;
        //this.date = date;
        this.accountNo = accountNo;
    }

    // Getters
    public int getTransactionId() {
        return transactionId;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

//    public String getDate() {
//        return date;
//    }

    // To print transaction details
    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
                ", Account No: " + accountNo +
                ", Type: " + type +
                ", Amount: " + amount ;
               // ", Date: " + date;
    }
}
