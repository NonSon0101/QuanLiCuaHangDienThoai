/**
 * NhanVienService cung cấp các chức năng quản lý thông tin nhân viên.
 * Bao gồm truy xuất danh sách tất cả nhân viên, thêm mới, cập nhật, xóa nhân viên,
 * kiểm tra đăng nhập, và lấy thông tin nhân viên từ cơ sở dữ liệu qua DAONhanVien.
 *
 * Các phương thức bao gồm:
 * - Truy xuất danh sách tất cả nhân viên.
 * - Thêm mới một nhân viên vào cơ sở dữ liệu.
 * - Cập nhật thông tin một nhân viên.
 * - Xóa một nhân viên dựa trên mã nhân viên.
 * - Kiểm tra đăng nhập với tên đăng nhập và mật khẩu.
 * - Lấy thông tin một nhân viên dựa trên tên đăng nhập và mật khẩu.
 *
 * Sử dụng ví dụ:
 * ```java
 * NhanVienService nhanVienService = new NhanVienService();
 * // Truy xuất danh sách tất cả nhân viên
 * List<NhanVien> danhSachNhanVien = nhanVienService.getAllNhanVien();
 * // Thêm mới nhân viên
 * NhanVien nhanVienMoi = new NhanVien(...);
 * nhanVienService.insertNhanVien(nhanVienMoi);
 * // Cập nhật thông tin nhân viên
 * NhanVien nhanVienCapNhat = new NhanVien(...);
 * nhanVienService.updateNhanVien(nhanVienCapNhat);
 * // Xóa nhân viên
 * nhanVienService.deleteNhanVien(maNhanVien);
 * // Kiểm tra đăng nhập
 * boolean isAuthenticated = nhanVienService.loginAuthentication(username, password);
 * // Lấy thông tin nhân viên
 * NhanVien thongTinNhanVien = nhanVienService.getNhanVien(username, password);
 * ```
 */
package Service;
import DAL.DAONhanVien;
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
