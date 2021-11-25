CREATE DATABASE STIA
GO 

USE STIA
GO 

CREATE TABLE KhachHang
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	MaSoThue NVARCHAR(100) NULL,
	Ten NVARCHAR(100) NOT NULL,
	DiaChi NVARCHAR(150) NOT NULL,
	Email NVARCHAR(50) NOT NULL,
	SoDienThoai NVARCHAR(11) NOT NULL,
	GhiChu NVARCHAR(100) NULL,
	TrangThai BIT NOT NULL,
)
GO


CREATE TABLE PhongBan
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenPhongBan NVARCHAR(100) NOT NULL,
	TrangThai BIT NOT NULL,
)
GO

CREATE TABLE TaiKhoan
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenTK NVARCHAR(50) NOT NULL,
	MatKhau NVARCHAR(50) NOT NULL,
	VaiTro BIT NOT NULL,
	TrangThai BIT NOT NULL,
)
GO

CREATE TABLE NhanVien
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	HoTen NVARCHAR(50) NOT NULL,
	GioiTinh BIT NOT NULL,
	NgaySinh DATE NOT NULL,
	DiaChi NVARCHAR(200) NOT NULL,
	Email NVARCHAR(50) NOT NULL,
	SoDienThoai NVARCHAR(15) NOT NULL,
	CCCD NVARCHAR(12) NOT NULL,
	ChucVu BIT NOT NULL,
	GhiChu NVARCHAR(100) NULL,
	TrangThai BIT NOT NULL,
	Id_PhongBan INT NOT NULL,
	Id_TaiKhoan INT NULL,
	Id_TruongPhong INT NULL,
	FOREIGN KEY(Id_PhongBan) REFERENCES PHONGBAN(Id),
	FOREIGN KEY(Id_TaiKhoan) REFERENCES TAIKHOAN(Id),
	FOREIGN KEY(Id_TruongPhong) REFERENCES dbo.NhanVien(Id)
)
GO

CREATE TABLE NhaCungCap
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenNCC NVARCHAR(100) NOT NULL,
	DiaChi NVARCHAR(200) NOT NULL,
	SDT NVARCHAR(100) NOT NULL,
	Email NVARCHAR(100) NOT NULL,
	GhiChu NVARCHAR(100) NULL,
	TrangThai BIT NOT NULL
)
GO

CREATE TABLE DanhMuc
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenDanhMuc NVARCHAR(100) NOT NULL,
	TrangThai BIT NOT NULL
)
GO


CREATE TABLE HangHoa
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenHang NVARCHAR(100) NOT NULL,
	Id_NhaCungCap INT NOT NULL,
	Id_DanhMuc INT NOT NULL,
	FOREIGN KEY(Id_NhaCungCap) REFERENCES dbo.NhaCungCap(Id),
	FOREIGN KEY(Id_DanhMuc) REFERENCES dbo.DanhMuc(Id),
)
GO

---------------------Thành phần chi tiết hàng hóa-----------------------
CREATE TABLE KichThuoc
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenKichThuoc NVARCHAR(50) NOT NULL,
	TrangThai BIT NOT NULL
)
GO

CREATE TABLE MauSac
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenMau NVARCHAR(20) NOT NULL,
	TrangThai BIT NOT NULL
)
GO


CREATE TABLE ApSuat
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenApSuat NVARCHAR(20) NOT NULL,
	TrangThai BIT NOT NULL
)
GO

CREATE TABLE DonViTinh
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenDonVi NVARCHAR(20) NOT NULL,
	TrangThai BIT NOT NULL
)
GO


CREATE TABLE LoaiHang
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenLoai NVARCHAR(50) NOT NULL,
	TrangThai BIT NOT NULL
)
GO

CREATE TABLE ChieuDay
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	DoDay FLOAT NULL,
)
GO

