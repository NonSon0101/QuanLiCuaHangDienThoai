package DAO;

import model.LoaiPhuKien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DAOLoaiPhuKien {
    Connection conn;
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public List<LoaiPhuKien> getAllLoaiPhuKien(){
        List<LoaiPhuKien> loaiPhuKiens = new ArrayList<LoaiPhuKien>();
        String sql = "SELECT * FROM LoaiPhuKien;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                LoaiPhuKien lpk = new LoaiPhuKien();
                lpk.setMalpk(rs.getInt("malpk"));
                lpk.setTenlpk(rs.getString("tenlpk"));
                loaiPhuKiens.add(lpk);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loaiPhuKiens;
    }

    public int getMaLoaiPhuKien(String tenlpk){
        String sql = "SELECT malpk FROM LoaiPhuKien WHERE tenlpk = ?;";
        int malpk = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenlpk);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                malpk = rs.getInt("malpk");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return malpk;
    }

    public void inserLoaiPhuKien(String tenlpk){
        String sql = "INSERT INTO LoaiPhuKien(tenlpk) VALUES (?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenlpk);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLoaiPhuKien(int malpk, String tenlpk){
        String sql = "UPDATE LoaiPhuKien Set tenlpk=? WHERE malpk=?;";
        try {
            PreparedStatement  preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenlpk);
            preparedStatement.setInt(2, malpk);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
