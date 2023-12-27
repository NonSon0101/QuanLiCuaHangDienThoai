package Service;

import model.HoaDon;
import model.ViewHoaDon;
import DAO.DAOHoaDon;

import javax.swing.*;
import java.util.List;

public class HoaDonService {
    DAOHoaDon daoHoaDon;
    public HoaDonService(){
        daoHoaDon = new DAOHoaDon();
    }

    public List<ViewHoaDon> getAllHoaDon(){
        return daoHoaDon.getAllHoaDon();
    }

    public void insertHoaDon(HoaDon hoaDon){
        daoHoaDon.insertHoadon(hoaDon);
    }

    public void updateHoaDon(HoaDon hoaDon){
        daoHoaDon.updateHoaDon(hoaDon);
    }

    public void deleteHoaDon(int mahd){
        daoHoaDon.deleteHoaDon(mahd);
    }

    public int getMaHoaDonCuoiCung(){
       return daoHoaDon.getMaHoaDonCuoiCung();
    }
}
