import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BookingManager {

    public static void addBooking(int userId, String roomType, int daysOfStay, double totalPrice) {

        LocalDateTime currentTime = LocalDateTime.now();
        
       
        ZoneId philippineZone = ZoneId.of("Asia/Manila");
        
       
        ZonedDateTime checkInPhilippineTime = currentTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(philippineZone);
        
    
        ZonedDateTime checkOutPhilippineTime = checkInPhilippineTime.plusDays(daysOfStay);
        
      
        Timestamp checkInTimestamp = Timestamp.valueOf(checkInPhilippineTime.toLocalDateTime());
        Timestamp checkOutTimestamp = Timestamp.valueOf(checkOutPhilippineTime.toLocalDateTime());
        
       
        System.out.println("Check-in Time (Philippine): " + checkInTimestamp);
        System.out.println("Check-out Time (Philippine): " + checkOutTimestamp);
        
       
        try (Connection connection = DatabaseConnection.getConnection()) {
          
            try (PreparedStatement stmt = connection.prepareStatement("SET time_zone = 'Asia/Manila'")) {
                stmt.executeUpdate();
            }

           
            String sql = "INSERT INTO bookings (user_id, room_type, check_in_time, check_out_time, days_in_hotel, total_price) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {

             
                System.out.println("Executing SQL: " + sql);
                System.out.println("With parameters: userId=" + userId + ", roomType=" + roomType 
                        + ", checkInTime=" + checkInTimestamp + ", checkOutTime=" + checkOutTimestamp 
                        + ", daysOfStay=" + daysOfStay + ", totalPrice=" + totalPrice);

               
                stmt.setInt(1, userId);
                stmt.setString(2, roomType);
                stmt.setTimestamp(3, checkInTimestamp); 
                stmt.setTimestamp(4, checkOutTimestamp);  
                stmt.setInt(5, daysOfStay);
                stmt.setDouble(6, totalPrice);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Booking added successfully.");
                } else {
                    System.out.println("Failed to add booking.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error during booking insertion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
