package pl.michal.user;

public class UserCatalog {
    private int numberOfActualUser;
    private int actualAccessLevel;
    private User[] tabOfUser = new User[100];

    public UserCatalog() {

    }

    public void setNumberOfActualUser(int numberOfActualUser) {
        this.numberOfActualUser = numberOfActualUser;
    }

    public int getNumberOfActualUser() {
        return numberOfActualUser;
    }

    public void setActualAccessLevel(int actualAccessLevel) {
        this.actualAccessLevel = actualAccessLevel;
    }

    public int getActualAccessLevel() {
        return actualAccessLevel;
    }

    public UserCatalog(User[] initialUser) {
        addInitialUser(initialUser);
    }

    public User[] getTabOfUser() {
        return tabOfUser;
    }

    public User getUser(int numberOfUser) {
        return tabOfUser[numberOfUser - 1];
    }

    public void addUser (User user) { //metoda służąca do dodawania nowych użytkowników do tabeli użytkowników
        for (int i = 0; i < tabOfUser.length; i++) {
            if (tabOfUser[i] == null) {
                tabOfUser[i] = user;
                break;
            }
        }
    }
    public void displayListOfUsers() {
        for (int i = 0; i < tabOfUser.length; i++) {
            if (tabOfUser[i] != null && !tabOfUser[i].getBlocked()) {
                User user = tabOfUser[i];
                System.out.print(i + 1 + ". " + user.getName() + " " + user.getLastName() + ". Data urodzenia: "
                        + user.getDateOfBirth() + ". Stanowisko: ");
                displayPosition(i+1);
                //poniższa linijka jest tymczasowo, wyświetla login i hasło pod każdym użytkownikiem, dla wygodnego testowania
                System.out.println(user.getLogin() + " " + user.getPassword());

            }
        }
    }
    public void displayListOfBlockedUsers() {
        for (int i = 0; i < tabOfUser.length; i++) {
            if (tabOfUser[i] != null && tabOfUser[i].getBlocked()) {
                User user = tabOfUser[i];
                System.out.print(i + 1 + ". " + user.getName() + " " + user.getLastName() + ". Data urodzenia: "
                        + user.getDateOfBirth() + ". Stanowisko: ");
                displayPosition(i + 1);
            }
        }
    }
    private void addInitialUser(User[] initialUser) {
        for (int i = 0; i < initialUser.length; i++) {
            tabOfUser[i] = initialUser[i];
        }
    }

    public void deleteUser(int numberOfUser) {
        tabOfUser[numberOfUser - 1] = null;
        for (int i = 0; i < tabOfUser.length; i++){
            if (tabOfUser[i] == null) {
                for (int j = i + 1; j < tabOfUser.length - 1; j++) {
                    if (tabOfUser[j] != null) {
                        tabOfUser[i] = tabOfUser[j];
                        tabOfUser[j] = null;
                        break;
                    }
                }
            }
        }
    }
    public void displayPosition(int numberOfUser) {
        switch (getUser(numberOfUser).getAccessLevel()) {
            case 1:
                System.out.println("Prezes.");
                break;
            case 2:
                System.out.println("Kierownik główny.");
                break;
            case 3:
                System.out.println("Kierownik sklepu.");
                break;
            case 4:
                System.out.println("Kierownik magazynu.");
                break;
            case 5:
                System.out.println("Kierownik produkcji.");
                break;
            case 6:
                System.out.println("Pracownik sklepu.");
                break;
            case 7:
                System.out.println("Pracownik magazynu.");
                break;
            case 8:
                System.out.println("Pracownik produkcji.");
                break;
        }
    }

}
