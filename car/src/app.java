import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import  java.util.List;

public class app {
    private static List<Client> clients = new ArrayList();
    private static List<car> cars = new ArrayList();
    private static List<manager> managers = new ArrayList();
    private static List<admin> admins = new ArrayList();
    private static List<Order> orders = new ArrayList();
    private static Scanner scr = new Scanner(System.in);

    private static int count = 0;

    private static int salonProfit = 0;


    static {
        Client Alina = new Client(10000000, "Alina", "Edemskaya");
        Client Dima = new Client(500500000, "Dima", "Gavrilovskiy");
        Client Liza = new Client(9000000, "Liza", "Brazhnikova");
        clients.add(Alina);
        clients.add(Dima);
        clients.add(Liza);
        car car1 = new car("BMW", 5000000, "red", 2);
        car car2 = new car("kia", 800000, "blue", 3);
        car car3 = new car("mazda", 7000000, "green", 4);
        car car4 = new car("toyota", 100000, "black", 1);
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        manager manager1 = new manager("Sofa", "Kochanova", 123, 1111);
        manager manager2 = new manager("Nikita", "Ivanov", 321, 2222);
        managers.add(manager1);
        managers.add(manager2);
        admin admin = new admin("admin", "admin admin");
        admins.add(admin);

    }

    static void startClient() {
        boolean running = true;
        while (running) {
            System.out.println("Выберите доступное действие: ");
            System.out.println("1.Посмотреть доступные автомобили");
            System.out.println("2.Оформить заказ");
            System.out.println("3.Принять заказ");
            System.out.println("4.Выйти из программы");
            String choice = scr.nextLine();
            switch (choice) {
                case "1":
                    auto();
                    break;
                case "2":
                    logIn();
                    break;
                case "3":
                    manager();
                    break;
                case "4":
                    System.out.println("Вы вышли из программы. До свидания!");
                    running = false;
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    startClient();
            }

        }
    }

    private static void auto() {
        System.out.println("Доступные авто: ");
        for (int i = 0; i < cars.size(); i++) {
            System.out.println("модель: " + cars.get(i).getModel() + " Цвет: " + (cars.get(i).color) + " Цена: " + (cars.get(i).cost) + " Количество:  " + (cars.get(i).shtyk));
        }
    }

    private static void logIn() {
        if (count >= 3) {
            System.out.println("Превышено максимальное количество попыток входа. Выход из программы.");
            return;
        }

        System.out.println("Введите логин");
        String login = scr.nextLine();

        boolean userFound = false;

        for (var client : clients) {
            if (login.equals(client.getName())) {
                System.out.println("Вы успешно вошли");
                userFound = true;
                buyAuto();
                break;
            }
        }

        if (!userFound) {
            System.out.println("Такого пользователя не существует! 1 - Попробовать снова, 2 - Зарегистрироваться");
            String choice = scr.nextLine();
            scr.nextLine();
            switch (choice) {
                case "1":
                    count++;
                    logIn();
                    break;

                case "2":
                    System.out.print("Введите имя клиента: ");
                    String name = scr.nextLine();

                    System.out.print("Введите фамилию клиента: ");
                    String surname = scr.nextLine();
                    Random rand = new Random();
                    int balance = rand.nextInt(4700000) + 300000;

                    Client newClient = new Client(balance, name, surname);
                    clients.add(newClient);
                    System.out.println("Клиент добавлен с балансом: " + balance + " рублей!");
                    buyAuto();
                    break;

                default:
                    System.out.println("Некорректный выбор.");
                    break;
            }
        }
    }

    private static void buyAuto() {
        System.out.println("Введите модель машины, которую вы хотите купить: ");
        String buyName = scr.nextLine();

        car selectedCar = null;
        for (var car : cars) {
            if (buyName.equals(car.getModel())) {
                selectedCar = car;
                break;
            }
        }

        if (selectedCar == null) {
            System.out.println("Автомобиль с такой моделью не найден или он уже продан.");
            startClient();
        }

        System.out.println("Введите имя клиента: ");
        String clientName = scr.nextLine();
        System.out.println("Введите фамилию клиента: ");
        String clientSurname = scr.nextLine();

        Client selectedClient = null;
        for (var client : clients) {
            if (client.getName().equals(clientName) && client.getSurname().equals(clientSurname)) {
                selectedClient = client;
                break;
            }
        }

        if (selectedClient == null) {
            System.out.println("Клиент с таким именем и фамилией не найден.");
            buyAuto();
        }

        Order newOrder = new Order(generateOrderNumber(), selectedClient, selectedCar);
        orders.add(newOrder);

        startClient();
    }

