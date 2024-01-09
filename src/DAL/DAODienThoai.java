package DAL;

import model.DienThoai;
import model.ViewDienThoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp DAODienThoai thực hiện các thao tác liên quan đến cơ sở dữ liệu cho đối tượng DienThoai.
 * Bao gồm truy xuất thông tin điện thoại, thêm mới, cập nhật, và xóa điện thoại.
 */
public class DAODienThoai {
    Connection conn;

    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Truy xuất danh sách tất cả điện thoại và thông tin liên quan từ cơ sở dữ liệu.
     * @return Danh sách các đối tượng ViewDienThoai chứa thông tin điện thoại.
     */
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

    /**
     * Thêm mới một đối tượng DienThoai vào cơ sở dữ liệu.
     * @param dienThoai Đối tượng DienThoai cần thêm mới.
     */
    public void insertDienThoai(DienThoai dienThoai){
        String sql = "INSERT INTO DienThoai (tendt, mansx, soluong, giaban, phantramgiam) VALUES(?, ?, ?, ?, ?);";

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

    /**
     * Cập nhật thông tin một đối tượng DienThoai trong cơ sở dữ liệu.
     * @param dienThoai Đối tượng DienThoai cần cập nhật.
     */
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

    /**
     * Tăng số lượng của một điện thoại trong cơ sở dữ liệu.
     * @param madt Mã điện thoại cần tăng số lượng.
     * @param soluong Số lượng cần thêm vào.
     */
    public void addSoLuong(int madt, int soluong){
        String sql = "UPDATE DienThoai SET soluong = soluong + ? WHERE madt = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, soluong);
            preparedStatement.setInt(2, madt);

            int rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Giảm số lượng của một điện thoại trong cơ sở dữ liệu.
     * @param madt Mã điện thoại cần giảm số lượng.
     * @param soluong Số lượng cần trừ đi.
     */
    public void subSoLuong(int madt, int soluong){
        String sql = "UPDATE DienThoai SET soluong = soluong - ? WHERE madt = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, soluong);
            preparedStatement.setInt(2, madt);

            int rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Xóa một đối tượng DienThoai khỏi cơ sở dữ liệu dựa trên mã điện thoại.
     * @param madt Mã điện thoại cần xóa.
     */
    public void deleteDienThoai(int madt){
        String sql = "DELETE FROM DienThoai WHERE madt=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, madt);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



