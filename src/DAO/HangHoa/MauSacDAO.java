/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.HangHoa;

import DAO.StiaDAO;
import Models.HangHoa.DanhMuc;
import Models.HangHoa.MauSac;
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
public class MauSacDAO extends StiaDAO<MauSac, Integer>{
    
    final String INSERT_SQL = "INSERT INTO dbo.MauSac(TenMau)VALUES(?)";
    final String UPDATE_SQL = "UPDATE dbo.MauSac SET TenMau = ? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM MauSac";
    final String SELECT_BY_ID_SQL = "SELECT * FROM MauSac WHERE Id = ?";

    @Override
    public void insert(MauSac entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenMau());
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(MauSac entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenMau(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MauSac> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public MauSac selectById(Integer Id) {
        List<MauSac> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<MauSac> selectBySql(String sql, Object... args) {
        List<MauSac> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()) {
                MauSac entity = new MauSac();
                entity.setId(rs.getInt("Id"));
                entity.setTenMau(rs.getString("TenMau"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
