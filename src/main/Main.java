package main;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AttendanceFrame frame = new AttendanceFrame();
            frame.setVisible(true);
        });
    }
}