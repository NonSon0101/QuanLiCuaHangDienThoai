package DAL;

import model.LoaiKhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp DAOLoaiKhachHang thực hiện các thao tác liên quan đến cơ sở dữ liệu cho đối tượng LoaiKhachHang.
 * Bao gồm truy xuất danh sách tất cả loại khách hàng và tìm kiếm mã loại khách hàng dựa trên tên loại khách hàng.
 */
public class DAOLoaiKhachHang {
    Connection conn;

    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Truy xuất danh sách tất cả loại khách hàng từ cơ sở dữ liệu.
     * @return Danh sách các đối tượng LoaiKhachHang chứa thông tin loại khách hàng.
     */
    public List<LoaiKhachHang> getAllLoaiKhachHang(){
        List<LoaiKhachHang> loaiKhachHangs = new ArrayList<LoaiKhachHang>();
        String sql = "SELECT * FROM LoaiKhachHang;";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                LoaiKhachHang lkh = new LoaiKhachHang();
                lkh.setMaloaikh(rs.getInt("maloaikh"));
                lkh.setTenloaikh(rs.getString("tenloaikh"));
                loaiKhachHangs.add(lkh);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loaiKhachHangs;
    }

    /**
     * Tìm kiếm mã loại khách hàng trong cơ sở dữ liệu dựa trên tên loại khách hàng.
     * @param tenloaikh Tên loại khách hàng cần tìm kiếm.
     * @return Mã loại khách hàng tìm thấy.
     */
    public int findMaKhachHang(String tenloaikh){
        int maloaikh = 0;
        String sql = "SELECT maloaikh FROM LoaiKhachHang WHERE tenloaikh = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenloaikh);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                maloaikh = rs.getInt("maloaikh");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maloaikh;
    }
}
