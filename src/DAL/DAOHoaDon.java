package DAL;
import model.HoaDon;
import model.ViewHoaDon;
import model.HoanSanPham;
import model.ViewSanPhamHoan;

import javax.swing.text.View;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp DAOHoaDon thực hiện các thao tác liên quan đến cơ sở dữ liệu cho đối tượng HoaDon.
 * Bao gồm truy xuất thông tin hóa đơn, lấy mã hóa đơn cuối cùng,
 * thêm mới, cập nhật thông tin hóa đơn
 */
public class DAOHoaDon {
    Connection conn;

    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Truy xuất danh sách tất cả hóa đơn từ cơ sở dữ liệu.
     *
     * @return Danh sách các đối tượng ViewHoaDon chứa thông tin hóa đơn.
     */
    public List<ViewHoaDon> getAllHoaDon() {
        List<ViewHoaDon> hoaDons = new ArrayList<ViewHoaDon>();
        String sql = "SELECT HoaDon.mahd, KhachHang.tenkh, NhanVien.tennv, HoaDon.ngaylap FROM HoaDon JOIN KhachHang ON HoaDon.makh = KhachHang.makh JOIN NhanVien ON HoaDon.manv = NhanVien.manv ;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
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

    /**
     * Lấy mã hóa đơn cuối cùng từ cơ sở dữ liệu.
     *
     * @return Mã hóa đơn cuối cùng.
     */
    public int getMaHoaDonCuoiCung() {
        int mahd = 0;
        String sql = "SELECT * FROM HoaDon ORDER BY mahd DESC LIMIT 1;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                mahd = rs.getInt("mahd");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mahd;
    }

    /**
     * Thêm mới một hóa đơn vào cơ sở dữ liệu.
     *
     * @param hoaDon Đối tượng HoaDon chứa thông tin hóa đơn cần thêm mới.
     */
    public void insertHoadon(HoaDon hoaDon) {
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

    /**
     * Cập nhật thông tin một hóa đơn trong cơ sở dữ liệu.
     *
     * @param hoaDon Đối tượng HoaDon chứa thông tin hóa đơn cần cập nhật.
     */
    public void updateHoaDon(HoaDon hoaDon) {
        String sql = "UPDATE HoaDon SET makh=?, manv=?, ngaylap=? WHERE mahd=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, hoaDon.getMakh());
            preparedStatement.setInt(2, hoaDon.getManv());
            preparedStatement.setDate(3, hoaDon.getNgaylap());
            preparedStatement.setInt(4, hoaDon.getMahd());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ViewSanPhamHoan> getSanPhamHoan(int mahd){
        List<ViewSanPhamHoan> viewSanPhamHoans = new ArrayList<ViewSanPhamHoan>();
        String sql = "SELECT\n" +
                "    hsp.mahd,\n" +
                "    hsp.masanpham,\n" +
                "    hsp.soluong AS soluong,\n" +
                "    hsp.loaisanpham,\n" +
                "    COALESCE(dt.tendt, pk.tenpk) AS tensp\n" +
                "FROM\n" +
                "    HoanSanPham hsp\n" +
                "LEFT JOIN DienThoai dt ON hsp.loaisanpham = 'DT' AND hsp.masanpham = dt.madt\n" +
                "LEFT JOIN PhuKien pk ON hsp.loaisanpham = 'PK' AND hsp.masanpham = pk.mapk\n" +
                "WHERE hsp.mahd=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,mahd);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                ViewSanPhamHoan viewSanPhamHoan = new ViewSanPhamHoan();
                viewSanPhamHoan.setMahd(rs.getInt("mahd"));
                viewSanPhamHoan.setMasp(rs.getInt("masanpham"));
                viewSanPhamHoan.setLoaisanpham(rs.getString("loaisanpham"));
                viewSanPhamHoan.setSoluong(rs.getInt("soluong"));
                viewSanPhamHoan.setTensanpham(rs.getString("tensp"));
                viewSanPhamHoans.add(viewSanPhamHoan);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return viewSanPhamHoans;
    }

    public void insertSanPhamHoan(HoanSanPham hoanSanPham) {
        String sql = "INSERT INTO HoanSanPham\n" +
                "(mahd, masanpham, soluong, loaisanpham)\n" +
                "VALUES(?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, hoanSanPham.getMahd());
            preparedStatement.setInt(2, hoanSanPham.getMasp());
            preparedStatement.setInt(3, hoanSanPham.getSoluong());
            preparedStatement.setString(4, hoanSanPham.getLoaisanpham());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSanPhamHoan(int mahd, int masp){
        String sql = "DELETE FROM HoanSanPham WHERE mahd=? AND masanpham=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mahd);
            preparedStatement.setInt(2, masp);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

