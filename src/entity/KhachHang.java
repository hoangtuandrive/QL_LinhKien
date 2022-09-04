package entity;

import java.util.Date;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private boolean gioiTinh;
	private String SDT;
	private String CMND;
	private Date ngaySinh;
	private String diaChi;
	private String email;
	private boolean gioHang;

	public KhachHang(String maKH, String tenKH, boolean gioiTinh, String sDT, String cMND, Date ngaySinh, String diaChi,
			String email, boolean gioHang) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.gioiTinh = gioiTinh;
		SDT = sDT;
		CMND = cMND;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
		this.gioHang = gioHang;
	}

	public boolean isGioHang() {
		return gioHang;
	}

	public void setGioHang(boolean gioHang) {
		this.gioHang = gioHang;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", gioiTinh=" + gioiTinh + ", SDT=" + SDT + ", CMND="
				+ CMND + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maKH == null) ? 0 : maKH.hashCode());
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
		KhachHang other = (KhachHang) obj;
		if (maKH == null) {
			if (other.maKH != null)
				return false;
		} else if (!maKH.equals(other.maKH))
			return false;
		return true;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public KhachHang(String maKH, String tenKH, boolean gioiTinh, String sDT, String cMND, Date ngaySinh, String diaChi,
			String email) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.gioiTinh = gioiTinh;
		SDT = sDT;
		CMND = cMND;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
	}

	public KhachHang(String maKH, String sDT) {
		this.maKH = maKH;
		SDT = sDT;
	}
	
	public KhachHang(String maKH, String tenKH ,String sDT) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		SDT = sDT;
	}

	public KhachHang() {
	}

	public KhachHang(String maKH) {
		this.maKH = maKH;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
