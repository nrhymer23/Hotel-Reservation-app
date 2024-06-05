package model;

public class FreeRoom extends Room {

    // Constructor
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    // Override toString() method for better description
    @Override
    public String toString() {
        return "Free Room: " + getRoomNumber() +
                ", Type: " + getRoomType() +
                ", Price: $" + getRoomPrice() +
                ", Free: " + (isFree() ? "Yes" : "No");
    }
}