CREATE TABLE ChiTietHangHoa
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	SoLuong NVARCHAR(50) NOT NULL,
	GiaNhap INT NULL,
	GiaBan INT NULL,
	GhiChu NVARCHAR(100),
	TrangThai BIT NOT NULL,
	Id_KichThuoc INT NULL,
	Id_MauSac INT NULL,
	Id_HangHoa INT NOT NULL,
	Id_ApSuat INT NULL,
	Id_DonViTinh INT NOT NULL,
	Id_LoaiHang INT NOT NULL,
	Id_ChieuDay INT NULL,
	FOREIGN KEY(Id_KichThuoc) REFERENCES dbo.KichThuoc(Id),
	FOREIGN KEY(Id_MauSac) REFERENCES dbo.MauSac(Id),
	FOREIGN KEY(Id_HangHoa) REFERENCES dbo.HangHoa(Id),
	FOREIGN KEY(Id_ApSuat) REFERENCES dbo.ApSuat(Id),
	FOREIGN KEY(Id_DonViTinh) REFERENCES dbo.DonViTinh(Id),
	FOREIGN KEY(Id_LoaiHang) REFERENCES dbo.LoaiHang(Id),
	FOREIGN KEY(Id_ChieuDay) REFERENCES dbo.ChieuDay(Id),
)
GO

CREATE TABLE HoaDon
(
	Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	ThanhTien INT NULL,
	DatCoc INT NULL,
	NgayTao DATETIME NOT NULL,
	GhiChu NVARCHAR(100) NULL,
	DiaChi NVARCHAR(100) NOT NULL,
	TrangThaiHD BIT,
	TrangThaiTT BIT,
	Id_NhanVien INT NOT NULL,
	Id_KhachHang INT NULL,
	FOREIGN KEY(Id_NhanVien) REFERENCES dbo.NhanVien(Id),
	FOREIGN KEY(Id_KhachHang) REFERENCES dbo.KhachHang(Id)
)

CREATE TABLE HoaDonChiTiet
(
	Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	SoLuong INT NOT NULL,
	TrangThai BIT NOT NULL,
	Id_HoaDon INT NOT NULL,
	Id_CTHangHoa INT NOT NULL,
	FOREIGN KEY(Id_HoaDon) REFERENCES dbo.HoaDon(Id),
	FOREIGN KEY(Id_CTHangHoa) REFERENCES dbo.ChiTietHangHoa(Id)
)
go

select * from HoaDonChiTiet
select * from HangHoa
select * from NhanVien
select * from KhachHang

select * from NhaCungCap Where TrangThai = 1 and SDT like '%%'
SELECT * FROM NhaCungCap WHERE TrangThai = 1 AND SDT like %'098'%
SELECT * FROM NhaCungCap WHERE TrangThai = 1 AND SDT like '%0945%'
--INSERT INTO dbo.ChiTietHangHoa(SoLuong,GiaNhap,GiaBan,GhiChu,TrangThai,Id_KichThuoc,Id_MauSac,Id_HangHoa,Id_ApSuat,Id_DonViTinh,Id_LoaiHang,Id_ChieuDay) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)

