package pl.michal.production;

public class ReleaseHistory {
    private Release[] tabOfReleaseHistory = new Release[200];
    private boolean isAvailable = true;

    public void addNewReleaseToTab (Release release) {
        for (int i = 0; i < tabOfReleaseHistory.length; i++) {
            if (tabOfReleaseHistory[i] == null) {
                tabOfReleaseHistory[i] = release;
                break;
            }
        }
    }
    public void displayHistoryOfRelease(){
        for (int i = 0; i < tabOfReleaseHistory.length; i++ ){
            if (tabOfReleaseHistory[i] != null) {
                Release release = tabOfReleaseHistory[i];
                System.out.println(i + 1 + ". " + release.getName() + " " + release.getColor() + ". " +
                        "Ilość: "  + release.getHowManyProducts() + ". Łączna waga: " + release.getWeight() * release.getHowManyProducts() +
                        "kg. Użytkownik: " + release.getLogin());
            }
        }
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public boolean getAvailable() {
        return isAvailable;
    }

    public Release[] getTabOfReleaseHistory() {
        return tabOfReleaseHistory;
    }
}
