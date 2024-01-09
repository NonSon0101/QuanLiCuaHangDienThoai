/**
 * HoaDonService cung cấp các chức năng quản lý hóa đơn mua hàng.
 * Bao gồm truy xuất danh sách tất cả hóa đơn, thêm mới, cập nhật, xóa hóa đơn,
 * và lấy mã hóa đơn cuối cùng từ cơ sở dữ liệu qua DAOHoaDon.
 *
 * Các phương thức bao gồm:
 * - Truy xuất danh sách tất cả hóa đơn mua hàng.
 * - Thêm mới một hóa đơn vào cơ sở dữ liệu.
 * - Cập nhật thông tin một hóa đơn.
 * - Xóa một hóa đơn dựa trên mã hóa đơn.
 * - Lấy mã hóa đơn cuối cùng từ cơ sở dữ liệu.
 *
 * Sử dụng ví dụ:
 * ```java
 * HoaDonService hoaDonService = new HoaDonService();
 * List<ViewHoaDon> danhSachHoaDon = hoaDonService.getAllHoaDon();
 * // Thêm mới hóa đơn
 * HoaDon hoaDonMoi = new HoaDon(...);
 * hoaDonService.insertHoaDon(hoaDonMoi);
 * // Cập nhật thông tin hóa đơn
 * HoaDon hoaDonCapNhat = new HoaDon(...);
 * hoaDonService.updateHoaDon(hoaDonCapNhat);
 * // Lấy mã hóa đơn cuối cùng
 * int maHoaDonCuoiCung = hoaDonService.getMaHoaDonCuoiCung();
 * ```
 */package Service;

import model.HoaDon;
import model.ViewHoaDon;
import DAL.DAOHoaDon;
import model.ViewSanPhamHoan;

import java.util.List;

public class HoaDonService {
    DAOHoaDon daoHoaDon;
    public HoaDonService(){
        daoHoaDon = new DAOHoaDon();
    }

    public List<ViewHoaDon> getAllHoaDon(){
        return daoHoaDon.getAllHoaDon();
    }

    public void insertHoaDon(HoaDon hoaDon){
        daoHoaDon.insertHoadon(hoaDon);
    }

    public void updateHoaDon(HoaDon hoaDon){
        daoHoaDon.updateHoaDon(hoaDon);
    }

    public int getMaHoaDonCuoiCung(){
       return daoHoaDon.getMaHoaDonCuoiCung();
    }


}
