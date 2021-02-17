package pl.michal.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class User {
    private String name;
    private String lastName;
    private int accessLevel;
    private LocalDate dateOfBirth;
    private String login;
    private String password;
    private UserCatalog userCatalog;
    private boolean isBlocked;
    private boolean loginUnchangeable;
    private boolean permissionToAccess;

    public User() {
    }
    public User(UserCatalog userCatalog) {
        this.userCatalog = userCatalog;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
    public boolean getBlocked() {
        return isBlocked;
    }

    public User(String name, String lastName, int accessLevel, String dateOfBirth, String login, String password) {
        this.name = name;
        this.lastName = lastName;
        this.accessLevel = accessLevel;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dateTimeFormatter);
        this.login = login;
        this.password = password;
    }

    public User addNewUser() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dodaj użytkownika.");
        System.out.print("Podaj imię:");
        user.name = scanner.nextLine();
        System.out.print("Podaj nazwisko:");
        user.lastName = scanner.nextLine();
        System.out.print("Podaj datę urodzenia w formacie yyyyMMdd:");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateOfBirth = scanner.next();
        if (!Pattern.compile("\\d{8}").matcher(dateOfBirth).matches()) {
            System.out.println("Podano błędną datę urodzenia!");
            return null;
        }
        user.dateOfBirth = LocalDate.parse(dateOfBirth,dateTimeFormatter);
        System.out.println("Podaj poziom dostępu: (2-8)");
        System.out.println("2.Kierownik główny.");
        System.out.println("3.Kierownik sklepu.");
        System.out.println("4.Kierownik magazynu.");
        System.out.println("5.Kierownik produkcji.");
        System.out.println("6.Pracownik sklepu.");
        System.out.println("7.Pracownik magazynu.");
        System.out.println("8.Pracownik produkcji.");
        int accessLevel = scanner.nextInt();
        if (accessLevel > 1 && accessLevel < 9) {
            user.accessLevel = accessLevel;
        } else {
            System.out.println("Podano błędny poziom dostępu!");
            return null;
        }
        System.out.println("Twój login do pierwszego logowania to: " + user.name + user.lastName + user.accessLevel);
        user.login = user.name + user.lastName + user.accessLevel;
        System.out.println("Twoje hasło do pierwszego logowania to: " + dateOfBirth);
        user.password = dateOfBirth;
        System.out.println("Użytkownik dodany pomyślnie!");
        return user;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginUnchangeable(boolean loginUnchangeable) {
        this.loginUnchangeable = loginUnchangeable;
    }
    public boolean getLoginUnchangeable() {
        return loginUnchangeable;
    }

    public void setPermissionToAccess(boolean permissionToAccess) {
        this.permissionToAccess = permissionToAccess;
    }
    public boolean getPermissionToAccess() {
        return permissionToAccess;
    }
}
