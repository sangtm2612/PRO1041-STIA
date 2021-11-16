/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Models.TaiKhoan;
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
public class TaiKhoanDAO extends StiaDAO<TaiKhoan, Integer>{
    
    final String INSERT_SQL = "INSERT INTO dbo.TaiKhoan(TenTK,MatKhau,VaiTro,TrangThai)VALUES(?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.TaiKhoan SET TenTK=?, MatKhau=?, VaiTro=?, TrangThai=? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM TaiKhoan Where TrangThai = 1";
    final String SELECT_BY_ID_SQL = "SELECT * FROM TaiKhoan WHERE Id = ?";
    final String SELECT_BY_Name_SQL = "SELECT * FROM TaiKhoan WHERE TenTK = ? And TrangThai = 1";

    @Override
    public void insert(TaiKhoan entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenTK(), entity.getMatKhau(), entity.isVaiTro(), entity.isTrangThai());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(TaiKhoan entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenTK(), entity.getMatKhau(), entity.isVaiTro(), entity.isTrangThai(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TaiKhoan> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public TaiKhoan selectById(Integer Id) {
        List<TaiKhoan> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public TaiKhoan selectByTenTK(String TenTK) {
        List<TaiKhoan> list = selectBySql(SELECT_BY_Name_SQL, TenTK);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<TaiKhoan> selectBySql(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()) {
                TaiKhoan entity = new TaiKhoan();
                entity.setId(rs.getInt("Id"));
                entity.setTenTK(rs.getString("TenTK"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    
    
}
