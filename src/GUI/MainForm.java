package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPanel MainPanel;
    private JButton btnBanHang;
    private JButton btnQuanLiPhuKien;
    private JButton btnQuanLiHoaDon;
    private JButton btnQuanDienThoai;
    private JPanel panelBtn;
    private JPanel panelChild;

    public MainForm() {

        setContentPane(MainPanel);
        setTitle("Quản Cửa Hàng Di Động");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 550);
        setLocationRelativeTo(null);
        setVisible(true);

        btnQuanDienThoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new QuanLiDienThoai();
            }
        });
        btnQuanLiPhuKien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new QuanLiPhuKien();
            }
        });

        btnQuanLiHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new QuanLiHoaDon();
            }
        });
    }

    public static void main(String[] args){
        new MainForm();
    }
}
