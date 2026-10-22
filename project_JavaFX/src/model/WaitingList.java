package model;

import java.sql.Timestamp;

public class WaitingList {

    private int waitingId;
    private String studentId;
    private String courseId;
    private Timestamp registeredDate;
    private int priority;

    public WaitingList() {
    }

    public WaitingList(int waitingId, String studentId, String courseId, Timestamp registeredDate, int priority) {
        this.waitingId = waitingId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.registeredDate = registeredDate;
        this.priority = priority;
    }

    public int getWaitingId() {
        return waitingId;
    }

    public void setWaitingId(int waitingId) {
        this.waitingId = waitingId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Timestamp getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Timestamp registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}