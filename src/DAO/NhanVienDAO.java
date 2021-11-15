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
import java.util.Date;

/**
 *
 * @author sangt
 */
public class NhanVienDAO extends StiaDAO<NhanVien, String> {

    final String INSERT_SQL = "";
    final String UPDATE_SQL = "";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM NHANVIEN";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NHANVIEN WHERE Id = ?";

    @Override
    public void insert(NhanVien entity) {
       // jdbcHelper.Update(UPDATE_SQL, entity.getMa);
    }

    @Override
    public void update(NhanVien entity) {
       // jdbcHelper.Update(UPDATE_SQL, args);
    }

    @Override
    public void delete(String Key) {
       // jdbcHelper.Update(DELETE_SQL, args)
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String Id) {
        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, Id);
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
                Date d = new Date(rs.getDate("NgaySinh").getTime());
                entity.setNgaySinh(d);
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setSDT(rs.getString("SDT"));
                entity.setEmail(rs.getString("Email"));
                entity.setCCCD(rs.getString("CCCD"));
                entity.setChucVu(rs.getBoolean("ChucVu"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                entity.setIdTaiKhoan(rs.getInt("Id_TaiKhoan"));
                entity.setIdPhongBan(rs.getInt("Id_PhongBan"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
