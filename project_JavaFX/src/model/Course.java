package model;

public class Course {

    private String courseId;
    private String courseName;
    private int credits;
    private String schedule;
    private String instructor;
    private int maxStudents;
    private int currentStudents;

    public Course() {
    }

    public Course(String courseId, String courseName, int credits,
            String schedule, String instructor,
            int maxStudents, int currentStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.schedule = schedule;
        this.instructor = instructor;
        this.maxStudents = maxStudents;
        this.currentStudents = currentStudents;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    public void setCurrentStudents(int currentStudents) {
        this.currentStudents = currentStudents;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId
                + "\nCourse Name: " + courseName
                + "\nCredits: " + credits
                + "\nSchedule: " + schedule
                + "\nInstructor: " + instructor
                + "\nMax Students: " + maxStudents
                + "\nCurrent Students: " + currentStudents;
    }
}
