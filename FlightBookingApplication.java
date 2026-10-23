import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// --- ABSTRACTION & INHERITANCE ---
abstract class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    // Abstract method for polymorphism
    public abstract double calculateTotalFare(double baseFare, int tickets);
    public abstract String getPassengerType();
}

class RegularPassenger extends Passenger {
    public RegularPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double calculateTotalFare(double baseFare, int tickets) {
        // 0% discount for regular passengers
        return baseFare * tickets;
    }

    @Override
    public String getPassengerType() {
        return "Regular Passenger (0% Discount)";
    }
}

class StudentPassenger extends Passenger {
    public StudentPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double calculateTotalFare(double baseFare, int tickets) {
        // 15% discount for students
        double total = baseFare * tickets;
        return total * 0.85;
    }

    @Override
    public String getPassengerType() {
        return "Student Passenger (15% Discount)";
    }
}

// --- ENCAPSULATION: FLIGHT CLASS ---
class Flight {
    private String flightCode;
    private String airline;
    private String source;
    private String destination;
    private int availableSeats;
    private double baseFare;

    public Flight(String flightCode, String airline, String source, String destination, int availableSeats, double baseFare) {
        this.flightCode = flightCode;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.baseFare = baseFare;
    }

    public String getFlightCode() { return flightCode; }
    public String getAirline() { return airline; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getAvailableSeats() { return availableSeats; }
    public double getBaseFare() { return baseFare; }

    public void bookSeats(int count) {
        if (count <= availableSeats) {
            this.availableSeats -= count;
        }
    }
}

// --- ENCAPSULATION: BOOKING CLASS ---
class Booking {
    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int ticketCount;
    private double totalFare;

    public Booking(String bookingId, Flight flight, Passenger passenger, int ticketCount, double totalFare) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.ticketCount = ticketCount;
        this.totalFare = totalFare;
    }

    public void displayBookingSummary() {
        System.out.println("\n========================================");
        System.out.println("           BOOKING SUMMARY              ");
        System.out.println("========================================");
        System.out.println("Booking ID     : " + bookingId);
        System.out.println("Passenger Name : " + passenger.getName() + " (Age: " + passenger.getAge() + ")");
        System.out.println("Category       : " + passenger.getPassengerType());
        System.out.println("Flight Code    : " + flight.getFlightCode());
        System.out.println("Airline        : " + flight.getAirline());
        System.out.println("Route          : " + flight.getSource() + " -> " + flight.getDestination());
        System.out.println("Tickets Count  : " + ticketCount);
        System.out.println("Base Fare      : ₹" + flight.getBaseFare() + " per ticket");
        System.out.println("Total Final Amt: ₹" + totalFare);
        System.out.println("========================================");
    }
}

// --- SYSTEM MANAGEMENT CLASS ---
class FlightBookingSystem {
    private List<Flight> flights;
    private Booking latestBooking;
    private Scanner scanner;
    private Random random;

    public FlightBookingSystem() {
        flights = new ArrayList<>();
        scanner = new Scanner(System.in);
        random = new Random();
        
        // Initialize predefined flights
        flights.add(new Flight("AI-101", "Air India", "Pune", "Delhi", 50, 4500.0));
        flights.add(new Flight("6E-202", "IndiGo", "Mumbai", "Bangalore", 40, 3200.0));
        flights.add(new Flight("SG-303", "SpiceJet", "Pune", "Goa", 30, 2500.0));
    }

    public void start() {
        while (true) {
            System.out.println("\n--- FLIGHT BOOKING SYSTEM (OOP) ---");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayFlights();
                    break;
                case 2:
                    bookFlight();
                    break;
                case 3:
                    viewBooking();
                    break;
                case 4:
                    System.out.println("Thank you for using Flight Booking Application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please select an option between 1 and 4.");
            }
        }
    }

    public void displayFlights() {
        System.out.println("\n-------------------------------------------------------------------------");
        System.out.printf("%-10s | %-12s | %-10s | %-12s | %-15s | %-10s\n", 
                "Flight Code", "Airline", "Source", "Destination", "Available Seats", "Base Fare");
        System.out.println("-------------------------------------------------------------------------");
        for (Flight f : flights) {
            System.out.printf("%-10s | %-12s | %-10s | %-12s | %-15d | ₹%-9.2f\n", 
                    f.getFlightCode(), f.getAirline(), f.getSource(), f.getDestination(), f.getAvailableSeats(), f.getBaseFare());
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    public Flight findFlightByCode(String code) {
        for (Flight f : flights) {
            if (f.getFlightCode().equalsIgnoreCase(code.trim())) {
                return f;
            }
        }
        return null;
    }

    public void bookFlight() {
        displayFlights();
        System.out.print("Enter Flight Code to book: ");
        String code = scanner.nextLine();

        Flight selectedFlight = findFlightByCode(code);
        if (selectedFlight == null) {
            System.out.println("Error: Flight code not found!");
            return;
        }

        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Error: Passenger name cannot be empty!");
            return;
        }

        System.out.print("Enter Passenger Age: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid age entered!");
            scanner.next();
            return;
        }
        int age = scanner.nextInt();
        scanner.nextLine();

        if (age <= 0 || age > 120) {
            System.out.println("Error: Please enter a valid age between 1 and 120!");
            return;
        }

        System.out.print("Enter Number of Tickets: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid ticket count!");
            scanner.next();
            return;
        }
        int tickets = scanner.nextInt();
        scanner.nextLine();

        if (tickets <= 0 || tickets > selectedFlight.getAvailableSeats()) {
            System.out.println("Error: Invalid ticket count or insufficient seats available (Seats left: " + selectedFlight.getAvailableSeats() + ")!");
            return;
        }

        System.out.println("Select Passenger Category:");
        System.out.println("1. Regular Passenger (0% discount)");
        System.out.println("2. Student Passenger (15% discount)");
        System.out.print("Enter choice (1-2): ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid category choice!");
            scanner.next();
            return;
        }
        int catChoice = scanner.nextInt();
        scanner.nextLine();

        Passenger passenger;
        if (catChoice == 1) {
            passenger = new RegularPassenger(name, age);
        } else if (catChoice == 2) {
            passenger = new StudentPassenger(name, age);
        } else {
            System.out.println("Error: Invalid category selection!");
            return;
        }

        // Polymorphism applied here via calculateTotalFare
        double finalFare = passenger.calculateTotalFare(selectedFlight.getBaseFare(), tickets);

        // Confirm booking
        System.out.println("\n--- FARE CALCULATION ---");
        System.out.println("Base Fare (per ticket) : ₹" + selectedFlight.getBaseFare());
        System.out.println("Ticket Count           : " + tickets);
        System.out.println("Passenger Category     : " + passenger.getPassengerType());
        System.out.println("Final Total Amount     : ₹" + finalFare);
        System.out.print("Confirm booking? (y/n): ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("y")) {
            selectedFlight.bookSeats(tickets);
            String bookingId = "BK-" + (1000 + random.nextInt(9000));
            latestBooking = new Booking(bookingId, selectedFlight, passenger, tickets, finalFare);
            System.out.println("Booking confirmed successfully!");
            latestBooking.displayBookingSummary();
        } else {
            System.out.println("Booking cancelled by user.");
        }
    }

    public void viewBooking() {
        if (latestBooking == null) {
            System.out.println("\nNo recent bookings found! Please book a flight first.");
        } else {
            latestBooking.displayBookingSummary();
        }
    }
}

public class FlightBookingApplication {
    public static void main(String[] args) {
        FlightBookingSystem system = new FlightBookingSystem();
        system.start();
    }
}