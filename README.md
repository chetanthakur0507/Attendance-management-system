# Student Attendance Management System

A comprehensive Java-based application for managing student attendance records with a menu-driven interface.

## Features

- **Student Management**
  - Add new students with ID, name, email, and course
  - View all registered students
  - Update student information
  - Delete students

- **Attendance Management**
  - Mark attendance for students (Present, Absent, Late)
  - View all attendance records
  - View attendance history for a specific student
  - Generate attendance reports for specific dates
  - Add remarks to attendance records

- **Reports**
  - Individual student attendance summary with percentage
  - Date-wise attendance reports
  - Statistics (Present, Absent, Late counts)

## Project Structure

```
news/
├── src/
│   ├── Student.java                    # Student entity class
│   ├── AttendanceRecord.java           # Attendance record entity class
│   └── AttendanceManagementSystem.java # Main application class
└── README.md                           # Project documentation
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- No external dependencies required (uses only Java standard library)

## How to Compile and Run

### Compile the project:

```bash
javac src/*.java
```

### Run the application:

```bash
java -cp src AttendanceManagementSystem
```

## Usage Guide

1. **Add Student**: Register a new student with their details
2. **View All Students**: Display all registered students
3. **Mark Attendance**: Record attendance for a student on a specific date
4. **View Attendance Records**: See all attendance records
5. **View Student Attendance**: Check attendance history and statistics for a specific student
6. **Generate Attendance Report**: Get a report of attendance for a specific date
7. **Update Student**: Modify student information
8. **Delete Student**: Remove a student and their attendance records

## Example Workflow

1. Add students to the system
2. Mark daily attendance for students
3. View individual student attendance statistics
4. Generate reports for specific dates
5. Update student information as needed

## Notes

- Student IDs must be unique
- Date format: YYYY-MM-DD (e.g., 2024-01-15)
- Attendance statuses: Present, Absent, Late
- All data is stored in memory (not persisted to disk)

## Future Enhancements

- Database integration for data persistence
- Export reports to CSV/PDF
- Email notifications
- User authentication
- Web-based interface
- Batch attendance marking

## Author

Created as a Java project for student attendance management.
#
