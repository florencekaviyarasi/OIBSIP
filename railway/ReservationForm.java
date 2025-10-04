import javax.swing.*;
import java.io.*;

public class ReservationForm {
    public static void showReservationForm() {
        JFrame frame = new JFrame("Reservation Form");
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 30, 200, 25);
        frame.add(nameField);

        JLabel trainNoLabel = new JLabel("Train No:");
        trainNoLabel.setBounds(30, 70, 100, 25);
        frame.add(trainNoLabel);

        JTextField trainNoField = new JTextField();
        trainNoField.setBounds(150, 70, 200, 25);
        frame.add(trainNoField);

        JLabel trainNameLabel = new JLabel("Train Name:");
        trainNameLabel.setBounds(30, 110, 100, 25);
        frame.add(trainNameLabel);

        JTextField trainNameField = new JTextField();
        trainNameField.setBounds(150, 110, 200, 25);
        frame.add(trainNameField);

        JLabel classLabel = new JLabel("Class Type:");
        classLabel.setBounds(30, 150, 100, 25);
        frame.add(classLabel);

        String[] classes = {"Sleeper", "AC", "General"};
        JComboBox<String> classBox = new JComboBox<>(classes);
        classBox.setBounds(150, 150, 200, 25);
        frame.add(classBox);

        JLabel dateLabel = new JLabel("Date of Journey:");
        dateLabel.setBounds(30, 190, 120, 25);
        frame.add(dateLabel);

        JTextField dateField = new JTextField("DD-MM-YYYY");
        dateField.setBounds(150, 190, 200, 25);
        frame.add(dateField);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(30, 230, 100, 25);
        frame.add(fromLabel);

        JTextField fromField = new JTextField();
        fromField.setBounds(150, 230, 200, 25);
        frame.add(fromField);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(30, 270, 100, 25);
        frame.add(toLabel);

        JTextField toField = new JTextField();
        toField.setBounds(150, 270, 200, 25);
        frame.add(toField);

        JButton insertButton = new JButton("Insert");
        insertButton.setBounds(150, 320, 100, 30);
        frame.add(insertButton);

        insertButton.addActionListener(e -> {
            String pnr = "PNR" + System.currentTimeMillis();

            String reservationInfo = pnr +
                ", Name: " + nameField.getText() +
                ", Train No: " + trainNoField.getText() +
                ", Train Name: " + trainNameField.getText() +
                ", Class: " + classBox.getSelectedItem() +
                ", Date: " + dateField.getText() +
                ", From: " + fromField.getText() +
                ", To: " + toField.getText();

            try (FileWriter fw = new FileWriter("Reservations.txt", true)) {
                fw.write(reservationInfo + "\n");
                ClipboardHelper.copyToClipboard(pnr);
                JOptionPane.showMessageDialog(frame, "Train successfully booked!\nYour PNR: " + pnr + "\n(PNR copied to clipboard)");
                frame.dispose();
                LoginForm.showChoiceDialog(); // Return to menu
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving reservation.");
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
