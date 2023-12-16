package Service;

import DAO.DAOHangSanXuat;
import model.HangSanXuat;

import java.util.List;

public class NhaSanXuatService {
    private DAOHangSanXuat daoHangSanXuat;
    public  NhaSanXuatService(){
        daoHangSanXuat = new DAOHangSanXuat();
    }
    public List<HangSanXuat> getAllHangSanXuat(){
        return daoHangSanXuat.getAllHangSanXuat();
    }
    public int getMaHangSanXuat(String tenhsx){
        return daoHangSanXuat.getMaHangSanXuat(tenhsx);
    }
}
