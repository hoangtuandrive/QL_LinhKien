package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import entity.HoaDon;
import entity.NhanVien;

public class FrmHoaDon extends JFrame implements ActionListener, MouseListener {

	private static DefaultTableModel modelHoaDon;
	private static JTable tableHoaDon;
	private JTextField txtTim;
	private JLabel lblTim;
	private JButton btnTim;
	private JPanel p;
	private JPanel pNorth;
	private static HoaDon_DAO hoadon_dao;
	private JLabel lblDoanhThu;
	private static JComboBox<String> cmbTim;
	private JComboBox<String> cmbChon;
	private JLabel lblNgay;
	private static JLabel txtDoanhThu;
	private JDateChooser txtNgayStart;
	private JDateChooser txtNgayEnd;
	private JButton btnTim1;
	private JButton btnSort;
	private boolean sort;
	private JButton btnHoaDon;
	private boolean sortHoaDon;

	public JPanel createPanelHoaDon() {
		// TODO Auto-generated constructor stub
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		hoadon_dao = new HoaDon_DAO();

		setTitle("FrmHoaDon");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		p = new JPanel();
		pNorth = new JPanel();

		Box b = Box.createVerticalBox();
		Box bThongtin = Box.createHorizontalBox();
		Box btim = Box.createHorizontalBox();

		String[] colHeader = { "Mã Hóa Đơn", "Tên Khách Hàng", "Tên Nhân Viên", "Ngày Lập Hóa Đơn",
				"Thành Tiền" };
		modelHoaDon = new DefaultTableModel(colHeader, 0);
		JScrollPane tblscroll = new JScrollPane(tableHoaDon);
		tableHoaDon = new JTable(modelHoaDon) {
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
		tableHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableHoaDon.setGridColor(getBackground());
		tableHoaDon.setRowHeight(tableHoaDon.getRowHeight() + 20);
		tableHoaDon.setSelectionBackground(new Color(255, 255, 128));
		tableHoaDon.setPreferredScrollableViewportSize(new Dimension(1300, 750));
		JTableHeader tableHeader = tableHoaDon.getTableHeader();
		tableHeader.setBackground(new Color(0, 148, 224));
		tableHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		tableHeader.setForeground(Color.WHITE);
		tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
		tblscroll.add(new JScrollPane(tableHoaDon));
		tableHoaDon.addMouseListener(this);
		tblscroll.setViewportView(tableHoaDon);
		tableHoaDon.getColumnModel().getSelectionModel()
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		add(p);
		p.add(pNorth, BorderLayout.NORTH);
		pNorth.add(b);
		b.add(bThongtin);

		b.add(btim);

		String[] tim = { "Mã Hóa Đơn", "Tên Khách Hàng", "Tên Nhân Viên", "Ngày Lập Hóa Đơn", "Thành Tiền" };
		cmbChon = new JComboBox<String>(tim);
		btim.add(cmbChon);
		btim.add(Box.createHorizontalStrut(10));
		cmbTim = new JComboBox<String>();
		cmbTim.setEditable(true);
		AutoCompleteDecorator.decorate(cmbTim);
		cmbTim.setMaximumRowCount(10);
		cmbChon.setSize(200, cmbTim.getPreferredSize().height);
		btim.add(cmbTim);
		btim.add(Box.createHorizontalStrut(10));
		btnTim = new JButton("TÌM KIẾM", new ImageIcon("image/timkiem.png"));
		btnTim.setBackground(new Color(0, 148, 224));
		btnTim.setForeground(Color.WHITE);
		btnTim.setFocusPainted(false);
		btim.add(btnTim);
		btim.add(Box.createHorizontalStrut(100));
		btim.add(lblNgay = new JLabel("Nhập khoảng ngày để thống kê: "));
		btim.add(Box.createHorizontalStrut(10));
		btim.add(txtNgayStart = new JDateChooser());
		btim.add(Box.createHorizontalStrut(10));
		btim.add(txtNgayEnd = new JDateChooser());
		btnTim1 = new JButton("THỐNG KÊ THEO KHOẢNG NGÀY", new ImageIcon("image/timkiem.png"));
		btnTim1.setBackground(new Color(0, 148, 224));
		btnTim1.setForeground(Color.WHITE);
		btnTim1.setFocusPainted(false);
		btim.add(Box.createHorizontalStrut(10));
		btim.add(btnTim1);
		txtNgayStart.setDateFormatString("  yyyy-MM-dd");
		txtNgayStart.setDate(new Date(2022 - 1900, 1 - 1, 1));
		txtNgayEnd.setDateFormatString("  yyyy-MM-dd");
		txtNgayEnd.setDate(new Date(2022 - 1900, 1 - 1, 1));

//		btnTim.setBorder(new EmptyBorder(new Insets(0, 0, 0, 100)));

		tblscroll.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH HÓA ĐƠN: "));
		cmbTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnTim.addActionListener(this);
		p.add(tblscroll, BorderLayout.CENTER);
		p.add(Box.createHorizontalStrut(50), BorderLayout.SOUTH);
		p.add(btnHoaDon = new JButton("LỌC THEO THỨ TỰ HÓA ĐƠN", new ImageIcon("image/hoadon.png")));
		btnHoaDon.setBackground(new Color(0, 148, 224));
		btnHoaDon.setForeground(Color.WHITE);
		btnHoaDon.setFocusPainted(false);
		p.add(Box.createHorizontalStrut(100));
		p.add(lblDoanhThu = new JLabel("TỔNG DOANH THU: "));
		p.add(Box.createHorizontalStrut(20));
		p.add(txtDoanhThu = new JLabel(), BorderLayout.SOUTH);
		p.add(Box.createHorizontalStrut(200));
		p.add(btnSort = new JButton("LỌC THEO THÀNH TIỀN", new ImageIcon("image/thongke.png")));
		btnSort.setBackground(new Color(0, 148, 224));
		btnSort.setForeground(Color.WHITE);
		btnSort.setFocusPainted(false);
		lblDoanhThu.setBorder(new EmptyBorder(new Insets(20, 0, 0, 0)));
		txtDoanhThu.setBorder(new EmptyBorder(new Insets(20, 0, 0, 0)));
		docDuLieuDatabaseVaoTable();
		docMaHoaDonVaoCmbTim();
		tableHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableHoaDon.setDefaultEditor(Object.class, null);
		tableHoaDon.getTableHeader().setReorderingAllowed(false);

		lblNgay.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 24));

