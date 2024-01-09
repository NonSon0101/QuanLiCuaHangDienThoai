/**
 * DienThoaiService cung cấp các chức năng quản lý điện thoại di động.
 * Bao gồm truy xuất danh sách điện thoại, thêm mới, xóa và cập nhật thông tin điện thoại,
 * tương tác với cơ sở dữ liệu thông qua DAODienThoai.
 *
 * Các phương thức bao gồm:
 * - Truy xuất danh sách tất cả điện thoại di động.
 * - Thêm mới một điện thoại di động vào cơ sở dữ liệu.
 * - Xóa một điện thoại di động dựa trên mã điện thoại.
 * - Cập nhật thông tin một điện thoại di động.
 *
 * Sử dụng ví dụ:
 * ```java
 * DienThoaiService dienThoaiService = new DienThoaiService();
 * List<ViewDienThoai> danhSachDienThoai = dienThoaiService.getAllDienThoai();
 * // Thêm mới điện thoại
 * DienThoai dienThoaiMoi = new DienThoai(...);
 * dienThoaiService.addDienThoai(dienThoaiMoi);
 * // Xóa điện thoại
 * dienThoaiService.deleteDienThoai(maDienThoai);
 * // Cập nhật thông tin điện thoại
 * DienThoai dienThoaiCapNhat = new DienThoai(...);
 * dienThoaiService.updateDienThoai(dienThoaiCapNhat);
 * ```
 */package Service;

import DAL.DAODienThoai;
import model.DienThoai;
import model.ViewDienThoai;


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
