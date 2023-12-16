package DAO;

import model.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOKhachHang {
    Connection conn;
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<KhachHang> getAllKhachHang() throws SQLException {
        List<KhachHang> khachHangs = new ArrayList<KhachHang>();
        String sql = "SELECT * FORM KhachHang";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            KhachHang kh = new KhachHang();

            while (rs.next()){
                kh.setMakh(rs.getInt("makh"));
                kh.setHokh(rs.getString("hokh"));
                kh.setTenkh(rs.getString("tenkh"));
                kh.setLoaikh(rs.getInt("loaikh"));
                kh.setSodienthoai(rs.getString("sodienthoai"));
                kh.setDiachi(rs.getString("diachi"));
            }
            khachHangs.add(kh);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return khachHangs;
    }

    public void insertKhachHang (String hokh, String tenkh, int loaikh, String sodienthoai, String diachi){
        String sql = "INSERT INTO KhachHang (hokh, tenkh, loaikh, sodienthoai, diachi) VALUES(?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, hokh);
            preparedStatement.setString(2, tenkh);
            preparedStatement.setInt(3, loaikh);
            preparedStatement.setString(4, sodienthoai);
            preparedStatement.setString(5, diachi);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateKhachHang (int makh, String hokh, String tenkh, int loaikh, String sodienthoai, String diachi){
        String sql = "UPDATE KhachHang SET hokh=?, tenkh=?, loaikh=?, sodienthoai=?, diachi=? WHERE makh=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, hokh);
            preparedStatement.setString(2, tenkh);
            preparedStatement.setInt(3, loaikh);
            preparedStatement.setString(4, sodienthoai);
            preparedStatement.setString(5, diachi);
            preparedStatement.setInt(6, makh);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteKhachHang (int makh){
        String sql = "DELETE FROM KhachHang WHERE makh=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, makh);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
