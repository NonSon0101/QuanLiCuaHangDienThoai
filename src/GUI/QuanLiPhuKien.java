package GUI;

import Service.LoaiPhuKienService;
import Service.NhaSanXuatService;
import Service.PhuKienService;
import model.*;
import Exception.EmptyInputException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

public class QuanLiPhuKien extends JFrame {
    private JPanel panelBtn;
    private JButton refreshBtn;
    private JButton btnThem;
    private JButton btnXoa;
    private JButton btnCapNhat;
    private JScrollPane panelTable;
    private JTable tablePhuKien;
    private JTextField textTenPhuKien;
    private JTextField textSoLuong;
    private JLabel labelTenPhuKien;
    private JLabel labelSoLuong;
    private JLabel labelNhaSanXuat;
    private JLabel labelGiaBan;
    private JTextField textGiaBan;
    private JLabel labelGiamGia;
    private JTextField textGiamGia;
    private JComboBox comboBoxNsx;
    private JButton btnXacNhan;
    private JPanel mainPanel;
    private JLabel labelLoaiPhuKien;
    private JComboBox comboBoxLoaiPhuKien;
    private JButton btnQuayVe;
    private JPanel panelInfo;
    InputCheck inputChecker;
    LoaiPhuKienService loaiPhuKienService;
    NhaSanXuatService nhaSanXuatService;
    PhuKienService phuKienService;
    PhuKien phuKien;
    DefaultTableModel defaultTableModel;

    DecimalFormat decimalFormat = new DecimalFormat("#,###");

    int option = 0;