		cmbTim.addActionListener(this);
		cmbChon.addActionListener(this);
		btnTim.addActionListener(this);
		btnTim1.addActionListener(this);
		btnSort.addActionListener(this);
		btnHoaDon.addActionListener(this);

		TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(tableHoaDon.getModel());
		tableHoaDon.setRowSorter(sorter1);

		List<RowSorter.SortKey> sortKeyss = new ArrayList<>(50);
		sortKeyss.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
		sorter1.setSortKeys(sortKeyss);
		sortHoaDon = true;

		return p;
	}

	public static void main(String[] args) {
		new FrmDangNhap().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(cmbChon)) {
			if (cmbChon.getSelectedIndex() == 0) {
				docMaHoaDonVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 1) {
				docTenKhachHangVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 2) {
				docTenNhanVienVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 3) {
				docNgayLapHoaDonVaoCmbTim();
			} else if (cmbChon.getSelectedIndex() == 4) {
				docTongTienThanhToanVaoCmbTim();
			}
		}
		if (o.equals(btnTim)) {
			DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
			model.setRowCount(0);
			DecimalFormat df = new DecimalFormat("#,##0");
			SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
			if (cmbTim.getSelectedIndex() == 0) {
				docDuLieuDatabaseVaoTable();
			} else if (cmbChon.getSelectedIndex() == 0) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<HoaDon> list = hoadon_dao.getTatCaHoaDonDaThanhToan();
				for (HoaDon hd : list) {
					if (hd.getMaHD().trim().equals(tim.trim())) {
						modelHoaDon
								.addRow(new Object[] { hd.getMaHD(), hd.getMaKH().getTenKH(), hd.getMaNV().getTenNV(),
										date.format(hd.getNgayLapHoaDon()), df.format(hd.getTongTien()) });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 1) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<HoaDon> list = hoadon_dao.getTatCaHoaDonDaThanhToan();
				for (HoaDon hd : list) {
					if (hd.getMaKH().getTenKH().trim().equals(tim.trim())) {
						modelHoaDon
								.addRow(new Object[] { hd.getMaHD(), hd.getMaKH().getTenKH(), hd.getMaNV().getTenNV(),
										date.format(hd.getNgayLapHoaDon()), df.format(hd.getTongTien()) });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 2) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				List<HoaDon> list = hoadon_dao.getTatCaHoaDonDaThanhToan();
				for (HoaDon hd : list) {
					if (hd.getMaNV().getTenNV().trim().equals(tim.trim())) {
						modelHoaDon
								.addRow(new Object[] { hd.getMaHD(), hd.getMaKH().getTenKH(), hd.getMaNV().getTenNV(),
										date.format(hd.getNgayLapHoaDon()), df.format(hd.getTongTien()) });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 3) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				String[] a = tim.split("-");
				Date d = new Date(Integer.parseInt(a[0]) - 1900, Integer.parseInt(a[1]) - 1, Integer.parseInt(a[2]));
				List<HoaDon> list = hoadon_dao.getTatCaHoaDonDaThanhToan();
				for (HoaDon hd : list) {
					if (hd.getNgayLapHoaDon() == d) {
						modelHoaDon
								.addRow(new Object[] { hd.getMaHD(), hd.getMaKH().getTenKH(), hd.getMaNV().getTenNV(),
										date.format(hd.getNgayLapHoaDon()), df.format(hd.getTongTien()) });
					}
				}
			} else if (cmbChon.getSelectedIndex() == 4) {
				String tim = cmbTim.getSelectedItem().toString().trim();
				double d = Double.parseDouble(tim.replaceAll(",", "").trim());
				List<HoaDon> list = hoadon_dao.getTatCaHoaDonDaThanhToan();
				for (HoaDon hd : list) {
					if (hd.getTongTien() == d) {
						modelHoaDon
								.addRow(new Object[] { hd.getMaHD(), hd.getMaKH().getTenKH(), hd.getMaNV().getTenNV(),
										date.format(hd.getNgayLapHoaDon()), df.format(hd.getTongTien()) });
					}
				}
			}

		}
		if (o.equals(btnTim1)) {
			DefaultTableModel dm = (DefaultTableModel) tableHoaDon.getModel();
			dm.setRowCount(0);
			Date start = txtNgayStart.getDate();
			Date end = txtNgayEnd.getDate();
			if (start != null && end != null) {
				Date min = new Date(start.getYear(), start.getMonth(), start.getDate());
				Date max = new Date(end.getYear(), end.getMonth(), end.getDate());
				if (min.after(max)) {
					JOptionPane.showMessageDialog(this, "Nhập khoảng ngày không hợp lệ, vui lòng nhập lại.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			List<HoaDon> list = new ArrayList<HoaDon>();
			list = hoadon_dao.getTatCaHoaDonDaThanhToan();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			if (start != null) {
				List<HoaDon> listTemp = new ArrayList<HoaDon>();
				for (HoaDon hd : list) {
					listTemp.add(hd);
				}
				list.clear();
				for (HoaDon hd : listTemp) {
					try {
						String output = df.format(hd.getNgayLapHoaDon());
						Date date = new SimpleDateFormat("yyyy-MM-dd").parse(output);
						if (date.compareTo(start) >= 0) {
							list.add(hd);
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			if (end != null) {
				List<HoaDon> listTemp = new ArrayList<HoaDon>();
				for (HoaDon hd : list) {
					listTemp.add(hd);
				}
				list.clear();
				for (HoaDon hd : listTemp) {
					try {
						String output = df.format(hd.getNgayLapHoaDon());
						Date date = new SimpleDateFormat("yyyy-MM-dd").parse(output);
						if (date.compareTo(end) <= 0)
							list.add(hd);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				if (list.size() == 0) {
					txtDoanhThu.setText("0.0 VNĐ");
					JOptionPane.showMessageDialog(this, "Không có hóa đơn nào nằm trong khoảng ngày này.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);

					return;
				}
				DecimalFormat money = new DecimalFormat("#,##0");
				SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
				double tongDoanhThu = 0;
				for (HoaDon hd : list) {
					modelHoaDon.addRow(new Object[] { hd.getMaHD(), hd.getMaKH().getTenKH(), hd.getMaNV().getTenNV(),
							date.format(hd.getNgayLapHoaDon()), money.format(hd.getTongTien()) });
					tongDoanhThu += hd.getTongTien();
				}
				if (tongDoanhThu == 0)
					txtDoanhThu.setText("0.0 VNĐ");
				else
					txtDoanhThu.setText(money.format(tongDoanhThu) + " VNĐ");

			}
		}

		if (o.equals(btnSort)) {
			if (sort == true) {
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableHoaDon.getModel());
				tableHoaDon.setRowSorter(sorter);

				List<RowSorter.SortKey> sortKeys = new ArrayList<>(50);
				sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
				sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
				sort = false;
			} else {
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableHoaDon.getModel());
				tableHoaDon.setRowSorter(sorter);

				List<RowSorter.SortKey> sortKeys = new ArrayList<>(50);
				sortKeys.add(new RowSorter.SortKey(4, SortOrder.DESCENDING));
				sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
				sort = true;
			}
		}
		if (o.equals(btnHoaDon)) {
			if (sortHoaDon == true) {
				TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(tableHoaDon.getModel());
				tableHoaDon.setRowSorter(sorter1);

				List<RowSorter.SortKey> sortKeyss = new ArrayList<>(50);
				sortKeyss.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				sorter1.setSortKeys(sortKeyss);
				sortHoaDon = false;
			} else {
				TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(tableHoaDon.getModel());
				tableHoaDon.setRowSorter(sorter1);

				List<RowSorter.SortKey> sortKeyss = new ArrayList<>(50);
				sortKeyss.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
				sorter1.setSortKeys(sortKeyss);
				sortHoaDon = true;
			}
		}
	}

	public static void docDuLieuDatabaseVaoTable() {
		DefaultTableModel dm = (DefaultTableModel) tableHoaDon.getModel();
		dm.setRowCount(0);
		List<HoaDon> list = hoadon_dao.getTatCaHoaDonDaThanhToan();
		DecimalFormat df = new DecimalFormat("#,##0");
		SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
		double tongDoanhThu = 0;
		for (HoaDon hd : list) {
			modelHoaDon.addRow(new Object[] { hd.getMaHD(), hd.getMaKH().getTenKH(), hd.getMaNV().getTenNV(),
					date.format(hd.getNgayLapHoaDon()), df.format(hd.getTongTien()) });
			tongDoanhThu += hd.getTongTien();
		}
		if (tongDoanhThu == 0)
			txtDoanhThu.setText("0.0 VNĐ");
		else
			txtDoanhThu.setText(df.format(tongDoanhThu) + " VNĐ");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableHoaDon.getSelectedRow();
		if (e.getClickCount() == 2 && tableHoaDon.getSelectedRow() != -1) {
			HoaDon hd = hoadon_dao.getHoaDonTheoMaHD(tableHoaDon.getValueAt(row, 0).toString().trim());
			new FrmChiTietHoaDon(hd.getMaKH().getTenKH(), hd.getMaNV().getTenNV(), hd.getMaHD(), hd.getNgayLapHoaDon())
					.setVisible(true);
		}
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

	public static void docTenKhachHangVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = hoadon_dao.getTatCaTenKhachHang();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docMaHoaDonVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = hoadon_dao.getTatCaMaHoaDon();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docTenNhanVienVaoCmbTim() {
		cmbTim.removeAllItems();
		List<String> list = hoadon_dao.getTatCaTenKhachHang();
		cmbTim.addItem("");
		for (String s : list) {
			cmbTim.addItem(s.trim());
		}
	}

	public static void docNgayLapHoaDonVaoCmbTim() {
		SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
		cmbTim.removeAllItems();
		List<Date> list = hoadon_dao.getTatCaNgayLapHoaDon();
		cmbTim.addItem("");
		for (Date s : list) {
			cmbTim.addItem(date.format(s));
		}
	}

	public static void docTongTienThanhToanVaoCmbTim() {
		DecimalFormat df = new DecimalFormat("#,##0");
		cmbTim.removeAllItems();
		List<Double> list = hoadon_dao.getTatCaLuongNhanVien();
		cmbTim.addItem("");
		for (Double s : list) {
			cmbTim.addItem(df.format(s));
		}
	}

	public void sapXepTheoDoanhThu(List<HoaDon> list, boolean sort) {
		Collections.sort(list, new Comparator<HoaDon>() {
			@Override
			public int compare(HoaDon o1, HoaDon o2) {
				if (sort) {
					if (o1 != null && o2 != null)
						return Double.compare(o1.tinhTongTien(), o2.tinhTongTien());
					return 0;
				} else {
					if (o1 != null && o2 != null)
						return Double.compare(o2.tinhTongTien(), o1.tinhTongTien());
					return 0;
				}
			}
		});
	}

}