/*
--Thêm dữ liệu loại hàng
INSERT INTO dbo.LoaiHang(TenLoai,TrangThai)VALUES(N'Cấp nước',1)
INSERT INTO dbo.LoaiHang(TenLoai,TrangThai)VALUES(N'Thoát nước',1)
INSERT INTO dbo.LoaiHang(TenLoai,TrangThai)VALUES(N'Chống sét',1)
INSERT INTO dbo.LoaiHang(TenLoai,TrangThai)VALUES(N'Hệ thống PCCC',1)
INSERT INTO dbo.LoaiHang(TenLoai,TrangThai)VALUES(N'Khác',1)
--Thêm dữ liệu đơn vị tính
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'M',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Cái',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Bộ',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Cuộn',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Tủ',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Bình',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Cọc',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Kg',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Đôi',1)
--Thêm dữ liệu áp suất
INSERT INTO dbo.ApSuat(TenApSuat,TrangThai)VALUES(N'PN10',1)
INSERT INTO dbo.ApSuat(TenApSuat,TrangThai)VALUES(N'Class 2',1)
INSERT INTO dbo.ApSuat(TenApSuat,TrangThai)VALUES(N'Class 3',1)
--Thêm dữ liệu chiều dày
INSERT INTO dbo.ChieuDay(DoDay)VALUES(3.7)
INSERT INTO dbo.ChieuDay(DoDay)VALUES(2.8)
INSERT INTO dbo.ChieuDay(DoDay)VALUES(2.3)
INSERT INTO dbo.ChieuDay(DoDay)VALUES(NULL)
--Thêm dữ liệu màu sắc
INSERT INTO dbo.MauSac(TenMau,TrangThai)VALUES(N'Vàng',1)
INSERT INTO dbo.MauSac(TenMau,TrangThai)VALUES(N'Xanh',1)
INSERT INTO dbo.MauSac(TenMau,TrangThai)VALUES(N'Đỏ',1)
INSERT INTO dbo.MauSac(TenMau,TrangThai)VALUES(N'Trắng',1)
--Thêm dữ liệu danh mục
INSERT INTO dbo.DanhMuc(TenDanhMuc,TrangThai)VALUES(N'Vật tư điện',1)
INSERT INTO dbo.DanhMuc(TenDanhMuc,TrangThai)VALUES(N'Vật tư nước',1)
INSERT INTO dbo.DanhMuc(TenDanhMuc,TrangThai)VALUES(N'Bảo hộ lao động',1)
--Thêm dữ liệu nhà cung cấp
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Rạng Đông',N'Hà Nội','0987654321',N'rangdong@gmail.com',null,1)
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Cadisun',N'Hà Nội','0987614321',N'randong@gmail.com',null,1)
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Sino',N'Hà Nội','0987653321',N'rangdng@gmail.com',null,1)
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Sino/ Vanloock',N'Hà Nội','0984654321',N'randng@gmail.com',null,1)
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Điện cơ',N'Hà Nội','0987655621',N'rngdong@gmail.com',null,1)
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Tiền phong/ Deko',N'Hà Nội','0945654321',N'randong@gmail.com',null,1)
--Thêm dữ liệu phòng ban
INSERT INTO dbo.PhongBan(TenPhongBan,TrangThai)VALUES(N'Kho',1)
INSERT INTO dbo.PhongBan(TenPhongBan,TrangThai)VALUES(N'Nhân sự',1)
INSERT INTO dbo.PhongBan(TenPhongBan,TrangThai)VALUES(N'Tài chính',1)
--Thêm dữ liệu kích thước
INSERT INTO dbo.KichThuoc(TenKichThuoc,TrangThai)VALUES(N'S',1)
INSERT INTO dbo.KichThuoc(TenKichThuoc,TrangThai)VALUES(N'M',1)
INSERT INTO dbo.KichThuoc(TenKichThuoc,TrangThai)VALUES(N'L',1)
INSERT INTO dbo.KichThuoc(TenKichThuoc,TrangThai)VALUES(N'XL',1)
--Thêm dữ liệu HangHoa
INSERT INTO dbo.HangHoa(TenHang,Id_NhaCungCap,Id_DanhMuc)VALUES(N'Ống HDPE- D32',4,2)
INSERT INTO dbo.HangHoa(TenHang,Id_NhaCungCap,Id_DanhMuc)VALUES(N'Ống PPR- D40',4,2)
INSERT INTO dbo.HangHoa(TenHang,Id_NhaCungCap,Id_DanhMuc)VALUES(N'Ống PPR- D20',4,2)
INSERT INTO dbo.HangHoa(TenHang,Id_NhaCungCap,Id_DanhMuc)VALUES(N'MCB 3P-32A-10KA',1,1)
INSERT INTO dbo.HangHoa(TenHang,Id_NhaCungCap,Id_DanhMuc)VALUES(N'MCB 1P-16A-6KA',1,1)
INSERT INTO dbo.HangHoa(TenHang,Id_NhaCungCap,Id_DanhMuc)VALUES(N'Mặt công tắc đơn 10A',1,1)
--Thêm dữ liệu ChiTietHangHoa
INSERT INTO dbo.ChiTietHangHoa(SoLuong,GiaNhap,GiaBan,GhiChu,TrangThai,Id_KichThuoc,Id_MauSac,Id_HangHoa,Id_ApSuat,Id_DonViTinh,Id_LoaiHang,Id_ChieuDay) 
VALUES (3,20000,30000,N'',1,Null,Null,2,1,1,1,1)
--Thêm dữ liệu tài khoản
INSERT INTO dbo.TaiKhoan(TenTK,MatKhau,VaiTro,TrangThai)VALUES(N'admin','admin',1,1)
--Thêm dữ liệu nhân viên
INSERT dbo.NHANVIEN(HoTen,GioiTinh,NgaySinh,DiaChi,Email,SoDienThoai,CCCD,ChucVu,GhiChu,TrangThai,Id_PhongBan,Id_TaiKhoan,Id_TruongPhong) VALUES (N'Trần Minh Sáng', 1, '20020102', 'NINHBINH', 'sangtm@gmail.com', '0988615111', '037202000666', 1, NULL, 1,1,1, 1);
--Thêm dữ liệu khách hàng
INSERT INTO dbo.KhachHang(MaSoThue,Ten,DiaChi,Email,SoDienThoai,GhiChu,TrangThai)VALUES(Null,N'Nguyễn Anh Dũng',N'Hà Nội',N'dungna@gmail.com',N'0988765567',N'Giám đốc',1)
--INSERT INTO dbo.KhachHang(MaSoThue,Ten,DiaChi,Email,SoDienThoai,GhiChu	,TrangThai)VALUES(N'',N'',N'',N'',N'',N'',NULL)
--INSERT INTO dbo.KhachHang(MaSoThue,Ten,DiaChi,Email,SoDienThoai,GhiChu,TrangThai)VALUES(N'',N'',N'',N'',N'',N'',NULL)
--INSERT INTO dbo.KhachHang(MaSoThue,Ten,DiaChi,Email,SoDienThoai,GhiChu,TrangThai)VALUES(N'',N'',N'',N'',N'',N'',NULL)
--INSERT INTO dbo.KhachHang(MaSoThue,Ten,DiaChi,Email,SoDienThoai,GhiChu,TrangThai)VALUES(N'',N'',N'',N'',N'',N'',NULL)
--Thêm dữ liệu hóa đơn
INSERT INTO dbo.HOADON(ThanhTien,DatCoc,NgayTao,GhiChu,DiaChi,TrangThaiHD,TrangThaiTT,Id_NhanVien,Id_KhachHang) values (30000,10000, '20211111',N'',N'Nho Quan, Ninh Bình',1,1,1,1)
--Thêm dữ liệu hóa đơn
*/
select * from HoaDonChiTiet
select * from ChiTietHangHoa
select * from ChieuDay

