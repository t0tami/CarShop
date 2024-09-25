public class Client {
    static double balance;
    String name;
    String surname;

    public Client(double balance, String name, String surname) {
        this.balance = balance;
        this.name = name;
        this.surname = surname;
    }

    public static double getBalance() {
        return balance;
    }

    public static void setBalance(double balance) {
        Client.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return name + " " + surname;
    }

}

