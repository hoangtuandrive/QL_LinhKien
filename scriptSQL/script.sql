create database QlLinhKien
go

use QlLinhKien
go

create table NhanVien(
maNV nchar(20) primary key,
tenNV nchar(50),
gioiTinh bit,
SDT nchar(10),
chucVu nchar(30),
luong float,
CMND nchar(50),
ngaySinh date,
diaChi nchar(50),
email nchar(50),
taiKhoan nchar(10) null,
trangThai bit
)


create table KhachHang(
maKH nchar(20) primary key,
tenKH nchar(100),
gioiTinh bit,
SDT nchar(10),
CMND nchar(50),
ngaySinh date,
diaChi nchar(100),
email nchar(100),
gioHang bit
)

create table TaiKhoan(
tenDN nchar(20) primary key,
matKhau nchar(30)
CONSTRAINT fk_htk_id_nv FOREIGN KEY (tenDN) REFERENCES NhanVien (maNV)
)

create table LinhKien(
maLK nchar(20) primary key,
tenLk nchar(100),
loaiHang nchar(100),
nhaCungCap nchar(100),
donGia money,
soLuong int
)

create table HoaDon(
maHD nchar(20)not null primary key,
maNV nchar(20),
maKH nchar(20),
ngayLapHoaDon date,	
tongTienThanhToan money null,
CONSTRAINT fk_htk_id_nvhd FOREIGN KEY (maNV) REFERENCES NhanVien (maNV),
CONSTRAINT fk_htk_id_khhd FOREIGN KEY (maKH) REFERENCES KhachHang (maKH)
)

create table ChiTietHoaDon(
maHD nchar(20),
maLK nchar(20) not null,
soLuong int,
giaTien nchar(10),
thanhTien money
CONSTRAINT fk_htk_id_hdct FOREIGN KEY (maHD) REFERENCES HoaDon (maHD),
CONSTRAINT fk_htk_id_lkct FOREIGN KEY (maLK) REFERENCES LinhKien (maLK),
primary key(maHD, maLK)
)

