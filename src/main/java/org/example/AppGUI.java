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
    private JTextField descriptionField;
    private String currentPassword;

    public void run() throws NegativeNumberException {
        DatabaseManager.initializeDatabase();
        GUI();
        updateHistory();
    }

    private void GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLayout(new BorderLayout(15, 15));

        // Main panel for input controls
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Password Generator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Password length input
        JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lengthPanel.setBorder(BorderFactory.createTitledBorder("Password Settings"));
        lengthPanel.add(new JLabel("Password length: "));
        lengthField = new JTextField(10);
        lengthField.setPreferredSize(new Dimension(100, 30));
        lengthPanel.add(lengthField);
        mainPanel.add(lengthPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Checkboxes for password options
        JPanel optionsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Password Options"));
        numbersCheckBox = new JCheckBox("Include numbers");
        lettersCheckBox = new JCheckBox("Include letters");
        specialCharsCheckBox = new JCheckBox("Include special characters");
        
        optionsPanel.add(numbersCheckBox);
        optionsPanel.add(lettersCheckBox);
        optionsPanel.add(specialCharsCheckBox);
        mainPanel.add(optionsPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Generate button
        generateButton = new JButton("Generate Password");
        generateButton.setPreferredSize(new Dimension(200, 40));
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateButton.addActionListener(e -> generatePassword());
        mainPanel.add(generateButton);
        mainPanel.add(Box.createVerticalStrut(20));

        // Result panel with save checkbox and description
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Generated Password"));
        
        // Result label
        resultLabel = new JLabel("Generated password will appear here");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(resultLabel);
        resultPanel.add(Box.createVerticalStrut(10));
        
        // Description panel
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        descriptionPanel.add(new JLabel("Description: "));
        descriptionField = new JTextField(30);
        descriptionField.setEnabled(false);
        descriptionPanel.add(descriptionField);
        resultPanel.add(descriptionPanel);
        resultPanel.add(Box.createVerticalStrut(10));
        
        // Save checkbox
        savePasswordCheckBox = new JCheckBox("Save to history");
        savePasswordCheckBox.setEnabled(false);
        savePasswordCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        savePasswordCheckBox.addActionListener(e -> handleSaveCheckbox());
        resultPanel.add(savePasswordCheckBox);
        
        mainPanel.add(resultPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // History panel
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder("Password History"));
        historyArea = new JTextArea(10, 40);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        historyArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to frame
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(historyPanel, BorderLayout.CENTER);

        // Set frame properties
        frame.setTitle("Password Generator");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
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
            descriptionField.setEnabled(true);
            descriptionField.setText("");
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
                    descriptionField.getText().trim(),
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
