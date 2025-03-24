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
    private JTextArea historyArea;
    private JCheckBox savePasswordCheckBox;
    private String currentPassword;

    public void run() throws NegativeNumberException {
        DatabaseManager.initializeDatabase();
        GUI();
        updateHistory();
    }

    private void GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // Main panel for input controls
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1, 10, 10));

        // Password length input
        JPanel lengthPanel = new JPanel();
        lengthPanel.add(new JLabel("Password length: "));
        lengthField = new JTextField(10);
        lengthPanel.add(lengthField);
        mainPanel.add(lengthPanel);

        // Checkboxes for password options
        numbersCheckBox = new JCheckBox("Include numbers");
        lettersCheckBox = new JCheckBox("Include letters");
        specialCharsCheckBox = new JCheckBox("Include special characters");
        
        mainPanel.add(numbersCheckBox);
        mainPanel.add(lettersCheckBox);
        mainPanel.add(specialCharsCheckBox);

        // Generate button
        generateButton = new JButton("Generate Password");
        generateButton.addActionListener(e -> generatePassword());
        mainPanel.add(generateButton);

        // Result panel with save checkbox
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultLabel = new JLabel("Generated password will appear here");
        savePasswordCheckBox = new JCheckBox("Save to history");
        savePasswordCheckBox.setEnabled(false);
        savePasswordCheckBox.addActionListener(e -> handleSaveCheckbox());
        resultPanel.add(resultLabel, BorderLayout.CENTER);
        resultPanel.add(savePasswordCheckBox, BorderLayout.EAST);
        mainPanel.add(resultPanel);

        // History panel
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder("Password History"));
        historyArea = new JTextArea(10, 40);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to frame
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(historyPanel, BorderLayout.CENTER);

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
            
            currentPassword = Passwordgenerator.generatePassword(
                length,
                numbersCheckBox.isSelected(),
                lettersCheckBox.isSelected(),
                specialCharsCheckBox.isSelected()
            );
            resultLabel.setText("Your password: " + currentPassword);
            savePasswordCheckBox.setEnabled(true);
            savePasswordCheckBox.setSelected(false);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number");
        } catch (NegativeNumberException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }

    private void handleSaveCheckbox() {
        if (currentPassword != null) {
            if (savePasswordCheckBox.isSelected()) {
                DatabaseManager.savePassword(
                    currentPassword,
                    Integer.parseInt(lengthField.getText()),
                    numbersCheckBox.isSelected(),
                    lettersCheckBox.isSelected(),
                    specialCharsCheckBox.isSelected()
                );
            } else {
                DatabaseManager.deletePassword(currentPassword);
            }
            updateHistory();
        }
    }

    private void updateHistory() {
        historyArea.setText(DatabaseManager.getLastPasswords(10));
    }
}
