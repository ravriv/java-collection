import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickCounterWithGUI {
  private int count = 0;

  public static void main(String[] args) {
    new ClickCounterWithGUI().createAndShowGUI();
  }

  private void createAndShowGUI() {
    JFrame frame = new JFrame("Click Counter");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 100);

    JButton button = new JButton("Click Me");
    button.setBackground(Color.DARK_GRAY);
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Arial", Font.BOLD, 16));
    button.setFocusPainted(false);

    JLabel label = new JLabel("Click count: 0");

    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Click count: " + count);
      }
    });

    frame.setLayout(new FlowLayout());
    frame.add(button);
    frame.add(label);
    frame.setVisible(true);
  }
}