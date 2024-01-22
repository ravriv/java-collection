import java.util.Random;
import java.util.Scanner;

public class NumGuess {
  public static void main(String[] args) {
    Random rand = new Random();
    int num = rand.nextInt(10) + 1;
    Scanner system = new Scanner(System.in);
    int guess;
    int numGuesses = 0;
    int maxGuesses = 2;

    System.out.println("Number Guesser Game");

    while (numGuesses < maxGuesses) {
      System.out.print("Guess the number (1 to 10): ");
      guess = system.nextInt();
      numGuesses++;
      if (guess == num) {
        System.out.println("Congratulations, you guessed the number!");
        System.out.println("The correct number was: " + num);
        system.close();
        return;
      } else {
        System.out.println("Incorrect! You have " + (maxGuesses - numGuesses) + " guesses remaining");
      }
    }
    System.out.println("Sorry, you have used all your guesses!");
    System.out.println("The correct number was: " + num);
    system.close();
  }
}