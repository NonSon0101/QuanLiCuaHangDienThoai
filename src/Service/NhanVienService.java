package Service;
import DAO.DAONhanVien;
import model.NhanVien;

import java.util.List;

public class NhanVienService {
    DAONhanVien daoNhanVien;
    NhanVien nhanVien;

    public NhanVienService(){
        nhanVien = new NhanVien();
        daoNhanVien = new DAONhanVien();
    }
    public List<NhanVien> getAllNhanVien(){
        return daoNhanVien.getAllNhanVien();
    }
    public void insertNhanVien(NhanVien nhanVien){
        daoNhanVien.insertNhanVien(nhanVien);
    }
    public void updateNhanVien(NhanVien nhanVien){
        daoNhanVien.updateNhanVien(nhanVien);
    }
    public void deleteNhanVien(int manv){
        daoNhanVien.deleteNhanVien(manv);
    }
    public boolean loginAuthentication(String username, String pass){
        return daoNhanVien.login(username, pass);
    }

    public NhanVien getNhanVien(String username, String pass){
        return daoNhanVien.getNhanVien(username, pass);
    }
}
