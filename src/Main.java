import javax.security.auth.login.AccountNotFoundException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Bank bank = new Bank();

            // 1️⃣ Create Customers
            bank.createCustomer(1, "Alice", "alice@gmail.com", 987654321);
            bank.createCustomer(2, "Bob", "bob@gmail.com", 912345678);

            // 2️⃣ Open Accounts
            bank.openAccount(1001, 5000, "1", "Saving");
            bank.openAccount(1002, 3000, "2", "Current");

            // 3️⃣ Create Threads for Concurrent Transactions
            Thread t1 = new Thread(() -> {
                try {
                    bank.deposit(1001, 2000, 1);
                } catch (Exception e) {
                    System.out.println("Thread1 Error: " + e.getMessage());
                }
            });

            Thread t2 = new Thread(() -> {
                try {
                    bank.withdraw(1001, 1000, 1);
                } catch (Exception e) {
                    System.out.println("Thread2 Error: " + e.getMessage());
                }
            });

            Thread t3 = new Thread(() -> {
                try {
                    bank.transfer(1001, 1002, 1500, 1);
                } catch (Exception e) {
                    System.out.println("Thread3 Error: " + e.getMessage());
                }
            });

            Thread t4 = new Thread(() -> {
                try {
                    bank.deposit(1002, 500, 2);
                } catch (Exception e) {
                    System.out.println("Thread4 Error: " + e.getMessage());
                }
            });

            // 4️⃣ Start Threads (simulating multiple ATMs/users at same time)
            t1.start();
            t2.start();
            t3.start();
            t4.start();

            // 5️⃣ Wait for all threads to finish
            t1.join();
            t2.join();
            t3.join();
            t4.join();

            // 6️⃣ Show Balances After Concurrent Transactions
            System.out.println("\nFinal Balances:");
            bank.showBalance(1001);
            bank.showBalance(1002);

            // 7️⃣ Show Transaction History
            System.out.println("\nTransactions for Account 1001:");
            bank.showTransactions(1001);

            System.out.println("\nTransactions for Account 1002:");
            bank.showTransactions(1002);

        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }
    }
}





