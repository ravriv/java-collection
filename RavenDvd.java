import javax.swing.*;
import java.awt.*;

public class RavenDvd {
  public static void main(String[] args) {
    JFrame frame = new JFrame("ravriv");
    frame.setSize(400, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(Color.BLACK);
    frame.setLayout(null);

    JLabel label = new JLabel("Raven", SwingConstants.CENTER);
    label.setFont(new Font("Serif", Font.BOLD, 24));

    Thread thread = new Thread(() -> {
      int red = 0,
      green = 0,
      blue = 0;
      int x = 0,
      y = 0,
      xDirection = 5,
      yDirection = 5;
      while (true) {
        label.setForeground(new Color(red, green, blue));
        label.setBounds(x, y, 100, 50);
        frame.getContentPane().add(label);
        frame.revalidate();
        frame.repaint();

        red = (red + 5) % 256;
        green = (green + 3) % 256;
        blue = (blue + 1) % 256;

        x += xDirection;
        y += yDirection;

        if (x + label.getWidth() > frame.getWidth() || x < 0) {
          xDirection *= -1;
        }
        if (y + label.getHeight() > frame.getHeight() || y < 0) {
          yDirection *= -1;
        }

        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    thread.start();
    frame.setVisible(true);
  }
}