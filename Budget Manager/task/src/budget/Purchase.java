package budget;

import java.util.Locale;

public class Purchase {

    private final String name;
    private final double price;
    private final Categories category;

    public Purchase(String name, double price, Categories category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", name, price);
    }

    public String toSave() {
        return String.format(Locale.US,"%s,%.2f,%s", name, price, category);
    }

    public Categories getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }
}
