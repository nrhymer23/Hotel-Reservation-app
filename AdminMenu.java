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
            try {
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
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
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
        String roomNumber;
        double price = 0.0;
        RoomType roomType = null;

        // Looping to ensure correct input is provided
        while (true) {
            System.out.print("Enter room number: ");
            try {
                roomNumber = scanner.nextLine();
                Integer.parseInt(roomNumber); // if number loop breaks
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the room number.");
            }
        }


        while (true) {
            System.out.print("Enter room price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the price.");
            }
        }
        while (true) {
            System.out.println("Enter room type: 1 for SINGLE, 2 for DOUBLE");
            try {
                int roomTypeInput = Integer.parseInt(scanner.nextLine());
                if (roomTypeInput == 1 || roomTypeInput == 2) {
                    roomType = RoomType.values()[roomTypeInput - 1];
                    break; // Exit loop if parsing is successful
                } else {
                    System.out.println("Invalid room type. Please enter 1 for SINGLE or 2 for DOUBLE.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 for SINGLE or 2 for DOUBLE.");
            }
        }

            IRoom room = new Room(roomNumber, price, roomType);
            List<IRoom> rooms = new ArrayList<>();
            rooms.add(room);

            adminResource.addRoom(rooms);
            System.out.println("Room added successfully.");
        }
    }