package entity;

public class TaiKhoan {
	private String tenDN;
	private String matKhau;
	
	public TaiKhoan() {
		super();
	}
	public TaiKhoan(String tenDN, String matKhau) {
		super();
		this.tenDN = tenDN;
		this.matKhau = matKhau;
	}
	public String getTenDN() {
		return tenDN;
	}
	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	@Override
	public String toString() {
		return "TaiKhoan[TenDn=" + tenDN + ", MatKhau=" + matKhau + "]";
	}
}
