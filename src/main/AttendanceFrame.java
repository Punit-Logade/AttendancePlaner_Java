package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AttendanceFrame extends JFrame implements ActionListener {

    private Subject subject;

    private JLabel appLabel;

    private JTextField targetFeild;
    private JTextField totalField;
    private JTextField attendedField;

    private JLabel targetLabel;
    private JLabel totalLabel;
    private JLabel attendedLabel;

    private JLabel percentageValue;
    private JLabel needValue;
    private JLabel skipValue;
    private JLabel statusValue;

    private JLabel percentageLabel;
    private JLabel needLabel;
    private JLabel skipLabel;
    private JLabel statusLabel;

    private JPanel centralPanel;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JPanel btnPanel;

    private String[] btnList = {
            "Calculate", "Attend +1", "Miss +1",
            "Predict", "Reset", "Save"
    };

    public AttendanceFrame() {

        setTitle("Attendance Tracker");
        setLayout(new BorderLayout(15, 15));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getRootPane().setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, new Color(40, 43, 52)));

        appLabel = new JLabel("ATTENDANCE TRACKER");
        appLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        appLabel.setForeground(new Color(88, 166, 255));
        appLabel.setHorizontalAlignment(SwingConstants.CENTER);

        inputPanel = new JPanel(new GridLayout(3, 2, 10, 12));

        totalLabel = new JLabel("Total Classes:");
        totalField = new JTextField();

        attendedLabel = new JLabel("Attended Classes:");
        attendedField = new JTextField();

        targetLabel = new JLabel("Target % ");
        targetFeild = new JTextField("75");
        targetFeild.setEditable(true);

        inputPanel.add(totalLabel);
        inputPanel.add(totalField);
        inputPanel.add(attendedLabel);
        inputPanel.add(attendedField);
        inputPanel.add(targetLabel);
        inputPanel.add(targetFeild);

        btnPanel = new JPanel(new GridLayout(2, 3, 10, 30));
        for (String text : btnList) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btn.addActionListener(this);
            btnPanel.add(btn);
        }

        outputPanel = new JPanel(new GridLayout(4, 2, 10, 12));

        percentageLabel = new JLabel("Attendance Percentage:");
        percentageValue = new JLabel("");

        needLabel = new JLabel("Classes Needed:");
        needValue = new JLabel("");

        skipLabel = new JLabel("Classes Can Skip:");
        skipValue = new JLabel("");

        statusLabel = new JLabel("Status:");
        statusValue = new JLabel("--");

        outputPanel.add(percentageLabel);
        outputPanel.add(percentageValue);
        outputPanel.add(needLabel);
        outputPanel.add(needValue);
        outputPanel.add(skipLabel);
        outputPanel.add(skipValue);
        outputPanel.add(statusLabel);
        outputPanel.add(statusValue);

        centralPanel = new JPanel(new BorderLayout(20, 20));
        centralPanel.add(inputPanel, BorderLayout.NORTH);
        centralPanel.add(outputPanel, BorderLayout.CENTER);

        add(appLabel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        applyModernTheme();
        setButtonsEnabled(false);

        FileManager fileManager = new FileManager();
        Subject loaded = fileManager.loadSubject();

        if (loaded != null) {
            subject = loaded;
        
            totalField.setText(String.valueOf(subject.getTotalClasses()));
            attendedField.setText(String.valueOf(subject.getAttendedClasses()));
            targetFeild.setText(String.valueOf((int) subject.getTargetPercentage()));
        
            updateUI();
            setButtonsEnabled(true);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) { 

        String cmd = e.getActionCommand();

        if (cmd.equals("Reset")) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to reset?", "Confirm Reset", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                clearFields(); 
            }
            return;
        }

        if (!validateInput()) {
            return;
        }

        switch (cmd) {
            case "Calculate":
                handelCalculate();
                break;
            case "Attend +1":
                handelAttend();
                break;
            case "Miss +1":
                handelMiss();
                break;
            case "Predict":
                handelPredict();
                break;
            case "Save": new FileManager().saveSubject(subject);
                JOptionPane.showMessageDialog(this, "Data Saved");
                break;
        }
    }

    private void applyModernTheme() {

        Color background = new Color(28, 30, 36);
        Color panelColor = new Color(40, 43, 52);
        Color accent = new Color(88, 166, 255);
        Color textColor = new Color(230, 230, 235);
        Color secondaryText = new Color(200, 205, 215);
        Color fieldBg = new Color(55, 58, 68);

        getContentPane().setBackground(background);

        inputPanel.setBackground(panelColor);
        outputPanel.setBackground(panelColor);
        centralPanel.setBackground(panelColor);
        btnPanel.setBackground(panelColor);

        styleTextField(targetFeild, fieldBg, accent, textColor);
        styleTextField(totalField, fieldBg, accent, textColor);
        styleTextField(attendedField, fieldBg, accent, textColor);

        styleLabel(targetLabel, secondaryText);
        styleLabel(totalLabel, secondaryText);
        styleLabel(attendedLabel, secondaryText);

        styleLabel(percentageLabel, secondaryText);
        styleLabel(needLabel, secondaryText);
        styleLabel(skipLabel, secondaryText);
        styleLabel(statusLabel, secondaryText);

        percentageValue.setForeground(accent);
        percentageValue.setFont(new Font("Segoe UI", Font.BOLD, 16));

        needValue.setForeground(textColor);
        needValue.setFont(new Font("Segoe UI", Font.BOLD, 15));

        skipValue.setForeground(textColor);
        skipValue.setFont(new Font("Segoe UI", Font.BOLD, 15));

        statusValue.setForeground(textColor);
        statusValue.setFont(new Font("Segoe UI", Font.BOLD, 15));

        for (Component comp : btnPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                String text = btn.getText();

                if (text.equals("Calculate")) {
                    btn.setBackground(new Color(70, 130, 180));
                } else if (text.equals("Attend +1") || text.equals("Miss +1")) {
                    btn.setBackground(new Color(46, 204, 113));
                } else if (text.equals("Predict")) {
                    btn.setBackground(new Color(241, 196, 15));
                } else if (text.equals("Reset")) {
                    btn.setBackground(new Color(231, 76, 60));
                } else {
                    btn.setBackground(new Color(149, 165, 166));
                }

                btn.setForeground(Color.WHITE);
                btn.setFocusPainted(false);
                btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            }
        }
    }

    private void styleTextField(JTextField field, Color bg, Color border, Color fg) {

        field.setBackground(bg);
        field.setForeground(fg);
        field.setCaretColor(border);

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(border, 1),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void styleLabel(JLabel label, Color color) {

        label.setForeground(color);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    public void updateUI() {
        percentageValue.setText(String.format("%.2f %%", subject.calculatePercentage()));
        needValue.setText(String.valueOf(subject.classesNeededForTarget()));
        skipValue.setText(String.valueOf(subject.classesCanSkip()));
        statusValue.setText(subject.getStatus());
    }

    public void clearFields() {
        totalField.setText("");
        attendedField.setText("");
        targetFeild.setText("75");
        percentageValue.setText("");
        needValue.setText("");
        skipValue.setText("");
        statusValue.setText("");
        
        subject = null;
        setButtonsEnabled(false);
    }

    public boolean validateInput() {

        if (totalField.getText().trim().isEmpty() ||
                attendedField.getText().trim().isEmpty() ||
                targetFeild.getText().trim().isEmpty()) {
            showError("Please fill all fields");
            return false;
        }

        try {
            int total = Integer.parseInt(totalField.getText().trim());
            int attended = Integer.parseInt(attendedField.getText().trim());
            int target = Integer.parseInt(targetFeild.getText().trim());

            if (total < 0 || attended < 0 || target <= 0 || target > 100) {
                showError("Invalid values");
                return false;
            }

            if (attended > total) {
                showError("Attended cannot exceed total");
                return false;
            }

        } catch (NumberFormatException ex) {
            showError("Please enter valid numbers");
            return false;
        }
        return true;
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void handelCalculate() {

        int total = Integer.parseInt(totalField.getText().trim());
        int attended = Integer.parseInt(attendedField.getText().trim());
        int target = Integer.parseInt(targetFeild.getText().trim());

        subject = new Subject(total, attended, target);
        updateUI();
        setButtonsEnabled(true);
    }

    public void handelAttend() {

        if (subject == null) {
            showError("Calculate first");
            return;
        }

        subject.attendclass();

        totalField.setText(String.valueOf(subject.getTotalClasses()));
        attendedField.setText(String.valueOf(subject.getAttendedClasses()));

        updateUI();
    }

    public void handelMiss() {

        if (subject == null) {
            showError("Calculate first");
            return;
        }

        subject.skipclass();

        int total = Integer.parseInt(totalField.getText().trim()) + 1;
        int attended = Integer.parseInt(attendedField.getText().trim()) - 1;

        totalField.setText(String.valueOf(total));
        attendedField.setText(String.valueOf(attended));

        updateUI();
    }

    public void handelPredict() {

        if (subject == null) {
            showError("Calculate first");
            return;
        }

        JTextField futureClassesField = new JTextField();
        JTextField futureAttendField = new JTextField();

        Object[] message = {
                "Future Classes:", futureClassesField,
                "Future Attended:", futureAttendField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Predict", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int futureClasses = Integer.parseInt(futureClassesField.getText().trim());
                int futureAttend = Integer.parseInt(futureAttendField.getText().trim());

                if (futureClasses < 0 || futureAttend < 0 || futureAttend > futureClasses) {
                    showError("Invalid values");
                    return;
                }

                double predicted = subject.predictPercentageAfter(futureClasses, futureAttend);
                JOptionPane.showMessageDialog(this,
                        String.format("Predicted: %.2f %%", predicted));

            } catch (NumberFormatException ex) {
                showError("Invalid input");
            }
        }
    }

    public void setButtonsEnabled(boolean enabled) {
        for (Component comp : btnPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                String text = btn.getText();

                if (!text.equals("Calculate" ) && !text.equals("Reset")) {
                    btn.setEnabled(enabled);
                }
            }
        }
    }
    
}