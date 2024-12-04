import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class Main extends JFrame {
private JTextField startRangeField, endRangeField, guessField;
private JLabel messageLabel, rangeLabel, guessPromptLabel, attemptsLabel;
private JButton startButton, guessButton, resetButton;
private int targetNumber, guesses;
private Random random;
public Main() {
setTitle("Number Guessing Game");
setSize(400, 300);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new BorderLayout());
random = new Random();
JPanel mainPanel = new JPanel();
mainPanel.setLayout(new GridLayout(6, 1));
JPanel rangePanel = new JPanel();
rangeLabel = new JLabel("Enter the range (start and end): ");
startRangeField = new JTextField(5);
endRangeField = new JTextField(5);
rangePanel.add(rangeLabel);
rangePanel.add(startRangeField);
rangePanel.add(endRangeField);
startButton = new JButton("Start Game");
startButton.addActionListener(new StartGameListener());
JPanel guessPanel = new JPanel();
guessPromptLabel = new JLabel("Enter your guess: ");
guessField = new JTextField(5);
guessButton = new JButton("Submit Guess");
guessButton.addActionListener(new GuessListener());
guessPanel.add(guessPromptLabel);
guessPanel.add(guessField);
guessPanel.add(guessButton);
messageLabel = new JLabel("Welcome to the Number Guessing
Game!",JLabel.CENTER);
attemptsLabel = new JLabel("Attempts: 0", JLabel.CENTER);
resetButton = new JButton("Reset Game");
resetButton.addActionListener(new ResetGameListener());
mainPanel.add(rangePanel);
mainPanel.add(startButton);
mainPanel.add(guessPanel);
mainPanel.add(messageLabel);
mainPanel.add(attemptsLabel);
mainPanel.add(resetButton);
add(mainPanel, BorderLayout.CENTER);
resetGame();
}
private void resetGame()
{ startRangeField.setText("");
endRangeField.setText("");
guessField.setText("");
messageLabel.setText("Welcome to the Number Guessing Game!");
attemptsLabel.setText("Attempts: 0");
targetNumber = 0;
guesses = 0;
guessField.setEnabled(false);
guessButton.setEnabled(false);
}
private class StartGameListener implements ActionListener
{@Override
public void actionPerformed(ActionEvent e)
{try {
intstartRange = Integer.parseInt(startRangeField.getText().trim());
int endRange = Integer.parseInt(endRangeField.getText().trim());
if (startRange >= endRange) {
messageLabel.setText("Invalid range! Start should be less than end.");
} else {
targetNumber = random.nextInt(endRange - startRange + 1) + startRange;
guesses = 0;
messageLabel.setText("Game started! Guess the number.");
attemptsLabel.setText("Attempts: 0");
guessField.setEnabled(true);
guessButton.setEnabled(true);
}
} catch (NumberFormatException ex)
{ messageLabel.setText("Invalid input! Please enter valid
integers.");
}
}
}
private class GuessListener implements ActionListener
{@Override
public void actionPerformed(ActionEvent e)
{try {
int guess = Integer.parseInt(guessField.getText().trim());
guesses++;
if (guess < targetNumber)
{ messageLabel.setText("Too low! Try
again.");
} else if (guess > targetNumber)
{ messageLabel.setText("Too high! Try
again.");
} else {
messageLabel.setText("Congratulations! You guessed it in " + guesses + "
attempts.");
guessField.setEnabled(false);
guessButton.setEnabled(false);
}
attemptsLabel.setText("Attempts: " + guesses);
} catch (NumberFormatException ex) {
messageLabel.setText("Invalid input! Please enter a valid number.");
}
}
}
private class ResetGameListener implements ActionListener
{@Override
public void actionPerformed(ActionEvent e)
{resetGame();
}
}
public static void main(String[] args)
{SwingUtilities.invokeLater(() -> {
Main game = new Main();
game.setVisible(true);
}
}
