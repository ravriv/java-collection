import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class PasswordGeneratorGUI extends JFrame {
  private static final long serialVersionUID = 1L;

  private static final char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
  private static final char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  private static final char[] NUMBERS = "0123456789".toCharArray();
  private static final char[] SYMBOLS = "!@#$%^&*()_+-=[]{}|;':,.<>?".toCharArray();

  private static final SecureRandom random = new SecureRandom();

  private JCheckBox lowercaseCheckbox;
  private JCheckBox uppercaseCheckbox;
  private JCheckBox numbersCheckbox;
  private JCheckBox symbolsCheckbox;
  private JTextField lengthTextField;
  private JButton generateButton;
  private JTextArea passwordTextArea;

  public PasswordGeneratorGUI() {
    setTitle("Password Generator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setResizable(false);

    JPanel topPanel = createTopPanel();

    JPanel centerPanel = createCenterPanel();

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);

    add(mainPanel);

    generateButton.addActionListener(e -> generatePassword());

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private JPanel createTopPanel() {
    JPanel topPanel = new JPanel();
    topPanel.setBackground(new Color(0x2C3E50));
    topPanel.setPreferredSize(new Dimension(400, 100));
    topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

    JLabel titleLabel = new JLabel("Password Generator");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setForeground(Color.WHITE);

    topPanel.add(titleLabel);

    return topPanel;
  }

  private JPanel createCenterPanel() {
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridBagLayout());
    centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 0, 5, 10);

    lowercaseCheckbox = createCheckbox("Include Lowercase");
    centerPanel.add(lowercaseCheckbox, gbc);

    gbc.gridy++;
    uppercaseCheckbox = createCheckbox("Include Uppercase");
    centerPanel.add(uppercaseCheckbox, gbc);

    gbc.gridy++;
    numbersCheckbox = createCheckbox("Include Numbers");
    centerPanel.add(numbersCheckbox, gbc);

    gbc.gridy++;
    symbolsCheckbox = createCheckbox("Include Symbols");
    centerPanel.add(symbolsCheckbox, gbc);

    gbc.gridy++;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 0, 5, 0);

    JLabel lengthLabel = createLabel("Password length:");
    centerPanel.add(lengthLabel, gbc);

    gbc.gridx++;
    gbc.anchor = GridBagConstraints.WEST;

    lengthTextField = createTextField(10);
    centerPanel.add(lengthTextField, gbc);

    gbc.gridy++;
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.WEST;

    generateButton = createButton("Generate");
    centerPanel.add(generateButton, gbc);

    gbc.gridy++;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    passwordTextArea = createTextArea(1, 1);
    JScrollPane scrollPane = new JScrollPane(passwordTextArea);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    centerPanel.add(scrollPane, gbc);

    return centerPanel;
  }

  private JCheckBox createCheckbox(String text) {
    JCheckBox checkbox = new JCheckBox(text);
    checkbox.setFont(new Font("Arial", Font.PLAIN, 14));
    checkbox.setForeground(new Color(0x2C3E50));
    return checkbox;
  }

  private JLabel createLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Arial", Font.PLAIN, 14));
    label.setForeground(new Color(0x2C3E50));
    return label;
  }

  private JTextField createTextField(int columns) {
    JTextField textField = new JTextField(columns);
    textField.setFont(new Font("Arial", Font.PLAIN, 14));
    return textField;
  }

  private JButton createButton(String text) {
    JButton button = new JButton(text);
    button.setFont(new Font("Arial", Font.BOLD, 16));
    button.setForeground(Color.WHITE);
    button.setBackground(new Color(0x2C3E50));
    button.setFocusPainted(false);
    button.setOpaque(true);
    button.setBorderPainted(false);
    button.setBackground(new Color(0x2C3E50));
    button.setForeground(Color.WHITE);
    button.setFocusable(false);
    button.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent evt) {
        button.setBackground(new Color(0x3498DB));
      }
      public void mouseExited(MouseEvent evt) {
        button.setBackground(new Color(0x2C3E50));
      }
    });
    return button;
  }

  private JTextArea createTextArea(int rows, int columns) {
    JTextArea textArea = new JTextArea(rows, columns);
    textArea.setFont(new Font("Arial", Font.PLAIN, 14));
    textArea.setEditable(false);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    return textArea;
  }

  private void generatePassword() {
    int length;
    try {
      length = Integer.parseInt(lengthTextField.getText());
    } catch (NumberFormatException e) {
      showErrorDialog("Please enter a valid number for password length", "Invalid Input");
      return;
    }

    if (length < 8) {
      showErrorDialog("Password length must be at least 8 characters", "Invalid Length");
      return;
    }

    List < Character > passwordChars = new ArrayList < > ();
    if (!lowercaseCheckbox.isSelected() && !uppercaseCheckbox.isSelected() && !numbersCheckbox.isSelected() && !symbolsCheckbox.isSelected()) {
      showErrorDialog("At least one checkbox must be selected", "Invalid Selection");
      return;
    }

    if (lowercaseCheckbox.isSelected()) {
      addCharsToList(passwordChars, LOWERCASE);
    }
    if (uppercaseCheckbox.isSelected()) {
      addCharsToList(passwordChars, UPPERCASE);
    }
    if (numbersCheckbox.isSelected()) {
      addCharsToList(passwordChars, NUMBERS);
    }
    if (symbolsCheckbox.isSelected()) {
      addCharsToList(passwordChars, SYMBOLS);
    }
    Collections.shuffle(passwordChars, random);

    StringBuilder password = new StringBuilder();
    int passwordCharsSize = passwordChars.size();
    for (int i = 0; i < length; i++) {
      password.append(passwordChars.get(random.nextInt(passwordCharsSize)));
    }

    passwordTextArea.setText(password.toString());
  }

  private void addCharsToList(List < Character > list, char[] chars) {
    for (char c: chars) {
      list.add(c);
    }
  }

  private void showErrorDialog(String message, String title) {
    JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      new PasswordGeneratorGUI();
    });
  }
}