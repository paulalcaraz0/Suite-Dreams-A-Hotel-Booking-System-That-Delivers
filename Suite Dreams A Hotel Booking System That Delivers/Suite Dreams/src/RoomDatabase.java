import java.util.ArrayList;
import java.util.List;

public class RoomDatabase {
   
    public static List<Room> initializeRooms() {
        List<Room> rooms = new ArrayList<>();

       
        for (int i = 1; i <= 1; i++) {
            
            rooms.add(new Room(i, "Single Room", 100, true, "A simple room with a single bed, ideal for solo travelers."));
           
            rooms.add(new Room(i, "Double Room", 150, true, "A spacious room with a double bed, perfect for couples."));

            rooms.add(new Room(i, "Deluxe Suite", 200, true, "A luxurious suite with premium amenities, perfect for a high-end experience."));
        }

        return rooms;
    }

   
    public static void displayRooms(List<Room> rooms) {
        System.out.println("TYPE\t\tPRICE\tAVAILABLE\tDESCRIPTION");
        System.out.println("---------------------------------------------");

        for (Room room : rooms) {
            System.out.printf("%s\t%.2f\t%s\t%s\n", 
                room.getRoomType(), 
                room.getPrice(), 
                room.isAvailable() ? "Yes" : "No", 
                room.getDescription());
        }
    }
}
