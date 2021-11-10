/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.HangHoa;

import DAO.StiaDAO;
import Models.HangHoa.ApSuat;
import Models.HangHoa.DonViTinh;
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
public class DonViTinhDAO extends StiaDAO<DonViTinh, Integer>{
    
    final String INSERT_SQL = "INSERT INTO dbo.DonViTinh(TenDonVi)VALUES(?)";
    final String UPDATE_SQL = "UPDATE dbo.DonViTinh SET TenDonVi = ? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM DonViTinh";
    final String SELECT_BY_ID_SQL = "SELECT * FROM DonViTinh WHERE Id = ?";

    @Override
    public void insert(DonViTinh entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenDonVi());
        } catch (SQLException ex) {
            Logger.getLogger(DonViTinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(DonViTinh entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenDonVi(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(DonViTinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DonViTinh> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DonViTinh selectById(Integer Id) {
        List<DonViTinh> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DonViTinh> selectBySql(String sql, Object... args) {
        List<DonViTinh> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()) {
                DonViTinh entity = new DonViTinh();
                entity.setId(rs.getInt("Id"));
                entity.setTenDonVi(rs.getString("TenDonVi"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    
    
}
