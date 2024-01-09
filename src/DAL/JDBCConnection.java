// Tạo kết nối từ cơ sở dữ liệu đến chương trình
// Các lớp DAO sẽ sử dụng kết nối này để thực hiện truy vấn dữ liệu cần thiết
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    /**
     * Phương thức này trả về một kết nối JDBC đến cơ sở dữ liệu.
     *
     * @return Kết nối đến cơ sở dữ liệu.
     * @throws ClassNotFoundException Nếu không tìm thấy lớp Driver của MySQL.
     */
    public static Connection getJDBCConnection() throws ClassNotFoundException {
        // Thông tin kết nối đến cơ sở dữ liệu MySQL
        final String url = "jdbc:mysql://localhost:3306/QuanLiCuaHangDiDong";
        final String user = "root";
        final String password = "Duy010203@";

        try {
            // Đăng ký lớp Driver của MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Tạo và trả về kết nối
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            // Xử lý ngoại lệ ClassNotFoundException, ví dụ như ghi log thông báo lỗi
            e.printStackTrace();
        } catch (SQLException e) {
            // Ném một RuntimeException với SQLException được bao gồm
            throw new RuntimeException("Lỗi khi tạo kết nối đến cơ sở dữ liệu", e);
        }

        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // Kiểm tra kết nối bằng cách gọi phương thức getJDBCConnection
        Connection conn = getJDBCConnection();

        // Kiểm tra kết nối có thành công không và in ra kết quả
        if (conn != null) {
            System.out.println("Kết nối thành công");
        } else {
            System.out.println("Kết nối thất bại");
        }
    }
}
