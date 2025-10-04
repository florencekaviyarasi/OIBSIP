import javax.swing.*;

public class MainMenuGUI {
    public static void showMainMenu() {
        JFrame frame = new JFrame("Main Menu");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JButton profileButton = new JButton("Update Profile");
        profileButton.setBounds(120, 30, 150, 30);
        frame.add(profileButton);

        JButton passwordButton = new JButton("Change Password");
        passwordButton.setBounds(120, 70, 150, 30);
        frame.add(passwordButton);

        JButton testButton = new JButton("Take MCQ Test");
        testButton.setBounds(120, 110, 150, 30);
        frame.add(testButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(120, 150, 150, 30);
        frame.add(logoutButton);

        profileButton.addActionListener(e -> {
            frame.dispose();
            ProfileManagerGUI.showProfileForm();
        });

        passwordButton.addActionListener(e -> {
            frame.dispose();
            ProfileManagerGUI.showPasswordForm();
        });

        testButton.addActionListener(e -> {
            frame.dispose();
            MCQTestGUI.startTest();
        });

        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Logged out successfully.");
            frame.dispose();
            LoginFormGUI.showLoginForm();
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
