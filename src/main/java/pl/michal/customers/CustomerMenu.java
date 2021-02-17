package pl.michal.customers;

import pl.michal.user.User;
import pl.michal.user.UserCatalog;

import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerMenu {
    private CustomerCatalog customerCatalog;
    private Customer customer;
    public CustomerMenu(CustomerCatalog customerCatalog) {
        this.customerCatalog = customerCatalog;
    }
    public CustomerMenu() {

    }
    public void listCustomerMenu() {
        System.out.println("Witamy w panelu--> Klienci. Wybierz działanie:");
        System.out.println("1.Dodaj klienta.");
        System.out.println("2.Edytuj klienta.");
        System.out.println("3.Lista klientów.");
        System.out.println("4.Cofnij.");
    }
    public void functionsCustomerMenu(CustomerCatalog customerCatalog){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number == 1) {
            Customer customer = new Customer().addNewCustomer();
            if (customer != null) {
                customerCatalog.addCustomer(customer);
            }
        } else if (number == 2) {
            editCustomer(customerCatalog);
        } else if (number == 3) {
                customerCatalog.displayListOfCustomers();
        } else if (number == 4) {
            return;
        }
    }

    public void editCustomer(CustomerCatalog customerCatalog) {
        System.out.println("Wybierz klienta do edycji:");
        customerCatalog.displayListOfCustomers();
        Scanner scanner = new Scanner(System.in);
        int numberOfCustomer = scanner.nextInt();
        if (numberOfCustomer < 1 || customerCatalog.getCustomer(numberOfCustomer) == null) {
            return;
        }
        Customer customerToEdit = customerCatalog.getCustomer(numberOfCustomer);
        for (int i = 0; ; i++){
            System.out.println( "1.--> " + customerToEdit.getName() + " 2.--> " + customerToEdit.getLastName() + " 3.--> " + customerToEdit.getCompany() + " 4.--> " +
                    customerToEdit.getAddress() + " 5.--> " + customerToEdit.getEmail() + " 6.--> " + customerToEdit.getMobileNumber()
                    + ". Podaj numer edytowanego elementu:");
            int numberOfElement = scanner.nextInt();
            switch (numberOfElement){
                case 1:
                    System.out.println("Podaj nowe imię:");
                    String newName = scanner.next();
                    customerToEdit.setName(newName);
                    System.out.println("Zaktualizowano pomyślnie!");
                    break;
                case 2:
                    System.out.println("Podaj nowe nazwisko:");
                    String newLastName = scanner.next();
                    customerToEdit.setLastName(newLastName);
                    System.out.println("Zaktualizowano pomyślnie!");
                    break;
                case 3:
                    System.out.println("Podaj nową nazwę firmy:");
                    scanner.nextLine();
                    String newCompany = scanner.nextLine();
                    System.out.println("Zaktualizowano pomyślnie!");
                    customerToEdit.setCompany(newCompany);
                    break;
                case 4:
                    System.out.println("Podaj nowy adres:");
                    scanner.nextLine();
                    String newAddress = scanner.nextLine();
                    System.out.println("Zaktualizowano pomyślnie!");
                    customerToEdit.setAddress(newAddress);
                    break;
                case 5:
                    System.out.println("Podaj nowy adres e-mail:");
                    String email = scanner.next();
                    if (!Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]{2,64}").matcher(email).matches()) {
                        System.out.println("Podano błędny adres e-mail!");
                        break;
                    }
                    customerToEdit.setEmail(email);
                    System.out.println("Zaktualizowano pomyślnie!");
                    break;
                case 6:
                    System.out.println("Podaj nowy numer telefonu:");
                    String mobileNumber = scanner.next();
                    if (!Pattern.compile("\\d{3}-\\d{3}-\\d{3}").matcher(mobileNumber).matches()) {
                        System.out.println("Podano błędny numer telefonu!");
                        break;
                    }
                    customerToEdit.setMobileNumber(mobileNumber);
                    System.out.println("Zaktualizowano pomyślnie!");
                    break;
                default:
                    break;
            }
            System.out.println("Czy zakończyć edycję?[yes/no]");
            String yesNo = scanner.next();
            if (yesNo.equalsIgnoreCase("no")) {
                continue;
            } else {
                System.out.println("Zakończono edycję danych!");
                return;
            }
        }
    }

}
