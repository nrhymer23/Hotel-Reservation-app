package Service;


import model.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    // Static reference
    private static CustomerService instance = new CustomerService();

    private Map<String, Customer> customerMap = new HashMap<>();

    // Private constructor to enforce singleton pattern
    private CustomerService() {}

    // Static method to get the single instance of CustomerService
    public static CustomerService getInstance() {
        return instance;
    }

    // Method to add a customer
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customerMap.put(email, customer);
    }

    // Method to get a customer by email
    public Customer getCustomer(String customerEmail) {
        return customerMap.get(customerEmail);
    }

    // Method to get all customers
    public Collection<Customer> getAllCustomers() {
        return customerMap.values();
    }
}
