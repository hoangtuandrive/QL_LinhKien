/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.formdev.flatlaf.FlatLightLaf;

import connectDB.ConnectDB;
import dao.LinhKien_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.LinhKien;
import entity.NhanVien;
import entity.TaiKhoan;

/**
 *
 * @author ACER
 */
public class FrmLinhKien extends javax.swing.JFrame implements ActionListener, MouseListener {

	private JComboBox<String> cmbChon;
	private static JComboBox<String> cmbTim;
	private JButton btnTim;

	public JPanel createPanelSanPham() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		linhkien_dao = new LinhKien_DAO();

		pntblLinhKien = new javax.swing.JScrollPane();
		tableLinhKien = new javax.swing.JTable();
		pnlTimKiem = new javax.swing.JPanel();
		pnThongTin = new javax.swing.JPanel();
		lblMaLinhKien = new java.awt.Label();
		txtMaLinhKien = new javax.swing.JTextField();
		lblTenLinhKien = new java.awt.Label();
		txtTenLinhKien = new javax.swing.JTextField();
		lblLoaiHang = new java.awt.Label();
		txtLoaiHang = new javax.swing.JTextField();
		lblNhaCungCap = new java.awt.Label();
		txtNhaCungCap = new javax.swing.JTextField();
		lblDonGia = new java.awt.Label();
		txtDonGia = new javax.swing.JTextField();
		lblSoLuong = new java.awt.Label();
		txtSoLuong = new javax.swing.JTextField();

