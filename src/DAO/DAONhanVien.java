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
        String sql = "SELECT * FROM NhanVien";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                NhanVien nv = new NhanVien();
                nv.setManv(rs.getInt("manv"));
                nv.setTennv(rs.getString("tennv"));
                nv.setChucvu(rs.getString("chucvu"));
                nv.setSodienthoai(rs.getString("sodienthoai"));
                nv.setDiachi(rs.getString("diachi"));
                nv.setUsername(rs.getString("username"));
                nv.setPass(rs.getString("pass"));
                nhanViens.add(nv);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nhanViens;
    }

    public void insertNhanVien(NhanVien nhanVien ){
        String sql = "INSERT INTO NhanVien (tennv, chucvu, sodienthoai, diachi, username, pass) VALUES(?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nhanVien.getTennv());
            preparedStatement.setString(2, nhanVien.getChucvu());
            preparedStatement.setString(3, nhanVien.getSodienthoai());
            preparedStatement.setString(4, nhanVien.getDiachi());
            preparedStatement.setString(5, nhanVien.getUsername());
            preparedStatement.setString(6, nhanVien.getPass());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateNhanVien(NhanVien nhanVien){
        String sql = "UPDATE NhanVien SET tennv=?, chucvu=?, sodienthoai=?, diachi=?, username=?, pass=? WHERE manv=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nhanVien.getTennv());
            preparedStatement.setString(2, nhanVien.getChucvu());
            preparedStatement.setString(3, nhanVien.getSodienthoai());
            preparedStatement.setString(4, nhanVien.getDiachi());
            preparedStatement.setString(5, nhanVien.getUsername());
            preparedStatement.setString(6, nhanVien.getPass());
            preparedStatement.setInt(7, nhanVien.getManv());

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

    public boolean login(String username, String pass){
        String sql = "SELECT * FROM NhanVien WHERE username = ? AND pass = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public NhanVien getNhanVien(String username, String pass){
        NhanVien nhanVien = new NhanVien();
        String sql = "SELECT * FROM NhanVien WHERE username = ? and pass = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                nhanVien.setManv(rs.getInt("manv"));
                nhanVien.setTennv(rs.getString("tennv"));
                nhanVien.setChucvu(rs.getString("chucvu"));
                nhanVien.setSodienthoai(rs.getString("sodienthoai"));
                nhanVien.setDiachi(rs.getString("diachi"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nhanVien;
    }

}
