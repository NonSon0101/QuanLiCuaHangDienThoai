package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBCConnection {
    public static Connection getJDBCConnection() throws ClassNotFoundException {
        final String url = "jdbc:mysql://localhost:3306/QuanLiCuaHangDiDong";
        final String user = "root";
        final String password = "Duy010203@";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return  DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            // Handle the exception, such as logging an error message
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Connection conn = getJDBCConnection();

        if(conn != null)    {
            System.out.println("thanh cong");
        }
        else{
            System.out.println("That bai");
        }

    }
}
