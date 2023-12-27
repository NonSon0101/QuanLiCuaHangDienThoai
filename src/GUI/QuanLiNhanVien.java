package GUI;

import Service.NhanVienService;
import model.NhanVien;
import Exception.EmptyInputException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class QuanLiNhanVien extends JFrame {
    private JTextField textTenNhanVien;
    private JTextField textSoDienThoai;
    private JLabel labelGiaBan;
    private JTextField textDiaChi;
    private JLabel labelGiamGia;
    private JTextField textUsername;
    private JComboBox comboBoxChucVu;
    private JLabel labelLoaiPhuKien;
    private JButton btnXacNhan;
    private JScrollPane panelTable;
    private JTable tableNhanVien;
    private JPanel panelBtn;
    private JButton refreshBtn;
    private JButton btnThem;
    private JButton btnXoa;
    private JButton btnCapNhat;
    private JButton btnQuayVe;
    private JTextField textPassWord;
    private JPanel panelThongTin;
    private JPanel mainPanel;
    DefaultTableModel defaultTableModel;
    NhanVien nhanvien;
    NhanVienService nhanVienService;
    InputCheck inputCheck;
    int option = 0;
    public QuanLiNhanVien(NhanVien nhanVien){
        setContentPane(mainPanel);
        mainPanel.setBackground(new Color(32,178,170,255));
        panelThongTin.setBackground(new Color(32,178,170,255));
        panelBtn.setBackground(new Color(32,178,170,255));
        panelTable.setBackground(new Color(32,178,170,255));
        btnThem.setBackground(Color.GREEN);
        btnXoa.setBackground(Color.RED);
        btnCapNhat.setBackground(Color.YELLOW);
        btnQuayVe.setBackground(new Color(0,190,98,255));
        btnXacNhan.setBackground(new Color(92,169,112,255));
        refreshBtn.setBackground(new Color(92,169,112,255));
        setTitle("Quản Lí Hóa Đơn");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        nhanvien = new NhanVien();
        defaultTableModel = new DefaultTableModel();
        nhanVienService = new NhanVienService();
        tableNhanVien.setModel(defaultTableModel);
        inputCheck = new ThrowException();

        defaultTableModel.addColumn("Mã nhân viên");
        defaultTableModel.addColumn("Tên Nhân Viên");
        defaultTableModel.addColumn("Chức vụ");
        defaultTableModel.addColumn("Số điện thoại");
        defaultTableModel.addColumn("Địa Chỉ");
        defaultTableModel.addColumn("Username");
        defaultTableModel.addColumn("Password");

        reloadData(nhanVienService.getAllNhanVien());
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        panelTable.setViewportView(scrollPane);


        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reloadData(nhanVienService.getAllNhanVien());
            }
        });
        btnQuayVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MainForm(nhanVien);
                dispose();
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enabelInfo();
                option = 1;
                textTenNhanVien.setText("");
                textDiaChi.setText("");
                textUsername.setText("");
                textSoDienThoai.setText("");
                textPassWord.setText("");
            }
        });

        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(option == 1){
                    try{
                        inputCheck.checkInput(textTenNhanVien.getText());
                        inputCheck.checkInput(textSoDienThoai.getText());
                        inputCheck.checkInput(textDiaChi.getText());
                        inputCheck.checkInput(textUsername.getText());
                        inputCheck.checkInput(textPassWord.getText());
                        NhanVien nhanVien1 = new NhanVien();
                        nhanVien1.setTennv(textTenNhanVien.getText());
                        nhanVien1.setChucvu(String.valueOf(comboBoxChucVu.getSelectedItem()));
                        nhanVien1.setSodienthoai(textSoDienThoai.getText());
                        nhanVien1.setDiachi(textDiaChi.getText());
                        nhanVien1.setUsername(textUsername.getText());
                        nhanVien1.setPass(textPassWord.getText());
                        nhanVienService.insertNhanVien(nhanVien1);
                        reloadData(nhanVienService.getAllNhanVien());
                        JOptionPane.showMessageDialog(mainPanel, "Thêm Thành Công");
                    }catch (EmptyInputException e){
                        JOptionPane.showMessageDialog(mainPanel, "Thêm Thất Bại");
                    }
                }else{
                    try{
                        DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
                        int selectedRowIndex = tableNhanVien.getSelectedRow();
                        int manv = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
                        inputCheck.checkInput(textTenNhanVien.getText());
                        inputCheck.checkInput(textSoDienThoai.getText());
                        inputCheck.checkInput(textDiaChi.getText());
                        inputCheck.checkInput(textUsername.getText());
                        inputCheck.checkInput(textPassWord.getText());
                        NhanVien nhanVien1 = new NhanVien();
                        nhanVien1.setManv(manv);
                        nhanVien1.setTennv(textTenNhanVien.getText());
                        nhanVien1.setChucvu(String.valueOf(comboBoxChucVu.getSelectedItem()));
                        nhanVien1.setSodienthoai(textSoDienThoai.getText());
                        nhanVien1.setDiachi(textDiaChi.getText());
                        nhanVien1.setUsername(textUsername.getText());
                        nhanVien1.setPass(textPassWord.getText());
                        nhanVienService.updateNhanVien(nhanVien1);
                        reloadData(nhanVienService.getAllNhanVien());
                        JOptionPane.showMessageDialog(mainPanel, "Cập nhật Thành Công");
                    }catch (EmptyInputException e){
                        JOptionPane.showMessageDialog(mainPanel, "Cập nhật Thất Bại");
                    }
                }
            }
        });
        tableNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setInfo();
                disableInfo();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
                int selectedRowIndex = tableNhanVien.getSelectedRow();
                int manv = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
                try{
                    nhanVienService.deleteNhanVien(manv);
                    reloadData(nhanVienService.getAllNhanVien());
                    JOptionPane.showMessageDialog(mainPanel, "Xóa thành công");
                }catch (Exception e){
                    JOptionPane.showMessageDialog(mainPanel, "Xóa thất bại");
                }
            }
        });
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enabelInfo();
                setInfo();
            }
        });
    }
    private void reloadData(List<NhanVien> nhanViens){
        defaultTableModel.setRowCount(0);
        for(NhanVien nhanVien : nhanViens){
            defaultTableModel.addRow(new Object[]{nhanVien.getManv(), nhanVien.getTennv(), nhanVien.getChucvu(), nhanVien.getSodienthoai(), nhanVien.getDiachi(),
            nhanVien.getUsername(), nhanVien.getPass()});
        }
    }
    private void enabelInfo(){
        textTenNhanVien.enable(true);
        textSoDienThoai.enable(true);
        comboBoxChucVu.enable(true);
        textDiaChi.enable(true);
        textUsername.enable(true);
        textPassWord.enable(true);
    }
    private void disableInfo(){
        textTenNhanVien.enable(false);
        textSoDienThoai.enable(false);
        comboBoxChucVu.enable(false);
        textDiaChi.enable(false);
        textUsername.enable(false);
        textPassWord.enable(false);
    }
    private void setInfo(){
        DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
        int selectedRowIndex = tableNhanVien.getSelectedRow();
        textTenNhanVien.setText(model.getValueAt(selectedRowIndex, 1).toString());
        comboBoxChucVu.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
        textSoDienThoai.setText(model.getValueAt(selectedRowIndex, 3).toString());
        textDiaChi.setText(model.getValueAt(selectedRowIndex, 4).toString());
        textUsername.setText(model.getValueAt(selectedRowIndex, 5).toString());
        textPassWord.setText(model.getValueAt(selectedRowIndex, 6).toString());
    }
}
