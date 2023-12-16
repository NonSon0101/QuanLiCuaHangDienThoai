package DAO;

import model.DienThoai;
import model.ViewDienThoai;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DAODienThoai {
    Connection conn;
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ViewDienThoai> getAllDienThoai() {
        List<ViewDienThoai> dienThoais = new ArrayList<ViewDienThoai>();
        String sql = "SELECT DienThoai.madt, DienThoai.tendt, HangSanXuat.tenhsx, DienThoai.soluong, DienThoai.giaban, DienThoai.phantramgiam FROM DienThoai JOIN HangSanXuat ON DienThoai.mansx = HangSanXuat.mahsx;";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ViewDienThoai dt = new ViewDienThoai();
                dt.setMadt(rs.getInt("madt"));
                dt.setTendt(rs.getString("tendt"));
                dt.setNhasx(rs.getString("tenhsx"));
                dt.setSoluong(rs.getInt("soluong"));
                dt.setGiaban(rs.getInt("giaban"));
                dt.setPhantramgiam(rs.getInt("phantramgiam"));
                dienThoais.add(dt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dienThoais;
    }

    public void insertDienThoai(DienThoai dienThoai){
        String sql = "INSERT INTO QuanLiCuaHangDiDong.DienThoai (tendt, mansx, soluong, giaban, phantramgiam) VALUES(?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, dienThoai.getTendt());
            preparedStatement.setInt(2, dienThoai.getMansx());
            preparedStatement.setInt(3, dienThoai.getSoluong());
            preparedStatement.setInt(4, dienThoai.getGiaban());
            preparedStatement.setInt(5, dienThoai.getPhantramgiam());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateDienThoai(DienThoai dienThoai){
        String sql = "UPDATE DienThoai SET tendt= ?, mansx= ?, soluong= ?, giaban= ?, phantramgiam= ? WHERE madt=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, dienThoai.getTendt());
            preparedStatement.setInt(2, dienThoai.getMansx());
            preparedStatement.setInt(3, dienThoai.getSoluong());
            preparedStatement.setInt(4, dienThoai.getGiaban());
            preparedStatement.setInt(5, dienThoai.getPhantramgiam());
            preparedStatement.setInt(6, dienThoai.getMadt());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteDienThoai(int madt){
        String sql = "DELETE FROM QuanLiCuaHangDiDong.DienThoai WHERE madt=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, madt);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args){

    }


}


