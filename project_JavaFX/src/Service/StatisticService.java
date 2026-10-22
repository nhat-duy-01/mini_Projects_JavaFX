package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.CourseStatistic;
import util.DBContext;

public class StatisticService {
    private DBContext db = new DBContext();
    
    public ArrayList<CourseStatistic> getStatistic() {
        ArrayList<CourseStatistic> list = new ArrayList<>();


        String sql = "SELECT c.courseId,c.courseName,COUNT(r.studentId) "
                + "AS total FROM Courses c LEFT JOIN Registrations r "
                + "ON c.courseId=r.courseId AND r.status='REGISTERED'GROUP BY c.courseId,c.courseName";

        try {
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                CourseStatistic cs = new CourseStatistic();

                cs.setCourseId(rs.getString("courseId"));
                cs.setCourseName(rs.getString("courseName"));
                cs.setTotalStudents(rs.getInt("total"));
                list.add(cs);
            }

            rs.close();
            ps.close();
            cn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}