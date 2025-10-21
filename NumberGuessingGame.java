import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    private int lowerBound;
    private int upperBound;
    private Random rand;

    public NumberGuessingGame(int lower, int upper) {
        this.lowerBound = lower;
        this.upperBound = upper;
        this.rand = new Random();
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("=== Welcome to the Number Guessing Game! ===");

        while (playAgain) {
            int target = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean guessed = false;

            System.out.println("\nI have chosen a number between " + lowerBound + " and " + upperBound + ". Can you guess it?");

            while (!guessed) {
                System.out.print("Enter your guess: ");
                int guess;
                try {
                    guess = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid integer.");
                    continue;
                }
                attempts++;
                if (guess < target) {
                    System.out.println("Too low! Try again.");
                } else if (guess > target) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed it in " + attempts + " attempts.");
                    guessed = true;
                }
            }

            System.out.print("Do you want to play again? (y/n): ");
            String choice = sc.nextLine().trim().toLowerCase();
            playAgain = choice.equals("y") || choice.equals("yes");
        }

        System.out.println("Thanks for playing! Goodbye!");
    }

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame(1, 100);
        game.play();
    }
}
