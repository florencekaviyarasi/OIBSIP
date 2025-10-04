import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MCQTestGUI {
    private static int score = 0;
    private static int currentQuestion = 0;
    private static Timer timer;
    private static final int TIME_LIMIT = 60; // seconds

    private static String[] questions = {
        "What is the capital of France?",
        "Which planet is known as the Red Planet?"
    };

    private static String[][] options = {
        {"Berlin", "Madrid", "Paris", "Rome"},
        {"Earth", "Mars", "Jupiter", "Venus"}
    };

    private static int[] correctAnswers = {2, 1}; // Index of correct options

    public static void startTest() {
        JFrame frame = new JFrame("MCQ Test");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel questionLabel = new JLabel();
        questionLabel.setBounds(30, 30, 340, 25);
        frame.add(questionLabel);

        JRadioButton[] radioButtons = new JRadioButton[4];
        ButtonGroup group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton();
            radioButtons[i].setBounds(30, 60 + i * 30, 300, 25);
            group.add(radioButtons[i]);
            frame.add(radioButtons[i]);
        }

        JLabel timerLabel = new JLabel("Time left: " + TIME_LIMIT + "s");
        timerLabel.setBounds(30, 190, 200, 25);
        frame.add(timerLabel);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(250, 190, 100, 30);
        frame.add(nextButton);

        ActionListener countdown = new ActionListener() {
            int timeLeft = TIME_LIMIT;

            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft + "s");
                if (timeLeft <= 0) {
                    timer.stop();
                    submitTest(frame);
                }
            }
        };

        timer = new Timer(1000, countdown);
        timer.start();

        nextButton.addActionListener(e -> {
            for (int i = 0; i < 4; i++) {
                if (radioButtons[i].isSelected()) {
                    if (i == correctAnswers[currentQuestion]) {
                        score++;
                    }
                    break;
                }
            }
            currentQuestion++;
            if (currentQuestion < questions.length) {
                loadQuestion(questionLabel, radioButtons);
            } else {
                timer.stop();
                submitTest(frame);
            }
        });

        loadQuestion(questionLabel, radioButtons);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void loadQuestion(JLabel questionLabel, JRadioButton[] radioButtons) {
        questionLabel.setText((currentQuestion + 1) + ". " + questions[currentQuestion]);
        for (int i = 0; i < 4; i++) {
            radioButtons[i].setText(options[currentQuestion][i]);
            radioButtons[i].setSelected(false);
        }
    }

    private static void submitTest(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Test submitted. Score: " + score + "/" + questions.length);
        saveScore("admin", score, questions.length); // Replace "admin" with dynamic username if needed
        frame.dispose();
        MainMenuGUI.showMainMenu();
    }

    private static void saveScore(String username, int score, int total) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        try (FileWriter fw = new FileWriter("Scores.txt", true)) {
            fw.write("Username: " + username + " | Score: " + score + "/" + total + " | Date: " + timestamp + "\n");
        } catch (Exception e) {
            System.out.println("Error saving score.");
        }
    }
}
