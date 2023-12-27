package DAO;


import model.ChiTietHoaDonPhuKien;
import model.muapk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DAOMuaPhuKien {
    Connection conn;
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<muapk> getAllmuapk(){
        List<muapk> muapks = new ArrayList<muapk>();
        String sql = "SELECT * FORM muapk;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            muapk mpk = new muapk();

            while (rs.next()){
                mpk.setMapk(rs.getInt("mapk"));
                mpk.setMahd(rs.getInt("mahd"));
                mpk.setSoluong(rs.getInt("soluong"));
            }

            muapks.add(mpk);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return muapks;
    }

    public List<ChiTietHoaDonPhuKien> getChiTietHoaDonPhuKien(int mahd){
        List<ChiTietHoaDonPhuKien> chiTietHoaDonPhuKiens = new ArrayList<ChiTietHoaDonPhuKien>();
        String sql = "SELECT PhuKien.mapk,  PhuKien.tenpk , PhuKien.giaban, PhuKien.phantramgiam, muapk.soluong " +
                "FROM PhuKien JOIN muapk ON PhuKien.mapk  = muapk.mapk WHERE muapk.mahd = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mahd);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                ChiTietHoaDonPhuKien cthd = new ChiTietHoaDonPhuKien();
                cthd.setMapk(rs.getInt("mapk"));
                cthd.setTenpk(rs.getString("tenpk"));
                cthd.setGiaban(rs.getInt("giaban"));
                cthd.setPhamtramgiam(rs.getInt("phantramgiam"));
                cthd.setSoluong(rs.getInt("soluong"));
                chiTietHoaDonPhuKiens.add(cthd);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return chiTietHoaDonPhuKiens;
    }

    public void insertMuapk(muapk muapk){
        String sql = "INSERT INTO muapk(mahd, mapk, soluong) VALUES (?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, muapk.getMahd());
            preparedStatement.setInt(2, muapk.getMapk());
            preparedStatement.setInt(3, muapk.getSoluong());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updatemuapk(int mahd, int mapk, int soluong) {
        String sql = "UPDATE muapk set soluong=? WHERE mapk=? AND madt=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, soluong);
            preparedStatement.setInt(2, mahd);
            preparedStatement.setInt(3, mapk);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSanPham(int mapk){
        String sql = "DELETE FROM muapk WHERE mapk = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mapk);

            int rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSPHoaDon(int mahd){
        String sql = "DELETE FROM muapk WHERE mahd = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mahd);

            int rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
