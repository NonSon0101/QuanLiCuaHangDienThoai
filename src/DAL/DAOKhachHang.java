package DAL;

import model.KhachHang;
import model.ViewKhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp DAOKhachHang thực hiện các thao tác liên quan đến cơ sở dữ liệu cho đối tượng KhachHang.
 * Bao gồm truy xuất thông tin khách hàng, tìm kiếm khách hàng theo số điện thoại,
 * thêm mới, cập nhật thông tin khách hàng, xóa khách hàng.
 */
public class DAOKhachHang {
    Connection conn;

    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Truy xuất danh sách tất cả khách hàng từ cơ sở dữ liệu.
     * @return Danh sách các đối tượng ViewKhachHang chứa thông tin khách hàng.
     */
    public List<ViewKhachHang> getAllKhachHang() {
        List<ViewKhachHang> khachHangs = new ArrayList<ViewKhachHang>();
        String sql = "SELECT KhachHang.makh, KhachHang.hokh, KhachHang.tenkh, LoaiKhachHang.tenloaikh, KhachHang.sodienthoai, KhachHang.diachi " +
                "FROM  KhachHang JOIN LoaiKhachHang ON KhachHang.loaikh = LoaiKhachHang.maloaikh";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                ViewKhachHang kh = new ViewKhachHang();
                kh.setMakh(rs.getInt("makh"));
                kh.setHokh(rs.getString("hokh"));
                kh.setTenkh(rs.getString("tenkh"));
                kh.setLoaikh(rs.getString("tenloaikh"));
                kh.setSodienthoai(rs.getString("sodienthoai"));
                kh.setDiachi(rs.getString("diachi"));
                khachHangs.add(kh);

            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return khachHangs;
    }

    /**
     * Tìm kiếm khách hàng trong cơ sở dữ liệu dựa trên số điện thoại.
     * @param sodienthoai Số điện thoại của khách hàng cần tìm kiếm.
     * @return Đối tượng ViewKhachHang chứa thông tin khách hàng tìm thấy.
     */
    public ViewKhachHang findKhachHang(String sodienthoai){
        ViewKhachHang khachHang = new ViewKhachHang();
        String sql = "SELECT KhachHang.makh, KhachHang.hokh, KhachHang.tenkh, LoaiKhachHang.tenloaikh, KhachHang.sodienthoai, KhachHang.diachi " +
                "FROM KhachHang JOIN LoaiKhachHang ON KhachHang.loaikh = LoaiKhachHang.maloaikh WHERE KhachHang.sodienthoai = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, sodienthoai);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                khachHang.setMakh(rs.getInt("makh"));
                khachHang.setHokh(rs.getString("hokh"));
                khachHang.setTenkh(rs.getString("tenkh"));
                khachHang.setLoaikh(rs.getString("tenloaikh"));
                khachHang.setSodienthoai(rs.getString("sodienthoai"));
                khachHang.setDiachi(rs.getString("diachi"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return khachHang;
    }

    /**
     * Thêm mới một khách hàng vào cơ sở dữ liệu.
     * @param khachHang Đối tượng KhachHang chứa thông tin khách hàng cần thêm mới.
     */
    public void insertKhachHang (KhachHang khachHang){
        String sql = "INSERT INTO KhachHang (hokh, tenkh, loaikh, sodienthoai, diachi) VALUES(?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, khachHang.getHokh());
            preparedStatement.setString(2, khachHang.getTenkh());
            preparedStatement.setInt(3, khachHang.getLoaikh());
            preparedStatement.setString(4, khachHang.getSodienthoai());
            preparedStatement.setString(5, khachHang.getDiachi());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cập nhật thông tin một khách hàng trong cơ sở dữ liệu.
     * @param khachHang Đối tượng KhachHang chứa thông tin khách hàng cần cập nhật.
     */
    public void updateKhachHang (KhachHang khachHang){
        String sql = "UPDATE KhachHang SET hokh=?, tenkh=?, loaikh=?, sodienthoai=?, diachi=? WHERE makh=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, khachHang.getHokh());
            preparedStatement.setString(2, khachHang.getTenkh());
            preparedStatement.setInt(3, khachHang.getLoaikh());
            preparedStatement.setString(4, khachHang.getSodienthoai());
            preparedStatement.setString(5, khachHang.getDiachi());
            preparedStatement.setInt(6, khachHang.getMakh());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Xóa một khách hàng khỏi cơ sở dữ liệu dựa trên mã khách hàng.
     * @param makh Mã khách hàng cần xóa.
     */
    public void deleteKhachHang (int makh){
        String sql = "DELETE FROM KhachHang WHERE makh=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, makh);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
