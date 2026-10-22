package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    private static final String url ="jdbc:sqlserver://localhost:1433;databaseName=CourseRegistrationDB;encrypt=true;trustServerCertificate=true;";
    private static final String user = "sa";
    private static final String pass = "sa";

    public Connection getConnection() {

        Connection cn = null;

        try {
            cn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Connect failed!");
            e.printStackTrace();
        }

        return cn;
    }

}