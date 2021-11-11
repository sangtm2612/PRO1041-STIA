/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.KhachHang;
import Utils.jdbcHelper;
import java.util.List;

/**
 *
 * @author sangt
 */
public class KhachHangDAO extends StiaDAO<KhachHang, String>{
    
    final String INSERT_SQL = "";
    final String UPDATE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM KhachHang";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NHANVIEN WHERE SDT = ?";

    @Override
    public void insert(KhachHang entity) {
        //jdbcHelper.Update(sql, args);
    }

    @Override
    public void update(KhachHang entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KhachHang> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KhachHang selectById(String Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
