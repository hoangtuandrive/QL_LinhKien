package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LinhKien;

public class LinhKien_DAO {
	public List<LinhKien> getTatCaLinhKien() {
		List<LinhKien> dslk = new ArrayList<LinhKien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from LinhKien";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				String maLK = rs.getString(1).trim();
				String tenLK = rs.getString(2).trim();
				String loaiHang = rs.getString(3).trim();
				String nhaCungCap = rs.getString(4).trim();
				double giaTien = rs.getDouble(5);
				int soLuong = rs.getInt(6);
				LinhKien lk = new LinhKien(maLK, tenLK, loaiHang, nhaCungCap, giaTien, soLuong);
				dslk.add(lk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dslk;
	}

	public boolean create(LinhKien lk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LinhKien values(?,?,?,?,?,?)");
			stmt.setString(1, lk.getMaLK());
			stmt.setString(2, lk.getTenLK());
			stmt.setString(3, lk.getLoaiHang());
			stmt.setString(4, lk.getNhaCungCap());
			stmt.setDouble(5, lk.getDonGia());
			stmt.setInt(6, lk.getSoLuong());

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

	public boolean delete(LinhKien lk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from LinhKien where maLk=?");
			stmt.setString(1, lk.getMaLK());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean update(LinhKien lk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update LinhKien set tenLk=?, loaiHang=?, nhaCungCap=?, donGia=?, soLuong=? where maLk=?");
			stmt.setString(1, lk.getTenLK());
			stmt.setString(2, lk.getLoaiHang());
			stmt.setString(3, lk.getNhaCungCap());
			stmt.setDouble(4, lk.getDonGia());
			stmt.setInt(5, lk.getSoLuong());
			stmt.setString(6, lk.getMaLK());
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

	public List<String> getTatCaMaLinhKien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct maLK from LinhKien";
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

	public List<String> getTatCaTenLinhKien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct tenLk from LinhKien";
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
	public List<String> getTatCaNhaCungCapLinhKien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct nhaCungCap from LinhKien";
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
	public List<String> getTatCaLoaiHangLinhKien() {
		List<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct loaiHang from LinhKien";
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
	public List<Double> getTatCaDonGiaLinhKien() {
		List<Double> list = new ArrayList<Double>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct donGia from LinhKien";
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
	public List<Integer> getTatCaSoLuongTonLinhKien() {
		List<Integer> list = new ArrayList<Integer>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select distinct soLuong from LinhKien";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
