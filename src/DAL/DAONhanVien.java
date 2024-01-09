// DAONhanVien đảm nhiệm các thao tác CRUD liên quan đến nhân viên đang làm việc tại cửa hàng
// và kiểm tra đăng nhập vào hệ thống mỗi khi có người đăng nhập.
// Các phương thức trong lớp này được gọi bởi NhanVienService.

package DAL;

import model.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAONhanVien {
    Connection conn;

    {
        try {
            // Thiết lập kết nối JDBC
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            // Xử lý ClassNotFoundException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Trả về danh sách tất cả nhân viên từ cơ sở dữ liệu.
     *
     * @return Danh sách các đối tượng NhanVien đại diện cho nhân viên.
     */
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> nhanViens = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            // Duyệt qua bộ kết quả và tạo đối tượng NhanVien
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setManv(rs.getInt("manv"));
                nv.setTennv(rs.getString("tennv"));
                nv.setChucvu(rs.getString("chucvu"));
                nv.setSodienthoai(rs.getString("sodienthoai"));
                nv.setDiachi(rs.getString("diachi"));
                nv.setUsername(rs.getString("username"));
                nv.setPass(rs.getString("pass"));
                nhanViens.add(nv);
            }

        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException với thông điệp lỗi
            throw new RuntimeException("Lỗi khi truy xuất thông tin nhân viên", e);
        }
        return nhanViens;
    }

    /**
     * Chèn một nhân viên mới vào cơ sở dữ liệu.
     *
     * @param nhanVien Đối tượng NhanVien đại diện cho nhân viên mới.
     */
    public void insertNhanVien(NhanVien nhanVien) {
        String sql = "INSERT INTO NhanVien (tennv, chucvu, sodienthoai, diachi, username, pass) VALUES(?, ?, ?, ?, ?, ?);";

        try {
            // Tạo một câu lệnh chuẩn bị và thiết lập các tham số cho nhân viên mới
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nhanVien.getTennv());
            preparedStatement.setString(2, nhanVien.getChucvu());
            preparedStatement.setString(3, nhanVien.getSodienthoai());
            preparedStatement.setString(4, nhanVien.getDiachi());
            preparedStatement.setString(5, nhanVien.getUsername());
            preparedStatement.setString(6, nhanVien.getPass());

            // Thực hiện cập nhật và in kết quả
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Cập nhật thông tin của một nhân viên trong cơ sở dữ liệu.
     *
     * @param nhanVien Đối tượng NhanVien đại diện cho nhân viên cần cập nhật.
     */
    public void updateNhanVien(NhanVien nhanVien) {
        String sql = "UPDATE NhanVien SET tennv=?, chucvu=?, sodienthoai=?, diachi=?, username=?, pass=? WHERE manv=?;";

        try {
            // Tạo một câu lệnh chuẩn bị và thiết lập các tham số cho nhân viên cần cập nhật
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nhanVien.getTennv());
            preparedStatement.setString(2, nhanVien.getChucvu());
            preparedStatement.setString(3, nhanVien.getSodienthoai());
            preparedStatement.setString(4, nhanVien.getDiachi());
            preparedStatement.setString(5, nhanVien.getUsername());
            preparedStatement.setString(6, nhanVien.getPass());
            preparedStatement.setInt(7, nhanVien.getManv());

            // Thực hiện cập nhật và in kết quả
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Xóa một nhân viên khỏi cơ sở dữ liệu.
     *
     * @param manv ID của nhân viên cần xóa.
     */
    public void deleteNhanVien(int manv) {
        String sql = "DELETE FROM NhanVien WHERE manv=?;";
        try {
            // Tạo một câu lệnh chuẩn bị và thiết lập tham số cho ID của nhân viên cần xóa
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, manv);

            // Thực hiện xóa và in kết quả
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Kiểm tra đăng nhập của nhân viên vào hệ thống.
     *
     * @param username Tên đăng nhập của nhân viên.
     * @param pass     Mật khẩu của nhân viên.
     * @return True nếu đăng nhập thành công, ngược lại là False.
     */
    public boolean login(String username, String pass) {
        String sql = "SELECT * FROM NhanVien WHERE username = ? AND pass = ?;";
        try {
            // Tạo một câu lệnh chuẩn bị và thiết lập tham số cho tên đăng nhập và mật khẩu
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);

            // Thực hiện truy vấn và kiểm tra kết quả
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Lấy thông tin của một nhân viên từ cơ sở dữ liệu dựa trên tên đăng nhập và mật khẩu.
     *
     * @param username Tên đăng nhập của nhân viên.
     * @param pass     Mật khẩu của nhân viên.
     * @return Đối tượng NhanVien đại diện cho nhân viên.
     */
    public NhanVien getNhanVien(String username, String pass) {
        NhanVien nhanVien = new NhanVien();
        String sql = "SELECT * FROM NhanVien WHERE username = ? and pass = ?;";
        try {
            // Tạo một câu lệnh chuẩn bị và thiết lập tham số cho tên đăng nhập và mật khẩu
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);

            // Thực hiện truy vấn và lấy thông tin nhân viên
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                nhanVien.setManv(rs.getInt("manv"));
                nhanVien.setTennv(rs.getString("tennv"));
                nhanVien.setChucvu(rs.getString("chucvu"));
                nhanVien.setSodienthoai(rs.getString("sodienthoai"));
                nhanVien.setDiachi(rs.getString("diachi"));
            }
        } catch (SQLException e) {
            // Xử lý SQLException bằng cách ném một RuntimeException
            throw new RuntimeException(e);
        }
        return nhanVien;
    }
}
