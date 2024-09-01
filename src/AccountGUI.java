import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class AccountGUI extends JFrame {
    private Account userAccount;

    public AccountGUI(Account userAccount) {
        this.userAccount = userAccount;
        setTitle("Account Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1));

        // Add buttons for each functionality
        addButton("Deposit", e -> showDepositDialog());
        addButton("Withdraw", e -> showWithdrawDialog());
        addButton("Check Balance", e -> showBalanceDialog());
        addButton("Take Credit", e -> showCreditDialog());
        addButton("Repay Credit", e -> showRepayCreditDialog());
        addButton("Add Contact", e -> showAddContactDialog());
        addButton("Delete Contact", e -> showDeleteContactDialog());
        addButton("Send Money to Contact", e -> showSendMoneyDialog());
        addButton("Display Contacts", e -> displayContactsDialog());
        addButton("Search Contact", e -> showSearchContactDialog());
        addButton("Manage Services", e -> showServiceMenu());
        addButton("Print Receipt", e -> userAccount.printReceipt());

        setVisible(true);
    }

    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        add(button);
    }

    private void showDepositDialog() {
        try {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            int amount = Integer.parseInt(amountStr);
            userAccount.deposit(amount);
            JOptionPane.showMessageDialog(this, "Deposited " + amount + " successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showWithdrawDialog() {
        try {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            int amount = Integer.parseInt(amountStr);
            userAccount.withdraw(amount);
            JOptionPane.showMessageDialog(this, "Withdrew " + amount + " successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBalanceDialog() {
        int balance = userAccount.getBalance();
        JOptionPane.showMessageDialog(this, "Current balance: " + balance);
    }

    private void showCreditDialog() {
        try {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to borrow:");
            int amount = Integer.parseInt(amountStr);
            userAccount.takeCredit(amount);
            JOptionPane.showMessageDialog(this, "Credit approved. New balance: " + userAccount.getBalance());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showRepayCreditDialog() {
        try {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to repay:");
            int amount = Integer.parseInt(amountStr);
            userAccount.repayCredit(amount);
            JOptionPane.showMessageDialog(this, "Credit repaid successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAddContactDialog() {
        try {
            String contactName = JOptionPane.showInputDialog(this, "Enter contact name:");
            int contactId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter contact ID (integer):"));
            userAccount.addContact(contactName, contactId);
            JOptionPane.showMessageDialog(this, "Contact added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showDeleteContactDialog() {
        String contactName = JOptionPane.showInputDialog(this, "Enter contact name to delete:");
        userAccount.deleteContact(contactName);
        JOptionPane.showMessageDialog(this, "Contact deleted successfully.");
    }

    private void showSendMoneyDialog() {
        try {
            String contactName = JOptionPane.showInputDialog(this, "Enter contact name:");
            int amount = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter amount to send:"));
            userAccount.sendMoneyToContact(contactName, amount);
            JOptionPane.showMessageDialog(this, "Money sent successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayContactsDialog() {
        StringBuilder contactsList = new StringBuilder();
        for (Map.Entry<String, Integer> entry : userAccount.getContacts().entrySet()) {
            contactsList.append("Name: ").append(entry.getKey()).append(", ID: ").append(entry.getValue()).append("\n");
        }
        if (contactsList.length() == 0) {
            contactsList.append("No contacts found.");
        }
        JOptionPane.showMessageDialog(this, contactsList.toString());
    }

    private void showSearchContactDialog() {
        String contactName = JOptionPane.showInputDialog(this, "Enter contact name to search:");
        if (userAccount.getContacts().containsKey(contactName.toLowerCase())) {
            JOptionPane.showMessageDialog(this, "Contact found: Name: " + contactName + ", ID: " + userAccount.getContacts().get(contactName.toLowerCase()));
        } else {
            JOptionPane.showMessageDialog(this, "Contact not found.");
        }
    }

    private void showServiceMenu() {
        String[] options = {"Buy Internet Package", "Pay Supplier", "Buy Units", "Check Rewards", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this, "Select a service:", "Service Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        switch (choice) {
            case 0:
                showBuyPackageDialog();
                break;
            case 1:
                showPaySupplierDialog();
                break;
            case 2:
                showBuyUnitsDialog();
                break;
            case 3:
                userAccount.checkRewards();
                JOptionPane.showMessageDialog(this, "Rewards checked.");
                break;
            case 4:
                // Cancel
                break;
        }
    }

    private void showBuyPackageDialog() {
        String[] packageOptions = {"Social Package (2000f for 1 week)", "Social Package (3500f for 1 month)",
                "Unlimited Package (5000f for 1 month)", "Unlimited Package (8000f for 3 months)"};
        int choice = JOptionPane.showOptionDialog(this, "Select a package:", "Buy Internet Package",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, packageOptions, packageOptions[0]);
        switch (choice) {
            case 0:
                userAccount.buyInternetPackage(2000);
                break;
            case 1:
                userAccount.buyInternetPackage(3500);
                break;
            case 2:
                userAccount.buyInternetPackage(5000);
                break;
            case 3:
                userAccount.buyInternetPackage(8000);
                break;
        }
    }

    private void showPaySupplierDialog() {
        try {
            String supplierName = JOptionPane.showInputDialog(this, "Enter supplier name:");
            int amount = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter amount to pay supplier:"));
            userAccount.paySupplier(supplierName, amount);
            JOptionPane.showMessageDialog(this, "Payment to supplier completed.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBuyUnitsDialog() {
        try {
            int units = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter number of units to buy:"));
            userAccount.buyUnits(units);
            JOptionPane.showMessageDialog(this, "Units purchased.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number of units. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Launch the GUI with the sign-up process
        new SignUpGUI();
    }
}
