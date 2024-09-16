package budget;

import java.util.*;

public class Wallet {

    private final ArrayList<Purchase> purchases;
    private double balance;
    private double expenses;
    private final HashMap<String, Double> categoriesExpenses;

    public Wallet() {
        this.purchases = new ArrayList<>();
        this.balance = 0.0;
        this.expenses = 0.0;
        this.categoriesExpenses = new HashMap<>(Map.of(
        "Food", 0.0,
        "Clothes", 0.0,
        "Entertainment", 0.0,
        "Other", 0.0));
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void addIncome(double income) {
        this.balance += income;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void addPurchase(String purchase, double price, Categories category) {
        purchases.add(new Purchase(purchase, price, category));
        addExpense(price, category);
    }

    public void addExpense(double expense, Categories category) {
        switch (category) {
            case FOOD -> categoriesExpenses.compute("Food", (key, value) -> value + expense);
            case CLOTHES -> categoriesExpenses.compute("Clothes", (key, value) -> value + expense);
            case ENTERTAINMENT -> categoriesExpenses.compute("Entertainment", (key, value) -> value + expense);
            case OTHER -> categoriesExpenses.compute("Other", (key, value) -> value + expense);
        }
        this.expenses += expense;
        if (balance - expense > 0.0) {
            balance -= expense;
        } else {
            balance = 0.0;
        }
    }

    public double getExpenses() {
        return this.expenses;
    }

    public void clearPurchases(){
        purchases.clear();
    }

    public void sort() {
        purchases.sort(Comparator.comparingDouble(Purchase::getPrice).reversed());
    }

    public LinkedHashMap<String, Double> sortType() {
        List<Map.Entry<String, Double>> list = new ArrayList<>(categoriesExpenses.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        LinkedHashMap<String, Double> newMap = new LinkedHashMap<>();

        for (Map.Entry<String, Double> entry : list) {
            newMap.put(entry.getKey(),entry.getValue());
        }
        return newMap;
    }
}
