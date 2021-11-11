CREATE DATABASE STIA
GO 

USE STIA
GO 

CREATE TABLE PhongBan
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenPhongBan NVARCHAR(100) UNIQUE NOT NULL,
)
GO

CREATE TABLE TaiKhoan
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenTK NVARCHAR(50) UNIQUE NOT NULL,
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
	Email NVARCHAR(50) UNIQUE NOT NULL,
	SoDienThoai NVARCHAR(15) UNIQUE NOT NULL,
	CCCD NVARCHAR(12) UNIQUE NOT NULL,
	ChucVu BIT NOT NULL,
	GhiChu NVARCHAR(100) NULL,
	TrangThai BIT NOT NULL,
	Id_PhongBan INT NOT NULL,
	Id_TaiKhoan INT NULL,
	FOREIGN KEY(Id_PhongBan) REFERENCES PHONGBAN(Id),
	FOREIGN KEY(Id_TaiKhoan) REFERENCES TAIKHOAN(Id)
)
GO

--INSERT dbo.NHANVIEN(HoTen,GioiTinh,NgaySinh,DiaChi,Email,SoDienThoai,CCCD,ChucVu,GhiChu,TrangThai,Id_PhongBan,Id_TaiKhoan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )
--UPDATE dbo.NHANVIEN SET HoTen = '', GioiTinh = '', NgaySinh = '', DiaChi = '', Email = '', SoDienThoai = '', CCCD = '', ChucVu = '', GhiChu = '', TrangThai = ''

CREATE TABLE NhaCungCap
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenNCC NVARCHAR(100) NOT NULL,
	DiaChi NVARCHAR(200) NOT NULL,
	SDT NVARCHAR(100) UNIQUE NOT NULL,
	Email NVARCHAR(100) UNIQUE NOT NULL,
	GhiChu NVARCHAR(100) NULL,
	TrangThai BIT NOT NULL
)
GO

--select * from nhacungcap
--INSERT INTO dbo.NHASANXUAT(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(?,?,?,?,?,?,?)
--UPDATE dbo.NHASANXUAT SET TenNCC = ?, DiaChi = ?, SDT = ?, Email = ?, GhiChu = ?, TrangThai = ? WHERE Id = ?

CREATE TABLE DanhMuc
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenDanhMuc NVARCHAR(100) NOT NULL,
	TrangThai BIT NOT NULL
)
GO
--SELECT * FROM dbo.DANHMUC

CREATE TABLE HangHoa
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenHang NVARCHAR(100) NOT NULL,
	GhiChu NVARCHAR(100) NULL,
	TrangThai BIT NOT NULL
)
GO

---------------------Thành phần chi tiết hàng hóa-----------------------
CREATE TABLE KichThuoc
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenKichThuoc NVARCHAR(50) UNIQUE NOT NULL,
	TrangThai BIT NOT NULL
)
GO

CREATE TABLE MauSac
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenMau NVARCHAR(20) UNIQUE NOT NULL,
	TrangThai BIT NOT NULL
)
GO


CREATE TABLE ApSuat
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenApSuat NVARCHAR(20) UNIQUE NOT NULL,
	TrangThai BIT NOT NULL
)
GO

CREATE TABLE DonViTinh
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenDonVi NVARCHAR(20) UNIQUE NOT NULL,
	TrangThai BIT NOT NULL
)
GO

CREATE TABLE GiaTien
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	GiaNhap INT NOT NULL,
	GiaBan INT NULL,
)
GO

CREATE TABLE LoaiHang
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenLoai NVARCHAR(50) UNIQUE NOT NULL,
	TrangThai BIT NOT NULL
)
GO

CREATE TABLE ChieuDay
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	DoDay FLOAT UNIQUE NOT NULL,
)
GO

CREATE TABLE ChiTietHangHoa
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	SoLuong NVARCHAR(50) UNIQUE NOT NULL,
	GhiChu NVARCHAR(100) NOT NULL,
	TrangThai BIT NOT NULL,
	Id_KichThuoc INT NULL,
	Id_MauSac INT NULL,
	Id_HangHoa INT NOT NULL,
	Id_ApSuat INT NULL,
	Id_DonViTinh INT NOT NULL,
	Id_GiaTien INT NOT NULL,
	Id_LoaiHang INT NOT NULL,
	Id_ChieuDay INT NULL,
)
GO

INSERT INTO dbo.ChiTietHangHoa(SoLuong,TrangThai,Id_KichThuoc,Id_MauSac,Id_HangHoa,Id_ApSuat,Id_DonViTinh,Id_GiaTien,Id_LoaiHang,Id_ChieuDay)
VALUES
(   N'',  -- SoLuong - nvarchar(50)
    NULL, -- TrangThai - bit
    0,    -- Id_KichThuoc - int
    0,    -- Id_MauSac - int
    0,    -- Id_HangHoa - int
    0,    -- Id_ApSuat - int
    0,    -- Id_DonViTinh - int
    0,    -- Id_GiaTien - int
    0,    -- Id_LoaiHang - int
    0     -- Id_ChieuDay - int
    )