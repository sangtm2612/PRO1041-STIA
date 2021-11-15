/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.StiaDAO;
import DAO.Models.DonViTinh;
import DAO.Models.LoaiHang;
import Utils.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sangt
 */
public class LoaiHangDAO extends StiaDAO<LoaiHang, Integer> {

    final String INSERT_SQL = "INSERT INTO dbo.LoaiHang(TenLoai, TrangThai)VALUES(?,?)";
    final String UPDATE_SQL = "UPDATE dbo.LoaiHang SET TenLoai = ?, TrangThai = ? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM LoaiHang WHERE TrangThai = 1";
    final String SELECT_BY_ID_SQL = "SELECT * FROM LoaiHang WHERE Id = ? AND TrangThai = 1";
    final String SELECT_BY_Name_SQL = "SELECT * FROM LoaiHang WHERE TenLoai = ? AND TrangThai = 1";
    
    @Override
    public void insert(LoaiHang entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenLoai(), entity.isTrangThai());
        } catch (SQLException ex) {
            Logger.getLogger(LoaiHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(LoaiHang entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenLoai(), entity.isTrangThai(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(LoaiHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoaiHang> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public LoaiHang selectById(Integer Id) {
        List<LoaiHang> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public LoaiHang selectByName(String name) {
        List<LoaiHang> list = selectBySql(SELECT_BY_Name_SQL, name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<LoaiHang> selectBySql(String sql, Object... args) {
        List<LoaiHang> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                LoaiHang entity = new LoaiHang();
                entity.setId(rs.getInt("Id"));
                entity.setTenLoai(rs.getString("TenLoai"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
