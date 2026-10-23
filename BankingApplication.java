import java.util.Scanner;

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 10000.0; // Fixed opening balance of ₹10,000

        System.out.println("Welcome to Kiran Bank");

        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                scanner.next(); // Consume invalid token
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 1) {
                // Check Balance
                System.out.println("Current Balance: ₹" + (int) balance);
            } else if (choice == 2) {
                // Deposit Money
                System.out.print("Enter deposit amount: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid amount entered!");
                    scanner.next();
                    continue;
                }
                double depositAmount = scanner.nextDouble();
                if (depositAmount > 0) {
                    balance += depositAmount;
                    System.out.println("Deposit successful.");
                    System.out.println("Updated Balance: ₹" + (int) balance);
                } else {
                    System.out.println("Invalid amount! Deposit amount must be greater than 0.");
                }
            } else if (choice == 3) {
                // Withdraw Money
                System.out.print("Enter withdrawal amount: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid amount entered!");
                    scanner.next();
                    continue;
                }
                double withdrawAmount = scanner.nextDouble();
                if (withdrawAmount > 0) {
                    if (withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        System.out.println("Withdrawal successful.");
                        System.out.println("Updated Balance: ₹" + (int) balance);
                    } else {
                        System.out.println("Transaction failed: Insufficient balance.");
                        System.out.println("Current Balance: ₹" + (int) balance);
                    }
                } else {
                    System.out.println("Invalid amount! Withdrawal amount must be greater than 0.");
                }
            } else if (choice == 4) {
                // Exit
                System.out.println("Thank you for banking with us. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice! Please select an option between 1 and 4.");
            }
        }
        scanner.close();
    }
}