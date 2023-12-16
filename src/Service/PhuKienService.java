package Service;


import DAO.DAOPhuKien;
import model.PhuKien;
import model.ViewPhuKien;

import java.util.List;


public class PhuKienService {
    DAOPhuKien daoPhuKien;

    public  PhuKienService(){
        daoPhuKien = new DAOPhuKien();
    }

    public List<ViewPhuKien> getAllPhuKien(){
        return daoPhuKien.getAllPhuKien();
    }

    public void addPhuKien(PhuKien phuKien){
        daoPhuKien.insertPhuKien(phuKien);
    }

    public void updatePhuKien(PhuKien phuKien){
        daoPhuKien.updatePhuKien(phuKien);
    }

    public void deletePhuKien(int malpk){
        daoPhuKien.deletePhuKien(malpk);
    }
}
