package entity;

import java.util.Date;

public class NhanVien {
	public NhanVien(String maNV, String tenNV) {
		this.maNV = maNV;
		this.tenNV = tenNV;
	}

	private String maNV;
	private String tenNV;
	private Boolean gioiTinh;
	private String SDT;
	private String chucVu;
	private double luong;
	private String CMND;
	private Date ngaySinh;
	private String diaChi;
	private String email;
	private TaiKhoan taiKhoan;
	private Boolean trangThai;

	public NhanVien() {
	}

	public NhanVien(String maNV) {
		this.maNV = maNV;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
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
		NhanVien other = (NhanVien) obj;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", gioiTinh=" + gioiTinh + ", SDT=" + SDT + ", chucVu="
				+ chucVu + ", luong=" + luong + ", CMND=" + CMND + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi
				+ ", email=" + email + ", taiKhoan=" + taiKhoan + ", trangThai=" + trangThai + "]";
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public Boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		this.CMND = cMND;
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

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public NhanVien(String maNV, String tenNV, Boolean gioiTinh, String sDT, String chucVu, double luong, String cMND,
			Date ngaySinh, String diaChi, String email, TaiKhoan taiKhoan, Boolean trangThai) {
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.gioiTinh = gioiTinh;
		this.SDT = sDT;
		this.chucVu = chucVu;
		this.luong = luong;
		this.CMND = cMND;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
		this.taiKhoan = taiKhoan;
		this.trangThai = trangThai;
	}

}
