package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;

public class FrmDangNhap extends JFrame implements ActionListener, KeyListener {
	private static JTextField txtTenDangNhap;
	private JTextField txtMatKhau;
	private JButton btnDangNhap;
	private JButton btnThoat;
	private TaiKhoan_DAO taikhoan_dao;
	private JLabel lblMK;
	private JLabel lblTDN;

	public FrmDangNhap() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		taikhoan_dao = new TaiKhoan_DAO();
		
		setTitle("Đăng Nhập");
		setSize(450, 235);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);

		JPanel p = new JPanel(new BorderLayout());
		this.add(p);

		JPanel pTop = new JPanel();

		JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
		lblTitle.setFont(new Font("serif", Font.BOLD, 25));
		lblTitle.setForeground(Color.white);
		pTop.add(lblTitle);

		pTop.setBackground(new Color(0, 148, 224));
		p.add(pTop, BorderLayout.NORTH);

		JPanel pcenter = new JPanel();
		Box b, b1, b2;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();

		txtTenDangNhap = new JTextField(20);
		b1.add(lblTDN = new JLabel("Tên đăng nhập:"));
		b1.add(Box.createHorizontalStrut(5));
		b1.add(txtTenDangNhap);
		b.add(b1);

		b.add(Box.createVerticalStrut(20));
		txtMatKhau = new JPasswordField(20);
		b2.add(lblMK = new JLabel("Mật khẩu:"));
		b2.add(Box.createHorizontalStrut(45));
		b2.add(txtMatKhau);

		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		pcenter.add(b);

		p.add(pcenter, BorderLayout.CENTER);

		JPanel pBot = new JPanel();
		btnDangNhap = new JButton("Đăng nhập", new ImageIcon("image/trangchu.png"));
		btnThoat = new JButton("Thoát", new ImageIcon("image/out.png"));
		pBot.add(Box.createHorizontalStrut(100));
		pBot.add(btnDangNhap);

		pBot.add(btnThoat);
		pcenter.add(pBot);
		
		btnDangNhap.setBackground(new Color(0, 148, 224));
		btnDangNhap.setForeground(Color.WHITE);
		btnDangNhap.setFocusPainted(false);
		btnThoat.setBackground(new Color(0, 148, 224));
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setFocusPainted(false);
		
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		txtTenDangNhap.addKeyListener(this);
		txtMatKhau.addKeyListener(this);
		
		txtTenDangNhap.setFont(new Font("Tahoma",Font.PLAIN,14));
		txtMatKhau.setFont(new Font("Tahoma",Font.PLAIN,14));
		lblTDN.setFont(new Font("Tahoma",Font.BOLD,14));
		lblMK.setFont(new Font("Tahoma",Font.BOLD,14));
		btnDangNhap.setFont(new Font("Tahoma",Font.BOLD,14));
		btnThoat.setFont(new Font("Tahoma",Font.BOLD,14));
		lblTitle.setFont(new Font("Tahoma",Font.BOLD,14));
		

		txtTenDangNhap.setText("QL1002");
		txtMatKhau.setText("123");
	}

	public static void main(String[] args) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FrmDangNhap().setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnDangNhap)) {
			String taikhoan = txtTenDangNhap.getText();
			String matkhau = txtMatKhau.getText();

			int flag = 0;
			List<TaiKhoan> listTK = taikhoan_dao.getalltbTaiKhoan();
			for (TaiKhoan tk : listTK) {
				if (tk.getTenDN().trim().equals(taikhoan) && tk.getMatKhau().trim().equals(matkhau)) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				JOptionPane.showMessageDialog(this, "Đăng nhập thất bại!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				txtTenDangNhap.requestFocus();
				return;
			} else {
					GUI gui = new GUI();
					gui.setVisible(true);
					gui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					gui.setLocationRelativeTo(null);
					dispose();				
			}
		} else if (o.equals(btnThoat)) {
			System.exit(0);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			btnDangNhap.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static String getTaiKhoan() {
		return txtTenDangNhap.getText();
	}

}
