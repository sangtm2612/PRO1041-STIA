/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Models.ChiTietHangHoa;
import DAO.Models.PhongBan;
import Utils.jdbcHelper;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sangt
 */
public class PhongBanDAO  extends StiaDAO<PhongBan, Integer>{
    final String INSERT_SQL = "INSERT INTO dbo.PhongBan(TenPhongBan,TrangThai)VALUES(?,?)";
    final String UPDATE_SQL = "UPDATE dbo.PhongBan SET TenPhongBan = ?, TrangThai = ? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM PhongBan WHERE TrangThai = 1";
    final String SELECT_BY_ID_SQL = "SELECT * FROM PhongBan WHERE Id = ? AND TrangThai = 1";
    final String SELECT_BY_Name_SQL = "SELECT * FROM PhongBan WHERE TenPhongBan = ? AND TrangThai = 1";

    @Override
    public void insert(PhongBan entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenPhongBan(), entity.isTrangThai());
        } catch (SQLException ex) {
            Logger.getLogger(PhongBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(PhongBan entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenPhongBan(), entity.isTrangThai(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(PhongBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PhongBan> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PhongBan selectById(Integer Id) {
        List<PhongBan> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public PhongBan selectByName(String name) {
        List<PhongBan> list = selectBySql(SELECT_BY_Name_SQL, name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<PhongBan> selectBySql(String sql, Object... args) {
        List<PhongBan> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                PhongBan entity = new PhongBan();
                entity.setId(rs.getInt("Id"));
                entity.setTenPhongBan(rs.getString("TenPhongBan"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    
    
}
