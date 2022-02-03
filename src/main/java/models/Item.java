package models;

public class Item {
    final private int id;
    final private String name;
    final private int price;
    final private boolean available;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public Item(int id, String name, int price, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
    }
}
