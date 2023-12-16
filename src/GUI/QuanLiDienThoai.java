package GUI;

import Service.DienThoaiService;
import Service.NhaSanXuatService;
import model.DienThoai;
import model.HangSanXuat;
import model.ViewDienThoai;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class QuanLiDienThoai extends JFrame{
    private JTable tableDienThoai;
    private JPanel mainPanel;
    private JButton refreshBtn;
    private JButton btnThem;
    private JButton btnXoa;
    private JPanel panelBtn;
    private JScrollPane panelTable;
    private JTextField textSoLuong;
    private JTextField textTenDienThoai;
    private JTextField textGiaBan;
    private JTextField textGiamGia;
    private JLabel labelTenDienThoai;
    private JComboBox comboBoxNsx;
    private JLabel labelNhaSanXuat;
    private JLabel labelSoLuong;
    private JLabel labelGiaBan;
    private JLabel labelGiamGia;
    private JButton btnCapNhat;
    private JButton btnXacNhan;

    NhaSanXuatService nhaSanXuatService;
    DienThoaiService dienThoaiService;
    DefaultTableModel defaultTableModel;
    DienThoai dienThoai;
    int option = 0;
    public QuanLiDienThoai(){
        setContentPane(mainPanel);
        setTitle("Quản Lí Điện Thoại");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        dienThoai = new DienThoai();
        nhaSanXuatService = new NhaSanXuatService();
        dienThoaiService = new DienThoaiService();
        defaultTableModel = new DefaultTableModel();
        tableDienThoai.setModel(defaultTableModel);

        defaultTableModel.addColumn("Ma DT");
        defaultTableModel.addColumn("Tên điện thoại");
        defaultTableModel.addColumn("Nhà sản Xuất");
        defaultTableModel.addColumn("Số luọng");
        defaultTableModel.addColumn("Giá");
        defaultTableModel.addColumn("Giảm giá(%)");

        reloadData(dienThoaiService.getAllDienThoai());

        List<HangSanXuat> hangSanXuats = nhaSanXuatService.getAllHangSanXuat();
        for (HangSanXuat hangSanXuat : hangSanXuats){
            comboBoxNsx.addItem(hangSanXuat.getTenhsx());
        }

        JScrollPane scrollPane = new JScrollPane(tableDienThoai);
        panelTable.setViewportView(scrollPane);
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                defaultTableModel.setRowCount(0);
                reloadData(dienThoaiService.getAllDienThoai());
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enableInfo();
                textGiamGia.setText("");
                textTenDienThoai.setText("");
                textSoLuong.setText("");
                textGiaBan.setText("");
                option = 1;
            }
        });

        tableDienThoai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setInfo();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = JOptionPane.showConfirmDialog(mainPanel, "Bạn chắc chắn muốn xóa ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(res == JOptionPane.YES_OPTION){
                    DefaultTableModel model = (DefaultTableModel)tableDienThoai.getModel();
                    int selectedRowIndex = tableDienThoai.getSelectedRow();
                    int madt = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
                    dienThoaiService.deleteDienThoai(madt);
                    reloadData(dienThoaiService.getAllDienThoai());
                }
            }
        });
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enableInfo();
                setInfo();
                option = 2;
            }
        });
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(option ==1){
                    dienThoai.setTendt(textTenDienThoai.getText());
                    dienThoai.setSoluong(Integer.parseInt(textSoLuong.getText()));
                    dienThoai.setGiaban(Integer.parseInt(textGiaBan.getText()));
                    dienThoai.setPhantramgiam(Integer.parseInt(textGiamGia.getText()));
                    int mansx = nhaSanXuatService.getMaHangSanXuat(String.valueOf(comboBoxNsx.getSelectedItem()));
                    dienThoai.setMansx(mansx);

                    dienThoaiService.addDienThoai(dienThoai);
                    reloadData(dienThoaiService.getAllDienThoai());
                    option = 0;
                    disableInfo();
                }
               if (option == 2){
                   DefaultTableModel model = (DefaultTableModel)tableDienThoai.getModel();
                   int selectedRowIndex = tableDienThoai.getSelectedRow();
                   dienThoai.setMadt(Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()));
                   dienThoai.setTendt(textTenDienThoai.getText());
                   dienThoai.setSoluong(Integer.parseInt(textSoLuong.getText()));
                   dienThoai.setGiaban(Integer.parseInt(textGiaBan.getText()));
                   dienThoai.setPhantramgiam(Integer.parseInt(textGiamGia.getText()));
                   int mansx = nhaSanXuatService.getMaHangSanXuat(String.valueOf(comboBoxNsx.getSelectedItem()));
                   dienThoai.setMansx(mansx);

                   dienThoaiService.updateDienThoai(dienThoai);
                   reloadData(dienThoaiService.getAllDienThoai());
                   option = 0;
                    disableInfo();
               }
            }
        });
    }
    private void reloadData(List<ViewDienThoai> dienThoais){
        defaultTableModel.setRowCount(0);
        for(ViewDienThoai dienThoai : dienThoais){
            defaultTableModel.addRow(new Object[]{dienThoai.getMadt(), dienThoai.getTendt(), dienThoai.getNhasx(), dienThoai
                    .getSoluong(), dienThoai.getGiaban(), dienThoai.getPhantramgiam()});
        }
    }
    private void disableInfo(){
        textTenDienThoai.enable(false);
        textGiamGia.enable(false);
        textGiaBan.enable(false);
        textSoLuong.enable(false);
        comboBoxNsx.enable(false);
    }
    private void enableInfo(){
        textTenDienThoai.enable(true);
        textGiamGia.enable(true);
        textGiaBan.enable(true);
        textSoLuong.enable(true);
        comboBoxNsx.enable(true);
    }
    private void setInfo(){
        DefaultTableModel model = (DefaultTableModel)tableDienThoai.getModel();
        int selectedRowIndex = tableDienThoai.getSelectedRow();
        textTenDienThoai.setText(model.getValueAt(selectedRowIndex, 1).toString());
        textSoLuong.setText(model.getValueAt(selectedRowIndex, 3).toString());
        comboBoxNsx.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
        textGiaBan.setText(model.getValueAt(selectedRowIndex, 4).toString());
        textGiamGia.setText(model.getValueAt(selectedRowIndex, 5).toString());
    }


}
