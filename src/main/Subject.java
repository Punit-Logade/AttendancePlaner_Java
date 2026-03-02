package main;

public class Subject {

    private int totalClasses;
    private int attendedClasses;
    private double targetPercentage;

    public Subject(int totalClasses, int attendedClasses, double targetPercentage) {
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
        this.targetPercentage = targetPercentage / 100.0;
    }

    public double calculatePercentage() {
        if (totalClasses == 0) return 0;
        return (attendedClasses / (double) totalClasses) * 100;
    }

    public int classesNeededForTarget() {

        if (calculatePercentage() >= targetPercentage * 100) return 0;

        int needed = 0;

        while (((attendedClasses + needed) / (double) (totalClasses + needed)) < targetPercentage) {
            needed++;
        }

        return needed;
    }

    public int classesCanSkip() {

        if (calculatePercentage() < targetPercentage * 100) return 0;

        int skip = 0;

        while ((attendedClasses / (double) (totalClasses + skip + 1)) >= targetPercentage) {
            skip++;
        }

        return skip;
    }

    public void attendclass() {
        totalClasses++;
        attendedClasses++;
    }

    public void skipclass() {
        totalClasses++;
    }

    public String getStatus() {
        double p = calculatePercentage();
        if (p >= 85) return "Safe";
        if (p >= 75) return "Borderline";
        return "At Risk";
    }

    public double predictPercentageAfter(int futureClasses, int futureAttend) {
        return ((attendedClasses + futureAttend) /
                (double) (totalClasses + futureClasses)) * 100;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public int getAttendedClasses() {
        return attendedClasses;
    }

    public double getTargetPercentage() {
        return targetPercentage * 100;
    }
}