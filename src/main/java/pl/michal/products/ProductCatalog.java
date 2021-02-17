package pl.michal.products;


import pl.michal.user.User;

public class ProductCatalog {
    private Product[] tabOfProducts = new Product[100];


    public void addProduct(Product product) {//dodawanie produktu do tabeli produktów
        for (int i = 0; i < tabOfProducts.length; i++) {
            if (tabOfProducts[i] == null) {
                tabOfProducts[i] = product;
                break;
            }
        }
    }

    public void displayListOfProducts() {
        for (int i = 0; i < tabOfProducts.length; i++) {
            if (tabOfProducts[i] != null && tabOfProducts[i].getAvailable()) {
                Product product = tabOfProducts[i];
                System.out.println(i + 1 + ". " + product.getName() + " " + product.getColor() + " (" +
                        product.getWeight() + "kg) --- " + product.getPrice() + "PLN (" +
                        product.getHowManyOne() + ", " + product.getHowManyTwo() + ", " +
                        product.getHowManyThree() + ", " + product.getHowManyPigment() + ").");
            }
        }
    }
    public void displayQuantityOfProductsInProduction() {
       // int weightOfAllProducts = 0;
        System.out.println("Ilości produktów na magazynie produkcyjnym:");
        for (int i = 0; i < tabOfProducts.length; i++) {
            if (tabOfProducts[i] != null) {
                Product product = tabOfProducts[i];
                System.out.println(i + 1 + ". " + product.getName() + " " + product.getColor()  + ": " + product.getQuantityInProduction() + " sztuk. " +
                         " Łączna waga: " + product.getWeight() * product.getQuantityInProduction() + "kg.");
               // weightOfAllProducts += product.getWeight() * product.getQuantityInProduction();
            }
        }
    }

    public void displayQuantityOfProductsInTransport() {
        System.out.println("Produkty w transporcie na magazyn główny:");
        for (int i = 0; i < tabOfProducts.length; i++) {
            if (tabOfProducts[i] != null && tabOfProducts[i].getQuantityInTransport() - tabOfProducts[i].getQuantityInWarehouse() != 0) {
                Product product = tabOfProducts[i];
                System.out.println(product.getName() + " " + product.getColor()  + ": " + (product.getQuantityInTransport() - product.getQuantityInWarehouse()) + " sztuk. " +
                        " Łączna waga: " + product.getWeight() * product.getQuantityInTransport() + "kg.");
            }
        }
    }

    public void displayQuantityOfProductsInWarehouse() {
        System.out.println("Ilość produktów na magazynie głównym:");
        for (int i = 0; i < tabOfProducts.length; i++) {
            if (tabOfProducts[i] != null) {
                Product product = tabOfProducts[i];
                System.out.println(i + 1 + ". " + product.getName() + " " + product.getColor()  + ": " + product.getQuantityInWarehouse() + " sztuk. " +
                        " Łączna waga: " + product.getWeight() * product.getQuantityInWarehouse() + "kg.");
            }
        }
    }

    public void deleteProduct(int numberOfProduct) {
        tabOfProducts[numberOfProduct - 1] = null;
        for (int i = 0; i < tabOfProducts.length; i++){
            if (tabOfProducts[i] == null) {
                for (int j = i + 1; j < tabOfProducts.length - 1; j++) {
                    if (tabOfProducts[j] != null) {
                        tabOfProducts[i] = tabOfProducts[j];
                        tabOfProducts[j] = null;
                        break;
                    }
                }
            }
        }
    }
    public ProductCatalog(Product[] initialProduct) { //do gotowych produktów
        addInitialProduct(initialProduct);
    }

    private void addInitialProduct(Product[] initialProduct) {
        for (int i = 0; i < initialProduct.length; i++) {
            tabOfProducts[i] = initialProduct[i];
        }
    }
    public Product getProduct(int numberOfProduct) {
        return tabOfProducts[numberOfProduct - 1];
    }

    public Product[] getTabOfProducts() {
        return tabOfProducts;
    }
}
