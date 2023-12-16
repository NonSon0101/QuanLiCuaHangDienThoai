package DAO;

import model.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAONhanVien {
    Connection conn;
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<NhanVien> getAllNhanVien(){
        List<NhanVien> nhanViens = new ArrayList<NhanVien>();
        String sql = "SELECT * FROM Nhanvien";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            NhanVien nv = new NhanVien();
            while (rs.next()){
                nv.setManv(rs.getInt("manv"));
                nv.setTennv(rs.getString("tennv"));
                nv.setChucvu(rs.getString("chucvu"));
                nv.setSodienthoai(rs.getString("sodienthoai"));
                nv.setDiachi(rs.getString("diachi"));
            }
            nhanViens.add(nv);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nhanViens;
    }

    public void insertNhanVien(String tennv, String chucvu, String sodienthoai, String diachi ){
        String sql = "INSERT INTO NhanVien (tennv, chucvu, sodienthoai, diachi) VALUES(?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tennv);
            preparedStatement.setString(2, chucvu);
            preparedStatement.setString(3, sodienthoai);
            preparedStatement.setString(4, diachi);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateNhanVien(int manv, String tennv, String chucvu, String sodienthoai, String diachi){
        String sql = "UPDATE NhanVien SET tennv=?, chucvu=?, sodienthoai=?, diachi=? WHERE manv=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tennv);
            preparedStatement.setString(2, chucvu);
            preparedStatement.setString(3, sodienthoai);
            preparedStatement.setString(4, diachi);
            preparedStatement.setInt(5, manv);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNhanVien(int manv){
        String sql = "DELETE FROM NhanVien WHERE manv=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, manv);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
