package pl.michal.user;

import java.util.Scanner;

public class UserMenu {
    private User user;
    private UserCatalog userCatalog;

    public UserMenu(UserCatalog userCatalog) {
        this.userCatalog = userCatalog;
    }
    public UserMenu() {

    }

    public void listUserEditMenu(){
        System.out.println("Witamy w panelu--> Edycja danych. Wybierz działanie:");
        System.out.println("1.Zmień login.");
        System.out.println("2.Zmień hasło.");
        System.out.println("3.Cofnij.");
    }
    public void functionsUserEditMenu(UserCatalog userCatalog){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number == 1) {
            changeLogin(userCatalog.getUser(userCatalog.getNumberOfActualUser()));
        } else if (number == 2) {
            changePassword(userCatalog.getUser(userCatalog.getNumberOfActualUser()));
        } else if (number == 3) {
            return;
        }
    }
    public void listUserMenu(){
        System.out.println("Witamy w panelu--> Użytkownicy. Wybierz działanie:");
        System.out.println("1.Dodaj użytkownika.");
        System.out.println("2.Edytuj poziom dostępu.");
        System.out.println("3.Usuń użytkownika.");
        System.out.println("4.Wyświetl użytkowników.");
        System.out.println("5.Odblokuj użytkownika.");
        System.out.println("6.Cofnij.");
    }
    public void functionsUserMenu(UserCatalog userCatalog) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number == 1) {
            User user2 = new User().addNewUser();
            if (user2 != null) {
                userCatalog.addUser(user2);
            }
        } else if (number == 2) {
            changeAccessLevel(userCatalog);
        } else if (number == 3) {
            deleteUser(userCatalog);
        } else if (number == 4) {
            userCatalog.displayListOfUsers();
        } else if (number == 5) {
            unblockUser(userCatalog);
        } else if (number == 6) {
            return;
        }
    }
    public void deleteUser(UserCatalog userCatalog) {
        System.out.println("Wybierz użytkownika do usunięcia:");
        userCatalog.displayListOfUsers();
        Scanner scanner = new Scanner(System.in);
        int numberOfUser = scanner.nextInt();
        if (numberOfUser == userCatalog.getNumberOfActualUser()) {
            System.out.println("Nie możesz usunąć siebie!");
        } else if (numberOfUser == 1) {
            System.out.println("Nie możesz usunąć użytkownika nr 1!");
        } else if (userCatalog.getUser(numberOfUser) == null) {
            System.out.println("Nie ma takiego użytkownika!");
            return;
        } else {
            System.out.println("Czy na pewno chcesz usunąć użytkownika: " + userCatalog.getUser(numberOfUser).getName() +
                    " " + userCatalog.getUser(numberOfUser).getLastName() + "? [yes/no]");
            String yesNo = scanner.next();
            if (yesNo.equalsIgnoreCase("yes")) {
                System.out.println("Pomyślnie usunięto użytkownika!");
                userCatalog.deleteUser(numberOfUser);
            } else {
                System.out.println("Użytkownik nie został usunięty!");
                return;
            }
        }
    }
    public void unblockUser(UserCatalog userCatalog) {
        System.out.println("Wybierz użytkownika do odblokowania:");
        userCatalog.displayListOfBlockedUsers();
        Scanner scanner = new Scanner(System.in);
        int numberOfUser = scanner.nextInt();
        if (userCatalog.getUser(numberOfUser).getBlocked() == false || userCatalog.getUser(numberOfUser) == null) {
            System.out.println("Nie ma takiego użytkownika lub jest już aktywny!");
            return;
        }
        userCatalog.getUser(numberOfUser).setBlocked(false);
        System.out.println("Użytkownik odblokowany!");
    }



    public void changeAccessLevel(UserCatalog userCatalog) {
        System.out.println("Wybierz użytkownika, któremu chcesz zmienić poziom dostępu.");
        userCatalog.displayListOfUsers();
        Scanner scanner = new Scanner(System.in);
        int numberOfUser = scanner.nextInt();
        if (numberOfUser < 2){
            System.out.println("Błąd! Podałeś wartość ujemną, zero lub próbowałeś zmienić poziom dostępu użytkownika nr 1!");
            return;
        } else if (userCatalog.getUser(numberOfUser) == null) {
            System.out.println("Nie ma takiego użytkownika!");
            return;
        }

        User userToEdit = userCatalog.getUser(numberOfUser);
        System.out.println("Aktualny poziom dostępu: " + userToEdit.getAccessLevel());
        System.out.println("Podaj nowy poziom dostępu: (2-8)");
        System.out.println("2.Kierownik główny.");
        System.out.println("3.Kierownik sklepu.");
        System.out.println("4.Kierownik magazynu.");
        System.out.println("5.Kierownik produkcji.");
        System.out.println("6.Pracownik sklepu.");
        System.out.println("7.Pracownik magazynu.");
        System.out.println("8.Pracownik produkcji.");
        int newAccessLevel = scanner.nextInt();
        if (newAccessLevel > 1 && newAccessLevel < 9){
            System.out.println("Nowy poziom dostępu ustawiono pomyślnie!");
        userToEdit.setAccessLevel(newAccessLevel);
        } else {
            System.out.println("Błąd przy ustawianiu poziomu dostępu!");
            return;
        }
    }

    public void changeLogin(User userToEdit) {
        Scanner scanner = new Scanner(System.in);
        if (userToEdit.getLoginUnchangeable()){
            System.out.println("Twój login jest już niezmienialny!");
            return;
        }
        System.out.println("Twój aktualny login: " + userToEdit.getLogin());
        System.out.print("Podaj nowy login: ");
        String newLogin = scanner.nextLine();
        for (int i = 0; i < userCatalog.getTabOfUser().length; i++) {
            if (userCatalog.getUser(i + 1) != null && newLogin.equals(userCatalog.getUser(i + 1).getLogin())) {
                System.out.println("Login zajęty!");
                return;
            }
        }
        System.out.println("Czy potwierdzasz zmianę loginu na: " + newLogin + "?[yes/no]");
        String yesNo = scanner.nextLine();
        if (yesNo.equalsIgnoreCase("yes")) {
            System.out.println("Login zmieniony pomyślnie!");
            userToEdit.setLogin(newLogin);
        } else {
            System.out.println("Login pozostaje niezmieniony: " + userToEdit.getLogin());
            return;
        }
    }

    public void changePassword(User userToEdit) {
        Scanner scanner = new Scanner(System.in);
        String actualPassword = userToEdit.getPassword();
        System.out.print("Podaj stare hasło: ");
        String oldPassword = scanner.nextLine();
        if (oldPassword.equals(actualPassword)) {
            System.out.print("Podaj nowe hasło: ");
            String newPassword = scanner.nextLine();
            System.out.print("Powtórz hasło: ");
            String repeatPassword = scanner.nextLine();
            if (newPassword.equals(repeatPassword)) {
                userToEdit.setPassword(newPassword);
                System.out.println("Hasło zostało zmienione!");
            } else {
                System.out.println("Błędne hasło!");
                return;
            }
        } else {
            System.out.println("Błędne hasło!");
            return;
        }
    }
}
