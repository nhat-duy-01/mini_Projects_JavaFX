package model;

public class Student {

    private String studentId;
    private String fullName;
    private String major;
    private int semester;

    public Student() {
    }

    public Student(String studentId, String fullName,
                   String major, int semester) {

        this.studentId = studentId;
        this.fullName = fullName;
        this.major = major;
        this.semester = semester;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId
                + "\nFull Name: " + fullName
                + "\nMajor: " + major
                + "\nSemester: " + semester;
    }
}