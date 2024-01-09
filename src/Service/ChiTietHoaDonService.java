/**
 * ChiTietHoaDonService cung cấp các chức năng liên quan đến chi tiết hóa đơn.
 * Bao gồm quản lý và truy xuất chi tiết điện thoại di động và phụ kiện đã mua,
 * cũng như cập nhật số lượng tồn kho và thực hiện các thao tác trên cơ sở dữ liệu liên quan.
 *
 * Các phương thức bao gồm:
 * - Truy xuất chi tiết điện thoại di động và phụ kiện đã mua cho một hóa đơn cụ thể.
 * - Chèn các bản ghi cho điện thoại di động và phụ kiện đã mua vào cơ sở dữ liệu.
 * - Cập nhật số lượng điện thoại di động và phụ kiện trong kho.
 * - Xóa điện thoại di động và phụ kiện đã mua khỏi hóa đơn và cập nhật cơ sở dữ liệu.
 *
 * Tương tác với các Đối tượng Truy cập Dữ liệu (DAO) sau:
 * - DAOMuaDienThoai: Quản lý các thao tác truy cập dữ liệu cho điện thoại di động đã mua.
 * - DAOMuaPhuKien: Quản lý các thao tác truy cập dữ liệu cho phụ kiện đã mua.
 * - DAODienThoai: Quản lý các thao tác truy cập dữ liệu cho điện thoại di động trong kho.
 * - DAOPhuKien: Quản lý các thao tác truy cập dữ liệu cho phụ kiện trong kho.
 *
 * Ví dụ sử dụng:
 * ```java
 * ChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonService();
 * List<ChiTietHoaDonDienThoai> chiTietDTList = chiTietHoaDonService.getChiTietHoaDonDienThoai(maHoaDon);
 * List<ChiTietHoaDonPhuKien> chiTietPKList = chiTietHoaDonService.getChiTietHoaDonPhuKien(maHoaDon);
 */
package Service;

import DAL.*;
import model.*;

import java.util.List;

public class ChiTietHoaDonService {
    DAOMuaDienThoai daoMuaDienThoai;
    DAOMuaPhuKien daoMuaPhuKien;
    DAODienThoai daoDienThoai;
    DAOPhuKien daoPhuKien;
    DAOHoaDon daoHoaDon;
    public ChiTietHoaDonService(){
        daoMuaDienThoai = new DAOMuaDienThoai();
        daoMuaPhuKien = new DAOMuaPhuKien();
        daoDienThoai = new DAODienThoai();
        daoPhuKien = new DAOPhuKien();
        daoHoaDon = new DAOHoaDon();
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
    public List<ViewSanPhamHoan> getSanPhamHoan(int mahd){
        return daoHoaDon.getSanPhamHoan(mahd);
    }
    public void insertSanPhamHoan(HoanSanPham hoanSanPham){
        daoHoaDon.insertSanPhamHoan(hoanSanPham);
    }
    public void deleteSanPhamHoan(int mahd, int masp){
        daoHoaDon.deleteSanPhamHoan(mahd,masp);
    }
}
