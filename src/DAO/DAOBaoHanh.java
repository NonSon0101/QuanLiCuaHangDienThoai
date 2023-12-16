package DAO;

import model.BaoHanh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
public class DAOBaoHanh {
    Connection conn;
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BaoHanh> getAllBaoHanh(){
        List<BaoHanh> baoHanhs = new ArrayList<BaoHanh>();
        String sql = "SELECT * FROM BaoHanh";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs =  preparedStatement.executeQuery();
            BaoHanh bh = new BaoHanh();
            while(rs.next()){
                bh.setMabh(rs.getInt("mabh"));
                bh.setMahd(rs.getInt("mahd"));
                bh.setNgaylap(rs.getDate("ngaylap"));
                bh.setNgayhethan(rs.getDate("ngayhethan"));
            }
            baoHanhs.add(bh);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return baoHanhs;
    }

    public void insertBaoHanh(int mahd, Date ngaylap, Date ngayhethan){
        String sql = "INSERT INTO BaoHanh (mahd, ngaylap, ngayhethan) VALUES(?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mahd);
            preparedStatement.setDate(2, ngaylap);
            preparedStatement.setDate(3, ngayhethan);   
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBaoHanh(int mabh, int mahd, Date ngaylap, Date ngayhethan){
        String sql = "UPDATE QuanLiCuaHangDiDong.BaoHanh SET mahd=?, ngaylap=?, ngayhethan=? WHERE mabh=?;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mahd);
            preparedStatement.setDate(2, ngaylap);
            preparedStatement.setDate(3, ngayhethan);
            preparedStatement.setInt(4, mabh);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBaoHanh(int mabh){
        String sql = "DELETE FROM BaoHanh WHERE mabh=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mabh);

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
