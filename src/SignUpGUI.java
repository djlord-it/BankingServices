import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JFrame {
    public SignUpGUI() {
        setTitle("Sign Up");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Create and configure the components
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(phoneField, gbc);

        // Create and configure the sign-up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(100, 30));

        // Add action listener to the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phoneNumber = phoneField.getText();
                if (!name.isEmpty() && !phoneNumber.isEmpty()) {
                    // Create a new account with the given details
                    Account newAccount = Account.signUp(name, phoneNumber);
                    new AccountGUI(newAccount); // Launch the main GUI
                    dispose(); // Close the sign-up window
                } else {
                    JOptionPane.showMessageDialog(SignUpGUI.this, "Please fill in all fields.");
                }
            }
        });

        // Add components to the frame
        add(formPanel, BorderLayout.CENTER);
        add(signUpButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignUpGUI());
    }
}
