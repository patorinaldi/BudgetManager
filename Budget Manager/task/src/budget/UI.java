package budget;

import java.util.List;
import java.util.Scanner;

public class UI {

    private final Scanner scanner;
    private final Wallet wallet;
    private boolean finished;
    private final FileManager fileManager;

    public UI(Scanner scanner, Wallet wallet, FileManager fileManager) {
        this.scanner = scanner;
        this.wallet = wallet;
        this.finished = false;
        this.fileManager = fileManager;
    }

    public boolean mainMenu() {
        if (finished) {
            return false;
        }

        System.out.println("Choose your action: ");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
        System.out.println();

        switch (scanner.nextLine()) {
            case "1":
                addIncomeUI();
                break;
            case "2":
                addPurchaseUI();
                break;
            case "3":
                displayList();
                break;
            case "4":
                balanceUI();
                break;
            case "5":
                fileManager.saveToFile();
                savedUI();
                break;
            case "6":
                fileManager.loadFromFile();
                loadedUI();
                break;
            case "7":
                analyzeUI();
                break;
            case "0":
                sayBye();
                return false;
        }
        return true;
    }

    private void analyzeUI() {
        wallet.sort();
        while (true) {
            System.out.println("How do you want to sort?");
            System.out.println("1) Sort all purchases");
            System.out.println("2) Sort by type");
            System.out.println("3) Sort certain type");
            System.out.println("4) Back");
            System.out.println();

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("All:");
                    displayAll();
                    break;
                case "2":
                    displayTypesList();
                    break;
                case "3":
                    Categories category = getFilterCategoryUI();
                    displayCategory(category);
                    break;
                case "4":
                    mainMenu();
                    return;
            }
        }
    }

    private void displayTypesList() {
        System.out.println("Types:");
        wallet.sortType().forEach((s, aDouble) -> System.out.printf("%s - $%.2f\n", s, aDouble));
        System.out.printf("Total sum: $%.2f\n", wallet.getBalance());
        System.out.println();
    }

    private void savedUI() {
        System.out.println("Purchases were saved!");
        System.out.println();
    }

    private void loadedUI() {
        System.out.println("Purchases were loaded!");
        System.out.println();
    }

    private void addIncomeUI() {
        System.out.println("Enter income: ");
        wallet.addIncome(Double.parseDouble(scanner.nextLine()));
        System.out.println("Income was added!");
        System.out.println();
    }

    private void balanceUI() {
        System.out.printf("Balance: $%.2f\n", wallet.getBalance());
        System.out.println();
    }

    private void displayList() {
        if (wallet.getPurchases().isEmpty()) {
            System.out.println("The purchase list is empty");
            System.out.println();
        } else {
            while (true) {
                Categories category = getListCategoryUI();
                if (category == null) {
                    break;
                } else {
                    if (category == Categories.ALL) {
                        displayAll();
                    } else {
                        displayCategory(category);
                    }
                }
            }
        }
    }

    private void displayAll() {
        if (wallet.getPurchases().isEmpty()) {
            System.out.println("The purchase list is empty");
            System.out.println();
            return;
        }
        wallet.getPurchases().forEach(System.out::println);
        System.out.printf("Total sum: $%.2f\n", wallet.getExpenses());
        System.out.println();
    }

    private void displayCategory(Categories category) {

        List<Purchase> filteredList = filteredList(category);

        if (filteredList.isEmpty()) {
            System.out.println("The purchase list is empty!");
            System.out.println();
            return;
        }

        filteredList.forEach(System.out::println);

        Double sum = filteredList.stream().mapToDouble(Purchase::getPrice).sum();

        System.out.printf("Total sum: $%.2f\n", sum);
        System.out.println();
    }

    private List<Purchase> filteredList(Categories category) {
        return wallet.getPurchases().stream()
                .filter(o -> o.getCategory() == category).toList();
    }

    private void addPurchaseUI() {
        while (true) {
            Categories category = getPurchaseCategoryUI();
            if (category == null) {
                break;
            }
            System.out.println("Enter purchase name: ");
            String purchaseName = scanner.nextLine();
            System.out.println("Enter its price: ");
            double price = Double.parseDouble(scanner.nextLine());
            wallet.addPurchase(purchaseName, price, category);
            System.out.println();
        }
    }

    private void sayBye() {
        finished = true;
        System.out.println("Bye!");
        System.out.println();
    }

    private Categories getPurchaseCategoryUI() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
        System.out.println();

        switch (scanner.nextLine()) {
            case "1":
                return Categories.FOOD;
            case "2":
                return Categories.CLOTHES;
            case "3":
                return Categories.ENTERTAINMENT;
            case "4":
                return Categories.OTHER;
            case "5":
                mainMenu();
        }
        return null;
    }

    private Categories getListCategoryUI() {
        System.out.println("Choose the type of purchases");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
        System.out.println();

        switch (scanner.nextLine()) {
            case "1":
                return Categories.FOOD;
            case "2":
                return Categories.CLOTHES;
            case "3":
                return Categories.ENTERTAINMENT;
            case "4":
                return Categories.OTHER;
            case "5":
                return Categories.ALL;
            case "6":
                mainMenu();
        }
        return null;
    }

    private Categories getFilterCategoryUI() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println();

        return switch (scanner.nextLine()) {
            case "1" -> {
                System.out.println("Food:");
                yield Categories.FOOD;
            }
            case "2" -> {
                System.out.println("Clothes:");
                yield Categories.CLOTHES;
            }
            case "3" -> {
                System.out.println("Entertainment:");
                yield Categories.ENTERTAINMENT;
            }
            case "4" -> {
                System.out.println("Other:");
                yield Categories.OTHER;
            }
            default -> null;
        };
    }
}
