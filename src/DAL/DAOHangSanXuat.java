package DAL;

import model.HangSanXuat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp DAOHangSanXuat thực hiện các thao tác liên quan đến cơ sở dữ liệu cho đối tượng HangSanXuat.
 * Bao gồm truy xuất thông tin nhà sản xuất, lấy mã nhà sản xuất từ tên nhà sản xuất,
 */
public class DAOHangSanXuat {
    Connection conn;

    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Truy xuất danh sách tất cả nhà sản xuất từ cơ sở dữ liệu.
     * @return Danh sách các đối tượng HangSanXuat chứa thông tin nhà sản xuất.
     */
    public List<HangSanXuat> getAllHangSanXuat(){
        List<HangSanXuat> hangSanXuats = new ArrayList<HangSanXuat>();
        String sql = "SELECT * FROM HangSanXuat";

        try {
            PreparedStatement preparedStatement  = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                HangSanXuat hsx = new HangSanXuat();
                hsx.setMahsx(rs.getInt("mahsx"));
                hsx.setTenhsx(rs.getString("tenhsx"));
                hangSanXuats.add(hsx);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hangSanXuats;
    }

    /**
     * Lấy mã nhà sản xuất dựa trên tên nhà sản xuất.
     * @param tenhsx Tên nhà sản xuất cần lấy mã.
     * @return Mã nhà sản xuất tương ứng với tên nhà sản xuất.
     */
    public int getMaHangSanXuat(String tenhsx){
        int mahsx = 0;
        String sql = "SELECT mahsx FROM HangSanXuat WHERE tenhsx = ? ";
        try {
            PreparedStatement preparedStatement  = conn.prepareStatement(sql);
            preparedStatement.setString(1, tenhsx);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                mahsx = (rs.getInt("mahsx"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mahsx;
    }

}
