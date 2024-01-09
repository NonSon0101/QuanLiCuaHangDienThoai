// DAOPhuKien thực hiện các thao tác CRUD liên quan đến dữ liệu về phụ kiện cho điện thoại
// Các phương thức trong lớp này được gọi bởi PhuKienService và ChiTietHoaDonService
package DAL;

import model.PhuKien;
import model.ViewPhuKien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPhuKien {
    Connection conn;

    // Khối khởi tạo, thiết lập kết nối JDBC khi đối tượng DAO được tạo ra
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            // Xử lý ClassNotFoundException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Trả về danh sách tất cả phụ kiện từ cơ sở dữ liệu.
     *
     * @return Danh sách các đối tượng ViewPhuKien đại diện cho thông tin phụ kiện.
     */
    public List<ViewPhuKien> getAllPhuKien() {
        List<ViewPhuKien> phukiens = new ArrayList<>();
        String sql = "SELECT PhuKien.mapk, PhuKien.tenpk, LoaiPhuKien.tenlpk, HangSanXuat.tenhsx, PhuKien.soluong, PhuKien.giaban, PhuKien.phantramgiam\n" +
                "FROM PhuKien\n" +
                "JOIN HangSanXuat ON PhuKien.mansx = HangSanXuat.mahsx\n" +
                "JOIN LoaiPhuKien ON PhuKien.loaipk = LoaiPhuKien.malpk;\n";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            // Duyệt qua bộ kết quả và tạo đối tượng ViewPhuKien
            while (rs.next()) {
                ViewPhuKien pk = new ViewPhuKien();
                pk.setMapk(rs.getInt("mapk"));
                pk.setTenpk(rs.getString("tenpk"));
                pk.setTenloaipk(rs.getString("tenlpk"));
                pk.setTenhsx(rs.getString("tenhsx"));
                pk.setSoluong(rs.getInt("soluong"));
                pk.setGiaban(rs.getInt("giaban"));
                pk.setPhantramgiam(rs.getInt("phantramgiam"));

                phukiens.add(pk);
            }

        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException("Lỗi khi truy xuất thông tin phụ kiện", e);
        }
        return phukiens;
    }

    /**
     * Chèn một thông tin phụ kiện mới vào cơ sở dữ liệu.
     *
     * @param phuKien Đối tượng PhuKien đại diện cho phụ kiện mới.
     */
    public void insertPhuKien(PhuKien phuKien) {
        String sql = "INSERT INTO PhuKien (tenpk, loaipk, soluong, giaban, phantramgiam, mansx) VALUES(?, ?, ?, ?, ?, ?);";
        try {
            // Tạo một câu lệnh chuẩn bị và thiết lập các tham số cho phụ kiện mới
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, phuKien.getTenpk());
            preparedStatement.setInt(2, phuKien.getLoaipk());
            preparedStatement.setInt(3, phuKien.getSoluong());
            preparedStatement.setInt(4, phuKien.getGiaban());
            preparedStatement.setInt(5, phuKien.getPhantramgiam());
            preparedStatement.setInt(6, phuKien.getMansx());

            // Thực hiện cập nhật và in kết quả
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Cập nhật thông tin của một phụ kiện trong cơ sở dữ liệu.
     *
     * @param phuKien Đối tượng PhuKien đại diện cho phụ kiện cần cập nhật.
     */
    public void updatePhuKien(PhuKien phuKien) {
        String sql = "UPDATE PhuKien SET tenpk = ?, loaipk=?, soluong=?, giaban=?, phantramgiam=?, mansx=? WHERE mapk=?;";
        try {
            // Tạo một câu lệnh chuẩn bị và thiết lập các tham số cho phụ kiện cần cập nhật
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, phuKien.getTenpk());
            preparedStatement.setInt(2, phuKien.getLoaipk());
            preparedStatement.setInt(3, phuKien.getSoluong());
            preparedStatement.setInt(4, phuKien.getGiaban());
            preparedStatement.setInt(5, phuKien.getPhantramgiam());
            preparedStatement.setInt(6, phuKien.getMansx());
            preparedStatement.setInt(7, phuKien.getMapk());

            // Thực hiện cập nhật và in kết quả
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }

    }

    /**
     * Tăng số lượng của một phụ kiện trong cơ sở dữ liệu.
     *
     * @param mapk     ID của phụ kiện cần tăng số lượng.
     * @param soluong  Số lượng cần tăng.
     */
    public void addSoLuongPhuKien(int mapk, int soluong) {
        String sql = "UPDATE PhuKien SET soluong = soluong + ? WHERE mapk = ?";
        try {
            // Tạo một câu lệnh chuẩn bị và thiết lập tham số cho ID và số lượng của phụ kiện
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, soluong);
            preparedStatement.setInt(2, mapk);

            // Thực hiện cập nhật và in kết quả
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Giảm số lượng của một phụ kiện trong cơ sở dữ liệu.
     *
     * @param mapk     ID của phụ kiện cần giảm số lượng.
     * @param soluong  Số lượng cần giảm.
     */
    public void subSoLuongPhuKien(int mapk, int soluong) {
        String sql = "UPDATE PhuKien SET soluong = soluong - ? WHERE mapk = ?";
        try {
            // Tạo một câu lệnh chuẩn bị và thiết lập tham số cho ID và số lượng của phụ kiện
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, soluong);
            preparedStatement.setInt(2, mapk);

            // Thực hiện cập nhật và in kết quả
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Xóa một phụ kiện khỏi cơ sở dữ liệu dựa trên ID của nó.
     *
     * @param mapk ID của phụ kiện cần xóa.
     */
    public void deletePhuKien(int mapk) {
        String sql = "DELETE FROM PhuKien WHERE mapk=?;";
        try {
            // Tạo một câu lệnh chuẩn bị và thiết lập tham số cho ID của phụ kiện cần xóa
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mapk);

            // Thực hiện xóa và in kết quả
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
    }
}
