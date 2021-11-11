/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.HangHoa;

import java.sql.ResultSet;
import DAO.StiaDAO;
import Models.HangHoa.ApSuat;
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
public class ApSuatDAO extends StiaDAO<ApSuat, Integer>{
    
    final String INSERT_SQL = "INSERT INTO dbo.ApSuat(TenApSuat,TrangThai)VALUES(?,?)";
    final String UPDATE_SQL = "UPDATE dbo.ApSuat SET TenApSuat=?, TrangThai=? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM ApSuat Where TrangThai = 1";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ApSuat WHERE Id = ?";

    @Override
    public void insert(ApSuat entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenApSuat(), entity.isTrangThai());
        } catch (SQLException ex) {
            Logger.getLogger(ApSuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(ApSuat entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenApSuat(), entity.isTrangThai(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ApSuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApSuat> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ApSuat selectById(Integer Id) {
        List<ApSuat> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<ApSuat> selectBySql(String sql, Object... args) {
        List<ApSuat> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()) {
                ApSuat entity = new ApSuat();
                entity.setId(rs.getInt("Id"));
                entity.setTenApSuat(rs.getString("TenApSuat"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    
    
}