CREATE PROCEDURE dbo.selectHangHoa
AS
BEGIN
	SELECT TenHang, TenNCC, TenDanhMuc, TenLoai, TenMau, TenKichThuoc, TenApSuat, DoDay, TenDonVi, SoLuong, GiaNhap, GiaBan, ChiTietHangHoa.GhiChu FROM dbo.HangHoa 
	JOIN dbo.NhaCungCap ON NhaCungCap.Id = HangHoa.Id_NhaCungCap
	JOIN dbo.DanhMuc ON DanhMuc.Id = HangHoa.Id_DanhMuc 
	JOIN dbo.ChiTietHangHoa ON ChiTietHangHoa.Id_HangHoa = HangHoa.Id
	JOIN dbo.LoaiHang ON LoaiHang.Id = ChiTietHangHoa.Id_LoaiHang
	JOIN dbo.MauSac ON MauSac.Id = ChiTietHangHoa.Id_MauSac
	JOIN dbo.KichThuoc ON KichThuoc.Id = ChiTietHangHoa.Id_KichThuoc
	JOIN dbo.ApSuat ON ApSuat.Id = ChiTietHangHoa.Id_ApSuat
	JOIN dbo.ChieuDay ON ChieuDay.Id = ChiTietHangHoa.Id_ChieuDay
	JOIN dbo.DonViTinh ON DonViTinh.Id = ChiTietHangHoa.Id_DonViTinh
END;


SELECT TenHang, TenKichThuoc, TenMau, DoDay, TenDonVi FROM dbo.HangHoa 
	JOIN dbo.NhaCungCap ON NhaCungCap.Id = HangHoa.Id_NhaCungCap
	JOIN dbo.DanhMuc ON DanhMuc.Id = HangHoa.Id_DanhMuc 
	JOIN dbo.ChiTietHangHoa ON ChiTietHangHoa.Id_HangHoa = HangHoa.Id
	JOIN dbo.LoaiHang ON LoaiHang.Id = ChiTietHangHoa.Id_LoaiHang
	JOIN dbo.MauSac ON MauSac.Id = ChiTietHangHoa.Id_MauSac
	JOIN dbo.KichThuoc ON KichThuoc.Id = ChiTietHangHoa.Id_KichThuoc
	JOIN dbo.ApSuat ON ApSuat.Id = ChiTietHangHoa.Id_ApSuat
	JOIN dbo.ChieuDay ON ChieuDay.Id = ChiTietHangHoa.Id_ChieuDay
	JOIN dbo.DonViTinh ON DonViTinh.Id = ChiTietHangHoa.Id_DonViTinh