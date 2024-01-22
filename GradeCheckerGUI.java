import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCheckerGUI {
  private static final String[] subjectNames = {
    "Computer Programming",
    "21st Century Literature",
    "Practical Research",
    "Physical Science",
    "Physical Education",
    "EAPP",
    "Entrep"
  };

  private static JTextField[] gradeFields;
  private static JButton calculateButton;
  private static JLabel resultLabel;

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }

  private static void createAndShowGUI() {
    JFrame frame = new JFrame("Grade Checker");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(9, 2));

    gradeFields = new JTextField[7];
    for (int i = 0; i < 7; i++) {
      JLabel label = new JLabel(subjectNames[i] + ":");
      gradeFields[i] = new JTextField();
      mainPanel.add(label);
      mainPanel.add(gradeFields[i]);
    }

    calculateButton = new JButton("Calculate");
    calculateButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculateAverage();
      }
    });

    resultLabel = new JLabel();

    mainPanel.add(new JLabel());
    mainPanel.add(calculateButton);
    mainPanel.add(new JLabel());
    mainPanel.add(resultLabel);

    frame.getContentPane().add(mainPanel);
    frame.pack();
    frame.setVisible(true);
  }

  private static void calculateAverage() {
    int[] subjects = new int[7];
    int total = 0;

    for (int i = 0; i < 7; i++) {
      try {
        subjects[i] = Integer.parseInt(gradeFields[i].getText());
        total += subjects[i];

        if (subjects[i] > 100) {
          resultLabel.setText("Invalid grade");
          return;
        }
      } catch (NumberFormatException e) {
        resultLabel.setText("Invalid input");
        return;
      }
    }

    double average = total / 7.0;
    resultLabel.setText("The total average is: " + average);

    if (average > 100) {
      resultLabel.setText("Invalid average");
    } else if (average >= 90) {
      resultLabel.setText("Outstanding. The total average is: " + average);
    } else if (average >= 85) {
      resultLabel.setText("Strongly Satisfactory (Passed). The total average is: " + average);
    } else if (average >= 80) {
      resultLabel.setText("Satisfactory (Passed). The total average is: " + average);
    } else if (average >= 75) {
      resultLabel.setText("Fairly Satisfactory (Passed). The total average is: " + average);
    } else {
      resultLabel.setText("Did Not Meet Expectations (Failed). The total average is: " + average);
    }
  }
}