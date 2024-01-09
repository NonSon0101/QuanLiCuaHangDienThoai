package GUI;

import Service.ChiTietHoaDonService;
import Service.HoaDonService;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.text.DecimalFormat;

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
    private JButton btnHoan;
    private JTextField textMaSanPham;
    private JLabel labelMaSanPham;
    private JButton btnQuayVe;
    private JTextField textTongTien;
    private JLabel labelTongTien;
    private JPanel panelBtnCTHD;
    private JPanel panelBtnHD;
    private JPanel panelLabel1;
    private JPanel panelLabel2;
    private JButton btnNhapKho;
    private JTable tableSanPhamHoan;
    private JPanel panelBtnNhapKho;
    private JPanel panelBtnHoan;
    private JScrollPane panelTable3;
    private JPanel panelSanPhamHoan;
    private JScrollPane panelHThanhTien;
    private HoaDon hoaDon;
    private HoaDonService hoaDonService;
    private ChiTietHoaDonService chiTietHoaDonService;
    private DefaultTableModel defaultTableModel;
    private DefaultTableModel defaultTableModel1;
    private DefaultTableModel defaultTableModel2;
    private ChiTietHoaDonDienThoai chiTietHoaDonDienThoai;
    private ChiTietHoaDonPhuKien chiTietHoaDonPhuKien;

    DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public QuanLiHoaDon(NhanVien nhanVien) {
        setContentPane(mainPanel);
        mainPanel.setBackground(new Color(32,178,170,255));
        paneChiTietHoaDon.setBackground(new Color(32,178,170,255));
        panelHoaDon.setBackground(new Color(32,178,170,255));
        panelBtnCTHD.setBackground(new Color(32,178,170,255));
        panelBtnHD.setBackground(new Color(32,178,170,255));
        panelTable.setBackground(new Color(32,178,170,255));
        panelTable2.setBackground(new Color(32,178,170,255));
        btnQuayVe.setBackground(new Color(11,193,105,255));
        panelLabel1.setBackground(new Color(0,128,128,255));
        panelLabel2.setBackground(new Color(0,128,128,255));
        panelBtnHoan.setBackground(new Color(32,178,170,255));
        panelBtnNhapKho.setBackground(new Color(32,178,170,255));
        panelSanPhamHoan.setBackground(new Color(0,128,128,255));
        btnHoan.setBackground(new Color(83,150,237,255));
        btnNhapKho.setBackground(new Color(62,183,114,255));
        setTitle("Quản Lí Hóa Đơn");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        hoaDon = new HoaDon();
        hoaDonService = new HoaDonService();
        defaultTableModel = new DefaultTableModel();
        tableHoaDon.setModel(defaultTableModel);
        chiTietHoaDonDienThoai = new ChiTietHoaDonDienThoai();
        chiTietHoaDonPhuKien = new ChiTietHoaDonPhuKien();
        chiTietHoaDonService = new ChiTietHoaDonService();
        defaultTableModel1 = new DefaultTableModel();
        tableChitietHoaDon.setModel(defaultTableModel1);
        defaultTableModel2 = new DefaultTableModel();
        tableSanPhamHoan.setModel(defaultTableModel2);

        defaultTableModel.addColumn("Mã Hóa Đơn");
        defaultTableModel.addColumn("Tên Khách Hàng");
        defaultTableModel.addColumn("Tên Nhân Viên");
        defaultTableModel.addColumn("Ngày Lập");

        defaultTableModel1.addColumn("Mã Sản Phẩm");
        defaultTableModel1.addColumn("Tên Sản Phẩm");
        defaultTableModel1.addColumn("Loại Sản Phẩm");
        defaultTableModel1.addColumn("Giá Tiền");
        defaultTableModel1.addColumn("Giảm Giá");
        defaultTableModel1.addColumn("Số Lượng");

        defaultTableModel2.addColumn("Mã Hóa Đơn");
        defaultTableModel2.addColumn("Mã Sản Phẩm");
        defaultTableModel2.addColumn("Sản Phẩm");
        defaultTableModel2.addColumn("Loại Sản Phẩm");
        defaultTableModel2.addColumn("Số Lượng");

        loadHoaDon();

        JScrollPane scrollPane = new JScrollPane(tableHoaDon);
        panelTable.setViewportView(scrollPane);


        tableHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
                int selectedRowIndex = tableHoaDon.getSelectedRow();
                textMaHd.setText(model.getValueAt(selectedRowIndex, 0).toString());
                textKhachHang.setText(model.getValueAt(selectedRowIndex, 1).toString());
                textNhanVien.setText(model.getValueAt(selectedRowIndex, 2).toString());
                textNgayLap.setText(model.getValueAt(selectedRowIndex, 3).toString());

                int mahd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
                List<ChiTietHoaDonDienThoai> ctdt = chiTietHoaDonService.getChiTietHoaDonDienThoai(mahd);
                List<ChiTietHoaDonPhuKien> ctpk = chiTietHoaDonService.getChiTietHoaDonPhuKien(mahd);
                int giaban = 0;
                int phantramgiam = 0;
                int soluong = 0;
                float tongtien = 0;
                for (ChiTietHoaDonDienThoai chiTietHoaDonDienThoai : ctdt) {
                    giaban = chiTietHoaDonDienThoai.getGiaban();
                    phantramgiam = chiTietHoaDonDienThoai.getPhamtramgiam();
                    soluong = chiTietHoaDonDienThoai.getSoluong();
                    tongtien += (giaban - giaban * ((float) phantramgiam / 100)) * soluong;
                }
                for (ChiTietHoaDonPhuKien chiTietHoaDonPhuKien1 : ctpk) {
                    giaban = chiTietHoaDonPhuKien1.getGiaban();
                    phantramgiam = chiTietHoaDonPhuKien1.getPhamtramgiam();
                    soluong = chiTietHoaDonPhuKien1.getSoluong();
                    tongtien += (giaban - giaban * ((float) phantramgiam / 100)) * soluong;
                }
                textTongTien.setText(decimalFormat.format(tongtien));
                loadSanPham();
                loadSanPhamHoan(mahd);
            }
        });
        tableChitietHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) tableChitietHoaDon.getModel();
                int selectedRowIndex = tableChitietHoaDon.getSelectedRow();
                textMaSanPham.setText(model.getValueAt(selectedRowIndex, 0).toString());
                textTenSanPham.setText(model.getValueAt(selectedRowIndex, 1).toString());
                textGiaBan.setText(decimalFormat.format(Integer.parseInt(model.getValueAt(selectedRowIndex, 3).toString())));
                textGiamGia.setText(model.getValueAt(selectedRowIndex, 4).toString());
                textSoLuong.setText(model.getValueAt(selectedRowIndex, 5).toString());

                int giaban = Integer.parseInt(model.getValueAt(selectedRowIndex, 3).toString());
                int giamgia = Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString());
                int soluong = Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString());
                float tiengiam = giaban * ((float) giamgia / 100);
                float thanhtien = (giaban - tiengiam) * soluong;

                textThanhTien.setText(decimalFormat.format(thanhtien));
            }
        });
        btnHoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = JOptionPane.showConfirmDialog(mainPanel, "xác nhận hoàn sản phẩm này ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) tableChitietHoaDon.getModel();
                    int selectedRowIndex = tableChitietHoaDon.getSelectedRow();
                    int mahd = Integer.parseInt(textMaHd.getText());
                    int masp = Integer.parseInt(textMaSanPham.getText());
                    int soluong = Integer.parseInt(textSoLuong.getText());
                    String loaisp = model.getValueAt(selectedRowIndex, 2).toString();
                    HoanSanPham hoanSanPham = new HoanSanPham();
                    hoanSanPham.setMahd(mahd);
                    hoanSanPham.setMasp(masp);
                    hoanSanPham.setSoluong(soluong);
                    hoanSanPham.setLoaisanpham(loaisp);
                    chiTietHoaDonService.insertSanPhamHoan(hoanSanPham);
                    if (loaisp.equals("DT")) {
                        try{
                            chiTietHoaDonService.deleteSanPhamDT(masp);
                            JOptionPane.showMessageDialog(mainPanel, "Hoàn Sản Phẩm Thành Công");
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(mainPanel, "Lỗi, Thất bại");
                        }
                    }
                    if (loaisp.equals("PK")) {
                        try {
                            chiTietHoaDonService.deleteSanPhamPK(masp);
                            JOptionPane.showMessageDialog(mainPanel, "Hoàn Sản Phẩm Thành Công");
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(mainPanel, "Lỗi, Thất bại");
                        }
                    }
                    loadSanPham();
                    loadSanPhamHoan(mahd);
                }
            }
        });
        btnQuayVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MainForm(nhanVien);
                dispose();
            }
        });
        btnNhapKho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = JOptionPane.showConfirmDialog(mainPanel, "xác nhận nhập kho sản phẩm này ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) tableSanPhamHoan.getModel();
                    int selectedRowIndex = tableSanPhamHoan.getSelectedRow();
                    int mahd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
                    int masp = Integer.parseInt(model.getValueAt(selectedRowIndex, 1).toString());
                    int soluong = Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString());
                    String loaisp = model.getValueAt(selectedRowIndex, 3).toString();
                    if (loaisp.equals("DT")) {
                        try{
                            chiTietHoaDonService.addSoLuongDienThoai(masp, soluong);
                            chiTietHoaDonService.deleteSanPhamHoan(mahd,masp);
                            JOptionPane.showMessageDialog(mainPanel, "Nhập Kho Thành Công");
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(mainPanel, "Lỗi, Thất bại");
                        }
                    }
                    if (loaisp.equals("PK")) {
                        try {
                            chiTietHoaDonService.addSoLuongPhuKien(masp, soluong);
                            chiTietHoaDonService.deleteSanPhamHoan(mahd,masp);
                            JOptionPane.showMessageDialog(mainPanel, "Nhập Kho Thành Công");
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(mainPanel, "Lỗi, Thất bại");
                        }
                    }
                    loadSanPhamHoan(mahd);
                }

            }
        });
    }
    private void loadSanPhamHoan(int mahd){
        defaultTableModel2.setRowCount(0);
        List<ViewSanPhamHoan> viewSanPhamHoans = chiTietHoaDonService.getSanPhamHoan(mahd);
        for(ViewSanPhamHoan viewSanPhamHoan: viewSanPhamHoans){
            defaultTableModel2.addRow(new Object[]{viewSanPhamHoan.getMahd(), viewSanPhamHoan.getMasp(), viewSanPhamHoan.getTensanpham(), viewSanPhamHoan.getLoaisanpham(), viewSanPhamHoan.getSoluong()});
        }
    }
    private void loadHoaDon() {
        defaultTableModel.setRowCount(0);
        List<ViewHoaDon> hoaDons = hoaDonService.getAllHoaDon();
        for (ViewHoaDon hoaDon : hoaDons) {
            defaultTableModel.addRow(new Object[]{hoaDon.getMahd(), hoaDon.getKhachhang(), hoaDon.getNhanvien(), hoaDon.getNgaylap()});
        }
    }
    private void loadSanPham() {
        defaultTableModel1.setRowCount(0);
        DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
        int selectedRowIndex = tableHoaDon.getSelectedRow();
        int mahd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
        List<ChiTietHoaDonDienThoai> ctdt = chiTietHoaDonService.getChiTietHoaDonDienThoai(mahd);
        List<ChiTietHoaDonPhuKien> ctpk = chiTietHoaDonService.getChiTietHoaDonPhuKien(mahd);
        for (ChiTietHoaDonDienThoai chiTietHoaDonDienThoai : ctdt) {
            defaultTableModel1.addRow(new Object[]{chiTietHoaDonDienThoai.getMadt(), chiTietHoaDonDienThoai.getTendt(), "DT", chiTietHoaDonDienThoai.getGiaban(),
                    chiTietHoaDonDienThoai.getPhamtramgiam(), chiTietHoaDonDienThoai.getSoluong()});
        }
        for (ChiTietHoaDonPhuKien chiTietHoaDonPhuKien1 : ctpk) {
            defaultTableModel1.addRow(new Object[]{chiTietHoaDonPhuKien1.getMapk(), chiTietHoaDonPhuKien1.getTenpk(), "PK", chiTietHoaDonPhuKien1.getGiaban(),
                    chiTietHoaDonPhuKien1.getPhamtramgiam(), chiTietHoaDonPhuKien1.getSoluong()});
        }
    }
}


