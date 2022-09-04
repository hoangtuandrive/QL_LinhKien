package entity;

import java.util.Date;
import java.util.List;

public class HoaDon {
	private String maHD;
	private NhanVien maNV;
	private KhachHang maKH;
	private Date ngayLapHoaDon;
	List<ChiTietHoaDon> chiTietHoaDon;
	private double tongTien;

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public NhanVien getMaNV() {
		return maNV;
	}

	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}

	public KhachHang getMaKH() {
		return maKH;
	}

	public void setMaKH(KhachHang maKH) {
		this.maKH = maKH;
	}

	public Date getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(Date ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	public List<ChiTietHoaDon> getChiTietHoaDon() {
		return chiTietHoaDon;
	}

	public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) {
		this.chiTietHoaDon = chiTietHoaDon;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public HoaDon(String maHD, NhanVien maNV, KhachHang maKH, Date ngayLapHoaDon, List<ChiTietHoaDon> chiTietHoaDon) {
		this.maHD = maHD;
		this.maNV = maNV;
		this.maKH = maKH;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.chiTietHoaDon = chiTietHoaDon;
		this.tongTien = tinhTongTien();
	}

	
	
	public HoaDon(String maHD, NhanVien maNV, KhachHang maKH, Date ngayLapHoaDon) {
		this.maHD = maHD;
		this.maNV = maNV;
		this.maKH = maKH;
		this.ngayLapHoaDon = ngayLapHoaDon;
	}
	
	

	public HoaDon(String maHD, KhachHang maKH) {
		this.maHD = maHD;
		this.maKH = maKH;
	}

	public HoaDon(String maHD) {
		this.maHD = maHD;
	}

	public HoaDon() {
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", maNV=" + maNV + ", maKH=" + maKH + ", ngayLapHoaDon=" + ngayLapHoaDon
				+ ", chiTietHoaDon=" + chiTietHoaDon + ", tongTien=" + tongTien + "]";
	}

	public double tinhTongTien() {
		double tongTien = 0;
		for (ChiTietHoaDon cthd : chiTietHoaDon) {
			tongTien += cthd.getThanhTien();
		}
		return tongTien;
	}

}
