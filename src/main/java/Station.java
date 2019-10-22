public class Station {

    private String name;
    private double averagePower;

    public Station() {
    }

    public Station(String name, double averagePower) {
        this.name = name;
        this.averagePower = averagePower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAveragePower() {
        return averagePower;
    }

    public void setAveragePower(double averagePower) {
        this.averagePower = averagePower;
    }
}
