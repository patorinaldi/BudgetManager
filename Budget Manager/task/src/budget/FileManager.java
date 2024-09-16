package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class FileManager {

    private final Wallet wallet;
    private final String filePath = "purchases.txt";
    private final File file = new File(filePath);

    public FileManager(Wallet wallet) {
        this.wallet = wallet;
    }

    public void loadFromFile() {

        try (Scanner scanner = new Scanner(file)) {

            wallet.clearPurchases();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] purchases = line.split(",");
                if (purchases[0].equals("Balance")) {
                    wallet.setBalance(Double.parseDouble(purchases[1]));
                    break;
                }
                wallet.addPurchase(purchases[0], Double.parseDouble(purchases[1]), Categories.valueOf(purchases[2]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }

    }

    public void saveToFile() {

        try (PrintWriter writer = new PrintWriter(filePath)){
            wallet.getPurchases().forEach(o -> writer.println(o.toSave()));
            writer.printf(Locale.US,"Balance,%.2f\n",wallet.getBalance());
        } catch (Exception e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }
}
