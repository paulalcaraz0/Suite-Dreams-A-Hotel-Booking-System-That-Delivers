import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int userId;
    private String roomType;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private int daysInHotel;
    private double totalPrice;
    

   
    public Booking(int bookingId, int userId, String roomType, Timestamp checkInTime, Timestamp checkOutTime, int daysInHotel, double totalPrice) {
        this.bookingId = bookingId; 
        this.userId = userId;
        this.roomType = roomType;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.daysInHotel = daysInHotel;
        this.totalPrice = totalPrice;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public String getRoomType() {
        return roomType;
    }

    public Timestamp getCheckInTime() {
        return checkInTime;
    }

    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }

    public int getDaysInHotel() {
        return daysInHotel;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
