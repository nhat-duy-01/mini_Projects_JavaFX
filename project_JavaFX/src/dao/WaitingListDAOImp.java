package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.WaitingList;
import util.DBContext;

public class WaitingListDAOImp implements IWaitingListDAO {

    private DBContext db = new DBContext();

    @Override
    public ArrayList<WaitingList> getAllWaitingList() {
        ArrayList<WaitingList> list = new ArrayList<>();
        String sql = "SELECT * FROM WaitingList ORDER BY courseId, priority";
        try {

            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                WaitingList w = new WaitingList();

                w.setWaitingId(rs.getInt("waitingId"));
                w.setStudentId(rs.getString("studentId"));
                w.setCourseId(rs.getString("courseId"));
                w.setRegisteredDate(rs.getTimestamp("registeredDate"));
                w.setPriority(rs.getInt("priority"));

                list.add(w);

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
    public ArrayList<WaitingList> getWaitingListByCourse(String courseId) {
        ArrayList<WaitingList> list = new ArrayList<>();

        String sql = "SELECT * FROM WaitingList WHERE courseId=? ORDER BY priority";
        try {

            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, courseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                WaitingList w = new WaitingList();

                w.setWaitingId(rs.getInt("waitingId"));
                w.setStudentId(rs.getString("studentId"));
                w.setCourseId(rs.getString("courseId"));
                w.setRegisteredDate(rs.getTimestamp("registeredDate"));
                w.setPriority(rs.getInt("priority"));

                list.add(w);

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
    public WaitingList getWaitingStudent(String studentId, String courseId) {

        String sql = "SELECT * FROM WaitingList WHERE studentId=? AND courseId=?";

        try {

            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, studentId);
            ps.setString(2, courseId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                WaitingList w = new WaitingList();

                w.setWaitingId(rs.getInt("waitingId"));
                w.setStudentId(rs.getString("studentId"));
                w.setCourseId(rs.getString("courseId"));
                w.setRegisteredDate(rs.getTimestamp("registeredDate"));
                w.setPriority(rs.getInt("priority"));

                rs.close();
                ps.close();
                cn.close();

                return w;
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
    public boolean insertWaiting(WaitingList waiting) {

        String sql = "INSERT INTO WaitingList(studentId,courseId,priority) VALUES(?,?,?)";
        try {
            Connection cn = db.getConnection();

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, waiting.getStudentId());
            ps.setString(2, waiting.getCourseId());
            ps.setInt(3, waiting.getPriority());

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
    public boolean deleteWaiting(String studentId, String courseId) {
        String sql = "DELETE FROM WaitingList WHERE studentId=? AND courseId=?";
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
    @Override
    public int getNextPriority(String courseId) {

        String sql = "SELECT MAX(priority) AS maxPriority FROM WaitingList WHERE courseId=?";
        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int priority = rs.getInt("maxPriority") + 1;
                rs.close();
                ps.close();
                cn.close();

                return priority;
            }
            rs.close();
            ps.close();
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}