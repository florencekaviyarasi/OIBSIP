import javax.swing.*;

public class LoginFormGUI {
    private static String storedUsername = "Florence";
    private static String storedPassword = "1234";

    public static void showLoginForm() {
        JFrame frame = new JFrame("Login");
        frame.setSize(350, 200);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        frame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(120, 30, 150, 25);
        frame.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        frame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(120, 70, 150, 25);
        frame.add(passField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(120, 110, 100, 30);
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.equals(storedUsername) && password.equals(storedPassword)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                frame.dispose();
                MainMenuGUI.showMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.");
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }git init
    

    public static void setPassword(String newPass) {
        storedPassword = newPass;
    }
}
