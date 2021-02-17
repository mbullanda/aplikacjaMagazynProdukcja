package pl.michal;

import pl.michal.customers.Customer;
import pl.michal.customers.CustomerCatalog;
import pl.michal.customers.CustomerMenu;
import pl.michal.inTransport.InTransportTab;
import pl.michal.production.*;
import pl.michal.products.Product;
import pl.michal.products.ProductCatalog;
import pl.michal.products.ProductMenu;
import pl.michal.user.User;
import pl.michal.user.UserCatalog;
import pl.michal.user.UserMenu;
import pl.michal.warehouse.Warehouse;
import pl.michal.warehouse.WarehouseMenu;
import pl.michal.warehouse.WarehouseProducts;

import java.util.Scanner;

public class ConsoleMenu {


    public ConsoleMenu(){

    }
    public void welcomeMainMenu(User[] initialUser, Product[] initialProduct, Customer[] initialCustomer) {
        System.out.println("Witamy w naszym programie!");
        UserCatalog userCatalog = new UserCatalog(initialUser);
        UserMenu userMenu = new UserMenu(userCatalog);
        //User user = new User(userCatalog);
        ProductCatalog productCatalog = new ProductCatalog(initialProduct);
        ProductMenu productMenu = new ProductMenu(productCatalog);
        //Product product = new Product(productCatalog);
        CustomerCatalog customerCatalog = new CustomerCatalog(initialCustomer);
        CustomerMenu customerMenu = new CustomerMenu(customerCatalog);
//        Warehouse warehouse = new Warehouse(initialWarehouseProducts);
        WarehouseMenu warehouseMenu = new WarehouseMenu();
        int startupData = 1000;
        Production production = new Production(startupData,startupData,startupData,startupData); //ilości półproduktów i barwnika na start
        production.setWeightOfAllIntermediates(startupData * 4); //waga powyższych półproduktów i barwnika (jest równa ilości)
        production.setProductionCapacity(startupData * 20); //pojemność magazynu produkcyjnego
        ProductionMenu productionMenu = new ProductionMenu(productCatalog);
        ProductionHistory productionHistory = new ProductionHistory();
        ReleaseHistory releaseHistory = new ReleaseHistory();
        for (int i = 0; ; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Zaloguj się, aby przejść dalej! Wybierz użytkownika:");
            userCatalog.displayListOfUsers();
            System.out.println("Aby wyjść z programu wpisz -1");
            int numberOfUser = logIn(userCatalog);

            if (numberOfUser == -1) {
                break;
            } else if (numberOfUser == 0) {
                continue;
            }
            User selectedUser = userCatalog.getUser(numberOfUser);
            int accessLevel = selectedUser.getAccessLevel();
            userCatalog.setNumberOfActualUser(numberOfUser);

            for (int j = 0; ; j++) {
                if (!userCatalog.getUser(numberOfUser).getPermissionToAccess() && selectedUser.getAccessLevel() != 1) {
                    System.out.println("Wpisałeś niepoprawne hasło 3 razy! Użytkownik zostaje zablokowany!");
                    selectedUser.setBlocked(true);
                    break;
                } else if (!userCatalog.getUser(numberOfUser).getPermissionToAccess() && selectedUser.getAccessLevel() == 1){
                    System.out.println("Wpisałeś niepoprawne hasło 3 razy! Aktualna sesja zostaje przerwana! " +
                            "Skontaktuj się z developerem w celu rozwiązania problemu!");
                    return;
                }
                listOfFunctions();
                int number = scanner.nextInt();
                if (number == 1) {
                    //production
                    if (accessLevel == 1 || accessLevel == 5 || accessLevel == 8) {
                        productionMenu.listProductionMenu();
                        productionMenu.functionsProductionMenu(productCatalog,production, productionHistory, userCatalog, numberOfUser, releaseHistory);
                    } else {
                        noAccess();
                    }
                } else if (number == 2) {
                    //warehouse
                    if (accessLevel == 1 || accessLevel == 4 || accessLevel == 7) {
                        warehouseMenu.listOfWarehouseMenu();
                        warehouseMenu.functionsOfWarehouseMenu(productCatalog, releaseHistory);
                    } else {
                        noAccess();
                    }
                } else if (number == 3) {
                    //shop
                    if (accessLevel == 1 || accessLevel == 3 || accessLevel == 6) {
                        System.out.println("Witamy w panelu--> Sklep. Wybierz działanie:");
                    } else {
                        noAccess();
                    }
                } else if (number == 4) {
                    if (accessLevel == 1 || accessLevel == 2) {
                        userMenu.listUserMenu();
                        userMenu.functionsUserMenu(userCatalog);
                    } else {
                        noAccess();
                    }
                } else if (number == 5) {
                    if (accessLevel == 1 || accessLevel == 2 || accessLevel == 3 || accessLevel == 4 || accessLevel == 5) {
                        productMenu.listProductMenu();
                        productMenu.functionsProductMenu(productCatalog);
                    } else {
                        noAccess();
                    }
                } else if (number == 6) {
                    if (accessLevel == 1 || accessLevel == 2 || accessLevel == 3 || accessLevel == 6) {
                        customerMenu.listCustomerMenu();
                        customerMenu.functionsCustomerMenu(customerCatalog);
                    } else {
                        noAccess();
                    }
                } else if (number == 7) {
                    userMenu.listUserEditMenu();
                    userMenu.functionsUserEditMenu(userCatalog);
                } else if (number == 8) {
                    break;
                } else if (number == -1) {
                    return;
                }
            }
        }
        System.out.println("Dziękujemy za korzystanie z naszego programu!");
    }
    public void listOfFunctions() {
        System.out.println("Wybierz dział:");
        System.out.println("1.Produkcja.");
        System.out.println("2.Magazyn.");
        System.out.println("3.Sklep.");
        System.out.println("4.Użytkownicy.");
        System.out.println("5.Produkty.");
        System.out.println("6.Klienci.");
        System.out.println("7.Zmień login/hasło.");
        System.out.println("8.Wyloguj się.");
        System.out.println("Aby zakończyć działanie programu wpisz -1");
    }

    public void noAccess() {
        System.out.println("Nie masz dostępu do tej funkcji!");
    }

    public int logIn (UserCatalog userCatalog) {
        Scanner scanner = new Scanner(System.in);
        int numberOfUser = scanner.nextInt();
        if (numberOfUser == -1) {
            return -1;
            } else if (numberOfUser < 1 || userCatalog.getUser(numberOfUser) == null){
                return 0;
            }
        User selectedUser = userCatalog.getUser(numberOfUser);
        for (int j = 0; j < 3; j++) {
            System.out.println(selectedUser.getName() + " " + selectedUser.getLastName() + "---> " +
                    selectedUser.getLogin());
            System.out.print("Podaj hasło: ");
            String writePassword = scanner.next();
            String userPassword = selectedUser.getPassword();
            if (userPassword.equals(writePassword)) {
                System.out.println("Zalogowano pomyślnie!");
                userCatalog.getUser(numberOfUser).setPermissionToAccess(true);
                break;
            } else {
                System.out.println("Błędne hasło!");
            }
        }
        return numberOfUser;
    }
}
