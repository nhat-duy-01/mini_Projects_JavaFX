package dao;

import java.util.ArrayList;
import model.Student;

public interface IStudentDAO {

    ArrayList<Student> getAllStudents();

    Student getStudentById(String studentId);

    boolean insertStudent(Student student);

    boolean updateStudent(Student student);

    boolean deleteStudent(String studentId);

}