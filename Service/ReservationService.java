package Service;


import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    // Static reference for singleton pattern
    private static ReservationService instance = new ReservationService();

    private Map<String, IRoom> roomMap = new HashMap<>();
    private Map<String, Collection<Reservation>> reservationMap = new HashMap<>();

    // Private constructor to enforce singleton pattern
    private ReservationService() {}

    // Static method to get the single instance of ReservationService
    public static ReservationService getInstance() {
        return instance;
    }

    // Method to add a room
    public void addRoom(IRoom room) {
        roomMap.put(room.getRoomNumber(), room);
    }

    // Method to get a room by room number
    public IRoom getARoom(String roomId) {
        return roomMap.get(roomId);
    }

    // Method to reserve a room
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customerReservations = reservationMap.getOrDefault(customer.getEmail(), new ArrayList<>());
        customerReservations.add(reservation);
        reservationMap.put(customer.getEmail(), customerReservations);
        return reservation;
    }

    // Method to find available rooms
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>(roomMap.values());
        for (Collection<Reservation> reservations : reservationMap.values()) {
            for (Reservation reservation : reservations) {
                if (reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate)) {
                    availableRooms.remove(reservation.getRoom());
                }
            }
        }
        return availableRooms;
    }

    // Method to get a customer's reservations
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservationMap.getOrDefault(customer.getEmail(), new ArrayList<>());
    }

    // Method to print all reservations
    public void printAllReservation() {
        for (Collection<Reservation> reservations : reservationMap.values()) {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}
