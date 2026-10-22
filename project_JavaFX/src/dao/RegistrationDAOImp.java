package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Registration;
import util.DBContext;

public class RegistrationDAOImp implements IRegistrationDAO {
    private DBContext db = new DBContext();

    @Override
    public ArrayList<Registration> getAllRegistrations() {
        ArrayList<Registration> list = new ArrayList<>();
        String sql = "SELECT * FROM Registrations";

        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Registration r = new Registration();

                r.setRegistrationId(rs.getInt("registrationId"));
                r.setStudentId(rs.getString("studentId"));
                r.setCourseId(rs.getString("courseId"));
                r.setRegistrationDate(rs.getTimestamp("registrationDate"));
                r.setStatus(rs.getString("status"));

                list.add(r);
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
    public ArrayList<Registration> getRegistrationsByStudent(String studentId) {
        ArrayList<Registration> list = new ArrayList<>();
        String sql = "SELECT * FROM Registrations WHERE studentId=?";

        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setString(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Registration r = new Registration();

                r.setRegistrationId(rs.getInt("registrationId"));
                r.setStudentId(rs.getString("studentId"));
                r.setCourseId(rs.getString("courseId"));
                r.setRegistrationDate(rs.getTimestamp("registrationDate"));
                r.setStatus(rs.getString("status"));

                list.add(r);
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
    public Registration getRegistration(String studentId, String courseId) {

        String sql = "SELECT * FROM Registrations WHERE studentId=? AND courseId=?";

        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, studentId);
            ps.setString(2, courseId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Registration r = new Registration();

                r.setRegistrationId(rs.getInt("registrationId"));
                r.setStudentId(rs.getString("studentId"));
                r.setCourseId(rs.getString("courseId"));
                r.setRegistrationDate(rs.getTimestamp("registrationDate"));
                r.setStatus(rs.getString("status"));

                rs.close();
                ps.close();
                cn.close();
                return r;
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
    public boolean insertRegistration(Registration registration) {
        String sql = "INSERT INTO Registrations(studentId,courseId,status) VALUES(?,?,?)";

        try {

            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, registration.getStudentId());
            ps.setString(2, registration.getCourseId());
            ps.setString(3, registration.getStatus());

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
    public boolean updateRegistrationStatus(int registrationId, String status) {
        String sql = "UPDATE Registrations SET status=? WHERE registrationId=?";
        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, registrationId);

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
    public boolean deleteRegistration(String studentId, String courseId) {
        String sql = "DELETE FROM Registrations WHERE studentId=? AND courseId=?";
        try {

            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, studentId);
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
