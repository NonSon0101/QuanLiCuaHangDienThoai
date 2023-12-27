package DAO;


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
    {
        try {
            conn = JDBCConnection.getJDBCConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ViewPhuKien> getAllPhuKien() {
        List<ViewPhuKien> phukiens = new ArrayList<ViewPhuKien>();
        String sql = "SELECT PhuKien.mapk, PhuKien.tenpk, LoaiPhuKien.tenlpk, HangSanXuat.tenhsx, PhuKien.soluong, PhuKien.giaban, PhuKien.phantramgiam\n" +
                "FROM PhuKien\n" +
                "JOIN HangSanXuat ON PhuKien.mansx = HangSanXuat.mahsx\n" +
                "JOIN LoaiPhuKien ON PhuKien.loaipk = LoaiPhuKien.malpk;\n";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();


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
            throw new RuntimeException(e);
        }
        return phukiens;
    }


    public void insertPhuKien(PhuKien phuKien){
        String sql = "INSERT INTO PhuKien (tenpk, loaipk, soluong, giaban, phantramgiam, mansx) VALUES(?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, phuKien.getTenpk());
            preparedStatement.setInt(2, phuKien.getLoaipk());
            preparedStatement.setInt(3, phuKien.getSoluong());
            preparedStatement.setInt(4, phuKien.getGiaban());
            preparedStatement.setInt(5, phuKien.getPhantramgiam());
            preparedStatement.setInt(6, phuKien.getMansx());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePhuKien(PhuKien phuKien){
        String sql = "UPDATE PhuKien SET tenpk = ?, loaipk=?, soluong=?, giaban=?, phantramgiam=?, mansx=? WHERE mapk=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, phuKien.getTenpk());
            preparedStatement.setInt(2, phuKien.getLoaipk());
            preparedStatement.setInt(3, phuKien.getSoluong());
            preparedStatement.setInt(4, phuKien.getGiaban());
            preparedStatement.setInt(5, phuKien.getPhantramgiam());
            preparedStatement.setInt(6, phuKien.getMansx());
            preparedStatement.setInt(7, phuKien.getMapk());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addSoLuongPhuKien(int mapk, int soluong){
        String sql = "UPDATE PhuKien SET soluong = soluong + ? WHERE mapk = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, soluong);
            preparedStatement.setInt(2, mapk);

            int rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void subSoLuongPhuKien(int mapk, int soluong){
        String sql = "UPDATE PhuKien SET soluong = soluong - ? WHERE mapk = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, soluong);
            preparedStatement.setInt(2, mapk);

            int rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deletePhuKien(int mapk){
        String sql = "DELETE FROM PhuKien WHERE mapk=?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, mapk);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args){
        DAOPhuKien dt = new DAOPhuKien();
        dt.deletePhuKien(31);
    }

}
