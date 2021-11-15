/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.StiaDAO;
import DAO.Models.ChieuDay;
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
public class ChieuDayDAO extends StiaDAO<ChieuDay, Integer> {

    final String INSERT_SQL = "INSERT INTO dbo.ChieuDay(DoDay)VALUES(?)";
    final String UPDATE_SQL = "UPDATE dbo.ChieuDay SET DoDay = ? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM ChieuDay";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ChieuDay WHERE Id = ?";

    @Override
    public void insert(ChieuDay entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getDoDay());
        } catch (SQLException ex) {
            Logger.getLogger(ChieuDayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(ChieuDay entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getDoDay(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ChieuDayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ChieuDay> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChieuDay selectById(Integer Id) {
        List<ChieuDay> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<ChieuDay> selectBySql(String sql, Object... args) {
        List<ChieuDay> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                ChieuDay entity = new ChieuDay();
                entity.setId(rs.getInt("Id"));
                entity.setDoDay(rs.getDouble("DoDay"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
