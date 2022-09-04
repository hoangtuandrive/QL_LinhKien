package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.LinhKien;


public class ChiTietHoaDon_DAO {
	public List<ChiTietHoaDon> getTatCaHoaDonLK(){
		List<ChiTietHoaDon> dshd = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from ChiTietHoaDon";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maLK = rs.getString(2);
				int soLuong = rs.getInt(3);
				double giaTien = rs.getDouble(4);
				HoaDon hd = new HoaDon(maHD);
				LinhKien lk = new LinhKien(maLK);
				ChiTietHoaDon hdlk = new ChiTietHoaDon(hd, lk, soLuong, giaTien);
				dshd.add(hdlk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshd;
	}
	public boolean create(ChiTietHoaDon cthd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?,?)");
			stmt.setString(1, cthd.getMaHoaDon().getMaHD());
			stmt.setString(2, cthd.getMaLinhKien().getMaLK());
			stmt.setInt(3, cthd.getSoLuong());
			stmt.setDouble(4, cthd.getGiaTien());
			stmt.setDouble(5, cthd.getThanhTien());
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
	public boolean delete(String maLinhKien,String maHoaDon) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete ChiTietHoaDon where maLK = ? and maHD = ?");
			stmt.setString(1, maLinhKien);
			stmt.setString(2, maHoaDon);
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
	public boolean updateSoLuong(ChiTietHoaDon cthd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update ChiTietHoaDon set soLuong=?, thanhTien=? where maHD=? and maLK=?");
			stmt.setInt(1, cthd.getSoLuong());
			stmt.setDouble(2, cthd.getThanhTien());
			stmt.setString(3, cthd.getMaHoaDon().getMaHD());
			stmt.setString(4, cthd.getMaLinhKien().getMaLK());
			
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
//	public List<ChiTietHoaDon> getCTHDTheoMaHD(String maHoaDon){
//		List<ChiTietHoaDon> dshd = new ArrayList<ChiTietHoaDon>();
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement statement = null;
//		try {
//			String sql = "select * from ChiTietHoaDon where maHD = ?";
//			statement = con.prepareStatement(sql);
//			statement.setString(1, maHoaDon);
//			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
//			ResultSet rs = statement.executeQuery();
//			// Duyệt trên kết quả trả về
//			while (rs.next()) {
//				String maHD = rs.getString(1).trim();
//				String maLK = rs.getString(2).trim();
//				int soLuong = rs.getInt(3);
//				double giaTien = rs.getDouble(4);
//				HoaDon hd = new HoaDon(maHD);
//				LinhKien lk = new LinhKien(maLK);
//				ChiTietHoaDon hdlk = new ChiTietHoaDon(hd, lk, soLuong, giaTien);
//				dshd.add(hdlk);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return dshd;
//	}

	public List<ChiTietHoaDon> getCTHDTheoMaHDLenTable(String maHoaDon){
		List<ChiTietHoaDon> dshd = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select lk.maLK,lk.tenLK,lk.loaiHang,lk.nhaCungCap,ct.soLuong,lk.donGia,ct.thanhTien,ct.maHD\r\n" + 
					"from ChiTietHoaDon ct join LinhKien lk on ct.maLK = lk.maLK\r\n" + 
					" where ct.maHD = ?";
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
				LinhKien lk = new LinhKien(maLK,tenLK,loaiHang,nhaCungCap,donGia,soLuong);
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
}
