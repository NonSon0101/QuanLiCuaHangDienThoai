/**
 * KhachHangService cung cấp các chức năng quản lý thông tin khách hàng.
 * Bao gồm tìm kiếm, truy xuất danh sách tất cả khách hàng, cập nhật thông tin khách hàng,
 * truy xuất danh sách tất cả loại khách hàng, thêm mới khách hàng, và lấy mã loại khách hàng
 * từ cơ sở dữ liệu thông qua DAOKhachHang và DAOLoaiKhachHang.
 *
 * Các phương thức bao gồm:
 * - Tìm kiếm khách hàng theo số điện thoại.
 * - Truy xuất danh sách tất cả khách hàng.
 * - Cập nhật thông tin một khách hàng.
 * - Truy xuất danh sách tất cả loại khách hàng.
 * - Thêm mới một khách hàng vào cơ sở dữ liệu.
 * - Lấy mã loại khách hàng dựa trên tên loại khách hàng.
 *
 * Sử dụng ví dụ:
 * ```java
 * KhachHangService khachHangService = new KhachHangService();
 * // Tìm kiếm khách hàng
 * ViewKhachHang khachHangTimKiem = khachHangService.findKhachHang(soDienThoai);
 * // Truy xuất danh sách tất cả khách hàng
 * List<ViewKhachHang> danhSachKhachHang = khachHangService.getAllKhachHang();
 * // Cập nhật thông tin khách hàng
 * KhachHang khachHangCapNhat = new KhachHang(...);
 * khachHangService.updateKhachHang(khachHangCapNhat);
 * // Truy xuất danh sách tất cả loại khách hàng
 * List<LoaiKhachHang> danhSachLoaiKhachHang = khachHangService.getAllLoaiKhachHang();
 * // Thêm mới khách hàng
 * KhachHang khachHangMoi = new KhachHang(...);
 * khachHangService.insertKhachHang(khachHangMoi);
 * // Lấy mã loại khách hàng
 * int maLoaiKhachHang = khachHangService.getMaLoaiKhachHang(tenLoaiKhachHang);
 * ```
 */
package Service;

import DAL.DAOKhachHang;
import DAL.DAOLoaiKhachHang;
import model.KhachHang;
import model.LoaiKhachHang;
import model.ViewKhachHang;

import java.util.List;

public class KhachHangService {
    DAOKhachHang daoKhachHang;
    DAOLoaiKhachHang daoLoaiKhachHang;
    KhachHang khachHang;

    public KhachHangService(){
        daoKhachHang = new DAOKhachHang();
        daoLoaiKhachHang = new DAOLoaiKhachHang();
        khachHang = new KhachHang();
    }
    public ViewKhachHang findKhachHang(String sodienthoai){
        return daoKhachHang.findKhachHang(sodienthoai);
    }

    public List<ViewKhachHang> getAllKhachHang(){
        return daoKhachHang.getAllKhachHang();
    }

    public void updateKhachHang(KhachHang khachHang){
        daoKhachHang.updateKhachHang(khachHang);
    }

    public List<LoaiKhachHang> getAllLoaiKhachHang(){
        return daoLoaiKhachHang.getAllLoaiKhachHang();
    }

    public void insertKhachHang(KhachHang khachHang){
        daoKhachHang.insertKhachHang(khachHang);
    }

    public int getMaLoaiKhachHang(String tenloaikh){
        return daoLoaiKhachHang.findMaKhachHang(tenloaikh);
    }

}
