/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.HangHoa;

import DAO.StiaDAO;
import Models.HangHoa.DonViTinh;
import Models.HangHoa.GiaTien;
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
public class GiaTienDAO extends StiaDAO<GiaTien, Integer>{
    
    final String INSERT_SQL = "INSERT INTO dbo.GiaTien(GiaNhap,GiaBan)VALUES(?,?)";
    final String UPDATE_SQL = "UPDATE dbo.GiaTien SET GiaNhap = ?, GiaBan = ? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM GiaTien";
    final String SELECT_BY_ID_SQL = "SELECT * FROM GiaTien WHERE Id = ?";

    @Override
    public void insert(GiaTien entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getGiaNhap(), entity.getGiaBan());
        } catch (SQLException ex) {
            Logger.getLogger(GiaTienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GiaTien entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getGiaNhap(), entity.getGiaBan(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(GiaTienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GiaTien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public GiaTien selectById(Integer Id) {
        List<GiaTien> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<GiaTien> selectBySql(String sql, Object... args) {
        List<GiaTien> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()) {
                GiaTien entity = new GiaTien();
                entity.setId(rs.getInt("Id"));
                entity.setGiaBan(rs.getInt("GiaNhap"));
                entity.setGiaBan(rs.getInt("GiaBan"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    
    
}
