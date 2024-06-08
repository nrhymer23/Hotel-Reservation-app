import api.AdminResource;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayAdminMenu() {
        boolean exit = false;
        while (!exit) {
            printAdminMenu();
            int selection = Integer.parseInt(scanner.nextLine());
            switch (selection) {
                case 1:
                    seeAllCustomers();
                    break;
                case 2:
                    seeAllRooms();
                    break;
                case 3:
                    seeAllReservations();
                    break;
                case 4:
                    addARoom();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Please select a valid option.");
            }
        }
    }

    private static void printAdminMenu() {
        System.out.println("Admin Menu");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.print("Please select an option: ");
    }

    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            for (IRoom room : rooms) {
                System.out.println(room);
            }
        }
    }

    private static void seeAllReservations() {
        Collection<Reservation> reservations = adminResource.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    private static void addARoom() {
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();

        System.out.print("Enter room price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter room type: 1 for SINGLE, 2 for DOUBLE");
        RoomType roomType = RoomType.values()[Integer.parseInt(scanner.nextLine()) - 1];

        IRoom room = new Room(roomNumber, price, roomType);
        List<IRoom> rooms = new ArrayList<>();
        rooms.add(room);

        adminResource.addRoom(rooms);
        System.out.println("Room added successfully.");
    }
}