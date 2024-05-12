package co.edu.cue.velocerentals.model;

public class Vehicle {

    private String category;
    private double price;
    private Availability availability;
    private String type;

    public Vehicle(String category, double price, Availability availability, String type) {
        this.category = category;
        this.price = price;
        this.availability = availability;
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
