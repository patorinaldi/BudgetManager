package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Wallet wallet = new Wallet();
        FileManager fileManager = new FileManager(wallet);
        UI ui = new UI(scanner, wallet, fileManager);
        while(true) {
            if (!ui.mainMenu()) {
                break;
            }
        }

    }
}

