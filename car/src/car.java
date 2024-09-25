public class car {
    String model;
    int cost;
    String color;
    int shtyk;
    private boolean available;

    public car(String model, int cost, String color, int shtyk) {
        this.model = model;
        this.cost = cost;
        this.color = color;
        this.shtyk = shtyk;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }
    public String getModel() {
        return model;
    }

    public int getCost() {
        return cost;
    }

    public String getColor() {
        return color;
    }

    public int getShtyk() {
        return shtyk;
    }
    public void setShtyk(int shtyk) {
        this.shtyk = shtyk;
    }
}
