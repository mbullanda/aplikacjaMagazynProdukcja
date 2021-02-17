package pl.michal.customers;

import pl.michal.user.User;

public class CustomerCatalog {
    private Customer[] tabOfCustomer = new Customer[100];

    public void addCustomer (Customer customer) { //metoda służąca do dodawania nowych klientów do tabeli klientów
        for (int i = 0; i < tabOfCustomer.length; i++) {
            if (tabOfCustomer[i] == null) {
                tabOfCustomer[i] = customer;
                break;
            }
        }
    }
    public CustomerCatalog(Customer[] initialCustomer) {
        addInitialCustomer(initialCustomer);
    }
    private void addInitialCustomer(Customer[] initialCustomer) {
        for (int i = 0; i < initialCustomer.length; i++) {
            tabOfCustomer[i] = initialCustomer[i];
        }
    }
    public void displayListOfCustomers() {
        for (int i = 0; i < tabOfCustomer.length; i++) {
            if (tabOfCustomer[i] != null) {
                Customer customer = tabOfCustomer[i];
                System.out.println(i + 1 + ". " + customer.getName() + " " + customer.getLastName() + ". Firma: " +
                        customer.getCompany() + ".");
                System.out.println("   " + customer.getAddress() + " *** " + customer.getEmail() + " *** " +
                        customer.getMobileNumber());
            }
        }
    }

    public Customer getCustomer(int numberOfCustomer) {
        return tabOfCustomer[numberOfCustomer - 1];
    }


}
