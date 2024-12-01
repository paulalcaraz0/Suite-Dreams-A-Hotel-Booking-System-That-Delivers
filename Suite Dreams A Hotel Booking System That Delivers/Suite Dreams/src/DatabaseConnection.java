import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    
   
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_booking?serverTimezone=Asia/Manila";

    private static final String USER = "your_db_user";  
    private static final String PASSWORD = "your_db_password";  
    
  
    public static boolean addUserToDatabase(User user) {
        String queryCheck = "SELECT COUNT(*) FROM users WHERE username = ?";
        String queryInsert = "INSERT INTO users (username, password, first_name, last_name, contact_number, balance) VALUES (?, ?, ?, ?, ?, ?)";
    
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
           
            try (PreparedStatement stmtCheck = connection.prepareStatement(queryCheck)) {
                stmtCheck.setString(1, user.getUsername());
                ResultSet rs = stmtCheck.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                  
                    System.out.println("Username already exists.");
                    return false;
                }
            }
    
       
            try (PreparedStatement stmt = connection.prepareStatement(queryInsert)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getFirstName());
                stmt.setString(4, user.getLastName());
                stmt.setString(5, user.getContactNumber());
                stmt.setDouble(6, user.getBalance());
    
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               
                int userId = rs.getInt("id"); 
                String dbUsername = rs.getString("username");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String contactNumber = rs.getString("contact_number");
                double balance = rs.getDouble("balance");
    
                
                return new User(userId, dbUsername, password, firstName, lastName, contactNumber, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
    
    
    public static boolean addBookingToDatabase(int userId, String roomType, Timestamp checkInTime, Timestamp checkOutTime, int daysInHotel, double totalPrice) {

        System.out.println("Booking for user ID: " + userId);

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO bookings (user_id, room_type, check_in_time, check_out_time, days_in_hotel, total_price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
    
            statement.setInt(1, userId); 
            statement.setString(2, roomType); 
            statement.setTimestamp(3, checkInTime);
            statement.setTimestamp(4, checkOutTime); 
            statement.setInt(5, daysInHotel); 
            statement.setDouble(6, totalPrice); 

            return statement.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

 
    public static List<Booking> getBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM bookings WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
    
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int bookingId = resultSet.getInt("booking_id");
                String roomType = resultSet.getString("room_type");
                Timestamp checkInTime = resultSet.getTimestamp("check_in_time");
                Timestamp checkOutTime = resultSet.getTimestamp("check_out_time");
                int daysInHotel = resultSet.getInt("days_in_hotel");
                double totalPrice = resultSet.getDouble("total_price");
    
              
                Booking booking = new Booking(bookingId, userId, roomType, checkInTime, checkOutTime, daysInHotel, totalPrice);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    
    public static boolean deleteUserAndBooking(int userId) {
        String deleteBookingsQuery = "DELETE FROM bookings WHERE user_id = ?";
        String deleteUserQuery = "DELETE FROM users WHERE id = ?";
        Connection connection = null;  
    
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
          
            connection.setAutoCommit(false);
    
            
            try (PreparedStatement stmt = connection.prepareStatement(deleteBookingsQuery)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }
  
            try (PreparedStatement stmt = connection.prepareStatement(deleteUserQuery)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }
    
           
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();  
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();  
            }
            e.printStackTrace(); 
            return false;
        } finally {
           
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();  
            }
        }
    }
      
          public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }

        public static boolean updateUserBalance(int userId, double newBalance) {
            String query = "UPDATE users SET balance = ? WHERE id = ?";
            
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setDouble(1, newBalance);
                pstmt.setInt(2, userId);
                
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                System.out.println("Error updating balance: " + e.getMessage());
                return false;
            }
        }

        public static boolean updateRoomAvailability(int roomId, boolean availability) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "UPDATE rooms SET available = ? WHERE room_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setBoolean(1, availability);  
                    stmt.setInt(2, roomId);           
                    int rowsUpdated = stmt.executeUpdate();  
                    return rowsUpdated > 0;  
                }
            } catch (SQLException e) {
              
                System.out.println("Error updating room availability for room ID " + roomId + ": " + e.getMessage());
                return false; 
            }
        }
    }
