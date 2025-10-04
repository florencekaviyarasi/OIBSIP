import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileManagerGUI {
    public static void showProfileForm() {
        JFrame frame = new JFrame("Update Profile");
        frame.setSize(350, 250);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 80, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 30, 180, 25);
        frame.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 70, 80, 25);
        frame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(120, 70, 180, 25);
        frame.add(emailField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(120, 120, 100, 30);
        frame.add(saveButton);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();

            try (FileWriter fw = new FileWriter("User.txt")) {
                fw.write("Name: " + name + "\nEmail: " + email);
                JOptionPane.showMessageDialog(frame, "Profile updated!");
                frame.dispose();
                MainMenuGUI.showMainMenu();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving profile.");
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void showPasswordForm() {
        JFrame frame = new JFrame("Change Password");
        frame.setSize(350, 200);
        frame.setLayout(null);

        JLabel passLabel = new JLabel("New Password:");
        passLabel.setBounds(30, 50, 100, 25);
        frame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(140, 50, 150, 25);
        frame.add(passField);

        JButton changeButton = new JButton("Change");
        changeButton.setBounds(120, 100, 100, 30);
        frame.add(changeButton);

        changeButton.addActionListener(e -> {
            String newPass = new String(passField.getPassword());
            LoginFormGUI.setPassword(newPass); // We'll add this method next
            JOptionPane.showMessageDialog(frame, "Password changed!");
            frame.dispose();
            MainMenuGUI.showMainMenu();
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
