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
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;

public class BanHang_DAO {
	public String getMaKhachHangCuoi() {
		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select Top 1 * from KhachHang order by maKH Desc";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				ma = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}

	public String getMaHoaDonCuoi() {
		String ma = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select top 1 * from HoaDon order by maHD desc";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				ma = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}

	public boolean createGioHang(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"insert into HoaDon(maHD,maKH) values(?,?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setString(2, hd.getMaKH().getMaKH());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean updateTrangThai(String id, boolean gioHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update KhachHang set gioHang = ? where maKH = ?");
			stmt.setBoolean(1, gioHang);
			stmt.setString(2, id);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public List<KhachHang> getKhachHangTheoGioHang(boolean gioHang){
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select * from KhachHang where gioHang = ?";
			statement = con.prepareStatement(sql);
			statement.setBoolean(1, gioHang);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery();
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				String sdt = rs.getString(4);
				String cmnd = rs.getString(5);
				Date ngaySinh = rs.getDate(6);
				String diaChi = rs.getString(7);
				String email = rs.getString(8);
				KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh, sdt, cmnd, ngaySinh, diaChi, email);
				dsKH.add(kh);
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
		return dsKH;
	}
	public boolean update(String maHD, String maNV, Date ngayLapHoaDon, double tongTien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update HoaDon\r\n"
					+ "set maNV=?,ngayLapHoaDon=?,tongTienThanhToan=?\r\n"
					+ "where maHD=?");
			stmt.setString(1, maNV);
			java.sql.Timestamp sqlDate = new java.sql.Timestamp(ngayLapHoaDon.getTime());
			stmt.setTimestamp(2, sqlDate);
			stmt.setDouble(3, tongTien);
			stmt.setString(4, maHD);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean delete(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from HoaDon where maHD=?");
			stmt.setString(1, hd.getMaHD());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
}
