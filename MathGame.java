import java.util.Scanner;
import java.util.Random;

public class MathGame {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    int score = 0;
    int maxScore = 3;

    System.out.println("Welcome to the Math Puzzle Game!");

    while (true) {
      int num1 = random.nextInt(100) + 1;
      int num2 = random.nextInt(100) + 1;
      int operation = random.nextInt(4);

      String operator = "";
      int correctAnswer = 0;

      switch (operation) {
      case 0:
        operator = "+";
        correctAnswer = num1 + num2;
        break;
      case 1:
        operator = "-";
        correctAnswer = num1 - num2;
        break;
      case 2:
        operator = "*";
        correctAnswer = num1 * num2;
        break;
      case 3:
        operator = "÷";
        correctAnswer = num1 / num2;
        break;
      }

      System.out.print("What is " + num1 + " " + operator + " " + num2 + "? ");
      int userAnswer = scanner.nextInt();

      if (userAnswer == correctAnswer) {
        score++;
        System.out.println("Correct! Your score is now: " + score + " / " + maxScore);

        if (score == maxScore) {
          System.out.println("Congratulations, you've won!");
          break;
        }
      } else {
        System.out.println("Incorrect. The correct answer is: " + correctAnswer);
        System.out.print("Do you want to play again? (yes/no): ");
        String playAgain = scanner.next().toLowerCase();

        if (!playAgain.equals("yes")) {
          System.out.println("Thanks for playing! Your final score is: " + score);
          break;
        }
      }
    }

    scanner.close();
  }
}