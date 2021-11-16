/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Models.NhanVien;
import Utils.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sangt
 */
public class NhanVienDAO extends StiaDAO<NhanVien, Integer> {

    final String INSERT_SQL = "INSERT dbo.NHANVIEN(HoTen,GioiTinh,NgaySinh,DiaChi,Email,SoDienThoai,CCCD,ChucVu,GhiChu,TrangThai,Id_PhongBan,Id_TaiKhoan, Id_TruongPhong) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    final String UPDATE_SQL = "UPDATE NHANVIEN SET HoTen = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?, Email = ?, SoDienThoai = ?, CCCD = ?, ChucVu = ?, GhiChu = ?, TrangThai = ?, Id_PhongBan = ?, Id_TaiKhoan = ?, Id_TruongPhong = ? Where Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM NHANVIEN";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NHANVIEN WHERE Id = ?";
    final String SELECT_BY_SDT_SQL = "SELECT * FROM NHANVIEN WHERE SoDienThoai = ?";

    @Override
    public void insert(NhanVien entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getDiaChi(), entity.getEmail(), entity.getSDT(), entity.getCCCD(), entity.isChucVu(), entity.getGhiChu(), entity.isTrangThai(), entity.getId_PhongBan(), entity.getId_TaiKhoan(), entity.getId_PhongBan());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NhanVien entity) {
       try {
            jdbcHelper.Update(UPDATE_SQL, entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getDiaChi(), entity.getEmail(), entity.getSDT(), entity.getCCCD(), entity.isChucVu(), entity.getGhiChu(), entity.isTrangThai(), entity.getId_PhongBan(), entity.getId_TaiKhoan(), entity.getId_PhongBan(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
       // jdbcHelper.Update(DELETE_SQL, args)
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(Integer Id) {
        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public NhanVien selectBySDT(String Id) {
        List<NhanVien> list = selectBySql(SELECT_BY_SDT_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setId(rs.getInt("Id"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setSDT(rs.getString("SoDienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setCCCD(rs.getString("CCCD"));
                entity.setChucVu(rs.getBoolean("ChucVu"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                entity.setId_PhongBan(rs.getInt("Id_PhongBan"));
                entity.setId_TaiKhoan(rs.getInt("Id_TaiKhoan"));
                entity.setId_TaiKhoan(rs.getInt("Id_TruongPhong"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
