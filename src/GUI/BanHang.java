package GUI;

import Service.*;
import model.*;
import Exception.EmptyInputException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public class BanHang extends JFrame {
    private JPanel mainPanel;
    private JTable tableDienThoai;
    private JTable tablePhuKien;
    private JTable tableSanPham;
    private JCheckBox checkKhachMoi;
    private JCheckBox checkKhachDaMua;
    private JTextField textMaSanPham;
    private JTextField textTenSanPham;
    private JTextField textGiamGia;
    private JTextField textNhaSanXuat;
    private JTextField textDonGia;
    private JTextField textThanhTien;
    private JTextField textMaKhachHang;
    private JTextField textHoKhachHang;
    private JTextField textTenKhachHang;
    private JTextField textSoDienThoai;
    private JTextField textDiaChi;
    private JButton btnThemSanPham;
    private JButton btnThemKhachHang;
    private JButton btnTimKiem;
    private JButton btnXoaHet;
    private JButton btnXoaSanPham;
    private JScrollPane panelTableDienThoai;
    private JScrollPane panelTablePhuKien;
    private JScrollPane panelTabelSanPham;
    private JButton btnQuayVe;
    private JButton btnXacNhan;
    private JSpinner spinnerSoLuong;
    private JTextField textTongTien;
    private JTextField textLoaiSanPham;
    private JComboBox comboBoxLoaiKH;
    private JPanel panelPhuKien;
    private JPanel panelListSanPham;
    private JPanel panelSanPham;
    private JPanel panelThongTin;
    private JPanel panelTextThongTin;
    private JPanel panelTTSanPham;
    private JPanel panelTTKhachHang;
    private JPanel panelBanHang;
    private JPanel panelDienThoai;
    private JPanel panelEditSanPham;
    private JPanel panelBtn;
    private JLabel labelTenNhanVien;
    private JTextField textSoLuong;
    DienThoaiService dienThoaiService;
    DefaultTableModel defaultTableModelDienThoai;
    DienThoai dienThoai;
    LoaiPhuKienService loaiPhuKienService;
    KhachHangService khachHangService;
    NhaSanXuatService nhaSanXuatService;
    PhuKienService phuKienService;
    HoaDonService hoaDonService;
    ChiTietHoaDonService chiTietHoaDonService;
    DefaultTableModel defaultTableModelPhuKien;
    PhuKien phuKien;
    DefaultTableModel defaultTableModelSanPham;
    DecimalFormat decimalFormat = new DecimalFormat("#,###");
    InputCheck inputChecker;
    int giaban = 0;
    int giamgia = 0;
    float thanhtien = 0;
    float tongtien = 0;

    public BanHang(NhanVien nhanVien){
        setContentPane(mainPanel);
        mainPanel.setBackground(new Color(32,178,170,255));
        panelBanHang.setBackground(new Color(32,178,170,255));
        panelBtn.setBackground(new Color(32,178,170,255));
        panelDienThoai.setBackground(new Color(0,128,128,255));
        panelTabelSanPham.setBackground(new Color(32,178,170,255));
        panelEditSanPham.setBackground(new Color(32,178,170,255));
        panelTablePhuKien.setBackground(new Color(32,178,170,255));
        panelListSanPham.setBackground(new Color(32,178,170,255));
        panelListSanPham.setBackground(new Color(32,178,170,255));
        panelPhuKien.setBackground(new Color(0,128,128,255));
        panelSanPham.setBackground(new Color(0,128,128,255));
        panelTextThongTin.setBackground(new Color(32,178,170,255));
        panelThongTin.setBackground(new Color(0,128,128,255));
        panelTTKhachHang.setBackground(new Color(32,178,170,255));
        panelTTSanPham.setBackground(new Color(32,178,170,255));
        checkKhachMoi.setBackground(new Color(32,178,170,255));
        checkKhachDaMua.setBackground(new Color(32,178,170,255));
        btnXoaSanPham.setBackground(Color.yellow);
        btnXoaHet.setBackground(Color.RED);
        btnXacNhan.setBackground(Color.GREEN);
        btnThemKhachHang.setBackground(new Color(83,150,237,255));
        btnThemSanPham.setBackground(new Color(83,150,237,255));
        btnTimKiem.setBackground(new Color(62,183,114,255));
        btnQuayVe.setBackground(new Color(62,183,114,255));
        labelTenNhanVien.setBackground(new Color(156, 255, 4));
        setTitle("Bán Hàng");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        dienThoai = new DienThoai();
        nhaSanXuatService = new NhaSanXuatService();
        dienThoaiService = new DienThoaiService();
        defaultTableModelDienThoai = new DefaultTableModel();
        tableDienThoai.setModel(defaultTableModelDienThoai);
        phuKien = new PhuKien();
        loaiPhuKienService = new LoaiPhuKienService();
        nhaSanXuatService = new NhaSanXuatService();
        phuKienService = new PhuKienService();
        defaultTableModelPhuKien = new DefaultTableModel();
        tablePhuKien.setModel(defaultTableModelPhuKien);
        defaultTableModelSanPham = new DefaultTableModel();
        tableSanPham.setModel(defaultTableModelSanPham);
        khachHangService = new KhachHangService();
        hoaDonService = new HoaDonService();
        chiTietHoaDonService = new ChiTietHoaDonService();
        labelTenNhanVien.setText(nhanVien.getTennv());
        inputChecker = new ThrowException();

        defaultTableModelDienThoai.addColumn("Ma DT");
        defaultTableModelDienThoai.addColumn("Tên điện thoại");
        defaultTableModelDienThoai.addColumn("Nhà sản Xuất");
        defaultTableModelDienThoai.addColumn("Số luọng");
        defaultTableModelDienThoai.addColumn("Giá Bán");
        defaultTableModelDienThoai.addColumn("Giảm giá(%)");

        defaultTableModelPhuKien.addColumn("Mã PK");
        defaultTableModelPhuKien.addColumn("Tên Phụ Kiện");
        defaultTableModelPhuKien.addColumn("Nhà Sản Xuất");
        defaultTableModelPhuKien.addColumn("Số Lượng");
        defaultTableModelPhuKien.addColumn("Giá Bán");
        defaultTableModelPhuKien.addColumn("Giảm Giá");

        defaultTableModelSanPham.addColumn("Mã Sản Phẩm");
        defaultTableModelSanPham.addColumn("Tên Sản Phẩm");
        defaultTableModelSanPham.addColumn("Loại Sản Phẩm");
        defaultTableModelSanPham.addColumn("Số Lượng");
        defaultTableModelSanPham.addColumn("Giá Bán");
        defaultTableModelSanPham.addColumn("Giảm Giá");
        defaultTableModelSanPham.addColumn("Thành Tiền");

        reloadDataPhuKien(phuKienService.getAllPhuKien());
        reloadDataDienThoai(dienThoaiService.getAllDienThoai());

        JScrollPane scrollPaneDienThoai = new JScrollPane(tableDienThoai);
        panelTableDienThoai.setViewportView(scrollPaneDienThoai);
        JScrollPane scrollPanePhuKien = new JScrollPane(tablePhuKien);
        panelTablePhuKien.setViewportView(scrollPanePhuKien);
        JScrollPane scrollPanesanPham = new JScrollPane(tableSanPham);
        panelTabelSanPham.setViewportView(scrollPanesanPham);

        List<LoaiKhachHang> loaiKhachHangs = khachHangService.getAllLoaiKhachHang();
        for(LoaiKhachHang loaiKhachHang : loaiKhachHangs){
            comboBoxLoaiKH.addItem(loaiKhachHang.getTenloaikh());
        }


        tableDienThoai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                giaban = 0;
                giamgia = 0;
                DefaultTableModel model = (DefaultTableModel)tableDienThoai.getModel();
                int selectedRowIndex = tableDienThoai.getSelectedRow();
                textMaSanPham.setText(model.getValueAt(selectedRowIndex, 0).toString());
                textTenSanPham.setText(model.getValueAt(selectedRowIndex, 1).toString());
                textGiamGia.setText(model.getValueAt(selectedRowIndex, 5).toString());
                textLoaiSanPham.setText("DT");
                textNhaSanXuat.setText(model.getValueAt(selectedRowIndex, 2).toString());
                textDonGia.setText(decimalFormat.format(Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString())));
                spinnerSoLuong.setValue(0);
                giaban = Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString());
                giamgia = Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString());
            }
        });

        tablePhuKien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                giaban = 0;
                giamgia = 0;
                DefaultTableModel model = (DefaultTableModel)tablePhuKien.getModel();
                int selectedRowIndex = tablePhuKien.getSelectedRow();
                textMaSanPham.setText(model.getValueAt(selectedRowIndex, 0).toString());
                textTenSanPham.setText(model.getValueAt(selectedRowIndex, 1).toString());
                textGiamGia.setText(model.getValueAt(selectedRowIndex, 5).toString());
                textLoaiSanPham.setText("PK");
                textNhaSanXuat.setText(model.getValueAt(selectedRowIndex, 2).toString());
                textDonGia.setText(decimalFormat.format(Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString())));
                spinnerSoLuong.setValue(0);
                giaban = Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString());
                giamgia = Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString());
            }
        });
        spinnerSoLuong.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                int soluong = (int)spinnerSoLuong.getValue();
                if(soluong < 0){
                    spinnerSoLuong.setValue(0);
                }else{
                    float tiengiam = giaban*((float)giamgia / 100);
                    thanhtien = (giaban - tiengiam)*soluong;
                    textThanhTien.setText(decimalFormat.format(thanhtien));
                }
            }
        });

        btnThemSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DecimalFormat decimalFormat1 = new DecimalFormat("####");
                String masp = textMaSanPham.getText();
                String tensp = textTenSanPham.getText();
                String loaisp = textLoaiSanPham.getText();
                int soluong = (int)spinnerSoLuong.getValue();
                String giaban = textDonGia.getText();
                String giamgia = textGiamGia.getText();
                if(soluong != 0){
                    tongtien += thanhtien;
                    textTongTien.setText(decimalFormat.format(tongtien));
                    defaultTableModelSanPham.addRow(new Object[]{masp, tensp, loaisp, soluong, giaban, giamgia,decimalFormat1.format(thanhtien)});
                }else{

                    JOptionPane.showMessageDialog(mainPanel, "Vui lòng nhập số lượng sản phẩm .");
                }
            }
        });
        btnXoaSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = JOptionPane.showConfirmDialog(mainPanel, "Bạn chắc chắn muốn xóa ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel)tableSanPham.getModel();
                    int selectedRowIndex = tableSanPham.getSelectedRow();
                    if (selectedRowIndex != -1) {
                        int giatien = Integer.parseInt(model.getValueAt(selectedRowIndex, 6).toString());
                        tongtien -= giatien;
                        defaultTableModelSanPham.removeRow(selectedRowIndex);
                        textTongTien.setText(decimalFormat.format(tongtien));
                        defaultTableModelSanPham.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Vui lòng chọn cột để xóa");
                    }
                }

            }
        });
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String sodienthoai = textSoDienThoai.getText();
                if(sodienthoai.isEmpty()){
                    JOptionPane.showMessageDialog(mainPanel, "Vui lòng nhập số điện thoại");
                }
                else{
                    ViewKhachHang khachHang = khachHangService.findKhachHang(sodienthoai);
                    textMaKhachHang.setText(String.valueOf(khachHang.getMakh()));
                    textHoKhachHang.setText(khachHang.getHokh());
                    comboBoxLoaiKH.setSelectedItem(khachHang.getLoaikh());
                    textTenKhachHang.setText(khachHang.getTenkh());
                    textDiaChi.setText(khachHang.getDiachi());
                }
            }
        });
        btnXoaHet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = JOptionPane.showConfirmDialog(mainPanel, "Bạn chắc chắn muốn xóa ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel)tableSanPham.getModel();
                    int rowCount = model.getRowCount();
                    for (int i = rowCount - 1; i >= 0; i--) {
                        model.removeRow(i);
                    }
                    textTongTien.setText("0");
                }
            }
        });
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = JOptionPane.showConfirmDialog(mainPanel, "xác nhận tạo hóa đơn này ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    HoaDon addHoaDon = new HoaDon();
                    String sodienthoai = textSoDienThoai.getText();
                    ViewKhachHang khachHang = khachHangService.findKhachHang(sodienthoai);
                    int makh = khachHang.getMakh();
                    addHoaDon.setMakh(makh);
                    addHoaDon.setManv(nhanVien.getManv());
                    LocalDate currentDate = LocalDate.now();
                    Date sqlDate = Date.valueOf(currentDate);
                    addHoaDon.setNgaylap(sqlDate);
                    hoaDonService.insertHoaDon(addHoaDon);

                    int mahd = hoaDonService.getMaHoaDonCuoiCung();
                    DefaultTableModel tableModel = (DefaultTableModel) tableSanPham.getModel();
                    int rowCount = tableModel.getRowCount();
                    try {
                        for (int row = 0; row < rowCount; row++) {
                            int masp = Integer.parseInt(String.valueOf(tableModel.getValueAt(row, 0)));
                            int soluong = Integer.parseInt(String.valueOf(tableModel.getValueAt(row, 3)));
                            String loaisp = String.valueOf(tableModel.getValueAt(row, 2));

                            if (loaisp.equals("DT")){
                                muadt mdt = new muadt();
                                mdt.setMahd(mahd);
                                mdt.setMadt(masp);
                                mdt.setSoluong(soluong);

                                chiTietHoaDonService.insertMuadt(mdt);
                                chiTietHoaDonService.subSoLuongDienThoai(masp, soluong);
                            }else{
                                muapk mpk = new muapk();
                                mpk.setMahd(mahd);
                                mpk.setMapk(masp);
                                mpk.setSoluong(soluong);
                                chiTietHoaDonService.insertMuapk(mpk);
                                chiTietHoaDonService.subSoLuongPhuKien(masp, soluong);
                            }
                        }
                        reloadDataPhuKien(phuKienService.getAllPhuKien());
                        reloadDataDienThoai(dienThoaiService.getAllDienThoai());
                        JOptionPane.showMessageDialog(mainPanel, "Tạo hóa đơn thành công");
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(mainPanel, "Tạo hóa đơn thất bại");
                    }
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
        btnThemKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = JOptionPane.showConfirmDialog(mainPanel, "xác nhận thêm khách hàng này ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    try{
                        inputChecker.checkInput(textHoKhachHang.getText());
                        inputChecker.checkInput(textTenKhachHang.getText());
                        inputChecker.checkInput(textSoDienThoai.getText());
                        inputChecker.checkInput(textDiaChi.getText());
                        KhachHang addKhachHang = new KhachHang();
                        addKhachHang.setHokh(textHoKhachHang.getText());
                        addKhachHang.setTenkh(textTenKhachHang.getText());
                        String loaikh = String.valueOf(comboBoxLoaiKH.getSelectedItem());
                        addKhachHang.setLoaikh(khachHangService.getMaLoaiKhachHang(loaikh));
                        addKhachHang.setSodienthoai(textSoDienThoai.getText());
                        addKhachHang.setDiachi(textDiaChi.getText());
                        khachHangService.insertKhachHang(addKhachHang);

                        JOptionPane.showMessageDialog(mainPanel, "Thêm khách hàng thành công");
                    }catch (EmptyInputException e){
                        JOptionPane.showMessageDialog(mainPanel, "Thêm khách hàng thất bại");
                    }
                }

            }
        });
        checkKhachMoi.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                checkKhachDaMua.setSelected(false);
                btnThemKhachHang.enable(true);
                textHoKhachHang.enable(true);
                textTenKhachHang.enable(true);
                comboBoxLoaiKH.enable(true);
                textDiaChi.enable(true);
                textHoKhachHang.setText("");
                textMaKhachHang.setText("");
                textTenKhachHang.setText("");
                textSoDienThoai.setText("");
                textDiaChi.setText("");
            }
        });

        checkKhachDaMua.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                checkKhachMoi.setSelected(false);
                btnThemKhachHang.enable(false);
                textHoKhachHang.enable(false);
                textTenKhachHang.enable(false);
                comboBoxLoaiKH.enable(false);
                textDiaChi.enable(false);

                textHoKhachHang.setText("");
                textMaKhachHang.setText("");
                textTenKhachHang.setText("");
                textSoDienThoai.setText("");
                textDiaChi.setText("");
            }
        });
    }

    private void reloadDataDienThoai(List<ViewDienThoai> dienThoais){
        defaultTableModelDienThoai.setRowCount(0);
        for(ViewDienThoai dienThoai : dienThoais){
            defaultTableModelDienThoai.addRow(new Object[]{dienThoai.getMadt(), dienThoai.getTendt(), dienThoai.getNhasx(), dienThoai
                    .getSoluong(), dienThoai.getGiaban(), dienThoai.getPhantramgiam()});
        }
    }
    private void reloadDataPhuKien(List<ViewPhuKien> phuKiens){
        defaultTableModelPhuKien.setRowCount(0);
        for(ViewPhuKien phuKien : phuKiens){
            defaultTableModelPhuKien.addRow(new Object[]{phuKien.getMapk(), phuKien.getTenpk(), phuKien.getTenhsx(), phuKien
                    .getSoluong(), phuKien.getGiaban(), phuKien.getPhantramgiam()});
        }
    }
}
