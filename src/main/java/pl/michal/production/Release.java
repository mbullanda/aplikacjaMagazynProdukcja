package pl.michal.production;

public class Release {
    private String name;
    private String color;
    private double weight;
    private int howManyProducts;
    private String login;
    private boolean collected;

    public Release addNewRelease(String name, String color, double weight, int howManyProducts, String login){
        Release release = new Release();
        release.name = name;
        release.color = color;
        release.weight = weight;
        release.howManyProducts = howManyProducts;
        release.login = login;
        return release;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public int getHowManyProducts() {
        return howManyProducts;
    }

    public String getLogin() {
        return login;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHowManyProducts(int howManyProducts) {
        this.howManyProducts += howManyProducts;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
    public boolean getCollected() {
        return collected;
    }
}
