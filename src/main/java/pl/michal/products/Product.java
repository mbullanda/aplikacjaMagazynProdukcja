package pl.michal.products;

import pl.michal.warehouse.Warehouse;
import pl.michal.warehouse.WarehouseProducts;

import java.util.Random;
import java.util.Scanner;

public class Product {
    private String name;
    private String color;
    private double weight;
    private double price;
    private int quantityInProduction;
    private int quantityInTransport;
    private int quantityInWarehouse;
    private boolean available = true;
    private ProductCatalog productCatalog;
    private int howManyOne; //means how many intermediate "One" you need to produce this Product
    private int howManyTwo;
    private int howManyThree;
    private int howManyPigment; //same, but its about pigment, there are not different colors, its just for example



    public Product() {

    }
    public Product(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }
    public Product(String name, String color, double weight, double price, int howManyOne, int howManyTwo, int howManyThree, int howManyPigment) {
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.price = price;
        this.howManyOne = howManyOne;
        this.howManyTwo = howManyTwo;
        this.howManyThree = howManyThree;
        this.howManyPigment = howManyPigment;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean getAvailable() {
        return available;
    }

    public int getQuantityInProduction() {
        return quantityInProduction;
    }

    public void setQuantityInProduction(int quantity) {
        this.quantityInProduction += quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHowManyOne() {
        return howManyOne;
    }

    public int getHowManyPigment() {
        return howManyPigment;
    }

    public int getHowManyThree() {
        return howManyThree;
    }

    public int getHowManyTwo() {
        return howManyTwo;
    }

    public void setHowManyOne(int howManyOne) {
        this.howManyOne = howManyOne;
    }

    public void setHowManyPigment(int howManyPigment) {
        this.howManyPigment = howManyPigment;
    }

    public void setHowManyThree(int howManyThree) {
        this.howManyThree = howManyThree;
    }

    public void setHowManyTwo(int howManyTwo) {
        this.howManyTwo = howManyTwo;
    }

    public void setQuantityInTransport(int quantityInTransport) {
        this.quantityInTransport += quantityInTransport;
    }

    public void setQuantityInWarehouse(int quantityInWarehouse) {
        this.quantityInWarehouse += quantityInWarehouse;
    }

    public int getQuantityInTransport() {
        return quantityInTransport;
    }

    public int getQuantityInWarehouse() {
        return quantityInWarehouse;
    }

    public Product addNewProduct() {
        Product product = new Product();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Dodaj produkt.");
        System.out.print("Podaj nazwę produktu:");
        String name = scanner.nextLine();
        product.name = name;
        System.out.print("Podaj kolor:");
        String color = scanner.nextLine();
        product.color = color;
        System.out.print("Podaj wagę (kg):");
        double weight = scanner.nextDouble();
        product.weight = weight;
        product.howManyOne = (int)(weight) + random.nextInt(3);
        product.howManyTwo = random.nextInt((int)weight);
        product.howManyThree = random.nextInt(5);
        product.howManyPigment = 1;
        double price = ((product.getHowManyOne() * 3 + product.getHowManyTwo() * 2 + product.getHowManyThree() + product.getHowManyPigment() * 5 + 15) * 1.5);
        product.price = price;
        System.out.println("Nowy produkt: " + name + " " + color + " (" + weight + "kg) ---> " +
            price + "PLN.");
        System.out.println("Czy wprowadzone dane są poprawne? [yes/no]");
        String yesNo = scanner.next();
        if (yesNo.equalsIgnoreCase("yes")) {
            System.out.println("Produkt dodano pomyślnie!");
//            WarehouseProducts warehouseProducts = new WarehouseProducts(name, color, weight, price);
//            warehouse.addProduct(warehouseProducts);
        } else {
            System.out.println("Produkt nie został dodany!");
            return null;
        }
        return product;
    }


}
