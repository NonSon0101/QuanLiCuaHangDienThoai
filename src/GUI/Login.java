package GUI;

import Service.NhanVienService;
import model.NhanVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class Login extends JFrame {
    private JTextField textUserName;
    private JButton loginButton;
    private JPanel mainPanel;
    private JPasswordField textPassWord;
    private JPanel panelText;
    private JPanel panelable;
    private  NhanVienService nhanVienService;

    public Login(){
        setContentPane(mainPanel);
        mainPanel.setBackground(new Color(32,178,170,255));
        panelable.setBackground(new Color(32,178,170,255));
        panelText.setBackground(new Color(32,178,170,255));
        loginButton.setBackground(new Color(0,247,137));

        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                login();
            }
        });
        loginButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Handle the Enter key event here
                    login();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                // This method is called when a key is pressed.
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Handle the Enter key event here
                   login();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Handle the Enter key event here
                    login();
                }
            }
        });

        nhanVienService = new NhanVienService();

    }
    private void login(){
        String username = textUserName.getText();
        char[] passwordChars = textPassWord.getPassword();
        String password = new String(passwordChars);
        boolean isvalid = nhanVienService.loginAuthentication(username, password);
        if(isvalid){
            NhanVien nhanVien = nhanVienService.getNhanVien(username, password);
            new MainForm(nhanVien);
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(mainPanel, "Tên đăng nhập hoặc mật khẩu không đúng");
        }
    }
    public static void main(String[] args){
        new Login();
    }
}
