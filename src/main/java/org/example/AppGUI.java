package org.example;

import javax.swing.*;
import java.awt.*;

public class AppGUI extends JFrame {
    private static final JFrame frame = new JFrame("Password Generator");
    private JTextField lengthField;
    private JCheckBox numbersCheckBox;
    private JCheckBox lettersCheckBox;
    private JCheckBox specialCharsCheckBox;
    private JButton generateButton;
    private JLabel resultLabel;

    public void run() throws NegativeNumberException {
        GUI();
    }

    private void GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 1, 10, 10));

        // Password length input
        JPanel lengthPanel = new JPanel();
        lengthPanel.add(new JLabel("Password length: "));
        lengthField = new JTextField(10);
        lengthPanel.add(lengthField);
        frame.add(lengthPanel);

        // Checkboxes for password options
        numbersCheckBox = new JCheckBox("Include numbers");
        lettersCheckBox = new JCheckBox("Include letters");
        specialCharsCheckBox = new JCheckBox("Include special characters");
        
        frame.add(numbersCheckBox);
        frame.add(lettersCheckBox);
        frame.add(specialCharsCheckBox);

        // Generate button
        generateButton = new JButton("Generate Password");
        generateButton.addActionListener(e -> generatePassword());
        frame.add(generateButton);

        // Result label
        resultLabel = new JLabel("Generated password will appear here");
        frame.add(resultLabel);

        frame.setVisible(true);
    }

    private void generatePassword() {
        try {
            int length = Integer.parseInt(lengthField.getText());
            if (length < 1) {
                throw new NegativeNumberException("Number can't be smaller than 1");
            }
            if (length > 32) {
                throw new NegativeNumberException("Number can't be greater than 32");
            }
            
            String password = Passwordgenerator.generatePassword(
                length,
                numbersCheckBox.isSelected(),
                lettersCheckBox.isSelected(),
                specialCharsCheckBox.isSelected()
            );
            resultLabel.setText("Your password: " + password);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number");
        } catch (NegativeNumberException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }
}
