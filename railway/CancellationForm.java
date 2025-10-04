import javax.swing.*;
import java.io.*;

public class CancellationForm {
    public static void showCancellationForm() {
        JFrame frame = new JFrame("Cancellation Form");
        frame.setSize(500, 300);
        frame.setLayout(null);

        JLabel pnrLabel = new JLabel("Enter PNR:");
        pnrLabel.setBounds(30, 30, 100, 25);
        frame.add(pnrLabel);

        JTextField pnrField = new JTextField();
        pnrField.setBounds(150, 30, 300, 25);
        frame.add(pnrField);

        JTextArea infoArea = new JTextArea();
        infoArea.setBounds(30, 70, 420, 80);
        infoArea.setEditable(false);
        frame.add(infoArea);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(150, 160, 100, 30);
        frame.add(searchButton);

        JButton cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBounds(270, 160, 130, 30);
        cancelButton.setVisible(false); // hidden until valid PNR is found
        frame.add(cancelButton);

        searchButton.addActionListener(e -> {
            String pnr = pnrField.getText().trim();
            try (BufferedReader br = new BufferedReader(new FileReader("Reservations.txt"))) {
                String line;
                boolean found = false;
                while ((line = br.readLine()) != null) {
                    if (line.contains(pnr)) {
                        infoArea.setText(line);
                        found = true;
                        cancelButton.setVisible(true); // show cancel option
                        break;
                    }
                }
                if (!found) {
                    infoArea.setText("PNR not found.");
                    cancelButton.setVisible(false);
                }
            } catch (IOException ex) {
                infoArea.setText("Error reading file.");
                cancelButton.setVisible(false);
            }
        });

        cancelButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to cancel this ticket?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String pnr = pnrField.getText().trim();
                try {
                    File inputFile = new File("Reservations.txt");
                    File tempFile = new File("Temp.txt");

                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.contains(pnr)) {
                            writer.write(line + System.lineSeparator());
                        }
                    }

                    reader.close();
                    writer.close();

                    inputFile.delete();
                    tempFile.renameTo(inputFile);

                    JOptionPane.showMessageDialog(frame, "Ticket cancelled successfully.");
                    frame.dispose();
                    LoginForm.showChoiceDialog(); // return to menu
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error cancelling ticket.");
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
