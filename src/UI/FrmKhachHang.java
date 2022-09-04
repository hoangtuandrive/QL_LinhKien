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
import java.text.SimpleDateFormat;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import entity.KhachHang;
import entity.LinhKien;
import entity.KhachHang;
import entity.TaiKhoan;

public class FrmKhachHang extends javax.swing.JFrame implements ActionListener, MouseListener {

	private JComboBox<String> cmbChon;
	private static JComboBox<String> cmbTim;
	private JButton btnTim;

	public JPanel createPanelKhachHang() {

		// khởi tạo kết nối đến CSDL
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		khachhang_dao = new KhachHang_DAO();

		pntblKhachHang = new javax.swing.JScrollPane();
		tableKhachHang = new javax.swing.JTable();
		pnlTimKiem = new JPanel();
		pnThongTin = new javax.swing.JPanel();
		lblMaKhachHang = new java.awt.Label();
		txtMaKhachHang = new javax.swing.JTextField();
		lblTen = new java.awt.Label();
		txtTenKhachHang = new javax.swing.JTextField();
		lblGioiTinh = new java.awt.Label();
		cmbGioiTinh = new javax.swing.JComboBox<String>();
		lblSDT = new java.awt.Label();
		txtSDT = new javax.swing.JTextField();
		lblEmail = new java.awt.Label();
		txtEmail = new javax.swing.JTextField();
		lblDiaChi = new java.awt.Label();
		txtDiaChi = new javax.swing.JTextField();
		lblNgaySinh = new java.awt.Label();
		txtNgaySinh = new JDateChooser();
		lblCMND = new java.awt.Label();
		txtCMND = new javax.swing.JTextField();

		pnChucNang = new javax.swing.JPanel();
		btnThem = new javax.swing.JButton();
		btnSua = new javax.swing.JButton();
		btnXoa = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		String[] header = { "Mã khách hàng", "Tên khách hàng", "Ngày Sinh", "CMND", "Giới Tính", "SDT", "Email",
				"Địa chỉ" };

		modelKhachHang = new javax.swing.table.DefaultTableModel(header, 0);
		tableKhachHang = new JTable(modelKhachHang) {
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
		tableKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableKhachHang.setGridColor(getBackground());
		tableKhachHang.setRowHeight(tableKhachHang.getRowHeight() + 20);
		tableKhachHang.setSelectionBackground(new Color(255, 255, 128));
		JTableHeader tableHeader = tableKhachHang.getTableHeader();
		tableHeader.setBackground(new Color(0, 148, 224));
		tableHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		tableHeader.setForeground(Color.WHITE);
		tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
		pntblKhachHang.add(new JScrollPane(tableKhachHang));
		tableKhachHang.addMouseListener(this);
		pntblKhachHang.setViewportView(tableKhachHang);
		tableKhachHang.getColumnModel().getSelectionModel()
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		pnThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết:"));
		pnThongTin.setToolTipText("Info of selected table object");
		pnThongTin.setName("pnThongTin"); // NOI18N

		String[] gioitinh = { "Nam", "Nữ" };
		cmbGioiTinh = new JComboBox<String>(gioitinh);

		lblMaKhachHang.setText("Mã Khách Hàng:");
		lblTen.setText("Tên Khách Hàng:");
		lblGioiTinh.setText("Giới Tính:");
		lblSDT.setText("SDT:");
		lblEmail.setText("Email:");
		lblDiaChi.setText("Địa Chỉ:");
		lblCMND.setText("CMND:");
		lblNgaySinh.setText("Ngày Sinh");

		txtNgaySinh.setDateFormatString("yyyy-MM-dd");
		txtNgaySinh.setDate(new Date(1999 - 1900, 1 - 1, 1));

		javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
		pnThongTin.setLayout(pnThongTinLayout);
		pnThongTinLayout.setHorizontalGroup(pnThongTinLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap()
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCMND, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnThongTinLayout.createSequentialGroup().addComponent(txtMaKhachHang,
										javax.swing.GroupLayout.PREFERRED_SIZE, 169,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cmbGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtCMND, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		pnThongTinLayout.setVerticalGroup(pnThongTinLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap().addGroup(pnThongTinLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCMND, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(28, Short.MAX_VALUE)));

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
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnThem)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSua)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnXoa)
						.addGap(48, 48, 48)));
		pnChucNangLayout.setVerticalGroup(pnChucNangLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnChucNangLayout.createSequentialGroup().addGap(26, 26, 26)
						.addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnThem).addComponent(btnSua).addComponent(btnXoa))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		Box b = Box.createHorizontalBox();
		String[] tim = { "Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính", "SDT", "CMND", "Ngày Sinh", "Địa Chỉ",
				"Email" };
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


		cmbChon.addActionListener(this);
		cmbTim.addActionListener(this);
		btnTim.addActionListener(this);

		JPanel panel = new JPanel();
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		panel.add(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(pntblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 1050,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 900,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(pnThongTin, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(pntblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 800,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		
		pntblKhachHang.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH KHÁCH HÀNG: "));
		lblMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCMND.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDiaChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		tableKhachHang.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableKhachHang.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableKhachHang.getColumnModel().getColumn(3).setPreferredWidth(30);
		tableKhachHang.getColumnModel().getColumn(4).setPreferredWidth(10);
		tableKhachHang.getColumnModel().getColumn(5).setPreferredWidth(30);
		
		txtMaKhachHang.setEditable(false);
		docDuLieuDatabaseVaoTable();
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		tableKhachHang.addMouseListener(this);

		tableKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableKhachHang.setDefaultEditor(Object.class, null);
		tableKhachHang.getTableHeader().setReorderingAllowed(false);
		docMaKhachHangVaoCmbTim();
		
		pack();
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
			java.util.logging.Logger.getLogger(FrmKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FrmKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FrmKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FrmKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
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
	private java.awt.Label lblGioiTinh;
	private java.awt.Label lblSDT;
	private java.awt.Label lblTen;
	private java.awt.Label lblMaKhachHang;
	private java.awt.Label lblEmail;
	private java.awt.Label lblDiaChi;
	private java.awt.Label lblCMND;
	private java.awt.Label lblNgaySinh;
	private javax.swing.JPanel pnChucNang;
	private javax.swing.JPanel pnThongTin;
	private javax.swing.JScrollPane pntblKhachHang;
	private JComboBox<String> cmbGioiTinh;
	private javax.swing.JTextField txtSDT;
	private javax.swing.JTextField txtTenKhachHang;
	private JPanel pnlTimKiem;
	private javax.swing.JTextField txtMaKhachHang;
	private javax.swing.JTextField txtEmail;
	private javax.swing.JTextField txtDiaChi;
	private javax.swing.JTextField txtCMND;
	private JDateChooser txtNgaySinh;
	private static KhachHang_DAO khachhang_dao;

	private static DefaultTableModel modelKhachHang;
	private static JTable tableKhachHang;

	// End of variables declaration//GEN-END:variables
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (!validInput()) {
				return;
			} else {
				String maKH;
				List<KhachHang> listKH = khachhang_dao.getTatCaKhachHang();
				if (listKH.size() == 0)
					maKH = "KH1001";
				else {
					String maKHCuoi = listKH.get(listKH.size() - 1).getMaKH().trim();
					int layMaSo = Integer.parseInt(maKHCuoi.substring(2, maKHCuoi.length()));
					maKH = "KH" + (layMaSo + 1);
				}
				String tenKH = txtTenKhachHang.getText();
				String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
				String CMND = txtCMND.getText();
				String SDT = txtSDT.getText();
				String diaChi = txtDiaChi.getText();
				String email = txtEmail.getText();
				Date ngaySinh = txtNgaySinh.getDate();
				java.sql.Date date = new java.sql.Date(ngaySinh.getYear(), ngaySinh.getMonth(), ngaySinh.getDate());

				KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh == "Nam" ? true : false, SDT, CMND, date, diaChi,
						email, false);
				khachhang_dao.create(kh);

				xoaHetDL();
				docDuLieuDatabaseVaoTable();
			}
			tableKhachHang.getSelectionModel().clearSelection();
			emptyTextField();
			FrmBanHang.docDuLieuVaoCmbSDT();
		}
		if (o.equals(btnSua)) {
			String maKH = txtMaKhachHang.getText();
			String tenKH = txtTenKhachHang.getText();
			String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
			String CMND = txtCMND.getText();
			String SDT = txtSDT.getText();
			String diaChi = txtDiaChi.getText();
			String email = txtEmail.getText();

			Date ngaySinh = txtNgaySinh.getDate();
			java.sql.Date date = new java.sql.Date(ngaySinh.getYear(), ngaySinh.getMonth(), ngaySinh.getDate());
			if (!validInput()) {
				return;
			} else {
				KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh == "Nam" ? true : false, SDT, CMND, date, diaChi,
						email);

				khachhang_dao.update(kh);

				xoaHetDL();
				docDuLieuDatabaseVaoTable();
				tableKhachHang.getSelectionModel().clearSelection();
				FrmBanHang.docDuLieuVaoCmbSDT();
			}
		}
		if (o.equals(btnXoa)) {
			String maKH = txtMaKhachHang.getText();
			KhachHang kh = new KhachHang(maKH);

			khachhang_dao.delete(kh);

			xoaHetDL();
			docDuLieuDatabaseVaoTable();
			tableKhachHang.getSelectionModel().clearSelection();
			FrmBanHang.docDuLieuVaoCmbSDT();
		}
		if (o.equals(cmbChon)) {
			if (cmbChon.getSelectedIndex() == 0) {
				docMaKhachHangVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 1) {
				docTenKhachHangVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 2) {
				docGioiTinhKhachHangVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 3) {
				docSDTKhachHangVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 4) {
				docCMNDKhachHangVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 5) {
				docNgaySinhKhachHangVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 6) {
				docDiaChiKhachHangVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 7) {
				docEmailKhachHangVaoCmbTim();
			}
		}
		if (o.equals(btnTim)) {
			DefaultTableModel model = (DefaultTableModel) tableKhachHang.getModel();
			model.setRowCount(0);
			DecimalFormat df = new DecimalFormat("#,##0");
			if (cmbTim.getSelectedIndex() == 0) {
				docDuLieuDatabaseVaoTable();
			} else if (cmbChon.getSelectedIndex() == 0) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<KhachHang> list = khachhang_dao.getTatCaKhachHang();
				for (KhachHang kh : list) {
					if (kh.getMaKH().trim().equals(tim.trim())) {
						modelKhachHang.addRow(new Object[] { kh.getMaKH().trim(), kh.getTenKH().trim(),
								kh.getNgaySinh(), kh.getCMND().trim(), kh.isGioiTinh() == true ? "Nam" : "Nữ",
								kh.getSDT().trim(), kh.getEmail().trim(), kh.getDiaChi().trim() });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 1) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<KhachHang> list = khachhang_dao.getTatCaKhachHang();
				for (KhachHang kh : list) {
					if (kh.getTenKH().trim().equals(tim.trim())) {
						modelKhachHang.addRow(new Object[] { kh.getMaKH().trim(), kh.getTenKH().trim(),
								kh.getNgaySinh(), kh.getCMND().trim(), kh.isGioiTinh() == true ? "Nam" : "Nữ",
								kh.getSDT().trim(), kh.getEmail().trim(), kh.getDiaChi().trim() });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 2) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				Boolean b = null;
				if (tim == "Nam") {
					b = true;
				} else if (tim == "Nữ") {
					b = false;
				}
				List<KhachHang> list = khachhang_dao.getTatCaKhachHang();
				for (KhachHang kh : list) {
					if (kh.isGioiTinh() == b) {
						modelKhachHang.addRow(new Object[] { kh.getMaKH().trim(), kh.getTenKH().trim(),
								kh.getNgaySinh(), kh.getCMND().trim(), kh.isGioiTinh() == true ? "Nam" : "Nữ",
								kh.getSDT().trim(), kh.getEmail().trim(), kh.getDiaChi().trim() });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 3) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<KhachHang> list = khachhang_dao.getTatCaKhachHang();
				for (KhachHang kh : list) {
					if (kh.getSDT().trim().equals(tim.trim())) {
						modelKhachHang.addRow(new Object[] { kh.getMaKH().trim(), kh.getTenKH().trim(),
								kh.getNgaySinh(), kh.getCMND().trim(), kh.isGioiTinh() == true ? "Nam" : "Nữ",
								kh.getSDT().trim(), kh.getEmail().trim(), kh.getDiaChi().trim() });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 4) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<KhachHang> list = khachhang_dao.getTatCaKhachHang();
				for (KhachHang kh : list) {
					if (kh.getCMND().trim().equals(tim.trim())) {
						modelKhachHang.addRow(new Object[] { kh.getMaKH().trim(), kh.getTenKH().trim(),
								kh.getNgaySinh(), kh.getCMND().trim(), kh.isGioiTinh() == true ? "Nam" : "Nữ",
								kh.getSDT().trim(), kh.getEmail().trim(), kh.getDiaChi().trim() });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 5) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				String[] a = tim.split("-");
				Date d = new Date(Integer.parseInt(a[0]) - 1900, Integer.parseInt(a[1]) - 1, Integer.parseInt(a[2]));
				List<KhachHang> list = khachhang_dao.getTatCaKhachHang();
				for (KhachHang kh : list) {
					if (kh.getNgaySinh().equals(d)) {
						modelKhachHang.addRow(new Object[] { kh.getMaKH().trim(), kh.getTenKH().trim(),
								kh.getNgaySinh(), kh.getCMND().trim(), kh.isGioiTinh() == true ? "Nam" : "Nữ",
								kh.getSDT().trim(), kh.getEmail().trim(), kh.getDiaChi().trim() });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 6) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<KhachHang> list = khachhang_dao.getTatCaKhachHang();
				for (KhachHang kh : list) {
					if (kh.getDiaChi().trim().equals(tim.trim())) {
						modelKhachHang.addRow(new Object[] { kh.getMaKH().trim(), kh.getTenKH().trim(),
								kh.getNgaySinh(), kh.getCMND().trim(), kh.isGioiTinh() == true ? "Nam" : "Nữ",
								kh.getSDT().trim(), kh.getEmail().trim(), kh.getDiaChi().trim() });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 7) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<KhachHang> list = khachhang_dao.getTatCaKhachHang();
				for (KhachHang kh : list) {
					if (kh.getEmail().trim().equals(tim.trim())) {
						modelKhachHang.addRow(new Object[] { kh.getMaKH().trim(), kh.getTenKH().trim(),
								kh.getNgaySinh(), kh.getCMND().trim(), kh.isGioiTinh() == true ? "Nam" : "Nữ",
								kh.getSDT().trim(), kh.getEmail().trim(), kh.getDiaChi().trim() });
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableKhachHang.getSelectedRow();
		txtMaKhachHang.setText(modelKhachHang.getValueAt(row, 0).toString());
		txtTenKhachHang.setText(modelKhachHang.getValueAt(row, 1).toString());
		String dateString = modelKhachHang.getValueAt(row, 2).toString();
		String[] a = dateString.split("-");
		txtNgaySinh
				.setDate(new Date(Integer.parseInt(a[0]) - 1900, Integer.parseInt(a[1]) - 1, Integer.parseInt(a[2])));
		txtCMND.setText(modelKhachHang.getValueAt(row, 3).toString());
		cmbGioiTinh.setSelectedItem(modelKhachHang.getValueAt(row, 4).toString().trim());
		txtSDT.setText(modelKhachHang.getValueAt(row, 5).toString());
		txtEmail.setText(modelKhachHang.getValueAt(row, 6).toString());
		txtDiaChi.setText(modelKhachHang.getValueAt(row, 7).toString());
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

	private void emptyTextField() {
		txtMaKhachHang.setText(null);
		txtTenKhachHang.setText(null);
		txtNgaySinh.setDate(null);
		txtCMND.setText(null);
		cmbGioiTinh.setSelectedIndex(0);
		txtSDT.setText(null);
		txtEmail.setText(null);
		txtDiaChi.setText(null);
	}

	public static void docDuLieuDatabaseVaoTable() {
		List<KhachHang> listKH = new ArrayList<KhachHang>();
		listKH = khachhang_dao.getTatCaKhachHang();
		for (KhachHang kh : listKH) {
			modelKhachHang.addRow(new Object[] { kh.getMaKH().trim(), kh.getTenKH().trim(), kh.getNgaySinh(),
					kh.getCMND().trim(), kh.isGioiTinh() == true ? "Nam" : "Nữ", kh.getSDT().trim(),
					kh.getEmail().trim(), kh.getDiaChi().trim() });
		}
	}

	public static void xoaHetDL() {
		DefaultTableModel dm = (DefaultTableModel) tableKhachHang.getModel();
		dm.setRowCount(0);
	}

	private boolean validInput() {
		// TODO Auto-generated method stub
		String maKH = txtMaKhachHang.getText();
		String tenKH = txtTenKhachHang.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		String diaChi = txtDiaChi.getText();
		String cmnd = txtCMND.getText();

		if (tenKH.trim().length() > 0) {
			if (!(tenKH.matches("[^\\@\\!\\$\\^\\&\\*\\(\\)]+"))) {
				JOptionPane.showMessageDialog(this, "Tên khách hàng không chứa ký tự đặc biệt", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Tên khách hàng không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (sdt.trim().length() > 0) {
			if (!(sdt.matches(
					"^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$"))) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (cmnd.trim().length() > 0) {
			if (!(cmnd.matches("\\d{9}"))) {
				JOptionPane.showMessageDialog(this, "CMND không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "CMND không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (email.trim().length() > 0) {
			if (!(email.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$"))) {
				JOptionPane.showMessageDialog(this, "Email không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Email không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (diaChi.trim().length() > 0) {
			if (!(diaChi.matches("[^\\@\\!\\$\\^\\&\\*\\(\\)]+"))) {
				JOptionPane.showMessageDialog(this, "Địa chỉ không chứa ký tự đặc biệt", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static void docMaKhachHangVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = khachhang_dao.getTatCaMaKhachHang();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docTenKhachHangVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = khachhang_dao.getTatCaTenKhachHang();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docGioiTinhKhachHangVaoCmbTim() {
		cmbTim.removeAllItems();
		List<Boolean> list = khachhang_dao.getTatCaGioiTinhKhachHang();
		cmbTim.addItem("");
		for (Boolean s : list) {
			cmbTim.addItem(s == true ? "Nam" : "Nữ");
		}
	}

	public static void docSDTKhachHangVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = khachhang_dao.getTatCaSDTKhachHangCMB();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docCMNDKhachHangVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = khachhang_dao.getTatCaCMNDKhachHang();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docNgaySinhKhachHangVaoCmbTim() {
		SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
		cmbTim.removeAllItems();
		List<Date> list = khachhang_dao.getTatCaNgaySinhKhachHang();
		cmbTim.addItem("");
		for (Date s : list) {
			cmbTim.addItem(date.format(s));
		}
	}

	public static void docDiaChiKhachHangVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = khachhang_dao.getTatCaDiaChiKhachHang();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docEmailKhachHangVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = khachhang_dao.getTatCaEmailKhachHang();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}
}
