public class User {
    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private double balance;
    private Room selectedRoom;
    private int daysOfStay;

    // Constructor to initialize the user with details
    public User(String username, String password, String firstName, String lastName, String contactNumber, double balance) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.balance = balance;
    }

    public User(int userId, String username, String password, String firstName, String lastName, String contactNumber, double balance) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.balance = balance;
    }


    public int getUserId() {
        return userId;
    }

        public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public int getDaysOfStay() {
        return daysOfStay;
    }

    public void setDaysOfStay(int daysOfStay) {
        this.daysOfStay = daysOfStay;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;  
    }

    public double getBalance() {
        return balance;
    }

    
    public static double generateRandomBalance() {
        return 100 + (Math.random() * 900);  
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
}
