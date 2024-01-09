package GUI;

import model.NhanVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPanel MainPanel;
    private JButton btnBanHang;
    private JButton btnQuanLiPhuKien;
    private JButton btnQuanLiHoaDon;
    private JButton btnQuanDienThoai;
    private JPanel panelBtn;
    private JButton btnDangXuat;
    private JLabel labelTenNhanVien;
    private JButton btnQuanLiKhachHang;
    private JButton btnQuanLiNhanVien;
    private JPanel panelDangXuat;
    private JButton buttonLogo;
    private JPanel panelChild;


    public MainForm(NhanVien nhanVien) {

        setContentPane(MainPanel);
        MainPanel.setBackground(new Color(32,178,170,255));
        panelBtn.setBackground(new Color(32,178,170,255));
        panelDangXuat.setBackground(new Color(32,178,170,255));
        btnBanHang.setBackground(new Color(0,128,128,255));
        btnQuanLiNhanVien.setBackground(new Color(0,128,128,255));
        btnQuanDienThoai.setBackground(new Color(0,128,128,255));
        btnDangXuat.setBackground(new Color(199,0,67));
        btnQuanLiHoaDon.setBackground(new Color(0,128,128,255));
        btnQuanLiKhachHang.setBackground(new Color(0,128,128,255));
        btnQuanLiPhuKien.setBackground(new Color(0,128,128,255));
        setTitle("Quản Cửa Hàng Di Động");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        String chucvu = nhanVien.getChucvu();
        if(chucvu.equals("Quản lí")){
            btnQuanLiNhanVien.setVisible(true);
        }

        labelTenNhanVien.setText(nhanVien.getTennv());
        btnQuanDienThoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new QuanLiDienThoai(nhanVien);
                dispose();
            }
        });
        btnQuanLiPhuKien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new QuanLiPhuKien(nhanVien);
                dispose();
            }
        });
        btnQuanLiHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new QuanLiHoaDon(nhanVien);
                dispose();
            }
        });
        btnDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = JOptionPane.showConfirmDialog(MainPanel, "Bạn muốn đăng xuất ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(res == JOptionPane.YES_OPTION){
                    new Login();
                    dispose();
                }
            }
        });
        btnBanHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new BanHang(nhanVien);
                dispose();
            }
        });
        btnQuanLiKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new QuanLiKhachHang(nhanVien);
                dispose();
            }
        });
        btnQuanLiNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new QuanLiNhanVien(nhanVien);
                dispose();
            }
        });
    }
}


