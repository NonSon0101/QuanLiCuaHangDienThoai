package DAO;

import model.LoaiKhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOLoaiKhachHang {
    Connection conn;
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LoaiKhachHang> getAllLoaiKhachHang(){
        List<LoaiKhachHang> loaiKhachHangs = new ArrayList<LoaiKhachHang>();
        String sql = "SELECT * FROM LoaiKhachHang;";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                LoaiKhachHang lkh = new LoaiKhachHang();
                lkh.setMaloaikh(rs.getInt("maloaikh"));
                lkh.setTenloaikh(rs.getString("tenloaikh"));
                loaiKhachHangs.add(lkh);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loaiKhachHangs;
    }

    public void insertLoaiKhachHang(String tenloaikh){
        String sql = "INSERT INTO LoaiKhachHang(tenloaikh) VALUES (?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenloaikh);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLoaiKhachHang(int maloaikh, String tenloaikh){
        String sql = "UPDATE LoaiKhachHang SET tenloaikh=? WHERE maloaikh=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenloaikh);
            preparedStatement.setInt(2, maloaikh);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findMaKhachHang(String tenloaikh){
        int maloaikh = 0;
        String sql = "SELECT maloaikh FROM LoaiKhachHang WHERE tenloaikh = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenloaikh);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                maloaikh = rs.getInt("maloaikh");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maloaikh;
    }
}
