package service;

import java.util.ArrayList;
import dao.CourseDAOImp;
import model.Course;

public class CourseService {

    private CourseDAOImp courseDAO;
    public CourseService() {
        courseDAO = new CourseDAOImp();

    }
    public ArrayList<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }


    public Course getCourseById(String id) {
        return courseDAO.getCourseById(id);
    }


    public boolean addCourse(Course course) {
        return courseDAO.insertCourse(course);
    }


    public boolean updateCourse(Course course) {
        return courseDAO.updateCourse(course);
    }


    public boolean deleteCourse(String courseId) {
        return courseDAO.deleteCourse(courseId);
    }


    public boolean isCourseFull(String courseId) {
        Course course = courseDAO.getCourseById(courseId);
        if(course == null) {
            return true;
        }
        return course.getCurrentStudents() >= course.getMaxStudents();
    }

    public void increaseStudent(String courseId) {
        Course course = courseDAO.getCourseById(courseId);
        int current = course.getCurrentStudents();
        courseDAO.updateCurrentStudents( courseId,current + 1);
    }
    
    public void decreaseStudent(String courseId) {
        Course course = courseDAO.getCourseById(courseId);
        int current = course.getCurrentStudents();
        if(current > 0) {
            courseDAO.updateCurrentStudents( courseId,current - 1);
        }
    }
}