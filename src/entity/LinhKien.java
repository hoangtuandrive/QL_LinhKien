package entity;

public class LinhKien {
	private String maLK;
	private String tenLK;
	private String loaiHang;
	private String nhaCungCap;
	private double donGia;
	private int soLuong;

	public String getMaLK() {
		return maLK;
	}

	public void setMaLK(String maLK) {
		this.maLK = maLK;
	}

	public String getTenLK() {
		return tenLK;
	}

	public void setTenLK(String tenLK) {
		this.tenLK = tenLK;
	}

	public String getLoaiHang() {
		return loaiHang;
	}

	public void setLoaiHang(String loaiHang) {
		this.loaiHang = loaiHang;
	}

	public String getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "LinhKien [maLK=" + maLK + ", tenLK=" + tenLK + ", loaiHang=" + loaiHang + ", nhaCungCap=" + nhaCungCap
				+ ", donGia=" + donGia + ", soLuong=" + soLuong + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLK == null) ? 0 : maLK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinhKien other = (LinhKien) obj;
		if (maLK == null) {
			if (other.maLK != null)
				return false;
		} else if (!maLK.equals(other.maLK))
			return false;
		return true;
	}

	public LinhKien(String maLK, String tenLK, String loaiHang, String nhaCungCap, double donGia, int soLuong) {
		this.maLK = maLK;
		this.tenLK = tenLK;
		this.loaiHang = loaiHang;
		this.nhaCungCap = nhaCungCap;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}

	public LinhKien(String maLK) {
		this.maLK = maLK;
	}

	public LinhKien() {
	}

}
