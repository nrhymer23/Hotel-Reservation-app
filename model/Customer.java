package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    // Email validation pattern
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    // Constructor
    public Customer(String firstName, String lastName, String email) {
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    // Overriding toString() method for better description
    @Override
    public String toString() {
        return "Customer: " + firstName + " " + lastName + ", Email: " + email;
    }
}

