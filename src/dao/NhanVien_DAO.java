package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVien_DAO {
	public List<NhanVien> getTatCaNhanVien() {
		List<NhanVien> dsnv = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NhanVien";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				String SDT = rs.getString(4);
				String chucVu = rs.getString(5);
				double luong = rs.getDouble(6);
				String CMND = rs.getString(7);
				Date ngaySinh = rs.getDate(8);
				String diaChi = rs.getString(9);
				String email = rs.getString(10);
				String taiKhoan = rs.getString(11);
				TaiKhoan tk = new TaiKhoan(taiKhoan, "123");
				boolean trangThai = rs.getBoolean(12);
				NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, SDT, chucVu, luong, CMND, ngaySinh, diaChi, email, tk,
						trangThai);
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}

	public boolean createNV(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setBoolean(3, nv.getGioiTinh());
			stmt.setString(4, nv.getSDT());
			stmt.setString(5, nv.getChucVu());
			stmt.setDouble(6, nv.getLuong());
			stmt.setString(7, nv.getCMND());
			stmt.setDate(8, (java.sql.Date) nv.getNgaySinh());
			stmt.setString(9, nv.getDiaChi());
			stmt.setString(10, nv.getEmail());
			stmt.setString(11, nv.getTaiKhoan().getTenDN());
			stmt.setBoolean(12, true);

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

	public boolean delete(String id) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update NhanVien set trangThai = 0, taiKhoan = null where maNV = ?");
			stmt.setString(1, id);
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

	public boolean updateNV(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update NhanVien set tenNV=?, gioiTinh=?, SDT=?, "
					+ "chucVu=?, luong=?, CMND=?, ngaySinh=?, diaChi=?, email=?, taiKhoan=?, trangThai=? where maNV = ?");

			stmt.setString(1, nv.getTenNV());
			stmt.setBoolean(2, nv.getGioiTinh());
			stmt.setString(3, nv.getSDT());
			stmt.setString(4, nv.getChucVu());
			stmt.setDouble(5, nv.getLuong());
			stmt.setString(6, nv.getCMND());
			stmt.setDate(7, (java.sql.Date) nv.getNgaySinh());
			stmt.setString(8, nv.getDiaChi());
			stmt.setString(9, nv.getEmail());
			stmt.setString(10, nv.getTaiKhoan().getTenDN());
			stmt.setBoolean(11, nv.getTrangThai());
			stmt.setString(12, nv.getMaNV());

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

	public boolean setNullTK(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update NhanVien set taiKhoan=null where maNV=?");
			stmt.setString(1, nv.getMaNV());
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

	public NhanVien getTenNV(String maNV) {
		NhanVien nv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select maNV,tenNV from NhanVien where maNV=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maNV);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery();
			// Duyệt trên kết quả trả về
			while (rs.next()) {

				String maNVien = rs.getString(1);
				String tenNVien = rs.getString(2);
				nv = new NhanVien(maNVien, tenNVien);
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

	public List<String> getTatCaMaNhanVien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct maNV from NhanVien";
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
			String sql = "select distinct tenNV from NhanVien";
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

	public List<Boolean> getTatCaGioiTinhNhanVien() {
		List<Boolean> list = new ArrayList<Boolean>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct gioiTinh from NhanVien";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				list.add(rs.getBoolean(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<String> getTatCaSDTNhanVien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct SDT from NhanVien";
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

	public List<String> getTatCaChucVuNhanVien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct chucVu from NhanVien";
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

	public List<Double> getTatCaLuongNhanVien() {
		List<Double> list = new ArrayList<Double>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct luong from NhanVien";
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
	
	public List<String> getTatCaCMNDNhanVien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct CMND from NhanVien";
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
	
	public List<Date> getTatCaNgaySinhNhanVien() {
		List<Date> list = new ArrayList<Date>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct ngaySinh from NhanVien";
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
	public List<String> getTatCaDiaChiNhanVien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct diaChi from NhanVien";
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
	public List<String> getTatCaEmailNhanVien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct email from NhanVien";
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
	public List<Boolean> getTatCaTrangThaiNhanVien() {
		List<Boolean> list = new ArrayList<Boolean>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct trangThai from NhanVien";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				list.add(rs.getBoolean(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