    public QuanLiPhuKien(NhanVien nhanVien) {
        setContentPane(mainPanel);
        setTitle("Quản Lí Phụ Kiện");
        mainPanel.setBackground(new Color(32,178,170,255));
        panelInfo.setBackground(new Color(32,178,170,255));
        panelBtn.setBackground(new Color(32,178,170,255));
        panelTable.setBackground(new Color(32,178,170,255));
        btnThem.setBackground(Color.GREEN);
        btnXoa.setBackground(Color.RED);
        btnCapNhat.setBackground(Color.YELLOW);
        btnQuayVe.setBackground(new Color(0,190,98,255));
        btnXacNhan.setBackground(new Color(92,169,112,255));
        refreshBtn.setBackground(new Color(92,169,112,255));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        String chucvu = nhanVien.getChucvu();
        if(chucvu.equals("Nhân viên")){
            btnThem.setVisible(false);
            btnCapNhat.setVisible(false);
            btnXoa.setVisible(false);
            btnXacNhan.setVisible(false);
        }

        phuKien = new PhuKien();
        loaiPhuKienService = new LoaiPhuKienService();
        nhaSanXuatService = new NhaSanXuatService();
        phuKienService = new PhuKienService();
        defaultTableModel = new DefaultTableModel();
        tablePhuKien.setModel(defaultTableModel);
        inputChecker = new ThrowException();

        defaultTableModel.addColumn("Mã PK");
        defaultTableModel.addColumn("Tên Phụ Kiện");
        defaultTableModel.addColumn("Loại Phụ Kiện");
        defaultTableModel.addColumn("Nhà Sản Xuất");
        defaultTableModel.addColumn("Số Lượng");
        defaultTableModel.addColumn("Giá Bán");
        defaultTableModel.addColumn("Giảm Giá");

        reloadData(phuKienService.getAllPhuKien());

        JScrollPane scrollPane = new JScrollPane(tablePhuKien);
        panelTable.setViewportView(scrollPane);

        List<LoaiPhuKien> loaiPhuKiens = loaiPhuKienService.getAllLoaiPhuKien();
        for (LoaiPhuKien loaiPhuKien : loaiPhuKiens) {
            comboBoxLoaiPhuKien.addItem(loaiPhuKien.getTenlpk());
        }

        List<HangSanXuat> hangSanXuats = nhaSanXuatService.getAllHangSanXuat();
        for (HangSanXuat hangSanXuat : hangSanXuats) {
            comboBoxNsx.addItem(hangSanXuat.getTenhsx());
        }

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reloadData(phuKienService.getAllPhuKien());
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enabelInfo();
                option = 1;
                textTenPhuKien.setText("");
                textSoLuong.setText("");
                textGiaBan.setText("");
                textGiamGia.setText("");
            }
        });
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (option == 1) {
                    try{
                        phuKien.setTenpk(textTenPhuKien.getText());
                        inputChecker.checkInput(textTenPhuKien.getText());
                        try {
                            phuKien.setGiaban(Integer.parseInt(textGiaBan.getText()));
                            phuKien.setSoluong(Integer.parseInt(textSoLuong.getText()));
                            phuKien.setPhantramgiam(Integer.parseInt(textGiamGia.getText()));
                            int maplk = loaiPhuKienService.getMaLoaiPhuKien(String.valueOf(comboBoxLoaiPhuKien.getSelectedItem()));
                            phuKien.setLoaipk(maplk);
                            int mansx = nhaSanXuatService.getMaHangSanXuat(String.valueOf(comboBoxNsx.getSelectedItem()));
                            phuKien.setMansx(mansx);
                            int res = JOptionPane.showConfirmDialog(mainPanel, "Xác nhận thêm sản phẩm này ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if(res == JOptionPane.YES_OPTION){
                                phuKienService.addPhuKien(phuKien);
                                reloadData(phuKienService.getAllPhuKien());
                                JOptionPane.showMessageDialog(mainPanel, "Thêm thành công");
                                option = 0;
                                disableInfo();
                            }

                        }catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(mainPanel, "Số lượng, giá bán, Giảm giá không được bỏ trống và không nhập bằng chữ");
                        }
                    }
                    catch (EmptyInputException ex){
                        JOptionPane.showMessageDialog(mainPanel, "Vui lòng nhập tên sản phẩm");
                    }

                }
                if (option == 2) {
                    DefaultTableModel model = (DefaultTableModel) tablePhuKien.getModel();
                    int selectedRowIndex = tablePhuKien.getSelectedRow();
                    int mapk = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
                    int maplk = loaiPhuKienService.getMaLoaiPhuKien(String.valueOf(comboBoxLoaiPhuKien.getSelectedItem()));
                    int mansx = nhaSanXuatService.getMaHangSanXuat(String.valueOf(comboBoxNsx.getSelectedItem()));
                    try{
                        phuKien.setTenpk(textTenPhuKien.getText());
                        inputChecker.checkInput(textTenPhuKien.getText());
                        try {
                            phuKien.setGiaban(Integer.parseInt(textGiaBan.getText()));
                            phuKien.setSoluong(Integer.parseInt(textSoLuong.getText()));
                            phuKien.setPhantramgiam(Integer.parseInt(textGiamGia.getText()));
                            phuKien.setMansx(mansx);
                            phuKien.setLoaipk(maplk);
                            phuKien.setMapk(mapk);
                            int res = JOptionPane.showConfirmDialog(mainPanel, "Xác nhận cập nhật sản phẩm này ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if(res == JOptionPane.YES_OPTION){
                                phuKienService.updatePhuKien(phuKien);
                                reloadData(phuKienService.getAllPhuKien());
                                JOptionPane.showMessageDialog(mainPanel, "Cập nhật thành công");
                                option = 0;
                                disableInfo();
                            }
                        }catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(mainPanel, "Số lượng, giá bán, Giảm giá không được bỏ trống và không nhập bằng chữ");
                        }
                    }
                    catch (EmptyInputException ex){
                        JOptionPane.showMessageDialog(mainPanel, "Vui lòng nhập tên sản phẩm");
                    }

                }
            }
        });
        tablePhuKien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setInfo();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int res = JOptionPane.showConfirmDialog(mainPanel, "Bạn chắc chắn muốn xóa ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (res == JOptionPane.YES_OPTION) {
                        DefaultTableModel model = (DefaultTableModel) tablePhuKien.getModel();
                        int selectedRowIndex = tablePhuKien.getSelectedRow();
                        int mansx = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
                        phuKienService.deletePhuKien(mansx);
                        reloadData(phuKienService.getAllPhuKien());
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(mainPanel, "Xóa thất bại");
                }
            }
        });
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                option = 2;
                enabelInfo();
                setInfo();
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

    private void reloadData(List<ViewPhuKien> phuKiens) {
        defaultTableModel.setRowCount(0);
        for (ViewPhuKien phuKien : phuKiens) {
            defaultTableModel.addRow(new Object[]{phuKien.getMapk(), phuKien.getTenpk(), phuKien.getTenloaipk(), phuKien.getTenhsx(), phuKien
                    .getSoluong(), phuKien.getGiaban(), phuKien.getPhantramgiam()});
        }
    }

    private void disableInfo() {
        textTenPhuKien.enable(false);
        textSoLuong.enable(false);
        textGiaBan.enable(false);
        textGiamGia.enable(false);
        comboBoxNsx.enable(false);
        comboBoxLoaiPhuKien.enable(false);
    }

    private void enabelInfo() {
        textTenPhuKien.enable(true);
        textSoLuong.enable(true);
        textGiaBan.enable(true);
        textGiamGia.enable(true);
        comboBoxNsx.enable(true);
        comboBoxLoaiPhuKien.enable(true);
    }

    private void setInfo() {
        DefaultTableModel model = (DefaultTableModel) tablePhuKien.getModel();
        int selectedRowIndex = tablePhuKien.getSelectedRow();
        textTenPhuKien.setText(model.getValueAt(selectedRowIndex, 1).toString());
        textSoLuong.setText(model.getValueAt(selectedRowIndex, 4).toString());
        textGiamGia.setText(model.getValueAt(selectedRowIndex, 6).toString());
        textGiaBan.setText(decimalFormat.format(Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString())));
        comboBoxNsx.setSelectedItem(model.getValueAt(selectedRowIndex, 3).toString());
        comboBoxLoaiPhuKien.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
    }
}

