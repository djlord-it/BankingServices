import java.util.*;

public class Account {
    private static final double INTEREST_RATE = 0.05; // Example interest rate for credit
    private static final int INITIAL_BALANCE = 500; // Initial balance for new accounts

    private String name;
    private String phoneNumber;
    private int balance;
    private int credit;
    private Map<String, Integer> contacts;
    private List<String> transactionHistory;

    public Account(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.balance = INITIAL_BALANCE;
        this.credit = 0;
        this.contacts = new HashMap<>();
        this.transactionHistory = new ArrayList<>();
    }

    // Sign up with provided name and phone number
    public static Account signUp(String name, String phoneNumber) {
        return new Account(name, phoneNumber);
    }

    // Deposit amount into the account
    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited " + amount);
        } else {
            System.err.println("Amount must be positive.");
        }
    }

    // Withdraw amount from the account
    public void withdraw(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrew " + amount);
        } else {
            System.err.println("Insufficient balance or invalid amount.");
        }
    }

    // Get the balance of the account
    public int getBalance() {
        return balance;
    }

    // Take credit
    public void takeCredit(int amount) {
        if (amount > 0) {
            credit += amount;
            balance += amount; // Increase balance by credit amount
            transactionHistory.add("Took credit of " + amount);
        } else {
            System.err.println("Credit amount must be positive.");
        }
    }

    // Repay credit
    public void repayCredit(int amount) {
        if (amount > 0 && amount <= credit) {
            credit -= amount;
            balance -= amount;
            transactionHistory.add("Repaid credit of " + amount);
        } else {
            System.err.println("Invalid amount or insufficient credit.");
        }
    }

    // Print a receipt
    public void printReceipt() {
        System.out.println("Receipt:");
        System.out.println("Balance: " + balance);
        System.out.println("Credit: " + credit);
        System.out.println("Transaction History: " + transactionHistory);
    }

    // Add contact
    public void addContact(String contactName, int contactId) {
        contacts.put(contactName.toLowerCase(), contactId);
        transactionHistory.add("Added contact " + contactName + " with ID " + contactId);
    }

    // Delete contact
    public void deleteContact(String contactName) {
        if (contacts.remove(contactName.toLowerCase()) != null) {
            transactionHistory.add("Deleted contact " + contactName);
        } else {
            System.err.println("Contact not found.");
        }
    }

    // Send money to contact
    public void sendMoneyToContact(String contactName, int amount) {
        if (contacts.containsKey(contactName.toLowerCase())) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
                transactionHistory.add("Sent " + amount + " to contact " + contactName);
            } else {
                System.err.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.err.println("Contact not found.");
        }
    }

    // Display contacts
    public void displayContacts() {
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
    public void searchContact(String contactName) {
        if (contacts.containsKey(contactName.toLowerCase())) {
            System.out.println("Contact found: Name: " + contactName + ", ID: " + contacts.get(contactName.toLowerCase()));
        } else {
            System.err.println("Contact not found.");
        }
    }

    // Buy an internet package
    public void buyInternetPackage(int price) {
        if (balance >= price) {
            balance -= price;
            transactionHistory.add("Bought package for " + price);
        } else {
            System.err.println("Insufficient balance.");
        }
    }

    // Pay a supplier
    public void paySupplier(String supplierName, int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add("Paid supplier (" + supplierName + "): " + amount);
        } else {
            System.err.println("Insufficient balance or invalid amount.");
        }
    }

    // Buy units
    public void buyUnits(int units) {
        int totalCost = units; // Assume cost is equal to units for simplicity
        if (units > 0 && balance >= totalCost) {
            balance -= totalCost;
            transactionHistory.add("Bought " + units + " units for " + totalCost);
        } else {
            System.err.println("Insufficient balance or invalid number of units.");
        }
    }

    // Check rewards (placeholder)
    public void checkRewards() {
        System.out.println("Checking rewards...");
    }

    // Getter for contacts (for GUI use)
    public Map<String, Integer> getContacts() {
        return contacts;
    }
}
