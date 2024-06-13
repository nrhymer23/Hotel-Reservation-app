package Service;


import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService instance = new ReservationService();

    private Map<String, IRoom> roomMap = new HashMap<>();
    private Map<String, Collection<Reservation>> reservationMap = new HashMap<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        return instance;
    }

    public void addRoom(IRoom room) {
        roomMap.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return roomMap.get(roomId);
    }

    // Method to check if a room is available for the given dates
    public boolean isRoomAvailable(IRoom room, Date checkInDate, Date checkOutDate) {
        Collection<Reservation> reservations = getAllReservations();
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room)) {
                if (checkInDate.before(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckInDate())) {
                    return false; // Means Duplicate detected
                }
            }
        }
        return true;
    }


    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (!isRoomAvailable(room, checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("Room is not available for the given dates.");
        }
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customerReservations = reservationMap.getOrDefault(customer.getEmail(), new ArrayList<>());
        customerReservations.add(reservation);
        reservationMap.put(customer.getEmail(), customerReservations);
        return reservation;
    }

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

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservationMap.getOrDefault(customer.getEmail(), new ArrayList<>());
    }

    public void printAllReservation() {
        for (Collection<Reservation> reservations : reservationMap.values()) {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    public Collection<Reservation> getAllReservations() {
        Collection<Reservation> allReservations = new ArrayList<>();
        for (Collection<Reservation> reservations : reservationMap.values()) {
            allReservations.addAll(reservations);
        }
        return allReservations;
    }

    public Collection<IRoom> getAllRooms() {
        return new ArrayList<>(roomMap.values());
    }
}