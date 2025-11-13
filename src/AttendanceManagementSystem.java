
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Main class for Student Attendance Management System
 * Provides a menu-driven interface for managing students and attendance
 */
public class AttendanceManagementSystem {
    private Map<String, Student> students;
    private List<AttendanceRecord> attendanceRecords;
    private Scanner scanner;
    
    public AttendanceManagementSystem() {
        students = new HashMap<>();
        attendanceRecords = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        AttendanceManagementSystem system = new AttendanceManagementSystem();
        system.run();
    }
    
    public void run() {
        boolean running = true;
        
        System.out.println("========================================");
        System.out.println("  Student Attendance Management System");
        System.out.println("========================================\n");
        
        while (running) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    markAttendance();
                    break;
                case 4:
                    viewAttendance();
                    break;
                case 5:
                    viewStudentAttendance();
                    break;
                case 6:
                    generateAttendanceReport();
                    break;
                case 7:
                    updateStudent();
                    break;
                case 8:
                    deleteStudent();
                    break;
                case 9:
                    running = false;
                    System.out.println("\nThank you for using the Attendance Management System!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Mark Attendance");
        System.out.println("4. View Attendance Records");
        System.out.println("5. View Student Attendance");
        System.out.println("6. Generate Attendance Report");
        System.out.println("7. Update Student");
        System.out.println("8. Delete Student");
        System.out.println("9. Exit");
        System.out.print("\nEnter your choice: ");
    }
    
    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        if (students.containsKey(studentId)) {
            System.out.println("Student with ID " + studentId + " already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Enter Course: ");
        String course = scanner.nextLine().trim();
        
        Student student = new Student(studentId, name, email, course);
        students.put(studentId, student);
        System.out.println("\nStudent added successfully!");
    }
    
    private void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        
        int index = 1;
        for (Student student : students.values()) {
            System.out.println(index + ". " + student);
            index++;
        }
    }
    
    private void markAttendance() {
        System.out.println("\n--- Mark Attendance ---");
        
        if (students.isEmpty()) {
            System.out.println("No students registered. Please add students first.");
            return;
        }
        
        System.out.print("Enter Date (YYYY-MM-DD) or press Enter for today: ");
        String dateInput = scanner.nextLine().trim();
        LocalDate date;
        
        if (dateInput.isEmpty()) {
            date = LocalDate.now();
        } else {
            try {
                date = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Using today's date.");
                date = LocalDate.now();
            }
        }
        
        System.out.println("\nMarking attendance for date: " + date);
        System.out.println("Available students:");
        viewAllStudents();
        
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        if (!students.containsKey(studentId)) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("Select Status:");
        System.out.println("1. Present");
        System.out.println("2. Absent");
        System.out.println("3. Late");
        System.out.print("Enter choice: ");
        
        int statusChoice = getChoice();
        String status;
        
        switch (statusChoice) {
            case 1:
                status = "Present";
                break;
            case 2:
                status = "Absent";
                break;
            case 3:
                status = "Late";
                break;
            default:
                System.out.println("Invalid choice! Marking as Absent.");
                status = "Absent";
        }
        
        System.out.print("Enter Remarks (optional): ");
        String remarks = scanner.nextLine().trim();
        
        AttendanceRecord record = new AttendanceRecord(studentId, date, status, remarks);
        attendanceRecords.add(record);
        System.out.println("\nAttendance marked successfully!");
    }
    
    private void viewAttendance() {
        System.out.println("\n--- All Attendance Records ---");
        if (attendanceRecords.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }
        
        int index = 1;
        for (AttendanceRecord record : attendanceRecords) {
            System.out.println(index + ". " + record);
            index++;
        }
    }
    
    private void viewStudentAttendance() {
        System.out.println("\n--- View Student Attendance ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        if (!students.containsKey(studentId)) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\nStudent: " + students.get(studentId).getName());
        System.out.println("Attendance Records:");
        
        List<AttendanceRecord> studentRecords = new ArrayList<>();
        for (AttendanceRecord record : attendanceRecords) {
            if (record.getStudentId().equals(studentId)) {
                studentRecords.add(record);
            }
        }
        
        if (studentRecords.isEmpty()) {
            System.out.println("No attendance records found for this student.");
            return;
        }
        
        int present = 0, absent = 0, late = 0;
        for (AttendanceRecord record : studentRecords) {
            System.out.println("  - " + record);
            if (record.getStatus().equals("Present")) present++;
            else if (record.getStatus().equals("Absent")) absent++;
            else if (record.getStatus().equals("Late")) late++;
        }
        
        int total = studentRecords.size();
        double attendancePercentage = total > 0 ? ((double)(present + late) / total) * 100 : 0;
        
        System.out.println("\nSummary:");
        System.out.println("  Total Records: " + total);
        System.out.println("  Present: " + present);
        System.out.println("  Absent: " + absent);
        System.out.println("  Late: " + late);
        System.out.printf("  Attendance Percentage: %.2f%%\n", attendancePercentage);
    }
    
    private void generateAttendanceReport() {
        System.out.println("\n--- Generate Attendance Report ---");
        System.out.print("Enter Date (YYYY-MM-DD) or press Enter for today: ");
        String dateInput = scanner.nextLine().trim();
        LocalDate date;
        
        if (dateInput.isEmpty()) {
            date = LocalDate.now();
        } else {
            try {
                date = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Using today's date.");
                date = LocalDate.now();
            }
        }
        
        System.out.println("\nAttendance Report for: " + date);
        System.out.println("========================================");
        
        List<AttendanceRecord> dateRecords = new ArrayList<>();
        for (AttendanceRecord record : attendanceRecords) {
            if (record.getDate().equals(date)) {
                dateRecords.add(record);
            }
        }
        
        if (dateRecords.isEmpty()) {
            System.out.println("No attendance records found for this date.");
            return;
        }
        
        int present = 0, absent = 0, late = 0;
        for (AttendanceRecord record : dateRecords) {
            Student student = students.get(record.getStudentId());
            String studentName = student != null ? student.getName() : "Unknown";
            System.out.println(studentName + " (" + record.getStudentId() + "): " + record.getStatus());
            
            if (record.getStatus().equals("Present")) present++;
            else if (record.getStatus().equals("Absent")) absent++;
            else if (record.getStatus().equals("Late")) late++;
        }
        
        int total = dateRecords.size();
        System.out.println("\nSummary:");
        System.out.println("  Total Students: " + total);
        System.out.println("  Present: " + present);
        System.out.println("  Absent: " + absent);
        System.out.println("  Late: " + late);
    }
    
    private void updateStudent() {
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        if (!students.containsKey(studentId)) {
            System.out.println("Student not found!");
            return;
        }
        
        Student student = students.get(studentId);
        System.out.println("Current Information: " + student);
        
        System.out.print("Enter new Name (or press Enter to keep current): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            student.setName(name);
        }
        
        System.out.print("Enter new Email (or press Enter to keep current): ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) {
            student.setEmail(email);
        }
        
        System.out.print("Enter new Course (or press Enter to keep current): ");
        String course = scanner.nextLine().trim();
        if (!course.isEmpty()) {
            student.setCourse(course);
        }
        
        System.out.println("\nStudent information updated successfully!");
        System.out.println("Updated Information: " + student);
    }
    
    private void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        if (!students.containsKey(studentId)) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.print("Are you sure you want to delete this student? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("yes")) {
            students.remove(studentId);
            // Remove all attendance records for this student
            attendanceRecords.removeIf(record -> record.getStudentId().equals(studentId));
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}
