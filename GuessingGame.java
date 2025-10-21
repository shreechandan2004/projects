import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class GuessingGame {
    private static int numberToGuess;
    private static int attempts = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Guessing Game");
        JLabel label = new JLabel("Guess a number between 1 and 100:");
        JTextField textField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        JLabel resultLabel = new JLabel("");

        numberToGuess = new Random().nextInt(100) + 1;

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(textField);
        panel.add(guessButton);
        panel.add(resultLabel);

        guessButton.addActionListener(e -> {
            try {
                int guess = Integer.parseInt(textField.getText());
                attempts++;
                if (guess == numberToGuess) {
                    resultLabel.setText("Correct! Attempts: " + attempts);
                    guessButton.setEnabled(false);
                } else if (guess < numberToGuess) {
                    resultLabel.setText("Too low!");
                } else {
                    resultLabel.setText("Too high!");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input.");
            }
        });

        frame.setContentPane(panel);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
