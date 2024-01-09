package DAL;

import model.ChiTietHoaDonDienThoai;
import model.muadt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp DAOMuaDienThoai thực hiện các thao tác truy vấn dữ liệu liên quan đến việc mua điện thoại trong hóa đơn.
 * Các method trong class này được gọi bởi ChiTietHoaDonService.
 */
public class DAOMuaDienThoai {
    Connection conn;

    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Truy xuất danh sách tất cả mua điện thoại từ cơ sở dữ liệu.
     * @return Danh sách các đối tượng muadt chứa thông tin mua điện thoại.
     */
    public List<muadt> getAllMuadt(){
        List<muadt> muadts = new ArrayList<muadt>();
        String sql = "SELECT * FROM muadt;";
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

    /**
     * Truy xuất danh sách chi tiết hóa đơn điện thoại từ cơ sở dữ liệu dựa trên mã hóa đơn.
     * @param mahd Mã hóa đơn cần tìm chi tiết.
     * @return Danh sách các đối tượng ChiTietHoaDonDienThoai chứa thông tin chi tiết hóa đơn điện thoại.
     */
    public List<ChiTietHoaDonDienThoai> getChiTietHoaDonDienThoai(int mahd){
        List<ChiTietHoaDonDienThoai> chiTietHoaDonDienThoais = new ArrayList<ChiTietHoaDonDienThoai>();
        String sql = "SELECT DienThoai.madt, DienThoai.tendt, DienThoai.giaban, DienThoai.phantramgiam, " +
                "muadt.soluong FROM DienThoai JOIN muadt ON DienThoai.madt = muadt.madt WHERE muadt.mahd = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mahd);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                ChiTietHoaDonDienThoai cthd = new ChiTietHoaDonDienThoai();
                cthd.setMadt(rs.getInt("madt"));
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

    /**
     * Thêm mới thông tin mua điện thoại vào cơ sở dữ liệu.
     * @param muadt Thông tin mua điện thoại cần thêm mới.
     */
    public void insertMuadt(muadt muadt){
        String sql = "INSERT INTO muadt(mahd, madt, soluong) VALUES (?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, muadt.getMahd());
            preparedStatement.setInt(2, muadt.getMadt());
            preparedStatement.setInt(3, muadt.getSoluong());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cập nhật thông tin mua điện thoại trong cơ sở dữ liệu.
     * @param mahd Mã hóa đơn cần cập nhật.
     * @param madt Mã điện thoại cần cập nhật.
     * @param soluong Số lượng cần cập nhật.
     */
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

    /**
     * Xóa thông tin mua điện thoại dựa trên mã điện thoại.
     * @param madt Mã điện thoại cần xóa.
     */
    public void deleteSanPham(int madt){
        String sql = "DELETE FROM muadt WHERE madt = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, madt);

            int rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Xóa thông tin mua điện thoại dựa trên mã hóa đơn.
     * @param mahd Mã hóa đơn cần xóa.
     */
    public void deleteSPHoaDon(int mahd){
        String sql = "DELETE FROM muadt WHERE mahd = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mahd);

            int rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
