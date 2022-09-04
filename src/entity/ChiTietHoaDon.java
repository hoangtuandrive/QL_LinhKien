package entity;

public class ChiTietHoaDon {
	private HoaDon maHoaDon;
	private LinhKien maLinhKien;
	private int soLuong;
	private double giaTien;
	private double thanhTien;

	public ChiTietHoaDon(HoaDon maHoaDon, LinhKien maLinhKien, int soLuong, double giaTien) {
		this.maHoaDon = maHoaDon;
		this.maLinhKien = maLinhKien;
		this.soLuong = soLuong;
		this.giaTien = giaTien;
		this.thanhTien = giaTien * soLuong;
	}

	public HoaDon getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(HoaDon maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LinhKien getMaLinhKien() {
		return maLinhKien;
	}

	public void setMaLinhKien(LinhKien maLinhKien) {
		this.maLinhKien = maLinhKien;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public ChiTietHoaDon(LinhKien maLinhKien, int soLuong) {
		this.maLinhKien = maLinhKien;
		this.soLuong = soLuong;
	}

	public ChiTietHoaDon() {
		super();
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [maHoaDon=" + maHoaDon + ", maLinhKien=" + maLinhKien + ", soLuong=" + soLuong
				+ ", giaTien=" + giaTien + ", thanhTien=" + thanhTien + "]";
	}

}
