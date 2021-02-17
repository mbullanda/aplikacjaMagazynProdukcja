package pl.michal;

import pl.michal.customers.Customer;
import pl.michal.products.Product;
import pl.michal.user.User;
import pl.michal.warehouse.Warehouse;
import pl.michal.warehouse.WarehouseProducts;

public class Main {
    public static void main(String[] args) {
        ConsoleMenu consoleMenu = new ConsoleMenu();
        User[] initialUser = buildInitialUser();
        Product[] initialProduct = buildInitialProduct();
        Customer[] initialCustomer = buildInitialCustomer();
       // WarehouseProducts[] initialWarehouseProducts = buildInitialWarehouseProducts();
        consoleMenu.welcomeMainMenu(initialUser, initialProduct, initialCustomer);
    }
    private static User[] buildInitialUser() {
        User user1 = new User("Michał", "Bulanda", 1, "19940626", "michal9109", "aaa");
        User user2 = new User("Jan", "Kowalski", 2, "19901030", "jan121", "takbylo1");
        User user3 = new User("Teodor", "Nowak", 3, "19840216", "theo1", "lalala");
        User user4 = new User("Kasia", "Kowalska", 4, "19541231", "AndSalt", "kasia2");
        User user5 = new User("Michael", "Jordan", 5, "19000101", "bulls", "czikago");
        User user6 = new User("Zbigniew", "Stonoga", 8, "20001219", "ziobro", "przestańMiRodzinęPrześladować");
        User user7 = new User("Marian", "Paździoch", 2, "20010303", "mariano", "3przez4");
        User user8 = new User("Klemens", "Murańka", 7, "19700201", "adammałysz", "wSkokachNieIdzie");
        User user9 = new User("Leo", "Messi", 6, "19940623", "leo90", "CR7best");
        return new User[]{user1,user2,user3,user4,user5,user6,user7,user8,user9};
    }
    private static Product[] buildInitialProduct(){
        Product product = new Product("First", "Black", 2.00, 43.50, 1,3,0, 1);
        Product product1 = new Product("First", "White", 2.00, 43.50,1,3,0, 1);
        Product product2 = new Product("First", "Red", 2.00, 43.50,1,3,0, 1);
        Product product3 = new Product("Second", "Black", 1.00, 37.50,1,1,0, 1);
        Product product4 = new Product("Second", "White", 1.00, 37.50,1,1,0, 1);
        Product product5 = new Product("Second", "Red", 1.00, 37.50,1,1,0, 1);
        Product product6 = new Product("Third", "Black", 4.00, 55.50,4,1,3, 1);
        Product product7 = new Product("Third", "White", 4.00, 55.50,4,1,3, 1);
        Product product8 = new Product("Third", "Red", 4.00, 55.50,4,1,3, 1);
        return new Product[]{product,product1,product2,product3,product4,product5,product6,product7,product8};
    }
    private static Customer[] buildInitialCustomer() {
        Customer customer = new Customer("Janusz", "Kowalski", "Czysty Company", "Kraków, ul.Centralna 70", "janusz@gmail.pl", "111-222-333");
        Customer customer1 = new Customer("Robert", "Lewandowski", "Bayern Monachium", "Monachium 80939, Werner-Heisenberg-Allee 25", "lewy@prawy.com", "999-999-999");
        Customer customer2 = new Customer("Geralt", "z Rivii", "Cech Wilka", "Kaer Morhen", "geraltzrivii@visit.km", "987-654-321");
        return new Customer[]{customer,customer1,customer2};
    }
//    private static WarehouseProducts[] buildInitialWarehouseProducts() {
//        WarehouseProducts warehouseProducts = new WarehouseProducts("First", "Black", 2.00, 43.50);
//        WarehouseProducts warehouseProducts1 = new WarehouseProducts("First", "White", 2.00, 43.50);
//        WarehouseProducts warehouseProducts2 = new WarehouseProducts("First", "Red", 2.00, 43.50);
//        WarehouseProducts warehouseProducts3 = new WarehouseProducts("Second", "Black", 1.00, 37.50);
//        WarehouseProducts warehouseProducts4 = new WarehouseProducts("Second", "White", 1.00, 37.50);
//        WarehouseProducts warehouseProducts5 = new WarehouseProducts("Second", "Red", 1.00, 37.50);
//        WarehouseProducts warehouseProducts6 = new WarehouseProducts("Third", "Black", 4.00, 55.50);
//        WarehouseProducts warehouseProducts7 = new WarehouseProducts("Third", "White", 4.00, 55.50);
//        WarehouseProducts warehouseProducts8 = new WarehouseProducts("Third", "Red", 4.00, 55.50);
//        return new WarehouseProducts[]{warehouseProducts,warehouseProducts1,warehouseProducts2,warehouseProducts3,warehouseProducts4,warehouseProducts5,warehouseProducts6,warehouseProducts7,warehouseProducts8};
//    }
}
