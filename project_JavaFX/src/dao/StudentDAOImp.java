package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Student;
import util.DBContext;

public class StudentDAOImp implements IStudentDAO {
    private DBContext db = new DBContext();

    @Override
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getString("studentId"));
                s.setFullName(rs.getString("fullName"));
                s.setMajor(rs.getString("major"));

                s.setSemester(rs.getInt("semester"));
                list.add(s);
            }
            rs.close();
            ps.close();
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public Student getStudentById(String studentId) {
        String sql = "SELECT * FROM Students WHERE studentId = ?";
        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                Student s = new Student();
                s.setStudentId(rs.getString("studentId"));
                s.setFullName(rs.getString("fullName"));
                s.setMajor(rs.getString("major"));
                s.setSemester(rs.getInt("semester"));

                rs.close();
                ps.close();
                cn.close();
                return s;
            }
            rs.close();
            ps.close();
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertStudent(Student student) {

        String sql = "INSERT INTO Students VALUES(?,?,?,?)";
        try {

            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, student.getStudentId());
            ps.setString(2, student.getFullName());
            ps.setString(3, student.getMajor());
            ps.setInt(4, student.getSemester());

            int row = ps.executeUpdate();
            ps.close();
            cn.close();

            return row > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        String sql = "UPDATE Students SET fullName=?, major=?, semester=? WHERE studentId=?";

        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, student.getFullName());
            ps.setString(2, student.getMajor());
            ps.setInt(3, student.getSemester());
            ps.setString(4, student.getStudentId());
            
            int row = ps.executeUpdate();
            ps.close();
            cn.close();

            return row > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM Students WHERE studentId=?";

        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, studentId);
            int row = ps.executeUpdate();

            ps.close();
            cn.close();

            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}