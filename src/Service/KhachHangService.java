package Service;

import DAO.DAOKhachHang;
import DAO.DAOLoaiKhachHang;
import model.KhachHang;
import model.LoaiKhachHang;
import model.ViewKhachHang;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class KhachHangService {
    DAOKhachHang daoKhachHang;
    DAOLoaiKhachHang daoLoaiKhachHang;
    KhachHang khachHang;

    public KhachHangService(){
        daoKhachHang = new DAOKhachHang();
        daoLoaiKhachHang = new DAOLoaiKhachHang();
        khachHang = new KhachHang();
    }
    public ViewKhachHang findKhachHang(String sodienthoai){
        return daoKhachHang.findKhachHang(sodienthoai);
    }

    public List<ViewKhachHang> getAllKhachHang(){
        return daoKhachHang.getAllKhachHang();
    }

    public void updateKhachHang(KhachHang khachHang){
        daoKhachHang.updateKhachHang(khachHang);
    }

    public List<LoaiKhachHang> getAllLoaiKhachHang(){
        return daoLoaiKhachHang.getAllLoaiKhachHang();
    }

    public void insertKhachHang(KhachHang khachHang){
        daoKhachHang.insertKhachHang(khachHang);
    }

    public int getMaLoaiKhachHang(String tenloaikh){
        return daoLoaiKhachHang.findMaKhachHang(tenloaikh);
    }

}
