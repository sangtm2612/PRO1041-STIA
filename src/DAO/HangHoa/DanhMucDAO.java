/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.HangHoa;

import java.sql.ResultSet;
import DAO.StiaDAO;
import Models.HangHoa.DanhMuc;
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
public class DanhMucDAO extends StiaDAO<DanhMuc, String>{
    
    final String INSERT_SQL = "INSERT INTO dbo.DanhMuc(TenDanhMuc)VALUES(?)";
    final String UPDATE_SQL = "UPDATE dbo.DanhMuc SET TenDanhMuc = ? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM DanhMuc";
    final String SELECT_BY_ID_SQL = "SELECT * FROM DanhMuc WHERE Id = ?";

    @Override
    public void insert(DanhMuc entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenDanhMuc());
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(DanhMuc entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenDanhMuc(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String Key) {
        
    }

    @Override
    public List<DanhMuc> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DanhMuc selectById(String Id) {
        
        List<DanhMuc> list = selectBySql(SELECT_BY_ID_SQL, Integer.parseInt(Id));
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DanhMuc> selectBySql(String sql, Object... args) {
        List<DanhMuc> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()) {
                DanhMuc entity = new DanhMuc();
                entity.setId(rs.getInt("Id"));
                entity.setTenDanhMuc(rs.getString("TenDanhMuc"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static void main(String[] args) {
        //new DanhMucDAO().update(new DanhMuc(1,"byebye"));
    }
}
