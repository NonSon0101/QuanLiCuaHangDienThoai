package Service;

import DAO.DAOMuaDienThoai;
import DAO.DAOMuaPhuKien;
import model.ChiTietHoaDonDienThoai;
import model.ChiTietHoaDonPhuKien;

import java.util.List;

public class ChiTietHoaDonService {
    DAOMuaDienThoai daoMuaDienThoai;
    DAOMuaPhuKien daoMuaPhuKien;
    public ChiTietHoaDonService(){
        daoMuaDienThoai = new DAOMuaDienThoai();
        daoMuaPhuKien = new DAOMuaPhuKien();
    }

    public List<ChiTietHoaDonDienThoai> getChiTietHoaDonDienThoai(){
        return daoMuaDienThoai.getChiTietHoaDonDienThoai();
    }

    public List<ChiTietHoaDonPhuKien> getChiTietHoaDonPhuKien(){
        return daoMuaPhuKien.getChiTietHoaDonPhuKien();
    }
}
