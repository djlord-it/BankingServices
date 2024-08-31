import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

class Account {
    private String accountNo;
    private String name;
    private String phoneNumber;
    private int balance;
    private int prevTransaction;
    private int creditAmount;
    private double interestRate = 0.05; // 5% interest
    private List<String> transactionHistory = new ArrayList<>();
    private Map<String, Integer> contacts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    // Constructor
    public Account(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.accountNo = generateAccountNumber();
        this.balance = 500; // Start with a bonus of 500
    }

    // Method to generate a random account number
    private static String generateAccountNumber() {
        Random random = new Random();
        return String.format("9%02d-%03d-%02d", random.nextInt(100), random.nextInt(1000), random.nextInt(100));
    }

    // Sign-up method
    public static Account signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        Account newAccount = new Account(name, phoneNumber);
        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + newAccount.accountNo);
        System.out.println("Starting Balance: 500");
        return newAccount;
    }

    // Deposit method
    public void deposit() {
        System.out.print("Enter amount to deposit: ");
        int amount = scanner.nextInt();
        if (amount > 0) {
            balance += amount;
            prevTransaction = amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Deposited " + amount + " successfully.");
        } else {
            System.err.println("Deposit amount must be positive.");
        }
    }

    // Withdraw method
    public void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        int amount = scanner.nextInt();
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                prevTransaction = -amount;
                transactionHistory.add("Withdrew: " + amount);
                System.out.println("Withdrew " + amount + " successfully.");
            } else {
                System.err.println("Insufficient balance.");
            }
        } else {
            System.err.println("Withdrawal amount must be positive.");
        }
    }

    // Check balance method
    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }

    // Display interest
    public void displayInterest() {
        double interest = balance * interestRate;
        System.out.println("Your balance interest at " + (interestRate * 100) + "% is: " + interest);
    }

    // Take credit method
    public void takeCredit() {
        System.out.print("Enter amount to borrow: ");
        int amount = scanner.nextInt();
        if (amount % 10 != 0) {
            System.err.println("Enter a multiple of 10.");
        } else if (amount > balance * 2) {
            System.err.println("Maximum allowed credit: " + (balance * 2));
        } else {
            creditAmount = amount + (amount / 10); // Adding 10% interest
            balance += amount;
            transactionHistory.add("Took credit of: " + amount + " with interest of " + (amount / 10));
            System.out.println("Credit approved. New balance: " + balance);
        }
    }

    // Repay credit method
    private void repayCredit() {
        if (creditAmount == 0) {
            System.out.println("No credit to repay.");
            return;
        }

        System.out.print("Enter amount to repay: ");
        int amount = scanner.nextInt();
        if (amount >= creditAmount) {
            balance -= creditAmount;
            creditAmount = 0;
            transactionHistory.add("Repaid credit amount: " + amount);
            System.out.println("Credit repaid successfully.");
        } else {
            System.err.println("Repay the full credit amount or more.");
        }
    }

    // Contact menu
    public void contactMenu() {
        System.out.println("Contact Menu:\n1. Add Contact\n2. Delete Contact\n3. Send Money to Contact\n4. Display Contacts\n5. Search Contact\n0. Back to Main Menu");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addContact();
                break;
            case 2:
                deleteContact();
                break;
            case 3:
                sendMoneyToContact();
                break;
            case 4:
                displayContacts();
                break;
            case 5:
                searchContact();
                break;
            case 0:
                showMenu();
                break;
            default:
                System.err.println("Invalid choice.");
        }
    }

    // Add contact
    private void addContact() {
        System.out.print("Enter contact name: ");
        String contactName = scanner.next().toLowerCase(); // Store in lowercase
        System.out.print("Enter contact ID (integer): ");
        int contactId = scanner.nextInt();
        contacts.put(contactName, contactId);
        System.out.println("Contact added successfully.");
    }

    // Delete contact
    private void deleteContact() {
        System.out.print("Enter contact name to delete: ");
        String contactName = scanner.next().toLowerCase(); // Convert input to lowercase
        if (contacts.remove(contactName) != null) {
            System.out.println("Contact deleted successfully.");
        } else {
            System.err.println("Contact not found.");
        }
    }

    // Send money to contact
    private void sendMoneyToContact() {
        System.out.print("Enter contact name: ");
        String contactName = scanner.next().toLowerCase(); // Convert input to lowercase
        if (contacts.containsKey(contactName)) {
            System.out.print("Enter amount to send: ");
            int amount = scanner.nextInt();
            if (amount > 0 && balance >= amount) {
                balance -= amount;
                transactionHistory.add("Sent " + amount + " to contact " + contactName);
                System.out.println("Money sent to " + contactName + " successfully.");
            } else {
                System.err.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.err.println("Contact not found.");
        }
    }

    // Display contacts
    private void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Contacts:");
            for (Map.Entry<String, Integer> entry : contacts.entrySet()) {
                System.out.println("Name: " + entry.getKey() + ", ID: " + entry.getValue());
            }
        }
    }

    // Search contact
    private void searchContact() {
        System.out.print("Enter contact name to search: ");
        String contactName = scanner.next().toLowerCase(); // Convert input to lowercase
        if (contacts.containsKey(contactName)) {
            System.out.println("Contact found: Name: " + contactName + ", ID: " + contacts.get(contactName));
        } else {
            System.err.println("Contact not found.");
        }
    }

    // Service menu
    public void serviceMenu() {
        System.out.println("Services Menu:\n1. Buy Internet Package\n2. Pay Supplier\n3. Buy Units\n4. Check Rewards\n0. Back to Main Menu");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                buyInternetPackage();
                break;
            case 2:
                paySupplier();
                break;
            case 3:
                buyUnits();
                break;
            case 4:
                checkRewards();
                break;
            case 0:
                showMenu();
                break;
            default:
                System.err.println("Invalid choice.");
        }
    }

    // Buy internet package
    private void buyInternetPackage() {
        System.out.println("1. Social Package\n2. Unlimited Package\n0. Back to Services");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                buySocialPackage();
                break;
            case 2:
                buyUnlimitedPackage();
                break;
            case 0:
                serviceMenu();
                break;
            default:
                System.err.println("Invalid choice.");
        }
    }

    // Buy social package
    private void buySocialPackage() {
        System.out.println("1. 2000f for 1 week\n2. 3500f for 1 month");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                buyPackage(2000);
                break;
            case 2:
                buyPackage(3500);
                break;
            default:
                System.err.println("Invalid option.");
        }
    }

    // Buy unlimited package
    private void buyUnlimitedPackage() {
        System.out.println("1. 5000f for 1 month\n2. 8000f for 3 months");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                buyPackage(5000);
                break;
            case 2:
                buyPackage(8000);
                break;
            default:
                System.err.println("Invalid option.");
        }
    }

    // Buy package
    private void buyPackage(int price) {
        if (balance >= price) {
            balance -= price;
            transactionHistory.add("Bought package for " + price);
            System.out.println("Package purchased successfully.");
        } else {
            System.err.println("Insufficient balance.");
        }
    }

    // Pay supplier
    private void paySupplier() {
        System.out.print("Enter supplier name: ");
        scanner.nextLine(); // Consume newline
        String supplierName = scanner.nextLine();
        System.out.print("Enter amount to pay supplier: ");
        int amount = scanner.nextInt();

        if (amount <= 0) {
            System.err.println("Payment amount must be positive.");
            return;
        }

        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Paid supplier (" + supplierName + "): " + amount);
            System.out.println("Payment to supplier successful.");
        } else {
            System.err.println("Insufficient balance.");
        }
    }

    // Buy units
    private void buyUnits() {
        System.out.print("Enter number of units to buy: ");
        int units = scanner.nextInt();

        if (units <= 0) {
            System.err.println("Number of units must be positive.");
            return;
        }

        int totalCost = units;

        if (balance >= totalCost) {
            balance -= totalCost;
            transactionHistory.add("Bought " + units + " units for " + totalCost);
            System.out.println("Bought " + units + " units.");
        } else {
            System.err.println("Insufficient balance.");
        }
    }


    private void checkRewards() {
        System.out.println("Checking rewards...");

    }


    public void printReceipt() {
        BufferedWriter writer = null;
        try {
            String userHome = System.getProperty("user.home");
            String path = userHome + "/Receipt.txt";
            System.out.println("Saving receipt to: " + path);
            writer = new BufferedWriter(new FileWriter(path));
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            writer.write("***RECEIPT***\n");
            writer.write("Account No: " + accountNo + "\n");
            writer.write("Name: " + name + "\n");
            writer.write("Balance: " + balance + "\n");
            if (creditAmount > 0) {
                writer.write("Credit Amount: " + creditAmount + "\n");
            }
            writer.write("Transaction History:\n");
            if (transactionHistory.isEmpty()) {
                writer.write("No transactions yet.\n");
            } else {
                for (String transaction : transactionHistory) {
                    writer.write(transaction + "\n");
                }
            }
            writer.write("Date: " + formatter.format(date) + "\n");
            System.out.println("Receipt created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing the receipt file: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error closing the writer: " + e.getMessage());
                }
            }
        }
    }


    public void showMenu() {
        int option;
        do {
            System.out.println("Menu:\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Take Credit\n5. Repay Credit\n6. Contact Menu\n7. Service Menu\n8. Print Receipt\n0. Exit");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    takeCredit();
                    break;
                case 5:
                    repayCredit();
                    break;
                case 6:
                    contactMenu();
                    break;
                case 7:
                    serviceMenu();
                    break;
                case 8:
                    printReceipt();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.err.println("Invalid option");
            }
        } while (option != 0);
    }
}
