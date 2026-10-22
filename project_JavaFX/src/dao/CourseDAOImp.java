package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Course;
import util.DBContext;

public class CourseDAOImp implements ICourseDAO {

    private DBContext db = new DBContext();

    @Override
    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Courses";

        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course c = new Course();
                c.setCourseId(rs.getString("courseId"));
                c.setCourseName(rs.getString("courseName"));
                c.setCredits(rs.getInt("credits"));
                c.setSchedule(rs.getString("schedule"));
                c.setInstructor(rs.getString("instructor"));
                c.setMaxStudents(rs.getInt("maxStudents"));
                c.setCurrentStudents(rs.getInt("currentStudents"));
                list.add(c);

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
    public Course getCourseById(String courseId) {
        String sql = "SELECT * FROM Courses WHERE courseId = ?";
        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                Course c = new Course();
                c.setCourseId(rs.getString("courseId"));
                c.setCourseName(rs.getString("courseName"));
                c.setCredits(rs.getInt("credits"));
                c.setSchedule(rs.getString("schedule"));
                c.setInstructor(rs.getString("instructor"));
                c.setMaxStudents(rs.getInt("maxStudents"));
                c.setCurrentStudents(rs.getInt("currentStudents"));

                rs.close();
                ps.close();
                cn.close();
                return c;
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
    public boolean insertCourse(Course course) {
        String sql = "INSERT INTO Courses VALUES(?,?,?,?,?,?,?)";
        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, course.getCourseId());
            ps.setString(2, course.getCourseName());
            ps.setInt(3, course.getCredits());
            ps.setString(4, course.getSchedule());
            ps.setString(5, course.getInstructor());
            ps.setInt(6, course.getMaxStudents());
            ps.setInt(7, course.getCurrentStudents());

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
    public boolean updateCourse(Course course) {

        String sql = "UPDATE Courses SET courseName=?, credits=?,schedule=?,instructor=?,maxStudents=?,currentStudents=?WHERE courseId=?";

        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getCredits());
            ps.setString(3, course.getSchedule());
            ps.setString(4, course.getInstructor());
            ps.setInt(5, course.getMaxStudents());
            ps.setInt(6, course.getCurrentStudents());
            ps.setString(7, course.getCourseId());

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
    public boolean deleteCourse(String courseId) {
        String sql = "DELETE FROM Courses WHERE courseId=?";

        try {

            Connection cn = db.getConnection();

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, courseId);
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
    public int getCurrentStudents(String courseId) {
        String sql = "SELECT currentStudents FROM Courses WHERE courseId=?";
        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int total = rs.getInt("currentStudents");
                rs.close();
                ps.close();
                cn.close();

                return total;
            }
            rs.close();
            ps.close();
            cn.close();
        } catch (Exception e) {

            e.printStackTrace();

        }
        return 0;

    }

    @Override
    public boolean updateCurrentStudents(String courseId, int currentStudents) {
        String sql = "UPDATE Courses SET currentStudents=? WHERE courseId=?";
        try {

            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, currentStudents);
            ps.setString(2, courseId);
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

