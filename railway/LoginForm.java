import javax.swing.*;

public class LoginForm {
    private static String storedUsername = "user";
    private static String storedPassword = "pass";

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
            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            if (username.equals(storedUsername) && password.equals(storedPassword)) {
                JOptionPane.showMessageDialog(frame, "Welcome, " + username + "!");
                frame.dispose();
                showChoiceDialog();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.");
                userField.setText("");
                passField.setText("");
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center window
        frame.setVisible(true);
    }

    public static void showChoiceDialog() {
        JFrame menuFrame = new JFrame("Main Menu");
        menuFrame.setSize(300, 200);
        menuFrame.setLayout(null);

        JButton reserveButton = new JButton("Make Reservation");
        reserveButton.setBounds(60, 30, 180, 40);
        menuFrame.add(reserveButton);

        JButton cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBounds(60, 90, 180, 40);
        menuFrame.add(cancelButton);

        reserveButton.addActionListener(e -> {
            menuFrame.dispose();
            ReservationForm.showReservationForm();
        });

        cancelButton.addActionListener(e -> {
            menuFrame.dispose();
            CancellationForm.showCancellationForm();
        });

        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null); // center window
        menuFrame.setVisible(true);
    }
}
