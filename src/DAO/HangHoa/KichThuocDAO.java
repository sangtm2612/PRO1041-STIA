/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.HangHoa;

import DAO.StiaDAO;
import Models.HangHoa.DanhMuc;
import Models.HangHoa.KichThuoc;
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
public class KichThuocDAO extends StiaDAO<KichThuoc, Integer>{
    final String INSERT_SQL = "INSERT INTO dbo.KichThuoc(TenKichThuoc, TrangThai)VALUES(?,?)";
    final String UPDATE_SQL = "UPDATE dbo.KichThuoc SET TenKichThuoc=?, TrangThai=? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM KichThuoc";
    final String SELECT_BY_ID_SQL = "SELECT * FROM KichThuoc WHERE Id = ?";
    
    @Override
    public void insert(KichThuoc entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenKichThuoc(), entity.isTrangThai());
        } catch (SQLException ex) {
            Logger.getLogger(KichThuocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(KichThuoc entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenKichThuoc(), entity.isTrangThai(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(KichThuocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KichThuoc> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KichThuoc selectById(Integer Id) {
        List<KichThuoc> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<KichThuoc> selectBySql(String sql, Object... args) {
        List<KichThuoc> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()) {
                KichThuoc entity = new KichThuoc();
                entity.setId(rs.getInt("Id"));
                entity.setTenKichThuoc(rs.getString("TenKichThuoc"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    
}
