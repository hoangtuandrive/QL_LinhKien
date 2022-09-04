package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

public class HoaDon_DAO {
	public List<HoaDon> getTatCaHoaDon() {
		List<HoaDon> dshd = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from HoaDon";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				String maHD = rs.getString(1);
				NhanVien nhanVien = new NhanVien(rs.getString(2));
				KhachHang khachHang = new KhachHang(rs.getString(3));
				java.util.Date ngayLap = rs.getDate(4);

				HoaDon hoaDon = new HoaDon(maHD, nhanVien, khachHang, ngayLap);
				dshd.add(hoaDon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshd;
	}

	public List<HoaDon> getTatCaHoaDonChuaTinhTien() {
		List<HoaDon> dshd = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select hd.maHD,hd.maNV,hd.maKH,hd.ngayLapHoaDon,kh.SDT,kh.tenKH\r\n"
					+ "from HoaDon hd join KhachHang kh on hd.maKH = kh.maKH\r\n" + " where tongTienThanhToan is NULL";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maNV = rs.getString(2);
				String maKH = rs.getString(3);
				Date ngayLapHoaDon = rs.getTimestamp(4);
				String SDT = rs.getString(5);
				String tenKH = rs.getString(6);

				KhachHang kh = new KhachHang(maKH.trim(), tenKH.trim(), SDT.trim());
				NhanVien nv = new NhanVien(maNV);

				HoaDon hd = new HoaDon(maHD.trim(), nv, kh, ngayLapHoaDon);
				dshd.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshd;
	}

	public NhanVien getNhanVienSuDung(String tenDN) {
		NhanVien nv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select * from NhanVien where maNV=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, tenDN);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery();
			// Duyệt trên kết quả trả về
			while (rs.next()) {

				String maNVien = rs.getString(1);
				String tenNV = rs.getString(2);
				nv = new NhanVien(maNVien, tenNV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nv;
	}

	public HoaDon getHoaDonChuaTinhTienDeThanhToan(String maKH_thanhtoan) {
		HoaDon hd = null;
		List<ChiTietHoaDon> listCTHD = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select hd.maHD, hd.maNV, hd.maKH, hd.ngayLapHoaDon, kh.SDT, cthd.maHD, lk.maLK, lk.tenLK, "
					+ "lk.loaiHang, lk.nhaCungCap, cthd.soLuong, cthd.giaTien, kh.tenKH \r\n"
					+ "from HoaDon hd left join ChiTietHoaDon cthd on hd.maHD = cthd.maHD \r\n"
					+ "left join LinhKien lk on cthd.maLK = lk.maLK \r\n"
					+ "join KhachHang kh on hd.maKH = kh.maKH \r\n" + "where tongTienThanhToan is NULL and hd.maKH = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maKH_thanhtoan);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery();
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maNV = rs.getString(2);
				String maKH = rs.getString(3);
				Date ngayLapHoaDon = rs.getTimestamp(4);
				String SDT = rs.getString(5);
				String maLK = rs.getString(7);
				String tenLK = rs.getString(8);
				String loaiHang = rs.getString(9);
				String nhaCungCap = rs.getString(10);
				int soLuong = rs.getInt(11);
				Double giaTien = rs.getDouble(12);
				String tenKH = rs.getString(13);

				KhachHang kh = new KhachHang(maKH.trim(), tenKH.trim() ,SDT.trim());
				NhanVien nv = new NhanVien(maNV);
				LinhKien maDichVu = new LinhKien(maLK, tenLK, loaiHang, nhaCungCap, giaTien, 0);

				ChiTietHoaDon cthd = new ChiTietHoaDon(hd, maDichVu, soLuong, giaTien);
				listCTHD.add(cthd);

				hd = new HoaDon(maHD, new NhanVien(), kh, ngayLapHoaDon, listCTHD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hd;
	}

	public List<HoaDon> getTatCaHoaDonDaThanhToan() {
		List<HoaDon> dshd = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select hd.maHD,hd.maKH,k.SDT,k.tenKH,hd.maNV,nv.tenNV,hd.ngayLapHoaDon,hd.tongTienThanhToan\r\n"
					+ "from HoaDon hd \r\n" + "join KhachHang k on hd.maKH = k.maKH\r\n"
					+ "join NhanVien nv on nv.maNV = hd.maNV\r\n" + "where tongTienThanhToan is not NULL";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maKH = rs.getString(2);
				String SDT = rs.getString(3);
				String tenKH = rs.getString(4).trim();
				String maNV = rs.getString(5);
				String tenNV = rs.getString(6).trim();
				Date ngayLapHoaDon = rs.getTimestamp(7);
				double tongTienThanhToan = rs.getDouble(8);
				List<ChiTietHoaDon> cthd = getCTHDtheoMaHD(maHD);
				KhachHang kh = new KhachHang(maKH, tenKH, SDT);
				NhanVien nv = new NhanVien(maNV, tenNV);
				HoaDon hd = new HoaDon(maHD, nv, kh, ngayLapHoaDon, cthd);
				dshd.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshd;
	}
	
	public List<ChiTietHoaDon> getCTHDtheoMaHD(String maHoaDon) {
		List<ChiTietHoaDon> dshd = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select lk.maLK,lk.tenLK,lk.loaiHang,lk.nhaCungCap,ct.soLuong,lk.donGia,ct.thanhTien,ct.maHD\r\n"
					+ "from ChiTietHoaDon ct join LinhKien lk on ct.maLK = lk.maLK\r\n" + " where ct.maHD = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maHoaDon);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery();
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				String maLK = rs.getString(1).trim();
				String tenLK = rs.getString(2).trim();
				String loaiHang = rs.getString(3).trim();
				String nhaCungCap = rs.getString(4).trim();
				int soLuong = rs.getInt(5);
				double donGia = rs.getDouble(6);

				String maHD = rs.getString(8).trim();

				HoaDon hd = new HoaDon(maHD);
				LinhKien lk = new LinhKien(maLK, tenLK, loaiHang, nhaCungCap, donGia, soLuong);
				ChiTietHoaDon hddv = new ChiTietHoaDon(hd, lk, soLuong, donGia);
				dshd.add(hddv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dshd;
	}

	public HoaDon getHoaDonTheoMaHD(String maHoaDon) {
		HoaDon hd = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select hd.maHD,hd.maKH,k.SDT,k.tenKH,hd.maNV,nv.tenNV,hd.ngayLapHoaDon\r\n"
					+ "from HoaDon hd \r\n" + "join KhachHang k on hd.maKH = k.maKH\r\n"
					+ "join NhanVien nv on nv.maNV = hd.maNV\r\n" + "where maHD = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maHoaDon);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery();
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maKH = rs.getString(2);
				String SDT = rs.getString(3);
				String tenKH = rs.getString(4).trim();
				String maNV = rs.getString(5);
				String tenNV = rs.getString(6).trim();
				Date ngayLapHoaDon = rs.getTimestamp(7);

				List<ChiTietHoaDon> cthd = getCTHDtheoMaHD(maHD);
				KhachHang kh = new KhachHang(maKH, tenKH, SDT);
				NhanVien nv = new NhanVien(maNV, tenNV);
				hd = new HoaDon(maHD, nv, kh, ngayLapHoaDon, cthd);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hd;
	}

	public List<String> getTatCaTenKhachHang() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct kh.tenKH from HoaDon hd join KhachHang kh on hd.maKH=kh.maKH where hd.tongTienThanhToan is NOT NULL";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<String> getTatCaMaHoaDon() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct maHD from HoaDon where tongTienThanhToan is NOT NULL";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<String> getTatCaTenNhanVien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct nv.tenNV from HoaDon hd join NhanVien nv where hd.maNV=kh.maNV where hd.tongTienThanhToan is NOT NULL";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Date> getTatCaNgayLapHoaDon() {
		List<Date> list = new ArrayList<Date>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct ngayLapHoaDon from HoaDon where tongTienThanhToan is NOT NULL";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				list.add(rs.getTimestamp(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Double> getTatCaLuongNhanVien() {
		List<Double> list = new ArrayList<Double>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct tongTienThanhToan from HoaDon where tongTienThanhToan is NOT NULL";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				list.add(rs.getDouble(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
