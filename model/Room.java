package model;


public class Room implements IRoom {
    private String roomNumber;
    private double price;
    private RoomType roomType;

    // Constructor
    public Room(String roomNumber, double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    // Implementing methods from IRoom interface
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public double getRoomPrice() {
        return price;
    }

    @Override
    public boolean isFree() {
        // Implement logic to check if the room is free
        return true; // All start Free
    }

    // Overriding toString() method for better description
    @Override
    public String toString() {
        return "Room: " + roomNumber +
                ", Type: " + roomType +
                ", Price: $" + price +
                ", Free: " + (isFree() ? "Yes" : "No");
    }
}
