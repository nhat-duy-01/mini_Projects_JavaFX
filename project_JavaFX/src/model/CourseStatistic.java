package model;

public class CourseStatistic {

    private String courseId;
    private String courseName;
    private int totalStudents;

    public CourseStatistic() {
    }

    public CourseStatistic(String courseId,
                           String courseName,
                           int totalStudents) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.totalStudents = totalStudents;
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

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

}