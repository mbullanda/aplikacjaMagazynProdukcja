package pl.michal.production;

import pl.michal.products.Product;
import pl.michal.products.ProductCatalog;
import pl.michal.user.UserCatalog;

import java.util.Scanner;

public class ProductionMenu {
    private ProductCatalog productCatalog;

    public ProductionMenu(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public void listProductionMenu() {
        System.out.println("Witamy w panelu--> Produkcja. Wybierz działanie:");
        System.out.println("1.Wyprodukuj towar.");
        System.out.println("2.Wyświetl historię produkcji.");
        System.out.println("3.Wyświetl stany na magazynie produkcyjnym.");
        System.out.println("4.Wydaj towar na magazyn.");
        System.out.println("5.Wyświetl historię wydań.");
        System.out.println("6.Sprawdź ilość półproduktu.");
        System.out.println("7.Zamów półprodukt.");
        System.out.println("8.Pojemność magazynu produkcyjnego.");
        System.out.println("9.Cofnij.");
    }
    public void functionsProductionMenu(ProductCatalog productCatalog, Production production, ProductionHistory productionHistory, UserCatalog userCatalog, int numberOfUser, ReleaseHistory releaseHistory) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number == 1) {
            produce(productCatalog, production, productionHistory, userCatalog, numberOfUser);
        } else if (number == 2) {
            productionHistory.displayHistoryOfProductions();
        } else if (number == 3) {
            productCatalog.displayQuantityOfProductsInProduction();
        } else if (number == 4) {
            releaseProductsToWarehouse(production, userCatalog, numberOfUser, releaseHistory);
        } else if (number == 5) {
            releaseHistory.displayHistoryOfRelease();
        } else if (number == 6) {
            checkHowManyIntermediate(production);
        } else if (number == 7) {
            if (userCatalog.getUser(numberOfUser).getAccessLevel() == 8) {
                System.out.println("Nie masz dostępu do tej funkcji! W przypadku braku półproduktu zgłoś się do swojego przełożonego!");
                return;
            }
            orderIntermediate(production);
        } else if (number == 8) {
            if (userCatalog.getUser(numberOfUser).getAccessLevel() == 8) {
                System.out.println("Nie masz dostępu do tej funkcji!");
                return;
            }
            checkCapacity(production, userCatalog, numberOfUser);
        } else if (number == 9) {
            return;
        }
    }

    public void releaseProductsToWarehouse(Production production, UserCatalog userCatalog, int numberOfUser, ReleaseHistory releaseHistory) {
        if (!releaseHistory.getAvailable()) {
            System.out.println("Brak środka transportu, poczekaj aż kierowca wróci z magazynu!");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        boolean isReady = false;
        for (int i = 0; ; i++) {
            if (production.getWeightOfAllProducts() == 0) {
                System.out.println("Brak produktów do wydania, magazyn produkcyjny pusty!");
                return;
            }
            System.out.println("Wybierz produkt do wydania na magazyn:");
            productCatalog.displayQuantityOfProductsInProduction();
            int numberOfProduct = scanner.nextInt();
            if (numberOfProduct < 1 || productCatalog.getProduct(numberOfProduct) == null ||
                    productCatalog.getProduct(numberOfProduct).getQuantityInProduction() == 0){
                continue;
            }
            Product product = productCatalog.getProduct(numberOfProduct);
            System.out.println("Podaj ilość do wydania:");
            int quantityOfProductToRelease = scanner.nextInt();
            if (quantityOfProductToRelease < 0 || quantityOfProductToRelease > product.getQuantityInProduction()) {
                continue;
            }
            Release release = new Release();
            release.setName(product.getName());
            release.setColor(product.getColor());
            release.setHowManyProducts(quantityOfProductToRelease);
            release.setWeight(product.getWeight() * release.getHowManyProducts());
            release.setLogin(userCatalog.getUser(numberOfUser).getLogin());
            releaseHistory.addNewReleaseToTab(release);
            product.setQuantityInTransport(quantityOfProductToRelease);
            product.setQuantityInProduction(-quantityOfProductToRelease);
            production.setWeightOfAllProducts(-(product.getWeight() * quantityOfProductToRelease));
            releaseHistory.setAvailable(false);
            System.out.println("Zakończyć załadunek? [yes/no]");
            String yesNo = scanner.next();
            if (!yesNo.equalsIgnoreCase("yes") && production.getWeightOfAllProducts() == 0) {
                System.out.println("Brak produktów do wydania, magazyn produkcyjny pusty!");
                isReady = true;
                break;
            } else if (yesNo.equalsIgnoreCase("yes")) {
                isReady = true;
                break;
            }
        }
        if (isReady) {
            productCatalog.displayQuantityOfProductsInTransport();
            System.out.println("Zamówienie wysłane na magazyn główny!");
            return;
        }
    }

    public void checkCapacity(Production production, UserCatalog userCatalog, int numberOfUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz działanie:");
        System.out.println("1.Wyświetl pojemność magazynu produkcyjnego i aktualne zużycie.");
        System.out.println("2.Powiększ magazyn produkcyjny.");
        System.out.println("3.Cofnij.");
        int number = scanner.nextInt();
        if (number ==1) {
            double weightIntermediates = production.getWeightOfAllIntermediates();
            double weightProducts = production.getWeightOfAllProducts();
            double sum = weightIntermediates + weightProducts;
            System.out.println("Magazyn produkcyjny może pomieścić: " + production.getProductionCapacity() + "kg.");
            System.out.println("Aktualnie w użytku powierzchnia dla: " + weightProducts + "kg produktu i " + weightIntermediates + "kg półproduktu.");
            System.out.println("W sumie w użytku powierzchnia dla: " + sum + "kg produktu i półproduktu.");
            System.out.println("Pozostało wolnej powierzchni dla: " + (production.getProductionCapacity() - sum)
                    + "kg produktu i półproduktu.");
        } else if (number == 2) {
            if (userCatalog.getUser(numberOfUser).getAccessLevel() == 5) {
                System.out.println("Nie masz dostępu do tej funkcji. W przypadku potrzeby rozbudowy magazynu skontaktuj się z prezesem!");
                return;
            } else {
                System.out.println("Rozbudowa magazynu. W celu weryfikacji:");
                System.out.print("Podaj login: ");
                String login = scanner.next();
                if (!userCatalog.getUser(numberOfUser).getLogin().equals(login)){
                    System.out.println("Błędny login!");
                    return;
                }
                System.out.print("Podaj hasło: ");
                String password = scanner.next();
                if (!userCatalog.getUser(numberOfUser).getPassword().equals(password)){
                    System.out.println("Błędne hasło!");
                    return;
                }
                System.out.println("Weryfikacja przebiegła prawidłowo!");
            }
            System.out.println("Podaj ile kg produktu i półproduktu więcej pomieści magazyn produkcyjny w wyniku rozbudowy?");
            int expansion = scanner.nextInt();
            if (expansion < 1) {
                return;
            }
            production.setProductionCapacity(expansion);
            System.out.println("Pojemność magazynu produkcyjnego została zaktualizowana!");
        } else if (number == 3) {
            return;
        }
    }

    public void orderIntermediate(Production production){
        System.out.println("Który półprodukt chcesz zamówić?");
        System.out.println("1.One. * 2.Two. * 3.Three * 4.Pigment.");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number){
            case 1:
                System.out.println("Ile sztuk półproduktu One zamówić?");
                int howManyOne = scanner.nextInt();
                if (howManyOne < 1 || (production.getWeightOfAllIntermediates()) + production.getWeightOfAllProducts() +
                        howManyOne > production.getProductionCapacity()){
                    System.out.println("Nie można zrealizować zamówienia. Powód: ujemna wartość lub przepełnienie magazynu!");
                    return;
                }
                production.setIntermediateOneQuantity(howManyOne);
                production.setWeightOfAllIntermediates(howManyOne);
                break;
            case 2:
                System.out.println("Ile sztuk półproduktu Two zamówić?");
                int howManyTwo = scanner.nextInt();
                if (howManyTwo < 1 || (production.getWeightOfAllIntermediates()) + production.getWeightOfAllProducts() +
                        howManyTwo > production.getProductionCapacity()){
                    System.out.println("Nie można zrealizować zamówienia. Powód: ujemna wartość lub przepełnienie magazynu!");
                    return;
                }
                production.setIntermediateTwoQuantity(howManyTwo);
                production.setWeightOfAllIntermediates(howManyTwo);
                break;
            case 3:
                System.out.println("Ile sztuk półproduktu Three zamówić?");
                int howManyThree = scanner.nextInt();
                if (howManyThree < 1 || (production.getWeightOfAllIntermediates()) + production.getWeightOfAllProducts() +
                        howManyThree > production.getProductionCapacity()){
                    System.out.println("Nie można zrealizować zamówienia. Powód: ujemna wartość lub przepełnienie magazynu!");
                    return;
                }
                production.setIntermediateThreeQuantity(howManyThree);
                production.setWeightOfAllIntermediates(howManyThree);
                break;
            case 4:
                System.out.println("Ile sztuk barwnika Pigment zamówić?");
                int howManyPigment = scanner.nextInt();
                if (howManyPigment < 1 || (production.getWeightOfAllIntermediates()) + production.getWeightOfAllProducts() +
                        howManyPigment > production.getProductionCapacity()){
                    System.out.println("Nie można zrealizować zamówienia. Powód: ujemna wartość lub przepełnienie magazynu!");
                    return;
                }
                production.setPigmentQuantity(howManyPigment);
                production.setWeightOfAllIntermediates(howManyPigment);
                break;
            default:
                return;
        }
        System.out.println("Zamówienie zostało zrealizowane!");
    }

    public void checkHowManyIntermediate(Production production) {
        System.out.println("Stany półproduktów na magazynie produkcyjnym:");
        System.out.println("One: " + production.getIntermediateOneQuantity() + " sztuk.");
        System.out.println("Two: " + production.getIntermediateTwoQuantity() + " sztuk.");
        System.out.println("Three: " + production.getIntermediateThreeQuantity() + " sztuk.");
        System.out.println("Pigment: " + production.getPigmentQuantity() + " sztuk.");
    }
    public void produce(ProductCatalog productCatalog, Production production, ProductionHistory productionHistory, UserCatalog userCatalog, int numberOfUser){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz towar, który zostanie wyprodukowany: ");
        productCatalog.displayListOfProducts();
        int numberOfProduct = scanner.nextInt();
        if (numberOfProduct < 1 || productCatalog.getProduct(numberOfProduct) == null){
            return;
        }
        Product producedProduct = productCatalog.getProduct(numberOfProduct);

        int howManyOne = producedProduct.getHowManyOne();
        int howManyTwo = producedProduct.getHowManyTwo();
        int howManyThree = producedProduct.getHowManyThree();
        int howManyPigment = producedProduct.getHowManyPigment();
        System.out.println("Podaj ilość: ");
        int howManyProducts = scanner.nextInt();
        if (howManyProducts < 1){
            System.out.println("Nie możesz wyprodukować ilości mniejszej niż 1!");
            return;
        }
        boolean possibleOne = howManyOne * howManyProducts < production.getIntermediateOneQuantity();
        boolean possibleTwo = howManyTwo * howManyProducts < production.getIntermediateTwoQuantity();
        boolean possibleThree = howManyThree * howManyProducts < production.getIntermediateThreeQuantity();
        boolean possiblePigment = howManyPigment * howManyProducts < production.getPigmentQuantity();

        if (!possibleOne || !possibleTwo || !possibleThree || !possiblePigment) {
            if (!possibleOne){
                System.out.println("Brak półproduktu One.");
            }
            if (!possibleTwo){
                System.out.println("Brak półproduktu Two.");
            }
            if (!possibleThree){
                System.out.println("Brak półproduktu Three.");
            }
            if (!possiblePigment){
                System.out.println("Brak barwnika Pigment.");
            }
            return;
        }
        System.out.println("Produkt " + producedProduct.getName() + " " + producedProduct.getColor() + " został wyprodukowany pomyślnie!");
        producedProduct.setQuantityInProduction(howManyProducts);
        production.setWeightOfAllProducts(howManyProducts * producedProduct.getWeight());
        production.setIntermediateOneQuantity(-(howManyOne * howManyProducts));
        production.setIntermediateTwoQuantity(-(howManyTwo * howManyProducts));
        production.setIntermediateThreeQuantity(-(howManyThree * howManyProducts));
        production.setPigmentQuantity(-(howManyPigment * howManyProducts));
        production.setWeightOfAllIntermediates(-(howManyOne + howManyTwo + howManyThree + howManyPigment) * howManyProducts);
        ProductionHistory productionHistory1 = productionHistory.addNewProduction(producedProduct.getName(), producedProduct.getColor(), producedProduct.getWeight(), howManyProducts, userCatalog.getUser(numberOfUser).getLogin());
        productionHistory.addNewProductionToTab(productionHistory1);
        userCatalog.getUser(numberOfUser).setLoginUnchangeable(true);
        System.out.println("Aktualny stan na magazynie produkcyjnym: " + producedProduct.getQuantityInProduction() + " sztuk.");
    }



}
