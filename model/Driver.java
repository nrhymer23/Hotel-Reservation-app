package model;


public class Driver {
    public static void main(String[] args) {
        // Create a Customer object
        Customer customer = new Customer("first", "second", "nr@domain.com");
        // Customer customer = new Customer("first", "second", "email");
        // Print the Customer object to test the toString() method
       System.out.println(customer);
    }
}
