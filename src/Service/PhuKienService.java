/**
 * PhuKienService cung cấp các chức năng quản lý thông tin phụ kiện.
 * Bao gồm truy xuất danh sách tất cả phụ kiện, thêm mới, cập nhật, xóa phụ kiện,
 * tương tác với cơ sở dữ liệu thông qua DAOPhuKien.
 *
 * Các phương thức bao gồm:
 * - Truy xuất danh sách tất cả phụ kiện.
 * - Thêm mới một phụ kiện vào cơ sở dữ liệu.
 * - Cập nhật thông tin một phụ kiện.
 * - Xóa một phụ kiện dựa trên mã phụ kiện.
 *
 * Sử dụng ví dụ:
 * ```java
 * PhuKienService phuKienService = new PhuKienService();
 * // Truy xuất danh sách tất cả phụ kiện
 * List<ViewPhuKien> danhSachPhuKien = phuKienService.getAllPhuKien();
 * // Thêm mới phụ kiện
 * PhuKien phuKienMoi = new PhuKien(...);
 * phuKienService.addPhuKien(phuKienMoi);
 * // Cập nhật thông tin phụ kiện
 * PhuKien phuKienCapNhat = new PhuKien(...);
 * phuKienService.updatePhuKien(phuKienCapNhat);
 * // Xóa phụ kiện
 * phuKienService.deletePhuKien(maLoaiPhuKien);
 * ```
 */
package Service;


import DAL.DAOPhuKien;
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
