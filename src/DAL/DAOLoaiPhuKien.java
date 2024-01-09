package DAL;

import model.LoaiPhuKien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp DAOLoaiPhuKien thực hiện các thao tác liên quan đến cơ sở dữ liệu cho đối tượng LoaiPhuKien.
 * Bao gồm truy xuất danh sách tất cả loại phụ kiện, tìm kiếm mã loại phụ kiện dựa trên tên loại phụ kiện,
 * thêm mới loại phụ kiện, và cập nhật thông tin loại phụ kiện.
 */
public class DAOLoaiPhuKien {
    Connection conn;

    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Truy xuất danh sách tất cả loại phụ kiện từ cơ sở dữ liệu.
     * @return Danh sách các đối tượng LoaiPhuKien chứa thông tin loại phụ kiện.
     */
    public List<LoaiPhuKien> getAllLoaiPhuKien(){
        List<LoaiPhuKien> loaiPhuKiens = new ArrayList<LoaiPhuKien>();
        String sql = "SELECT * FROM LoaiPhuKien;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                LoaiPhuKien lpk = new LoaiPhuKien();
                lpk.setMalpk(rs.getInt("malpk"));
                lpk.setTenlpk(rs.getString("tenlpk"));
                loaiPhuKiens.add(lpk);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loaiPhuKiens;
    }

    /**
     * Tìm kiếm mã loại phụ kiện trong cơ sở dữ liệu dựa trên tên loại phụ kiện.
     * @param tenlpk Tên loại phụ kiện cần tìm kiếm.
     * @return Mã loại phụ kiện tìm thấy.
     */
    public int getMaLoaiPhuKien(String tenlpk){
        String sql = "SELECT malpk FROM LoaiPhuKien WHERE tenlpk = ?;";
        int malpk = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenlpk);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                malpk = rs.getInt("malpk");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return malpk;
    }

    /**
     * Thêm mới loại phụ kiện vào cơ sở dữ liệu.
     * @param tenlpk Tên loại phụ kiện cần thêm mới.
     */
    public void inserLoaiPhuKien(String tenlpk){
        String sql = "INSERT INTO LoaiPhuKien(tenlpk) VALUES (?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenlpk);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cập nhật thông tin loại phụ kiện trong cơ sở dữ liệu.
     * @param malpk Mã loại phụ kiện cần cập nhật.
     * @param tenlpk Tên mới của loại phụ kiện.
     */
    public void updateLoaiPhuKien(int malpk, String tenlpk){
        String sql = "UPDATE LoaiPhuKien Set tenlpk=? WHERE malpk=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenlpk);
            preparedStatement.setInt(2, malpk);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
