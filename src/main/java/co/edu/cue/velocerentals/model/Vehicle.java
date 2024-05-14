package co.edu.cue.velocerentals.model;

public class Vehicle {
    private int id;
    private String name;
    private String category;
    private double price;
    private String availability;
    private String type;

    public Vehicle() {
    }

    public Vehicle(int id, String name, double price,String category, String availability, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.availability = availability;
        this.type = type;
    }



    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
