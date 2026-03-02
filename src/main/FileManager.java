package main;

import java.io.*;

public class FileManager {

    private String fileName = "attendance_data.csv";

    public void createFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveSubject(Subject subject) {

        if (subject == null) return;

        createFile();

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(
                    subject.getTotalClasses() + "," +
                    subject.getAttendedClasses() + "," +
                    subject.getTargetPercentage()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Subject loadSubject() {

        File file = new File(fileName);
        if (!file.exists()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {

                String[] parts = line.split(",");

                if (parts.length == 3) {
                    int total = Integer.parseInt(parts[0]);
                    int attended = Integer.parseInt(parts[1]);
                    double target = Double.parseDouble(parts[2]);

                    return new Subject(total, attended, target);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean fileExists() {
        return new File(fileName).exists();
    }

    public boolean deleteFile() {
        File file = new File(fileName);
        return file.exists() && file.delete();
    }
}