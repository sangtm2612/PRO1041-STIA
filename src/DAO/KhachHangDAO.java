/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Models.KhachHang;
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
public class KhachHangDAO extends StiaDAO<KhachHang, Integer>{
    
    final String INSERT_SQL = "INSERT INTO dbo.KhachHang(MaSoThue,Ten,DiaChi,Email,SoDienThoai,GhiChu,TrangThai) VALUES (?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE KhachHang set MaSoThue = ?, Ten = ?, DiaChi = ?, Email = ?, SoDienThoai = ?, GhiChu = ?, TrangThai = ? WHERE Id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM KhachHang WHERE TrangThai = 1";
    final String SELECT_BY_ID_SQL = "SELECT * FROM KhachHang WHERE Id = ? And TrangThai = 1";
    final String SELECT_BY_SDT_SQL = "SELECT * FROM KhachHang WHERE SoDienThoai = ? And TrangThai = 1";

    @Override
    public void insert(KhachHang entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getMaSoThue(), entity.getTen(), entity.getDiaChi(), entity.getEmail(), entity.getSoDienThoai(), entity.getGhiChu(), entity.isTrangThai());
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(KhachHang entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getMaSoThue(), entity.getTen(), entity.getDiaChi(), entity.getEmail(), entity.getSoDienThoai(), entity.getGhiChu(), entity.isTrangThai(), entity.getID());
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KhachHang> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang selectById(Integer Id) {
        List<KhachHang> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public KhachHang selectBySDT(String SDT) {
        List<KhachHang> list = selectBySql(SELECT_BY_SDT_SQL, SDT);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setID(rs.getInt("Id"));
                entity.setMaSoThue(rs.getString("MaSoThue"));
                entity.setTen(rs.getString("Ten"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setEmail(rs.getString("Email"));
                entity.setSoDienThoai(rs.getString("SoDienThoai"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    
}
