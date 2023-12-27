package DAO;
import GUI.BanHang;
import model.HoaDon;
import model.ViewHoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DAOHoaDon {
    Connection conn;
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ViewHoaDon> getAllHoaDon(){
        List<ViewHoaDon> hoaDons = new ArrayList<ViewHoaDon>();
        String sql = "SELECT HoaDon.mahd, KhachHang.tenkh, NhanVien.tennv, HoaDon.ngaylap FROM HoaDon JOIN KhachHang ON HoaDon.makh = KhachHang.makh JOIN NhanVien ON HoaDon.manv = NhanVien.manv ;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                ViewHoaDon hd = new ViewHoaDon();
                hd.setMahd(rs.getInt("mahd"));
                hd.setKhachhang(rs.getString("tenkh"));
                hd.setNhanvien(rs.getString("tennv"));
                hd.setNgaylap(rs.getDate("ngaylap"));
                hoaDons.add(hd);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hoaDons;
    }

    public int getMaHoaDonCuoiCung(){
        int mahd = 0;
        String sql = "SELECT * FROM HoaDon ORDER BY mahd DESC LIMIT 1;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                mahd = rs.getInt("mahd");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mahd;
    }
    public void insertHoadon(HoaDon hoaDon){
        String sql = "INSERT INTO HoaDon (makh, manv, ngaylap) VALUES(?, ?, ?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, hoaDon.getMakh());
            preparedStatement.setInt(2, hoaDon.getManv());
            preparedStatement.setDate(3, hoaDon.getNgaylap());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateHoaDon(HoaDon hoaDon){
        String sql = "UPDATE HoaDon SET makh=?, manv=?, ngaylap=? WHERE mahd=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, hoaDon.getMahd());
            preparedStatement.setInt(2, hoaDon.getManv());
            preparedStatement.setDate(3, hoaDon.getNgaylap());
            preparedStatement.setInt(4, hoaDon.getMahd());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deleteHoaDon(int mahd){
        String sql = "DELETE FROM HoaDon WHERE mahd=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mahd);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
