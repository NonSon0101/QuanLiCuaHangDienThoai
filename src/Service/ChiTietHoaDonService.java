package Service;

import DAO.DAOMuaDienThoai;
import DAO.DAOMuaPhuKien;
import DAO.DAODienThoai;
import DAO.DAOPhuKien;
import model.ChiTietHoaDonDienThoai;
import model.ChiTietHoaDonPhuKien;
import model.muadt;
import model.muapk;

import java.util.List;

public class ChiTietHoaDonService {
    DAOMuaDienThoai daoMuaDienThoai;
    DAOMuaPhuKien daoMuaPhuKien;
    DAODienThoai daoDienThoai;
    DAOPhuKien daoPhuKien;
    public ChiTietHoaDonService(){
        daoMuaDienThoai = new DAOMuaDienThoai();
        daoMuaPhuKien = new DAOMuaPhuKien();
        daoDienThoai = new DAODienThoai();
        daoPhuKien = new DAOPhuKien();
    }

    public List<ChiTietHoaDonDienThoai> getChiTietHoaDonDienThoai(int mahd){
        return daoMuaDienThoai.getChiTietHoaDonDienThoai(mahd);
    }

    public List<ChiTietHoaDonPhuKien> getChiTietHoaDonPhuKien(int mahd){
        return daoMuaPhuKien.getChiTietHoaDonPhuKien(mahd);
    }

    public void insertMuadt(muadt muadt){
        daoMuaDienThoai.insertMuadt(muadt);
    }
    public void insertMuapk(muapk muapk){
        daoMuaPhuKien.insertMuapk(muapk);
    }
    public void addSoLuongDienThoai(int madt, int soluong){
        daoDienThoai.addSoLuong(madt, soluong);
    }
    public void subSoLuongDienThoai(int madt, int soluong){
        daoDienThoai.subSoLuong(madt, soluong);
    }
    public void addSoLuongPhuKien(int mapk, int soluong){
        daoPhuKien.addSoLuongPhuKien(mapk, soluong);
    }
    public void subSoLuongPhuKien(int mapk, int soluong){
        daoPhuKien.subSoLuongPhuKien(mapk, soluong);
    }

    public void deleteSanPhamDT(int madt){
        daoMuaDienThoai.deleteSanPham(madt);
    }
    public void deleteSanPhamPK(int mapk){
        daoMuaPhuKien.deleteSanPham(mapk);
    }
    public void deleteSanPhamHoaDon(int mahd){
        daoMuaPhuKien.deleteSPHoaDon(mahd);
        daoMuaDienThoai.deleteSPHoaDon(mahd);
    }
}
