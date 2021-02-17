package pl.michal.products;

import pl.michal.user.UserCatalog;
import pl.michal.warehouse.Warehouse;

import java.util.Random;
import java.util.Scanner;

public class ProductMenu {
    private Product product;
    private ProductCatalog productCatalog;

    public ProductMenu(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }
    public ProductMenu(){

    }
    public void listProductMenu(){
        System.out.println("Witamy w panelu--> Produkty. Wybierz działanie:");
        System.out.println("1.Dodaj produkt.");
        System.out.println("2.Usuń produkt.");
        System.out.println("3.Edytuj produkt.");
        System.out.println("4.Lista produktów.");
        System.out.println("5.Cofnij.");
    }
    public void functionsProductMenu(ProductCatalog productCatalog) {
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            if (number == 1) {
                Product product1 = new Product().addNewProduct();
                if (product1 != null) {
                    productCatalog.addProduct(product1);
                }
            } else if (number == 2) {
                deleteProduct(productCatalog);
            } else if (number == 3) {
                editProduct(productCatalog);
            } else if (number == 4) {
                productCatalog.displayListOfProducts();
            } else if (number == 5) {
                return;
            }
    }
    public void editProduct(ProductCatalog productCatalog) {
        System.out.println("Wybierz produkt do edycji:");
        productCatalog.displayListOfProducts();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numberOfProduct = scanner.nextInt();
        if (numberOfProduct < 1 || productCatalog.getProduct(numberOfProduct) == null) {
            return;
        }
        Product productToEdit = productCatalog.getProduct(numberOfProduct);
        for (int i = 0; ; i++){
            System.out.println( "1.--> " + productToEdit.getName() + " 2.--> " + productToEdit.getColor() + " 3.--> " + productToEdit.getWeight() + "kg 4.--> " +
                    productToEdit.getPrice() + "PLN. Podaj numer edytowanego elementu:");
            int numberOfElement = scanner.nextInt();
            switch (numberOfElement) {
                case 1:
                    System.out.print("Podaj nową nazwę:");
                    String newName = scanner.next();
                    productToEdit.setName(newName);
                    System.out.println("Edycja zakończona powodzeniem!");
                    break;
                case 2:
                    System.out.print("Podaj nowy kolor:");
                    String newColor = scanner.next();
                    productToEdit.setColor(newColor);
                    System.out.println("Edycja zakończona powodzeniem!");
                    break;
                case 3:
                    System.out.print("Podaj nową wagę:");
                    double newWeight = scanner.nextDouble();
                    productToEdit.setWeight(newWeight);
                    productToEdit.setHowManyOne((int)(newWeight) + random.nextInt(3));
                    productToEdit.setHowManyTwo(random.nextInt((int)newWeight));
                    productToEdit.setHowManyThree(random.nextInt(5));
                    System.out.println("Edycja zakończona powodzeniem!");
                    break;
                case 4:
                    System.out.print("Podaj nową cenę:");
                    double newPrice = scanner.nextDouble();
                    productToEdit.setPrice(newPrice);
                    System.out.println("Edycja zakończona powodzeniem!");
                    break;
                default:
                    System.out.println("Błąd podczas edycji!");
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
    public void deleteProduct(ProductCatalog productCatalog) {
        System.out.println("Podaj numer produktu do usunięcia:");
        productCatalog.displayListOfProducts();
        Scanner scanner = new Scanner(System.in);
        int numberOfProduct = scanner.nextInt();
        if (productCatalog.getProduct(numberOfProduct).getQuantityInProduction() != 0){
            System.out.println("Posiadasz jeszcze dany produkt na magazynie produkcyjnym! Wydaj produkt na magazyn główny, aby go usunąć!");
            return;
        }
        System.out.println("Czy na pewno usunąć produkt " + productCatalog.getProduct(numberOfProduct).getName() +
                " " + productCatalog.getProduct(numberOfProduct).getColor() + " z listy produktów?[yes/no]");
        String yesNo = scanner.next();
        if (yesNo.equalsIgnoreCase("yes")) {
            System.out.println("Produkt został usunięty!");
            productCatalog.getProduct(numberOfProduct).setAvailable(false);
        } else {
            System.out.println("Produkt nie został usunięty!");
            return;
        }
        productCatalog.deleteProduct(numberOfProduct);


    }
}
