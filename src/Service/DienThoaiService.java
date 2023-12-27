package Service;

import DAO.DAODienThoai;
import model.DienThoai;
import model.ViewDienThoai;


import javax.swing.*;
import java.util.List;

public class DienThoaiService {
    private DAODienThoai daoDienThoai;

    public DienThoaiService(){
        daoDienThoai = new DAODienThoai();
    }
    public List<ViewDienThoai> getAllDienThoai(){
        return daoDienThoai.getAllDienThoai();
    }

    public void addDienThoai(DienThoai dienThoai){

        daoDienThoai.insertDienThoai(dienThoai);
    }
    public void deleteDienThoai(int madt){
        daoDienThoai.deleteDienThoai(madt);
    }
    public void updateDienThoai(DienThoai dienThoai){
        daoDienThoai.updateDienThoai(dienThoai);
    }
}
