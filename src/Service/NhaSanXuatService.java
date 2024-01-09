/**
 * NhaSanXuatService cung cấp các chức năng quản lý thông tin nhà sản xuất.
 * Bao gồm truy xuất danh sách tất cả nhà sản xuất và lấy mã nhà sản xuất
 * từ cơ sở dữ liệu thông qua DAOHangSanXuat.
 *
 * Các phương thức bao gồm:
 * - Truy xuất danh sách tất cả nhà sản xuất.
 * - Lấy mã nhà sản xuất dựa trên tên nhà sản xuất.
 *
 * Sử dụng ví dụ:
 * ```java
 * NhaSanXuatService nhaSanXuatService = new NhaSanXuatService();
 * // Truy xuất danh sách tất cả nhà sản xuất
 * List<HangSanXuat> danhSachHangSanXuat = nhaSanXuatService.getAllHangSanXuat();
 * // Lấy mã nhà sản xuất
 * int maHangSanXuat = nhaSanXuatService.getMaHangSanXuat(tenHangSanXuat);
 * ```
 */
package Service;

import DAL.DAOHangSanXuat;
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
