package pl.michal.production;


public class ProductionHistory {
    private String name;
    private String color;
    private double weight;
    private int howManyProducts;
    private String login;
    private ProductionHistory[] tabOfAllProductions = new ProductionHistory[200];


    public ProductionHistory addNewProduction(String name, String color, double weight, int howManyProducts, String login){
        ProductionHistory productionHistory = new ProductionHistory();
        productionHistory.name = name;
        productionHistory.color = color;
        productionHistory.weight = weight;
        productionHistory.howManyProducts = howManyProducts;
        productionHistory.login = login;
        return productionHistory;
    }

    public void addNewProductionToTab (ProductionHistory productionHistory) {
        for (int i = 0; i < tabOfAllProductions.length; i++) {
            if (tabOfAllProductions[i] == null) {
                tabOfAllProductions[i] = productionHistory;
                break;
            }
        }
    }
    public void displayHistoryOfProductions(){
       // System.out.println(Arrays.toString(tabOfAllProductions));
        for (int i = 0; i < tabOfAllProductions.length; i++ ){
            if (tabOfAllProductions[i] != null) {
                ProductionHistory productionHistory = tabOfAllProductions[i];
                System.out.println(i + 1 + ". " + productionHistory.getName() + " " + productionHistory.getColor() + ". " +
                      "Ilość: "  + productionHistory.getHowManyProducts() + ". Łączna waga: " + productionHistory.getWeight() * productionHistory.getHowManyProducts() +
                        "kg. Użytkownik: " + productionHistory.getLogin());
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getLogin() {
        return login;
    }

    public double getWeight() {
        return weight;
    }

    public int getHowManyProducts() {
        return howManyProducts;
    }
}
