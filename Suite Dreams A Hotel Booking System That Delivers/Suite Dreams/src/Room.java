public class Room {
    private String roomType;
    private double price;
    private boolean available;
    private String description;

    // Constructor
    public Room(int roomId, String roomType, double price, boolean available, String description) {
        this.roomType = roomType;
        this.price = price;
        this.available = available;
        this.description = description;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Price: %.2f, Available: %s, Description: %s",
                roomType, price, available ? "Yes" : "No", description);
    }
}
