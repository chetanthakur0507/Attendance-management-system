import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * AttendanceRecord class represents a single attendance record for a student
 */
public class AttendanceRecord {
    private String studentId;
    private LocalDate date;
    private String status; // "Present", "Absent", "Late"
    private String remarks;
    
    public AttendanceRecord(String studentId, LocalDate date, String status) {
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.remarks = "";
    }
    
    public AttendanceRecord(String studentId, LocalDate date, String status, String remarks) {
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.remarks = remarks;
    }
    
    // Getters
    public String getStudentId() {
        return studentId;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    // Setters
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Student ID: " + studentId + 
               ", Date: " + date.format(formatter) + 
               ", Status: " + status + 
               (remarks.isEmpty() ? "" : ", Remarks: " + remarks);
    }
}

