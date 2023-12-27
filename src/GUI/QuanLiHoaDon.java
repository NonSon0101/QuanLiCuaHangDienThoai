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
    private JButton btnXoaSanPham;
    private JButton btnXoaHoaDon;
    private JTextField textMaSanPham;
    private JLabel labelMaSanPham;
    private JButton btnQuayVe;
    private JTextField textTongTien;
    private JLabel labelTongTien;
    private JPanel panelBtnCTHD;
    private JPanel panelBtnHD;
    private JPanel panelLabel1;
    private JPanel panelLabel2;
    private JScrollPane panelHThanhTien;
    private HoaDon hoaDon;
    private HoaDonService hoaDonService;
    private ChiTietHoaDonService chiTietHoaDonService;
    private DefaultTableModel defaultTableModel;
    private DefaultTableModel defaultTableModel1;
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
        btnXoaHoaDon.setBackground(Color.RED);
        btnXoaSanPham.setBackground(Color.RED);
        btnQuayVe.setBackground(new Color(11,193,105,255));
        panelLabel1.setBackground(new Color(0,128,128,255));
        panelLabel2.setBackground(new Color(0,128,128,255));
        setTitle("Quản Lí Hóa Đơn");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        String chucvu = nhanVien.getChucvu();
        if(chucvu.equals("Nhân viên")){
           btnXoaHoaDon.setVisible(false);
        }

        hoaDon = new HoaDon();
        hoaDonService = new HoaDonService();
        defaultTableModel = new DefaultTableModel();
        tableHoaDon.setModel(defaultTableModel);

        defaultTableModel.addColumn("Mã Hóa Đơn");
        defaultTableModel.addColumn("Tên Khách Hàng");
        defaultTableModel.addColumn("Tên Nhân Viên");
        defaultTableModel.addColumn("Ngày Lập");

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


                chiTietHoaDonDienThoai = new ChiTietHoaDonDienThoai();
                chiTietHoaDonPhuKien = new ChiTietHoaDonPhuKien();
                chiTietHoaDonService = new ChiTietHoaDonService();
                defaultTableModel1 = new DefaultTableModel();
                tableChitietHoaDon.setModel(defaultTableModel1);

                defaultTableModel1.addColumn("Mã Sản Phẩm");
                defaultTableModel1.addColumn("Tên Sản Phẩm");
                defaultTableModel1.addColumn("Loại Sản Phẩm");
                defaultTableModel1.addColumn("Giá Tiền");
                defaultTableModel1.addColumn("Giảm Giá");
                defaultTableModel1.addColumn("Số Lượng");

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
        btnXoaSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultTableModel model = (DefaultTableModel) tableChitietHoaDon.getModel();
                int selectedRowIndex = tableChitietHoaDon.getSelectedRow();
                int masp = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
                int soluong = Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString());
                String loaisp = model.getValueAt(selectedRowIndex, 2).toString();
                if (loaisp == "DT") {
                    try{
                        chiTietHoaDonService.addSoLuongDienThoai(masp, soluong);
                        chiTietHoaDonService.deleteSanPhamDT(masp);
                        JOptionPane.showMessageDialog(mainPanel, "Xóa thành công");
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(mainPanel, "Lỗi, xóa Thất bại");
                    }
                }
                if (loaisp == "PK") {
                    try {
                        chiTietHoaDonService.addSoLuongPhuKien(masp, soluong);
                        chiTietHoaDonService.deleteSanPhamPK(masp);
                        JOptionPane.showMessageDialog(mainPanel, "Xóa thành công");
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(mainPanel, "Lỗi, xóa Thất bại");
                    }
                }
                loadSanPham();
            }
        });
        btnXoaHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultTableModel model = (DefaultTableModel) tableChitietHoaDon.getModel();
                int selectedRowIndex = tableChitietHoaDon.getSelectedRow();
                int mahd = Integer.parseInt(textMaHd.getText());
                try{
                    chiTietHoaDonService.deleteSanPhamHoaDon(mahd);
                    hoaDonService.deleteHoaDon(mahd);
                    defaultTableModel1.setRowCount(0);
                    JOptionPane.showMessageDialog(mainPanel, "Xóa thành công");
                }catch (Exception e){
                    JOptionPane.showMessageDialog(mainPanel, "Lỗi, xóa Thất bại");
                }
                loadHoaDon();
            }
        });
        btnQuayVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MainForm(nhanVien);
                dispose();
            }
        });
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


