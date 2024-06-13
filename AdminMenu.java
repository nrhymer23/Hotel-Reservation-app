/**
 * Hotel Reservation Application
 *
 * This application allows users to find and reserve hotel rooms.
 * It provides an interface for both regular users and administrators
 * to manage reservations, rooms, and customer information.
 *
 * @see MainMenu
 *
 * @Author: Noel Rhymer
 * Email: nrhymer23@gmail.com
 * Date: 2024-06-01
 *
 * @Version 3.0
 *
 */

import api.AdminResource;
import api.HotelResource;
import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


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
                        addTestData();
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Please select a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }
    }

    private static void printAdminMenu() {
        System.out.println("Admin Menu");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Add Test Data");
        System.out.println("6. Back to Main Menu");
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

        // Looping to ensure correct input is provided and unique
        while (true) {
            System.out.print("Enter room number: ");
            roomNumber = scanner.nextLine();
                if (isRoomNumberUnique(roomNumber)) {
                    break;
                }
        }

        //Looping to ensure valid price is inputed
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
        //Checking to make sure room # is Unique and Correct
        private static boolean isRoomNumberUnique(String roomNumber) {
            // Check if the room number is a valid number
            try {
                Integer.parseInt(roomNumber);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Room number must be a valid number.");
                return false;
            }

            // Check if the room number is unique
            Collection<IRoom> rooms = adminResource.getAllRooms();
            for (IRoom room : rooms) {
                if (room.getRoomNumber().equals(roomNumber)) {
                    System.out.println("Room number already exists. Please enter a unique room number.");
                    return false;
                }
            }
            return true;
        }


    private static void addTestData() {

        // Test Customers
        HotelResource.createACustomer("Susanj@gmail.com","susan","Johnson");
        HotelResource.createACustomer("Johnt@yahoo.com","John","Turner");
        HotelResource.createACustomer("AnthonyS@gmail.com","Anthony","Smith");
        HotelResource.createACustomer("martinr@gmail.com","Martin","Rhy");
        HotelResource.createACustomer("Grantb@gmail.com","Grant ","Black");
        HotelResource.createACustomer("Torreya@gmail.com","Torrey","Armstrong");
        HotelResource.createACustomer("JessicaF@gmail.com","Jessica","Fair");

        // Test Rooms
        IRoom room1 = new Room("101", 100, RoomType.SINGLE);
        IRoom room2 = new Room("102", 200, RoomType.DOUBLE);
        IRoom room5 = new Room("201",150,RoomType.SINGLE);
        IRoom room6 = new Room("202",200,RoomType.DOUBLE);
        IRoom room7 = new Room("301",150,RoomType.SINGLE);
        IRoom room8 = new Room("302",200,RoomType.DOUBLE);
        IRoom room9 = new Room("401",200,RoomType.SINGLE);
        IRoom room10 = new Room("402",300,RoomType.DOUBLE);
        IRoom room3 = new FreeRoom("103", RoomType.SINGLE);
        IRoom room4 = new FreeRoom("203",RoomType.DOUBLE);

        List<IRoom> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);
        rooms.add(room7);
        rooms.add(room8);
        rooms.add(room9);
        rooms.add(room10);
        adminResource.addRoom(rooms);


        // Test Reservations
        try {
            HotelResource.bookARoom("Susanj@gmail.com", room1, dateFormat.parse("2024-07-01"), dateFormat.parse("2024-07-05"));
            HotelResource.bookARoom("Grantb@gmail.com", room5, dateFormat.parse("2024-07-03"), dateFormat.parse("2024-07-06"));
            HotelResource.bookARoom("martinr@gmail.com", room9, dateFormat.parse("2024-07-04"), dateFormat.parse("2024-07-07"));
        } catch (ParseException e) {
            System.out.println( e.getMessage());
        }

        System.out.println("Test data added successfully.");

    }

    }