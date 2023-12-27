package GUI;

import Service.KhachHangService;
import model.KhachHang;
import model.LoaiKhachHang;
import model.NhanVien;
import model.ViewKhachHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class QuanLiKhachHang extends JFrame {
    private JPanel panelBtn;
    private JButton refreshBtn;
    private JButton btnCapNhat;
    private JButton btnQuayVe;
    private JScrollPane panelTable;
    private JTable tabelKhachHang;
    private JTextField textHoKhachHang;
    private JTextField textTenKhachHang;
    private JLabel labelTenDienThoai;
    private JLabel labelSoLuong;
    private JLabel labelNhaSanXuat;
    private JLabel labelGiaBan;
    private JTextField textDiaChi;
    private JLabel labelGiamGia;
    private JTextField textSoDienThoai;
    private JComboBox comboBoxLoaiKH;
    private JButton btnXacNhan;
    private JPanel mainPanel;
    private JPanel panelInfo;

    private KhachHangService khachHangService;

    private DefaultTableModel defaultTableModel;

    public QuanLiKhachHang(NhanVien nhanVien){
        setContentPane(mainPanel);
        setTitle("Quản Lí Điện Thoại");
        mainPanel.setBackground(new Color(32,178,170,255));
        panelInfo.setBackground(new Color(32,178,170,255));
        panelBtn.setBackground(new Color(32,178,170,255));
        panelTable.setBackground(new Color(32,178,170,255));
        btnCapNhat.setBackground(Color.YELLOW);
        btnQuayVe.setBackground(new Color(0,190,98,255));
        btnXacNhan.setBackground(new Color(92,169,112,255));
        refreshBtn.setBackground(new Color(92,169,112,255));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        khachHangService = new KhachHangService();
        defaultTableModel = new DefaultTableModel();
        tabelKhachHang.setModel(defaultTableModel);

        defaultTableModel.addColumn("Mã Khách Hàng ");
        defaultTableModel.addColumn("Họ Khách Hàng ");
        defaultTableModel.addColumn("Tên Khách Hàng ");
        defaultTableModel.addColumn("Loại Khách Hàng ");
        defaultTableModel.addColumn("Số Điện Thoại ");
        defaultTableModel.addColumn("Địa Chỉ");

        List<LoaiKhachHang> loaiKhachHangs = khachHangService.getAllLoaiKhachHang();
        for(LoaiKhachHang loaiKhachHang : loaiKhachHangs){
            comboBoxLoaiKH.addItem(loaiKhachHang.getTenloaikh());
        }

        reloadData(khachHangService.getAllKhachHang());

        JScrollPane scrollPane = new JScrollPane(tabelKhachHang);
        panelTable.setViewportView(scrollPane);

        tabelKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setInfo();
                textHoKhachHang.enable(false);
                textTenKhachHang.enable(false);
                comboBoxLoaiKH.enable(false);
                textSoDienThoai.enable(false);
                textDiaChi.enable(false);
            }
        });
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textHoKhachHang.enable(true);
                textTenKhachHang.enable(true);
                comboBoxLoaiKH.enable(true);
                textSoDienThoai.enable(true);
                textDiaChi.enable(true);
                setInfo();
            }
        });
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultTableModel model = (DefaultTableModel)tabelKhachHang.getModel();
                int selectedRowIndex = tabelKhachHang.getSelectedRow();
                int makh = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
                KhachHang khachHang = new KhachHang();
                khachHang.setMakh(makh);
                khachHang.setHokh(textHoKhachHang.getText());
                khachHang.setTenkh(textTenKhachHang.getText());
                String loaikh = String.valueOf(comboBoxLoaiKH.getSelectedItem());
                khachHang.setLoaikh(khachHangService.getMaLoaiKhachHang(loaikh));
                khachHang.setSodienthoai(textSoDienThoai.getText());
                khachHang.setDiachi(textDiaChi.getText());
                try {
                    khachHangService.updateKhachHang(khachHang);
                    JOptionPane.showMessageDialog(mainPanel, "Cập nhật thành công");
                    reloadData(khachHangService.getAllKhachHang());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Cập nhật thất bại. Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reloadData(khachHangService.getAllKhachHang());
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
    private void setInfo(){
        DefaultTableModel model = (DefaultTableModel)tabelKhachHang.getModel();
        int selectedRowIndex = tabelKhachHang.getSelectedRow();
        textHoKhachHang.setText(model.getValueAt(selectedRowIndex, 1).toString());
        textTenKhachHang.setText(model.getValueAt(selectedRowIndex, 2).toString());
        comboBoxLoaiKH.setSelectedItem(String.valueOf((model.getValueAt(selectedRowIndex, 3).toString())));
        textSoDienThoai.setText(model.getValueAt(selectedRowIndex, 4).toString());
        textDiaChi.setText(model.getValueAt(selectedRowIndex, 5).toString());
    }

    private void reloadData(List<ViewKhachHang> khachHangs){
        defaultTableModel.setRowCount(0);
        for(ViewKhachHang khachHang : khachHangs){
            defaultTableModel.addRow(new Object[]{khachHang.getMakh(), khachHang.getHokh(), khachHang.getTenkh(), khachHang
                    .getLoaikh(), khachHang.getSodienthoai(), khachHang.getDiachi()});
        }
    }
}
