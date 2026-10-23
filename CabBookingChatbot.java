import java.util.Random;
import java.util.Scanner;

public class CabBookingChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            System.out.println("\n========================================");
            System.out.println("       KIRAN ACADEMY CAB BOOKING        ");
            System.out.println("========================================");
            System.out.println("1. Book Ride");
            System.out.println("2. Exit");
            System.out.print("Enter your choice (1-2): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter numbers 1 or 2.");
                scanner.next(); // Consume invalid token
                continue;
            }

            int mainChoice = scanner.nextInt();

            if (mainChoice == 2) {
                System.out.println("Thank you for using Kiran Academy Cab Booking Chatbot. Goodbye!");
                break;
            } else if (mainChoice != 1) {
                System.out.println("Invalid choice! Please select 1 or 2.");
                continue;
            }

            // 2. Choose cab type
            System.out.println("\n--- CHOOSE CAB TYPE ---");
            System.out.println("1. Auto (₹12/km)");
            System.out.println("2. Sedan (₹18/km)");
            System.out.println("3. SUV (₹25/km)");
            System.out.print("Enter cab type choice (1-3): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Returning to main menu.");
                scanner.next();
                continue;
            }
            int cabChoice = scanner.nextInt();

            String cabType = "";
            int ratePerKm = 0;

            if (cabChoice == 1) {
                cabType = "Auto";
                ratePerKm = 12;
            } else if (cabChoice == 2) {
                cabType = "Sedan";
                ratePerKm = 18;
            } else if (cabChoice == 3) {
                cabType = "SUV";
                ratePerKm = 25;
            } else {
                System.out.println("Invalid cab type selection! Returning to main menu.");
                continue;
            }

            // 3. Choose route
            System.out.println("\n--- CHOOSE ROUTE ---");
            System.out.println("1. Pune Station -> Shivajinagar (5 km)");
            System.out.println("2. Swargate -> Kothrud (8 km)");
            System.out.println("3. Hinjawadi -> Wakad (10 km)");
            System.out.print("Enter route choice (1-3): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Returning to main menu.");
                scanner.next();
                continue;
            }
            int routeChoice = scanner.nextInt();

            String source = "";
            String destination = "";
            int distance = 0;

            if (routeChoice == 1) {
                source = "Pune Station";
                destination = "Shivajinagar";
                distance = 5;
            } else if (routeChoice == 2) {
                source = "Swargate";
                destination = "Kothrud";
                distance = 8;
            } else if (routeChoice == 3) {
                source = "Hinjawadi";
                destination = "Wakad";
                distance = 10;
            } else {
                System.out.println("Invalid route selection! Returning to main menu.");
                continue;
            }

            // 4. Calculate fare
            int totalFare = ratePerKm * distance;
            System.out.println("\n--- RIDE FARE DETAILS ---");
            System.out.println("Cab Type     : " + cabType + " (₹" + ratePerKm + "/km)");
            System.out.println("Source       : " + source);
            System.out.println("Destination  : " + destination);
            System.out.println("Distance     : " + distance + " km");
            System.out.println("Total Fare   : ₹" + totalFare);

            // 5. Cancellation before OTP
            System.out.println("\n--- BOOKING CONFIRMATION ---");
            System.out.println("1. Confirm & Share OTP");
            System.out.println("2. Cancel Ride");
            System.out.print("Enter your choice (1-2): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Ride automatically cancelled. Returning to main menu.");
                scanner.next();
                continue;
            }
            int confirmChoice = scanner.nextInt();

            if (confirmChoice == 2) {
                System.out.println("Ride has been successfully cancelled. Returning to main menu.");
                continue;
            } else if (confirmChoice != 1) {
                System.out.println("Invalid confirmation choice! Ride cancelled. Returning to main menu.");
                continue;
            }

            // 6. OTP and ride lock
            int otp = 1000 + random.nextInt(9000);
            System.out.println("\n--- BOOKING CONFIRMED ---");
            System.out.println("Your 4-digit OTP is: " + otp);
            System.out.println("Status: OTP shared successfully!");
            System.out.println("Rule: Once the OTP has been shared, the ride CANNOT be cancelled.");
            System.out.println("Returning to main menu...\n");
        }
        scanner.close();
    }
}