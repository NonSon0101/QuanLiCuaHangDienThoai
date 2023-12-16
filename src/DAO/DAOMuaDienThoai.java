package DAO;
import model.ChiTietHoaDonDienThoai;
import model.muadt;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
public class DAOMuaDienThoai {
    Connection conn;
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<muadt> getAllMuadt(){
        List<muadt> muadts = new ArrayList<muadt>();
        String sql = "SELECT * FORM muadt;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();


           while (rs.next()){
               muadt mdt = new muadt();
               mdt.setMadt(rs.getInt("madt"));
               mdt.setMahd(rs.getInt("mahd"));
               mdt.setSoluong(rs.getInt("soluong"));
               muadts.add(mdt);
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return muadts;
    }

    public List<ChiTietHoaDonDienThoai> getChiTietHoaDonDienThoai(){
        List<ChiTietHoaDonDienThoai> chiTietHoaDonDienThoais = new ArrayList<ChiTietHoaDonDienThoai>();
        String sql = "SELECT DienThoai.tendt, DienThoai.giaban, DienThoai.phantramgiam, muadt.soluong FROM DienThoai JOIN muadt ON DienThoai.madt = muadt.madt;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                ChiTietHoaDonDienThoai cthd = new ChiTietHoaDonDienThoai();
                cthd.setTendt(rs.getString("tendt"));
                cthd.setGiaban(rs.getInt("giaban"));
                cthd.setPhamtramgiam(rs.getInt("phantramgiam"));
                cthd.setSoluong(rs.getInt("soluong"));
                chiTietHoaDonDienThoais.add(cthd);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return chiTietHoaDonDienThoais;
    }

    public void insertMuadt(int mahd, int madt, int soluong){
        String sql = "INSERT INTO muadt(mahd, madt, soluong) VALUES (?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mahd);
            preparedStatement.setInt(2, madt);
            preparedStatement.setInt(3, soluong);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateMuadt(int mahd, int madt, int soluong){
        String sql = "UPDATE muadt set soluong=? WHERE mahd=? AND madt=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, soluong);
            preparedStatement.setInt(2, mahd);
            preparedStatement.setInt(3, madt);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
