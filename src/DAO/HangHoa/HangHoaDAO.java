/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.HangHoa;

import DAO.StiaDAO;
import Models.HangHoa.HangHoa;
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
public class HangHoaDAO extends StiaDAO<HangHoa, Integer>{
    
    final String INSERT_SQL = "INSERT INTO dbo.HangHoa(TenHang,Id_NhaCungCap,Id_DanhMuc)VALUES(?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.HangHoa SET TenHang=?, Id_NhaCungCap=?, Id_DanhMuc=? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM HangHoa Where TrangThai = 1";
    final String SELECT_BY_ID_SQL = "SELECT * FROM HangHoa WHERE Id = ?";
    final String SELECT_BY_Name_SQL = "SELECT * FROM HangHoa WHERE TenApSuat = ? And TrangThai = 1";

    @Override
    public void insert(HangHoa entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenHang(), entity.getId_NhaCungCap(), entity.getId_DanhMuc());
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(HangHoa entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenHang(), entity.getId_NhaCungCap(), entity.getId_DanhMuc(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HangHoa> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HangHoa selectById(Integer Id) {
        List<HangHoa> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HangHoa> selectBySql(String sql, Object... args) {
        List<HangHoa> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                HangHoa entity = new HangHoa();
                entity.setId(rs.getInt("Id"));
                entity.setTenHang(rs.getString("TenHang"));
                entity.setId_NhaCungCap(rs.getInt("Id_NhaCungCap("));
                entity.setId_DanhMuc(rs.getInt("Id_DanhMuc(("));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    
    
}
