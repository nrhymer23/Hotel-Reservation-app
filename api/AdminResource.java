package api;



import model.Customer;
import model.IRoom;
import Service.CustomerService;
import Service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource instance = new AdminResource();
    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    // Private constructor to enforce singleton pattern
    private AdminResource() {}

    // Static method to get the single instance of AdminResource
    public static AdminResource getInstance() {
        return instance;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
