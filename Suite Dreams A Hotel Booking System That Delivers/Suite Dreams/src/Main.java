import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;  
    
        public static void displayHeader() {
            System.out.println("==================================================");
            System.out.println("             WELCOME TO SUITE DREAMS     ");
            System.out.println("==================================================");
            System.out.println("           CREATED BY: PAUL C. ALCARAZ         ");
            System.out.println("--------------------------------------------------");
        }


    public static void main(String[] args) {
    int choice = -1;
    displayHeader();
    
    
    while (true) {
        System.out.println("Welcome to the Hotel Booking System!");
        System.out.println("Please choose an option:");
        System.out.println("1. Register");
        System.out.println("2. Log in");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        
        
        while (true) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();  
                if (choice < 1 || choice > 3) {
                    System.out.print("Invalid choice. Please enter a number(1 - 3): ");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number (1 - 3): ");
                scanner.nextLine(); 
            }
        }

        switch (choice) {
            case 1:
                registerUser(); 
                break;
            case 2:
                logInUser();  
                break;
            case 3:
                System.out.println("Exiting the system. Goodbye!");
                return; 
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}


    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();

       
        double balance = User.generateRandomBalance();

       
        User newUser = new User(username, password, firstName, lastName, contactNumber, balance);
        if (DatabaseConnection.addUserToDatabase(newUser)) {
            System.out.println("Registration successful! You can now log in.");
            System.out.println();
        } else {
            System.out.println("Registration failed. Username might already exist.");
            System.out.println();
        }
    }

    
    private static void logInUser() {
        System.out.print("Enter username to log in: ");
        String username = scanner.nextLine();
        System.out.print("Enter password to log in: ");
        String password = scanner.nextLine();
    
       
        currentUser = DatabaseConnection.authenticate(username, password);
        
        if (currentUser != null) {
            System.out.println("Login successful! Welcome, " + currentUser.getFullName() + ".");
            System.out.println("Your balance is: $" + currentUser.getBalance());
            showUserMenu();  
        } else {
            System.out.println("Authentication failed: Incorrect username or password.");
            System.out.println();
        }
    }
    

    private static void showUserMenu() {
        int choice = -1;
        
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. View Bookings");
            System.out.println("3. Checkout");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            
            
            while (true) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); 
                    if (choice < 1 || choice > 4) {
                        System.out.print("Invalid choice. Please enter a number (1-4): ");
                        continue;
                    }
                    break; 
                } catch (InputMismatchException e) {
                    System.out.print("Invalid input. Please enter a number (1-4): ");
                    scanner.nextLine();  
                }
            }
    
            switch (choice) {
                case 1:
                    viewAvailableRooms(); 
                    break;
                case 2:
                    viewBookings();  
                    break;
                case 3:
                    checkOutUser(); 
                    return; 
                case 4:
                    System.out.println("You have logged out.");
                    currentUser = null;  
                    return;  
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
    
  
    private static void viewAvailableRooms() {
        
        Hotel hotel = new Hotel();
        hotel.showAvailableRooms(scanner, currentUser);
    }


    private static void viewBookings() {
        if (currentUser != null) {
           
            Hotel.showUserBookings(currentUser);
        } else {
            System.out.println("Please log in to view your bookings.");
        }
    }


    private static void checkOutUser() {
        if (currentUser != null) {
            System.out.println("Are you sure you want to check out? (yes/no): ");
            String choice = scanner.nextLine();
            
            if (choice.equalsIgnoreCase("yes")) {
               
                int userIdToDelete = currentUser.getUserId();  
                System.out.println("Deleting user with ID: " + userIdToDelete);

                boolean success = DatabaseConnection.deleteUserAndBooking(userIdToDelete);

                if (success) {
                    System.out.println("User and their bookings deleted successfully.");
                } else {
                    System.out.println("Error occurred while deleting user and bookings.");
                }
            } else {
                System.out.println("Checkout canceled.");
            }
        } else {
            System.out.println("Please log in to check out.");
        }
    }
}

