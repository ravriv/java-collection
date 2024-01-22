public class GradeChecker {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      System.out.println("Average Calculator");
      int[] subjects = new int[7];
      String[] subjectNames = {
        "Computer Programming",
        "21st Century Literature",
        "Practical Research",
        "Physical Science",
        "Physical Education",
        "EAPP",
        "Entrep"
      };
      int total = 0;

      for (int i = 0; i < 7; i++) {
        System.out.print("Enter grade for " + subjectNames[i] + ": ");
        subjects[i] = scanner.nextInt();
        total += subjects[i];

        if (subjects[i] > 100) {
          System.out.println("Invalid grade");
          return;
        }
      }

      double average = total / 7.0;
      System.out.println("The total average is: " + average);

      if (average > 100) {
        System.out.println("Invalid average");
      } else if (average >= 90) {
        System.out.println("Outstanding");
      } else if (average >= 85) {
        System.out.println("Strongly Satisfactory (Passed)");
      } else if (average >= 80) {
        System.out.println("Satisfactory (Passed)");
      } else if (average >= 75) {
        System.out.println("Fairly Satisfactory (Passed)");
      } else {
        System.out.println("Did Not Meet Expectations (Failed)");
      }
    } finally {
      scanner.close();
    }
  }
}