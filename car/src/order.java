class Order {
    private String orderNumber;
    private Client client;
    private car car;

    public Order(String orderNumber, Client client, car car) {
        this.orderNumber = orderNumber;
        this.client = client;
        this.car = car;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public car getCar() {
        return car;
    }

    public void setCar(car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Order Number: " + orderNumber + ", Client: " + client.getFullName() + ", Car: " + car.getModel() + " (" + car.getColor() + "), Price: " + car.getCost();
    }
}
