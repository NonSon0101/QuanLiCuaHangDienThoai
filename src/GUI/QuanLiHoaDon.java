package GUI;

import Service.ChiTietHoaDonService;
import Service.HoaDonService;
import model.ChiTietHoaDonDienThoai;
import model.ChiTietHoaDonPhuKien;
import model.HoaDon;
import model.ViewHoaDon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class QuanLiHoaDon extends JFrame {
    private JPanel mainPanel;
    private JLabel labelHoaDon;
    private JTable tableHoaDon;
    private JTable tableChitietHoaDon;
    private JTextField textNhanVien;
    private JTextField textKhachHang;
    private JTextField textNgayLap;
    private JTextField textMaHd;
    private JTextField textTenSanPham;
    private JTextField textSoLuong;
    private JTextField textGiaBan;
    private JTextField textGiamGia;
    private JTextField textThanhTien;
    private JLabel labelMaHoaDon;
    private JPanel panelHoaDon;
    private JPanel paneChiTietHoaDon;
    private JLabel labelNhanVien;
    private JLabel labelKhachHang;
    private JLabel labelNgayLap;
    private JLabel labelTenSanPham;
    private JLabel labelSoLuong;
    private JLabel labelGiaBan;
    private JLabel labelGiamGia;
    private JLabel labelThanhTien;
    private JScrollPane panelTable;
    private JScrollPane panelTable2;
    private JButton btnXoaSanPham;
    private JButton btnChinhSuaSanPham;
    private JButton button3;
    private JButton button4;
    private JScrollPane panelHThanhTien;

    private HoaDon hoaDon;
    private HoaDonService hoaDonService;
    private ChiTietHoaDonService chiTietHoaDonService;
    private DefaultTableModel defaultTableModel;
    private DefaultTableModel defaultTableModel1;
    private ChiTietHoaDonDienThoai chiTietHoaDonDienThoai;
    private ChiTietHoaDonPhuKien chiTietHoaDonPhuKien;
    public QuanLiHoaDon(){
        setContentPane(mainPanel);
        setTitle("Quản Lí Hóa Đơn");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);

        hoaDon = new HoaDon();
        hoaDonService = new HoaDonService();
        defaultTableModel = new DefaultTableModel();
        tableHoaDon.setModel(defaultTableModel);

        defaultTableModel.addColumn("Mã Hóa Đơn");
        defaultTableModel.addColumn("Tên Khách Hàng");
        defaultTableModel.addColumn("Tên Nhân Viên");
        defaultTableModel.addColumn("Ngày Lập");

        List<ViewHoaDon> hoaDons = hoaDonService.getAllHoaDon();
        for(ViewHoaDon hoaDon : hoaDons){
            defaultTableModel.addRow(new Object[]{hoaDon.getMahd(), hoaDon.getKhachhang(), hoaDon.getNhanvien(), hoaDon.getNgaylap()});
        }

        JScrollPane scrollPane = new JScrollPane(tableHoaDon);
        panelTable.setViewportView(scrollPane);


        tableHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel)tableHoaDon.getModel();
                int selectedRowIndex = tableHoaDon.getSelectedRow();
                textMaHd.setText(model.getValueAt(selectedRowIndex, 0).toString());
                textKhachHang.setText(model.getValueAt(selectedRowIndex, 1).toString());
                textNhanVien.setText(model.getValueAt(selectedRowIndex, 2).toString());
                textNgayLap.setText(model.getValueAt(selectedRowIndex, 3).toString());


                chiTietHoaDonDienThoai = new ChiTietHoaDonDienThoai();
                chiTietHoaDonPhuKien = new ChiTietHoaDonPhuKien();
                chiTietHoaDonService = new ChiTietHoaDonService();
                defaultTableModel1 = new DefaultTableModel();
                tableChitietHoaDon.setModel(defaultTableModel1);

                defaultTableModel1.addColumn("Tên Sản Phẩm");
                defaultTableModel1.addColumn("Loại Sản Phẩm");
                defaultTableModel1.addColumn("Giá Tiền");
                defaultTableModel1.addColumn("Giảm Giá");
                defaultTableModel1.addColumn("Số Lượng");

                List<ChiTietHoaDonDienThoai> ctdt = chiTietHoaDonService.getChiTietHoaDonDienThoai();
                List<ChiTietHoaDonPhuKien> ctpk = chiTietHoaDonService.getChiTietHoaDonPhuKien();

                for(ChiTietHoaDonDienThoai chiTietHoaDonDienThoai : ctdt){
                    defaultTableModel1.addRow(new Object[]{chiTietHoaDonDienThoai.getTendt(), "Điện Thoại", chiTietHoaDonDienThoai.getGiaban(),
                            chiTietHoaDonDienThoai.getPhamtramgiam(), chiTietHoaDonDienThoai.getSoluong()});

                }
                for(ChiTietHoaDonPhuKien chiTietHoaDonPhuKien1 : ctpk){
                    defaultTableModel1.addRow(new Object[]{chiTietHoaDonPhuKien1.getTenpk(), "Phụ Kiện", chiTietHoaDonPhuKien1.getGiaban(),
                            chiTietHoaDonPhuKien1.getPhamtramgiam(), chiTietHoaDonPhuKien1.getSoluong()});

                }

                JScrollPane scrollPane = new JScrollPane(tableChitietHoaDon);
                panelTable2.setViewportView(scrollPane);

            }
        });
        tableChitietHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel)tableChitietHoaDon.getModel();
                int selectedRowIndex = tableChitietHoaDon.getSelectedRow();
                textTenSanPham.setText(model.getValueAt(selectedRowIndex, 0).toString());
                textGiaBan.setText(model.getValueAt(selectedRowIndex, 1).toString());
                textGiamGia.setText(model.getValueAt(selectedRowIndex, 2).toString());
                textSoLuong.setText(model.getValueAt(selectedRowIndex, 3).toString());


                int giaban = Integer.parseInt(model.getValueAt(selectedRowIndex, 1).toString());
                int giamgia = Integer.parseInt(model.getValueAt(selectedRowIndex, 2).toString());
                int soluong = Integer.parseInt(model.getValueAt(selectedRowIndex, 3).toString());
                int thanhtien = (giaban - giaban*(giamgia/100))*soluong;

                textThanhTien.setText(String.valueOf(thanhtien));
            }
        });
    }

    public static void main(String[] args){
        new QuanLiHoaDon();
    }
}
