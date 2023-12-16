package DAO;

import model.HangSanXuat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOHangSanXuat {
    Connection conn;
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HangSanXuat> getAllHangSanXuat(){
        List<HangSanXuat> hangSanXuats = new ArrayList<HangSanXuat>();
        String sql = "SELECT * FROM HangSanXuat";

        try {
            PreparedStatement preparedStatement  = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()){
                HangSanXuat hsx = new HangSanXuat();
                hsx.setMahsx(rs.getInt("mahsx"));
                hsx.setTenhsx(rs.getString("tenhsx"));
                hangSanXuats.add(hsx);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hangSanXuats;
    }
    public int getMaHangSanXuat(String tenhsx){
        List<HangSanXuat> hangSanXuats = new ArrayList<HangSanXuat>();
        String sql = "SELECT mahsx FROM HangSanXuat WHERE tenhsx = ? ";
        int mahsx = 0;
        try {
            PreparedStatement preparedStatement  = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenhsx);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                mahsx = (rs.getInt("mahsx"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mahsx;
    }
    public void inserHangSanXuat(String tenhsx){
        String sql = "INSERT INTO HangSanXuat(tenhsx) VALUES (?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenhsx);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateHangSanXuat(int mahsx, String tenhsx){
        String sql = "UPDATE HangSanXuat Set tenhsx=? WHERE mahsx=?;";
        try {
            PreparedStatement  preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenhsx);
            preparedStatement.setInt(2, mahsx);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){
        DAOHangSanXuat hsx = new DAOHangSanXuat();
        int a = hsx.getMaHangSanXuat("Iphone");

        System.out.println(a);
    }
}
