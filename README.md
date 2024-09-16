Budget Manager
Overview

The Budget Manager is a simple Java-based console application that helps users manage their personal finances by tracking purchases, income, and balances. The application allows users to categorize their purchases, view a list of transactions, and analyze expenses based on different categories. Users can also save and load their financial data for later use.

This project was developed as part of the Hyperskill Java Backend Developer Course.

Features

    Add Income: Users can input their income, which is added to their balance.
    Add Purchases: Purchases can be added with a name, price, and category. Categories include:
        Food
        Clothes
        Entertainment
        Other
    View Purchases: View all purchases or filter them by category.
    Sort and Analyze: Purchases can be sorted by amount or categorized for analysis.
    Save and Load Data: Users can save their current financial data to a file and load it later.

Categories

The application supports the following categories for purchases:

    Food
    Clothes
    Entertainment
    Other

How to Use

    Add Income: Input your income to increase your balance.
    Add Purchases: Record your purchases by specifying the name, price, and category.
    View Purchases: Display a list of all purchases or view them by category.
    Analyze Purchases: Sort your purchases by amount or category to analyze your spending.
    Save/Load Data: Save your data to a file to resume tracking later.

File Structure

    Main.java: Entry point of the application.
    Wallet.java: Manages balance, expenses, and purchases.
    UI.java: Handles the user interface and input from the console.
    FileManager.java: Handles saving and loading data from a text file.
    Purchase.java: A class representing individual purchases with name, price, and category.

Saving and Loading Data

The application saves purchase data to a file named purchases.txt in the project directory. Each entry in the file consists of the purchase name, price, and category, and the last entry stores the user's balance.
