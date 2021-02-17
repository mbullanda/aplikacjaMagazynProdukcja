package pl.michal.customers;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer {
    private String name;
    private String lastName;
    private String company;
    private String address;
    private String email;
    private String mobileNumber;

    public Customer () {

    }
    public Customer (String name, String lastName, String company, String address, String email, String mobileNumber) {
        this.name = name;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer addNewCustomer() {
        Customer customer = new Customer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dodaj klienta.");
        System.out.print("Podaj imię: ");
        customer.name = scanner.nextLine();
        System.out.print("Podaj nazwisko: ");
        customer.lastName = scanner.nextLine();
        System.out.print("Podaj firmę: ");
        customer.company = scanner.nextLine();
        System.out.print("Podaj adres: ");
        customer.address = scanner.nextLine();
        System.out.print("Podaj adres e-mail: ");
        String email = scanner.nextLine();
        if (!Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]{2,64}").matcher(email).matches()) {
            System.out.println("Podano błędny adres e-mail!");
            return null;
        }
        customer.email = email;
        System.out.print("Podaj numer telefonu w formacie 123-456-789: ");
        String mobileNumber = scanner.nextLine();
        if (!Pattern.compile("\\d{3}-\\d{3}-\\d{3}").matcher(mobileNumber).matches()) {
            System.out.println("Podano błędny numer telefonu!");
            return null;
        }
        customer.mobileNumber = mobileNumber;
        System.out.println("Klient utworzony pomyślnie!");
        return customer;
    }
}
