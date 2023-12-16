package Service;

import DAO.DAOLoaiPhuKien;
import model.LoaiPhuKien;

import java.util.List;

public class LoaiPhuKienService {
    DAOLoaiPhuKien daoLoaiPhuKien;

    public LoaiPhuKienService(){
        daoLoaiPhuKien = new DAOLoaiPhuKien();
    }

    public List<LoaiPhuKien> getAllLoaiPhuKien(){
        return daoLoaiPhuKien.getAllLoaiPhuKien();
    }

    public int getMaLoaiPhuKien(String tenlpk){
        return daoLoaiPhuKien.getMaLoaiPhuKien(tenlpk);
    }
}
