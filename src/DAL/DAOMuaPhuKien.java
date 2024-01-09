package DAL;

import model.ChiTietHoaDonPhuKien;
import model.muapk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp DAOMuaPhuKien thực hiện các thao tác truy vấn dữ liệu liên quan đến việc mua phụ kiện trong hóa đơn.
 * Các method trong class này được gọi bởi ChiTietHoaDonService.
 */
public class DAOMuaPhuKien {
    Connection conn;

    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Truy xuất danh sách chi tiết hóa đơn phụ kiện từ cơ sở dữ liệu dựa trên mã hóa đơn.
     * @param mahd Mã hóa đơn cần tìm chi tiết.
     * @return Danh sách các đối tượng ChiTietHoaDonPhuKien chứa thông tin chi tiết hóa đơn phụ kiện.
     */
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

    /**
     * Thêm mới thông tin mua phụ kiện vào cơ sở dữ liệu.
     * @param muapk Thông tin mua phụ kiện cần thêm mới.
     */
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
    /**
     * Xóa thông tin mua phụ kiện dựa trên mã phụ kiện.
     * @param mapk Mã phụ kiện cần xóa.
     */
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
    /**
     * Xóa thông tin mua phụ kiện dựa trên mã hóa đơn.
     * @param mahd Mã hóa đơn cần xóa.
     */
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
