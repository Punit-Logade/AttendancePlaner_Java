# Attendance Planner 📊

A smart academic utility built in Java to help students strategically manage their attendance. This application provides real-time calculations to determine exactly how many classes a user can skip or must attend to hit their target percentage.

## 🚀 Key Features
- **Dynamic Tracking:** Add and manage attendance records for multiple subjects.
- **Strategic Planning:** Automated calculation of "Safe Skips" and "Mandatory Attendance" based on a user-defined target percentage.
- **Data Persistence:** Integrated file-saving system to ensure your records are stored locally and loaded upon restart.
- **Smart Logic:** Predicts future attendance outcomes based on your current status.

## 🛠️ Technical Stack
- **Language:** Java 8+
- **GUI Framework:** Swing/JavaFX (Standard Java Desktop Tools)
- **Data Storage:** Local File I/O (txt/serializable data)

## 📦 Getting Started
1. **Clone the repository:**
   ```bash
   git clone https://github.com
   cd attendance-planner
   ```
2. **Compile the application:**
   ```bash
   javac Main.java
   ```
3. **Run the project:**
   ```bash
   java Main
   ```

## 🎯 High-Level Logic Example
If your target is **75%** and you are currently at **80%**, the app will calculate exactly how many upcoming lectures you can skip without falling below your goal.

## 📈 Future Roadmap (20 LPA Goals)
- **Database Integration:** Moving from local files to a PostgreSQL database using Spring Data JPA.
- **Cloud Sync:** Transitioning into a Spring Boot web application for multi-device access.
- **Notification Engine:** Adding automated alerts for critical attendance levels.