    private static void manager() {
        if (count >= 3) {
            System.out.println("Превышено максимальное количество попыток входа. Выход из программы.");
            startClient();
        }

        System.out.println("Введите пинкод");
        int pin = scr.nextInt();

        boolean managerFound = false;

        for (var manager : managers) {
            if (pin == manager.getPincode()) {
                System.out.println("Вы успешно вошли. Здравствуйте " + manager.getName() + " (ID: " + manager.getId() + ")");
                managerFound = true;

                zakaz();
                approveOrder();
            }
        }

        if (!managerFound) {
            System.out.println("Такого пин-кода не существует! ");
            count++;
            manager();
        }
    }

    private static void approveOrder() {
        System.out.println("Введите номер заказа для одобрения: ");
        String orderNumber = scr.nextLine();

        Order selectedOrder = null;

        for (Order order : orders) {
            if (order.getOrderNumber().equals(orderNumber)) {
                selectedOrder = order;
                break;
            }
        }

        if (selectedOrder != null) {
            car car = selectedOrder.getCar();
            Client client = selectedOrder.getClient();

            if (car.isAvailable() && Client.getBalance() >= car.getCost()) {
                car.setAvailable(false);
                Client.setBalance(Client.getBalance() - car.getCost());
                salonProfit += car.getCost();

                System.out.println("Заказ одобрен! Клиент " + client.getFullName()
                        + " купил " + car.getModel() + ". Оставшийся баланс: " + Client.getBalance());
                System.out.println("Прибыль автосалона: " + salonProfit);
                startClient();

            } else {
                System.out.println("Недостаточно средств у клиента или автомобиль недоступен.");
                admin();
            }
        } else {
            System.out.println("Заказ с таким номером не найден.");
        }
    }

    private static void deleteOrder() {
        System.out.println("Введите номер заказа для удаления:");
        String orderNumber = scr.nextLine();
        Order order = findOrder(orderNumber);

        if (order != null) {
            orders.remove(order);
            System.out.println("Заказ успешно удален.");
        } else {
            System.out.println("Заказ не найден.");
        }
    }

    private static Order findOrder(String orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber().equals(orderNumber)) {
                return order;
            }
        }
        return null;
    }

    private static void zakaz() {
        if (orders.isEmpty()) {
            System.out.println("Нет доступных заказов.");
        } else {
            System.out.println("Список заказов:");
            for (Order order : orders) {
                System.out.println("Номер заказа: " + order.getOrderNumber()
                        + ", Клиент: " + order.getClient().getFullName()
                        + ", Автомобиль: " + order.getCar().getModel());
            }
        }
    }

    private static String generateOrderNumber() {
        int orderCount = orders.size() + 1;
        return String.format("%04d", orderCount);
    }

    private static void admin() {
        if (count >= 3) {
            System.out.println("Превышено максимальное количество попыток входа. Выход из программы.");
            return;
        }

        System.out.println("Введите логин");
        String login = scr.nextLine();
        System.out.println("Введите пароль");
        String password = scr.nextLine();

        boolean adminFound = false;

        for (var admin : admins) {
            if (password.equals(admin.getPassword())) {
                System.out.println("Вы успешно вошли");
                adminFound = true;
                String choice = scr.nextLine();
                System.out.println("Выберите действие : 1 - изменение заказа , 2 - удаление заказа");
                switch (choice){
                    case "1":
                        modifyOrder();
                    case "2":
                        deleteOrder();
                    default:
                        System.out.println("Некорректный выбор.");
                        break;

                }

                break;
            }
        }
        if (!adminFound) {
            System.out.println("Такого пользователя не существует!");
            admin();
        }
    }

    private static void modifyOrder(){
        System.out.println("Введите номер заказа для изменения:");
        String orderNumber = scr.nextLine();
        Order order = findOrder(orderNumber);

        if (order != null) {
            System.out.println("Введите новую модель автомобиля для замены:");
            String newCarModel = scr.nextLine();
            car newCar = findCarByModel(newCarModel);

            if (newCar != null && newCar.isAvailable()) {
                order.setCar(newCar);
                System.out.println("Заказ успешно изменен.");
            } else {
                System.out.println("Автомобиль не доступен.");
            }
        } else {
            System.out.println("Заказ не найден.");
        }
    }
    private static car findCarByModel(String model) {
        for (car car : cars) {
            if (car.getModel().equalsIgnoreCase(model)) {
                return car;
            }
        }
        return null;
    }
}