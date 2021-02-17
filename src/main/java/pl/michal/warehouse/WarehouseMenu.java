package pl.michal.warehouse;


import pl.michal.production.Release;
import pl.michal.production.ReleaseHistory;
import pl.michal.products.Product;
import pl.michal.products.ProductCatalog;

import java.util.Scanner;

public class WarehouseMenu {
    private Warehouse warehouse;

    public WarehouseMenu (Warehouse warehouse) {
        this.warehouse = warehouse;
    }
    public WarehouseMenu() {

    }

    public void listOfWarehouseMenu () {
        System.out.println("Witamy w panelu--> Magazyn. Wybierz działanie:");
        System.out.println("1.Przyjmij towar.");
        System.out.println("2.Wyświetl historię przyjęć.");
        System.out.println("3.Wyświetl stan magazynowy.");
        System.out.println("4.Złóż zamówienie dla klienta.");
        System.out.println("5.Wydaj towar dla klienta.");
        System.out.println("6.Wyświetl historię wydań.");
    }

    public void functionsOfWarehouseMenu(ProductCatalog productCatalog, ReleaseHistory releaseHistory) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number == 1) {
            collectionOfProducts(releaseHistory, productCatalog);
        } else if (number == 2) {

        } else if (number == 3) {
            productCatalog.displayQuantityOfProductsInWarehouse();
        } else if (number == 4) {

        } else if (number == 5) {

        } else if (number == 6) {

        } else if (number == 7) {
            return;
        }
    }

    public void collectionOfProducts(ReleaseHistory releaseHistory, ProductCatalog productCatalog) {
        if (releaseHistory.getAvailable()) {
            System.out.println("Brak transportu z produkcji!");
            return;
        }
        productCatalog.displayQuantityOfProductsInTransport();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Czy przyjąć powyższe zamówienie do stanu magazynowego? [yes/no]");
        String yesNo = scanner.next();
        if (yesNo.equalsIgnoreCase("yes")){
            for (int i = 0; i < releaseHistory.getTabOfReleaseHistory().length; i++) {
                Release release = releaseHistory.getTabOfReleaseHistory()[i];
                if (release != null) {
                    String name = release.getName();
                    String color = release.getColor();
                    for (int j = 0; j < productCatalog.getTabOfProducts().length; j++) {
                        Product product = productCatalog.getProduct(j + 1);
                        if (product.getName().equals(name) &&
                                product.getColor().equals(color)) {
                            int howManyInWarehouse = product.getQuantityInWarehouse();
                            product.setQuantityInWarehouse(releaseHistory.getTabOfReleaseHistory()[i].getHowManyProducts() - howManyInWarehouse);
                            release.setCollected(true);
                            break;
                        }

                    }

//                    product.setQuantityInWarehouse(product.getQuantityInTransport());
//                    product.setQuantityInTransport(-product.getQuantityInWarehouse());
                }
            }
            releaseHistory.setAvailable(true);
            System.out.println("Zamówienie przyjęte na magazyn!");
//            inTransportTab.clearTab();
//            inTransportTab.setAvailable(true);
//            inTransportTab.setWeightOfAllProducts(-inTransportTab.getWeightOfAllProducts());
        } else {
            System.out.println("Zamówienie nie zostało przyjęte! W kolejce oczekujących!");
            return;
        }
    }


}