		pnChucNang = new javax.swing.JPanel();
		btnThem = new javax.swing.JButton();
		btnSua = new javax.swing.JButton();
		btnXoa = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		String[] header = { " Mã Linh Kiện", "Tên Linh Kiện", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn" };
		modelLinhKien = new javax.swing.table.DefaultTableModel(header, 0);
		tableLinhKien = new JTable(modelLinhKien) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				Color color1 = new Color(219, 243, 255);
				Color color2 = Color.WHITE;
				if (!c.getBackground().equals(getSelectionBackground())) {
					Color coleur = (row % 2 == 0 ? color1 : color2);
					c.setBackground(coleur);
					coleur = null;
				}
				return c;
			}
		};
		tableLinhKien.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableLinhKien.setGridColor(getBackground());
		tableLinhKien.setRowHeight(tableLinhKien.getRowHeight() + 20);
		tableLinhKien.setSelectionBackground(new Color(255, 255, 128));
		JTableHeader tableHeader = tableLinhKien.getTableHeader();
		tableHeader.setBackground(new Color(0, 148, 224));
		tableHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		tableHeader.setForeground(Color.WHITE);
		tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
		tableLinhKien.setColumnSelectionAllowed(false);
		tableLinhKien.setName("tblThongTinNhanVien"); // NOI18N
		pntblLinhKien.setViewportView(tableLinhKien);
		tableLinhKien.getColumnModel().getSelectionModel()
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tableLinhKien.addMouseListener(this);
		
		pnThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết:"));
		pnThongTin.setToolTipText("Info of selected table object");

		lblMaLinhKien.setText("Mã linh kiện:");

		lblTenLinhKien.setText("Tên linh kiện:");

		lblLoaiHang.setText("Loại hàng");

		lblNhaCungCap.setText("Nhà cung cấp");

		lblDonGia.setText("Đơn giá");

		lblSoLuong.setText("Số lượng");

		javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
		pnThongTin.setLayout(pnThongTinLayout);
		pnThongTinLayout.setHorizontalGroup(pnThongTinLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap()
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTenLinhKien, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaLinhKien, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnThongTinLayout.createSequentialGroup().addComponent(txtMaLinhKien,
										javax.swing.GroupLayout.PREFERRED_SIZE, 169,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtTenLinhKien, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtLoaiHang, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		pnThongTinLayout.setVerticalGroup(pnThongTinLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap().addGroup(pnThongTinLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtMaLinhKien, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaLinhKien, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtTenLinhKien, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTenLinhKien, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(20, Short.MAX_VALUE)));

		pnChucNang.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng:"));

		btnThem.setText("THÊM");
		btnSua.setText("SỬA");
		btnXoa.setText("XÓA");
		
		btnThem.setBackground(new Color(0, 148, 224));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFocusPainted(false);
		btnSua.setBackground(new Color(0, 148, 224));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFocusPainted(false);
		btnXoa.setBackground(new Color(0, 148, 224));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFocusPainted(false);

		javax.swing.GroupLayout pnChucNangLayout = new javax.swing.GroupLayout(pnChucNang);
		pnChucNang.setLayout(pnChucNangLayout);
		pnChucNangLayout.setHorizontalGroup(pnChucNangLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChucNangLayout.createSequentialGroup()
						.addContainerGap(56, Short.MAX_VALUE).addComponent(btnThem)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSua)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnXoa)
						.addGap(48, 48, 48)));
		pnChucNangLayout.setVerticalGroup(pnChucNangLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnChucNangLayout.createSequentialGroup().addGap(26, 26, 26)
						.addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnThem).addComponent(btnSua).addComponent(btnXoa))
						.addContainerGap(35, Short.MAX_VALUE)));
		
		btnThem.setBackground(new Color(0, 148, 224));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFocusPainted(false);
		btnSua.setBackground(new Color(0, 148, 224));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFocusPainted(false);
		btnXoa.setBackground(new Color(0, 148, 224));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFocusPainted(false);
		
		Box b = Box.createHorizontalBox();
		String[] tim = { "Mã Linh Kiện", "Tên Linh Kiện", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn" };
		cmbChon = new JComboBox<String>(tim);
		cmbTim = new JComboBox<String>();
		cmbTim.setEditable(true);
		AutoCompleteDecorator.decorate(cmbTim);
		cmbTim.setMaximumRowCount(10);
		cmbChon.setSize(20, cmbTim.getPreferredSize().height);
		btnTim = new JButton("TÌM KIẾM",new ImageIcon("image/timkiem.png"));
		btnTim.setBackground(new Color(0, 148, 224));
		btnTim.setForeground(Color.WHITE);
		btnTim.setFocusPainted(false);
		

		b.add(cmbChon);
		b.add(Box.createHorizontalStrut(10));
		b.add(cmbTim);
		b.add(Box.createHorizontalStrut(10));
		b.add(btnTim);
		b.add(Box.createHorizontalStrut(30));
		pnlTimKiem.add(b);

		docMaLinhKienVaoCmbTim();
		cmbChon.addActionListener(this);
		cmbTim.addActionListener(this);
		btnTim.addActionListener(this);

		JPanel panel = new JPanel();
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		panel.add(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(
										pntblLinhKien, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
								.addComponent(pnlTimKiem))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout
										.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
										.addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addComponent(pntblLinhKien, javax.swing.GroupLayout.PREFERRED_SIZE, 800,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(2, 2, 2))
						.addGroup(layout.createSequentialGroup().addGap(14, 14, 14)
								.addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 27, Short.MAX_VALUE)))
						.addContainerGap()));

		pack();
		
		pntblLinhKien.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH LINH KIỆN: "));
		lblMaLinhKien.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMaLinhKien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTenLinhKien.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTenLinhKien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoaiHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtLoaiHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDonGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		tableLinhKien.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableLinhKien.getColumnModel().getColumn(1).setPreferredWidth(165);
		
		txtMaLinhKien.setEditable(false);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);

		tableLinhKien.addMouseListener(this);

		docDuLieuDatabaseVaoTable();

		tableLinhKien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLinhKien.setDefaultEditor(Object.class, null);
		tableLinhKien.getTableHeader().setReorderingAllowed(false);

		return panel;
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
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
			java.util.logging.Logger.getLogger(FrmLinhKien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FrmLinhKien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FrmLinhKien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FrmLinhKien.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FrmDangNhap().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnSua;
	private javax.swing.JButton btnThem;
	private javax.swing.JButton btnXoa;
	private java.awt.Label lblDonGia;
	private java.awt.Label lblNhaCungCap;
	private java.awt.Label lblSoLuong;
	private java.awt.Label lblTenLinhKien;
	private java.awt.Label lblLoaiHang;
	private java.awt.Label lblMaLinhKien;
	private javax.swing.JPanel pnChucNang;
	private javax.swing.JPanel pnThongTin;
	private javax.swing.JScrollPane pntblLinhKien;
	private static javax.swing.JTable tableLinhKien;
	private javax.swing.JTextField txtDonGia;
	private javax.swing.JTextField txtNhaCungCap;
	private javax.swing.JTextField txtSoLuong;
	private javax.swing.JTextField txtTenLinhKien;
	private javax.swing.JTextField txtLoaiHang;
	private JPanel pnlTimKiem;
	private javax.swing.JTextField txtMaLinhKien;
	private static DefaultTableModel modelLinhKien;
	private static LinhKien_DAO linhkien_dao;
	// End of variables declaration//GEN-END:variables

	public static void xoaHetDL() {
		DefaultTableModel dm = (DefaultTableModel) tableLinhKien.getModel();
		dm.setRowCount(0);
	}

	private boolean validInput() {
		String tenLk = txtTenLinhKien.getText();
		String loaiHang = txtLoaiHang.getText();
		String nhaCC = txtNhaCungCap.getText();
		String gialk = txtDonGia.getText();
		String soLuong = txtSoLuong.getText();
		if (tenLk.trim().length() > 0) {
			if (!(tenLk.matches("[^\\@\\!\\$\\^\\&\\*\\(\\)]+"))) {
				JOptionPane.showMessageDialog(this, "Tên linh kiện không chứa ký tự đặc biệt", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Tên linh kiện không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (loaiHang.trim().length() > 0) {
			if (!(tenLk.matches("[^\\@\\!\\$\\^\\&\\*\\(\\)]+"))) {
				JOptionPane.showMessageDialog(this, "Tên loại hàng không chứa ký tự đặc biệt", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Tên loại hàng không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (nhaCC.trim().length() > 0) {
			if (!(tenLk.matches("[^\\@\\!\\$\\^\\&\\*\\(\\)]+"))) {
				JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không chứa ký tự đặc biệt", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Tên cung cấp không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (gialk.trim().length() > 0) {
			try {
				double x = Double.parseDouble(gialk);
				if (x <= 0) {
					JOptionPane.showMessageDialog(this, "Giá linh kiện phải lớn hơn 0", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Error: Giá linh kiện phải nhập số", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Giá linh kiện không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (soLuong.trim().length() > 0) {
			try {
				int x = Integer.parseInt(soLuong);
				if (x <= 0) {
					JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Error: Số lượng phải nhập số", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Số lượng không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void emptyTextField() {
		txtMaLinhKien.setText(null);
		txtTenLinhKien.setText(null);
		txtDonGia.setText(null);
		txtSoLuong.setText(null);
		txtNhaCungCap.setText(null);
		txtLoaiHang.setText(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (!validInput()) {
				return;
			} else {
				String maLK;
				List<LinhKien> listLK = linhkien_dao.getTatCaLinhKien();
				if (listLK.size() == 0)
					maLK = "LK1001";
				else {
					String maLKcu = listLK.get(listLK.size() - 1).getMaLK().trim();
					int layMaSo = Integer.parseInt(maLKcu.substring(2, maLKcu.length()));
					maLK = "LK" + (layMaSo + 1);
				}
				String tenLK = txtTenLinhKien.getText();
				String loaiHang = txtLoaiHang.getText();
				String nhaCungCap = txtNhaCungCap.getText();
				int soLuong = Integer.parseInt(txtSoLuong.getText());
				double donGia = Double.parseDouble(txtDonGia.getText());

				LinhKien lk = new LinhKien(maLK, tenLK, loaiHang, nhaCungCap, donGia, soLuong);

				linhkien_dao.create(lk);

				xoaHetDL();
				docDuLieuDatabaseVaoTable();

			}
			tableLinhKien.getSelectionModel().clearSelection();
			emptyTextField();

			FrmBanHang.xoaHetDLLinhKien();
			FrmBanHang.docDuLieuDatabaseVaoTableLinhKien();
		}
		if (o.equals(btnSua)) {
			String maLK = txtMaLinhKien.getText();
			String tenLK = txtTenLinhKien.getText();
			String loaiHang = txtLoaiHang.getText();
			String nhaCungCap = txtNhaCungCap.getText();
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			double donGia = Double.parseDouble(txtDonGia.getText());
			if (!validInput()) {
				return;
			} else {

				LinhKien lk = new LinhKien(maLK, tenLK, loaiHang, nhaCungCap, donGia, soLuong);

				linhkien_dao.update(lk);

				xoaHetDL();
				docDuLieuDatabaseVaoTable();
				tableLinhKien.getSelectionModel().clearSelection();
				FrmBanHang.xoaHetDLLinhKien();
				FrmBanHang.docDuLieuDatabaseVaoTableLinhKien();
			}
		}
		if (o.equals(btnXoa)) {
			String maLK = txtMaLinhKien.getText();
			LinhKien lk = new LinhKien(maLK);

			linhkien_dao.delete(lk);

			xoaHetDL();
			docDuLieuDatabaseVaoTable();
			tableLinhKien.getSelectionModel().clearSelection();
			FrmBanHang.xoaHetDLLinhKien();
			FrmBanHang.docDuLieuDatabaseVaoTableLinhKien();
		}
		if (o.equals(cmbChon)) {
			if (cmbChon.getSelectedIndex() == 0) {
				docMaLinhKienVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 1) {
				docTenLinhKienVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 2) {
				docLoaiHangLinhKienVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 3) {
				docNhaCungCapLinhKienVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 4) {
				docDonGiaLinhKienVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 5) {
				docSoLuongTonLinhKienVaoCmbTim();
			}
		}
		if (o.equals(btnTim)) {
			DefaultTableModel model = (DefaultTableModel) tableLinhKien.getModel();
			model.setRowCount(0);
			DecimalFormat df = new DecimalFormat("#,##0");
			if (cmbTim.getSelectedIndex() == 0) {
				docDuLieuDatabaseVaoTable();
			} else if (cmbChon.getSelectedIndex() == 0) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<LinhKien> list = linhkien_dao.getTatCaLinhKien();
				for (LinhKien lk : list) {
					if (lk.getMaLK().trim().equals(tim.trim())) {
						modelLinhKien.addRow(new Object[] { lk.getMaLK().trim(), lk.getTenLK().trim(), lk.getLoaiHang(),
								lk.getNhaCungCap().trim(), df.format(lk.getDonGia()), lk.getSoLuong() });
					}
				}
			}
			if (cmbChon.getSelectedIndex() == 1) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<LinhKien> list = linhkien_dao.getTatCaLinhKien();
				for (LinhKien lk : list) {
					if (lk.getTenLK().trim().equals(tim.trim())) {
						modelLinhKien.addRow(new Object[] { lk.getMaLK().trim(), lk.getTenLK().trim(), lk.getLoaiHang(),
								lk.getNhaCungCap().trim(), df.format(lk.getDonGia()), lk.getSoLuong() });
					}
				}
			}
			if (cmbChon.getSelectedIndex() == 2) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<LinhKien> list = linhkien_dao.getTatCaLinhKien();
				for (LinhKien lk : list) {
					if (lk.getLoaiHang().trim().equals(tim.trim())) {
						modelLinhKien.addRow(new Object[] { lk.getMaLK().trim(), lk.getTenLK().trim(), lk.getLoaiHang(),
								lk.getNhaCungCap().trim(), df.format(lk.getDonGia()), lk.getSoLuong() });
					}
				}
			}
			if (cmbChon.getSelectedIndex() == 3) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<LinhKien> list = linhkien_dao.getTatCaLinhKien();
				for (LinhKien lk : list) {
					if (lk.getNhaCungCap().trim().equals(tim.trim())) {
						modelLinhKien.addRow(new Object[] { lk.getMaLK().trim(), lk.getTenLK().trim(), lk.getLoaiHang(),
								lk.getNhaCungCap().trim(), df.format(lk.getDonGia()), lk.getSoLuong() });
					}
				}
			}
			if (cmbChon.getSelectedIndex() == 4) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				double d = Double.parseDouble(tim.replaceAll(",","").trim());
				List<LinhKien> list = linhkien_dao.getTatCaLinhKien();
				for (LinhKien lk : list) {
					if (lk.getDonGia() == d) {
						modelLinhKien.addRow(new Object[] { lk.getMaLK().trim(), lk.getTenLK().trim(), lk.getLoaiHang(),
								lk.getNhaCungCap().trim(), df.format(lk.getDonGia()), lk.getSoLuong() });
					}
				}
			}
			if (cmbChon.getSelectedIndex() == 5) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				int d = Integer.parseInt(tim.trim());
				List<LinhKien> list = linhkien_dao.getTatCaLinhKien();
				for (LinhKien lk : list) {
					if (lk.getSoLuong() == d) {
						modelLinhKien.addRow(new Object[] { lk.getMaLK().trim(), lk.getTenLK().trim(), lk.getLoaiHang(),
								lk.getNhaCungCap().trim(), df.format(lk.getDonGia()), lk.getSoLuong() });
					}
				}
			}
		}
	}

	public static void docDuLieuDatabaseVaoTable() {
		List<LinhKien> listLK = new ArrayList<LinhKien>();
		listLK = linhkien_dao.getTatCaLinhKien();
		DecimalFormat df = new DecimalFormat("#,##0");
		for (LinhKien lk : listLK) {
			modelLinhKien.addRow(new Object[] { lk.getMaLK().trim(), lk.getTenLK().trim(), lk.getLoaiHang(),
					lk.getNhaCungCap().trim(), df.format(lk.getDonGia()), lk.getSoLuong() });
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableLinhKien.getSelectedRow();
		txtMaLinhKien.setText(modelLinhKien.getValueAt(row, 0).toString());
		txtTenLinhKien.setText(modelLinhKien.getValueAt(row, 1).toString());
		txtLoaiHang.setText(modelLinhKien.getValueAt(row, 2).toString());
		txtNhaCungCap.setText(modelLinhKien.getValueAt(row, 3).toString());
		String tien[] = modelLinhKien.getValueAt(row, 4).toString().split(",");
		String donGia = "";
		for (int i = 0; i < tien.length; i++)
			donGia += tien[i];
		txtDonGia.setText(donGia);
		txtSoLuong.setText(modelLinhKien.getValueAt(row, 5).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void docMaLinhKienVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = linhkien_dao.getTatCaMaLinhKien();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docTenLinhKienVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = linhkien_dao.getTatCaTenLinhKien();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docNhaCungCapLinhKienVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = linhkien_dao.getTatCaNhaCungCapLinhKien();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docLoaiHangLinhKienVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = linhkien_dao.getTatCaLoaiHangLinhKien();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docDonGiaLinhKienVaoCmbTim() {
		DecimalFormat df = new DecimalFormat("#,##0");
		cmbTim.removeAllItems();
		List<Double> list = linhkien_dao.getTatCaDonGiaLinhKien();
		cmbTim.addItem("");
		for (Double s : list) {
			cmbTim.addItem(df.format(s));
		}
	}

	public static void docSoLuongTonLinhKienVaoCmbTim() {
		DecimalFormat df = new DecimalFormat("#");
		cmbTim.removeAllItems();
		List<Integer> list = linhkien_dao.getTatCaSoLuongTonLinhKien();
		cmbTim.addItem("");
		for (Integer s : list) {
			cmbTim.addItem(df.format(s));
		}
	}

}
