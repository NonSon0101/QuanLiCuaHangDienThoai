/**
 * LoaiPhuKienService cung cấp các chức năng quản lý loại phụ kiện.
 * Bao gồm truy xuất danh sách tất cả loại phụ kiện và lấy mã loại phụ kiện
 * từ cơ sở dữ liệu thông qua DAOLoaiPhuKien.
 *
 * Các phương thức bao gồm:
 * - Truy xuất danh sách tất cả loại phụ kiện.
 * - Lấy mã loại phụ kiện dựa trên tên loại phụ kiện.
 *
 * Sử dụng ví dụ:
 * ```java
 * LoaiPhuKienService loaiPhuKienService = new LoaiPhuKienService();
 * // Truy xuất danh sách tất cả loại phụ kiện
 * List<LoaiPhuKien> danhSachLoaiPhuKien = loaiPhuKienService.getAllLoaiPhuKien();
 * // Lấy mã loại phụ kiện
 * int maLoaiPhuKien = loaiPhuKienService.getMaLoaiPhuKien(tenLoaiPhuKien);
 * ```
 */
package Service;

import DAL.DAOLoaiPhuKien;
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
