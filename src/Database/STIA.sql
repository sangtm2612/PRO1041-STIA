﻿CREATE DATABASE STIA
GO 

USE STIA
GO 

CREATE TABLE PhongBan
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenPhongBan NVARCHAR(100) NOT NULL,
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
	SDT NVARCHAR(100) NOT NULL,
	Email NVARCHAR(100) NOT NULL,
	GhiChu NVARCHAR(100) NULL,
	TrangThai BIT NOT NULL
)
GO

--select * from nhacungcap
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Rạng Đông',N'Hà Nội','0987654321',N'rangdong@gmail.com',null,1)
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Cadisun',N'Hà Nội','0987614321',N'randong@gmail.com',null,1)
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Sino',N'Hà Nội','0987653321',N'rangdng@gmail.com',null,1)
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Sino/ Vanloock',N'Hà Nội','0984654321',N'randng@gmail.com',null,1)
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Điện cơ',N'Hà Nội','0987655621',N'rngdong@gmail.com',null,1)
INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(N'Tiền phong/ Deko',N'Hà Nội','0945654321',N'randong@gmail.com',null,1)
--UPDATE dbo.NHASANXUAT SET TenNCC = ?, DiaChi = ?, SDT = ?, Email = ?, GhiChu = ?, TrangThai = ? WHERE Id = ?

CREATE TABLE DanhMuc
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenDanhMuc NVARCHAR(100) NOT NULL,
	TrangThai BIT NOT NULL
)
GO
INSERT INTO dbo.DanhMuc(TenDanhMuc,TrangThai)VALUES(N'Vật tư điện',1)
INSERT INTO dbo.DanhMuc(TenDanhMuc,TrangThai)VALUES(N'Vật tư nước',1)
INSERT INTO dbo.DanhMuc(TenDanhMuc,TrangThai)VALUES(N'Bảo hộ lao động',1)
--SELECT * FROM dbo.DANHMUC
--DELETE FROM DanhMuc



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
	TenKichThuoc NVARCHAR(50) NOT NULL,
	TrangThai BIT NOT NULL
)
GO
--select * from kichthuoc

CREATE TABLE MauSac
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenMau NVARCHAR(20) NOT NULL,
	TrangThai BIT NOT NULL
)
GO
INSERT INTO dbo.MauSac(TenMau,TrangThai)VALUES(N'Vàng',1)
INSERT INTO dbo.MauSac(TenMau,TrangThai)VALUES(N'Xanh',1)
INSERT INTO dbo.MauSac(TenMau,TrangThai)VALUES(N'Đỏ',1)
INSERT INTO dbo.MauSac(TenMau,TrangThai)VALUES(N'Trắng',1)
--SELECT * FROM dbo.MauSac
--DELETE FROM dbo.MauSac


CREATE TABLE ApSuat
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenApSuat NVARCHAR(20) NOT NULL,
	TrangThai BIT NOT NULL
)
GO
INSERT INTO dbo.ApSuat(TenApSuat,TrangThai)VALUES(N'PN10',1)
INSERT INTO dbo.ApSuat(TenApSuat,TrangThai)VALUES(N'class 2',1)
INSERT INTO dbo.ApSuat(TenApSuat,TrangThai)VALUES(N'class 3',1)
--DELETE FROM dbo.ApSuat
--SELECT * FROM dbo.ApSuat

CREATE TABLE DonViTinh
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TenDonVi NVARCHAR(20) NOT NULL,
	TrangThai BIT NOT NULL
)
GO
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'M',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Cái',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Bộ',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Cuộn',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Tủ',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Bình',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Cọc',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Kg',1)
INSERT INTO dbo.DonViTinh(TenDonVi,TrangThai)VALUES(N'Đôi',1)
--DELETE FROM dbo.DonViTinh
--SELECT * FROM dbo.DonViTinh

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
	TenLoai NVARCHAR(50) NOT NULL,
	TrangThai BIT NOT NULL
)
GO
INSERT INTO dbo.LoaiHang(TenLoai,TrangThai)VALUES(N'Cấp nước',1)
INSERT INTO dbo.LoaiHang(TenLoai,TrangThai)VALUES(N'Thoát nước',1)
INSERT INTO dbo.LoaiHang(TenLoai,TrangThai)VALUES(N'Chống sét',1)
INSERT INTO dbo.LoaiHang(TenLoai,TrangThai)VALUES(N'Hệ thống PCCC',1)
INSERT INTO dbo.LoaiHang(TenLoai,TrangThai)VALUES(N'Khác',1)
--DELETE FROM dbo.LoaiHang
--SELECT * FROM dbo.LoaiHang

CREATE TABLE ChieuDay
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	DoDay FLOAT NOT NULL,
)
GO

CREATE TABLE ChiTietHangHoa
(
	Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	SoLuong NVARCHAR(50) NOT NULL,
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