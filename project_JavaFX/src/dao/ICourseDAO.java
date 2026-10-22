package dao;

import java.util.ArrayList;
import model.Course;

public interface ICourseDAO {

    ArrayList<Course> getAllCourses();

    Course getCourseById(String courseId);

    boolean insertCourse(Course course);

    boolean updateCourse(Course course);

    boolean deleteCourse(String courseId);

    int getCurrentStudents(String courseId);

    boolean updateCurrentStudents(String courseId, int currentStudents);

}