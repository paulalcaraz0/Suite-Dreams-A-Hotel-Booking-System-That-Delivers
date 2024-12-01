import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Hotel {
    private List<Room> rooms;

    public Hotel() {
        rooms = RoomDatabase.initializeRooms();
    }


    public void showAvailableRooms(Scanner scanner, User user) {
        boolean continueSession = true;
    
        while (continueSession) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Back to User Menu");
            System.out.print("Enter your choice: ");
    
            int choice = -1;
    
            while (true) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();  
                    
                    if (choice < 1 || choice > 3) {
                        System.out.print("Invalid choice. Please choose a number (1-3): ");
                        continue;  
                    }
                    break; 
                } catch (InputMismatchException e) {
                    System.out.print("Invalid input. Please enter a valid number (1-3): ");
                    scanner.nextLine();  
                }
            }
    
            switch (choice) {
                case 1:
                    showRoomsByType(scanner, user.getBalance());  
                    break;
                case 2:
                    bookingOptions(scanner, user); 
                    break;
                case 3:
                    System.out.println("Returning to user menu...");
                    continueSession = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


    public void showRoomsByType(Scanner scanner, double userBalance) {
        System.out.println();
        System.out.println("Choose room type to view:");
        System.out.println("1. Single Room");
        System.out.println("2. Double Room");
        System.out.println("3. Deluxe Suite");
        
        int roomTypeChoice = -1;
    
      
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                roomTypeChoice = scanner.nextInt();
                scanner.nextLine();  
    
                if (roomTypeChoice < 1 || roomTypeChoice > 3) {
                    System.out.println("Invalid choice. Please select a valid room type (1-3).");
                    continue;
                }
                break; 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.nextLine(); 
            }
        }
    
        String roomType;
        switch (roomTypeChoice) {
            case 1:
                roomType = "Single Room";
                break;
            case 2:
                roomType = "Double Room";
                break;
            case 3:
                roomType = "Deluxe Suite";
                break;
            default:
                System.out.println("Invalid choice. Please select a valid room type.");
                return;
        }
    
        boolean found = false;
        for (Room room : rooms) {
            if (room.getRoomType().equals(roomType) && room.getPrice() <= userBalance && room.isAvailable()) {
                System.out.println(room);
                found = true;
            }
        }
    
        if (!found) {
            System.out.println("No available rooms found within your balance.");
        }
    }
    

    public void bookingOptions(Scanner scanner, User user) {
    boolean inBookingMenu = true;
    while (inBookingMenu) {
        System.out.println("\n--- Booking Options ---");
        System.out.println("1. Enter days of stay");
        System.out.println("2. Select Room Type");
        System.out.println("3. Confirm Price and Book");
        System.out.println("4. Back to Main Options");
        System.out.print("Enter your choice: ");
        
      
        int bookingChoice = -1;
        while (true) {
            try {
                bookingChoice = scanner.nextInt();
                scanner.nextLine(); 
                if (bookingChoice < 1 || bookingChoice > 4) {
                    System.out.print("Please enter a valid choice (1-4): ");
                    continue;
                }
                break; 
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number (1-4): ");
                scanner.nextLine();  
            }
        }

        switch (bookingChoice) {
            case 1:
                System.out.print("Enter number of days for stay: ");
                int days = -1;
                while (true) {
                    try {
                        days = scanner.nextInt();
                        scanner.nextLine(); 
                        if (days <= 0) {
                            System.out.println("Please enter a valid number of days (positive integer).");
                            continue;
                        }
                        user.setDaysOfStay(days);
                        break; 
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine(); 
                    }
                }
                break;
            case 2:
                selectRoomForBooking(scanner, user);
                break;
            case 3:
                confirmBooking(scanner, user);
                break;
            case 4:
                System.out.println("Returning to main menu...");
                inBookingMenu = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}


    private void selectRoomForBooking(Scanner scanner, User user) {
        System.out.println("Choose room type to book:");
        System.out.println("1. Single Room");
        System.out.println("2. Double Room");
        System.out.println("3. Deluxe Suite");
        System.out.print("Enter your choice: ");
        int roomTypeChoice = scanner.nextInt();
        scanner.nextLine();

        String roomType = switch (roomTypeChoice) {
            case 1 -> "Single Room";
            case 2 -> "Double Room";
            case 3 -> "Deluxe Suite";
            default -> {
                System.out.println("Invalid choice. Returning to booking options.");
                yield null;
            }
        };

        if (roomType != null) {
            Room selectedRoom = rooms.stream()
                    .filter(r -> r.getRoomType().equals(roomType) && r.isAvailable())
                    .findFirst()
                    .orElse(null);

            if (selectedRoom != null) {
                user.setSelectedRoom(selectedRoom);
                System.out.println("Room selected: " + selectedRoom);
            } else {
                System.out.println("No available rooms of the selected type.");
            }
        }
    }

  
    private void confirmBooking(Scanner scanner, User user) {
        Room room = user.getSelectedRoom();  
        int days = user.getDaysOfStay();     
    
        if (room != null && days > 0) {      
            double totalPrice = room.getPrice() * days;  
    
            System.out.println("\n--- Booking Confirmation ---");
            System.out.println("Room Type: " + room.getRoomType());
            System.out.println("Price per Night: $" + room.getPrice());
            System.out.println("Days of Stay: " + days);
            System.out.println("Total Price: $" + totalPrice);
            System.out.println("Your Balance: $" + user.getBalance());
    
            if (user.getBalance() >= totalPrice) {
                System.out.print("Confirm booking? (yes/no): ");
                String confirmation = scanner.nextLine();
    
                if (confirmation.equalsIgnoreCase("yes")) {
                    
                    room.setAvailable(false);
    
                   
                    double newBalance = user.getBalance() - totalPrice;
                    user.setBalance(newBalance);
    
                  
                    boolean balanceUpdated = DatabaseConnection.updateUserBalance(user.getUserId(), newBalance);
    
                    if (balanceUpdated) {
                        
                        boolean bookingSaved = DatabaseConnection.addBookingToDatabase(
                            user.getUserId(), 
                            room.getRoomType(),
                            new Timestamp(System.currentTimeMillis()), 
                            new Timestamp(System.currentTimeMillis() + days * 24L * 60 * 60 * 1000), 
                            days, 
                            totalPrice
                        );
    
                        if (bookingSaved) {
                            System.out.println("Booking successful. Your new balance is: $" + newBalance);
    
                            generateReceipt(user, room.getRoomType(), room.getPrice(), days, totalPrice);
                        } else {
                            System.out.println("Booking failed.");
                        }
                    } else {
                        System.out.println("Failed to update balance in the database.");
                    }
                } else {
                    System.out.println("Booking canceled.");
                }
            } else {
                System.out.println("Insufficient balance. You need $" + (totalPrice - user.getBalance()) + " more to complete this booking.");
            }
        } else {
            System.out.println("Please select a room and specify days of stay before confirming.");
        }
    }
        

 private static void generateReceipt(User user, String roomType, double pricePerNight, int daysOfStay, double totalPrice) {
        LocalDate checkInDate = LocalDate.now(); 
        LocalDate checkOutDate = checkInDate.plusDays(daysOfStay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
        System.out.println("\n-- RECEIPT --");
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Hotel Type: " + roomType);
        System.out.println("Check-In Date: " + checkInDate.format(formatter));
        System.out.println("Check-Out Date: " + checkOutDate.format(formatter));
        System.out.printf("Total Price: $%.2f%n", totalPrice);
        System.out.printf("Current Balance: $%.2f%n", user.getBalance());
        System.out.println("\nThank you for booking with us!");
    }
    
    
      
    public static void showUserBookings(User user) {
          
            List<Booking> bookings = DatabaseConnection.getBookingsByUserId(user.getUserId());
            if (bookings.isEmpty()) {
                System.out.println("You have no bookings.");
            } else {
                for (Booking booking : bookings) {
                    System.out.println("Booking ID: " + booking.getBookingId());
                    System.out.println("Room Type: " + booking.getRoomType());
                    System.out.println("Check-in: " + booking.getCheckInTime());
                    System.out.println("Check-out: " + booking.getCheckOutTime());
                    System.out.println("Total Price: $" + booking.getTotalPrice());
                    System.out.println("--------------------------------");
                }
            }
        }
    }
    
    
    
    
