/**
 * Hotel Reservation Application
 *
 * This application allows users to find and reserve hotel rooms.
 * It provides an interface for both regular users and administrators
 * to manage reservations, rooms, and customer information.
 *
 * @see AdminMenu
 *
 * @Author: Noel Rhymer
 * Email: nrhymer23@gmail.com
 * Date: 2024-06-01
 *
 * @Version 3.0
 */



import java.text.ParseException;
import java.text.SimpleDateFormat;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.RoomType;

import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMainMenu();
            try {
                int selection = Integer.parseInt(scanner.nextLine());
                switch (selection) {
                    case 1:
                        findAndReserveRoom();
                        break;
                    case 2:
                        seeMyReservations();
                        break;
                    case 3:
                        createAnAccount();
                        break;
                    case 4:
                        AdminMenu.displayAdminMenu();
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

    private static void printMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin (open the admin menu)");
        System.out.println("5. Exit");
        System.out.print("Please select an option: ");
    }

    private static void findAndReserveRoom() {
        Collection<IRoom> rooms = hotelResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        System.out.println("Available Rooms:");
        for (IRoom room : rooms) {
            System.out.println(room);
        }

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        Customer customer = hotelResource.getCustomer(email);
        if (customer == null) {
            System.out.println("Customer not found. Please create an account first.");
            return;
        }

        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();
        IRoom room = hotelResource.getRoom(roomNumber);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        Date checkInDate = null;
        Date checkOutDate = null;
        while (checkInDate == null) {
            try {
                System.out.print("Enter check-in date (yyyy-MM-dd): ");
                checkInDate = dateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }

        while (checkOutDate == null) {
            try {
                System.out.print("Enter check-out date (yyyy-MM-dd): ");
                checkOutDate = dateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }

        Reservation reservation = hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
        if (reservation != null) {
            System.out.println("Room reserved successfully.");
            System.out.println(reservation);
        } else {
            System.out.println("Room could not be reserved.");
        }
    }

    private static void seeMyReservations() {
        String email;

        while (true) {
            try {
                System.out.print("Enter your email: ");
                email = scanner.nextLine();
                validateEmail(email); // Validate email format
                break; // Exit loop if email is valid
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
            if (reservations == null || reservations.isEmpty()) {
                System.out.println("No reservations found or customer does not exist.");
            } else {
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Customer does not exist.");
        }
    }


    private static void createAnAccount() {
        String email;
        String firstName;
        String lastName;

        while (true) {
            try {
                System.out.print("Enter your email: ");
                email = scanner.nextLine();
                validateEmail(email); //Sends to validate method.
                break; // Exit loop if email is valid
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Enter your first name: ");
        firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        lastName = scanner.nextLine();

        hotelResource.createACustomer(email, firstName, lastName);
        System.out.println("Account created successfully.");
    }

    private static void validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format. Please enter a valid email (example: user@domain.com).");
        }
    }



}


//add test data