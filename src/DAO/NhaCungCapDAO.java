/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import DAO.StiaDAO;
import DAO.Models.NhaCungCap;
import Utils.jdbcHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sangt
 */
public class NhaCungCapDAO extends StiaDAO<NhaCungCap, Integer> {

    final String INSERT_SQL = "INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.NhaCungCap SET TenNCC = ?, DiaChi = ?, SDT = ?, Email = ?, GhiChu = ?, TrangThai = ? WHERE Id = ?";
    final String DELETE_SQL = "UPDATE dbo.NhaCungCap SET TrangThai = 0 WHERE Id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhaCungCap WHERE TrangThai = 1";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhaCungCap WHERE Id = ?";
    final String SELECT_BY_SDT_SQL = "SELECT * FROM NhaCungCap WHERE SDT = ? And TrangThai = 1";
    final String SELECT_BY_Name_SQL = "SELECT * FROM NhaCungCap WHERE TenNCC = ? And TrangThai = 1";

    @Override
    public void insert(NhaCungCap entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenNCC(), entity.getDiaChi(), entity.getSDT(), entity.getEmail(), entity.getGhiChu(), entity.isTrangThai());
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NhaCungCap entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenNCC(), entity.getDiaChi(), entity.getSDT(), entity.getEmail(), entity.getGhiChu(), entity.isTrangThai(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            jdbcHelper.Update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<NhaCungCap> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhaCungCap selectById(Integer Id) {
        List<NhaCungCap> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public NhaCungCap selectByName(String name) {
        List<NhaCungCap> list = selectBySql(SELECT_BY_Name_SQL, name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<NhaCungCap> findSDT(String sdt) {
        String sql = "SELECT * FROM NhaCungCap WHERE TrangThai = 1 AND SDT like '%" + sdt + "%' OR TenNCC like N'%" + sdt + "%'";
        return selectBySql(sql);
    }

    @Override
    protected List<NhaCungCap> selectBySql(String sql, Object... args) {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                NhaCungCap entity = new NhaCungCap();
                entity.setId(rs.getInt("Id"));
                entity.setTenNCC(rs.getString("TenNCC"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setSDT(rs.getString("SDT"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public NhaCungCap selectBySDT(String SDT) {
        List<NhaCungCap> list = selectBySql(SELECT_BY_SDT_SQL, SDT);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
