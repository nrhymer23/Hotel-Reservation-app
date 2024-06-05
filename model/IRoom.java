package model;

public interface IRoom {
    String getRoomNumber();
    RoomType getRoomType();
    double getRoomPrice();
    boolean isFree();
}
