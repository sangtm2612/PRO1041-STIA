/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.HangHoa;

import java.sql.ResultSet;
import DAO.StiaDAO;
import Models.HangHoa.NhaCungCap;
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
public class NhaCungCapDAO extends StiaDAO<NhaCungCap, String> {

    final String INSERT_SQL = "INSERT INTO dbo.NhaCungCap(TenNCC,DiaChi,SDT,Email,GhiChu,TrangThai)VALUES(?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.NhaCungCap SET TenNCC = ?, DiaChi = ?, SDT = ?, Email = ?, GhiChu = ?, TrangThai = ? WHERE Id = ?";
    final String DELETE_SQL = "UPDATE dbo.NhaCungCap SET TrangThai = 0 WHERE Id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhaCungCap";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhaCungCap WHERE Id = ?";

    @Override
    public void insert(NhaCungCap entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getTenNCC(), entity.getDiaChi(), entity.getSDT(), entity.getEmail(), entity.getGhiChu(), entity.isTrangThai());
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NhaCungCap entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getTenNCC(), entity.getDiaChi(), entity.getSDT(), entity.getEmail(), entity.getGhiChu(), entity.isTrangThai(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            jdbcHelper.Update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<NhaCungCap> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhaCungCap selectById(String Id) {
        List<NhaCungCap> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhaCungCap> selectBySql(String sql, Object... args) {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()) {
                NhaCungCap entity = new NhaCungCap();
                entity.setId(rs.getInt("Id"));
                entity.setId(rs.getInt("TenNCC"));
                entity.setId(rs.getInt("DiaChi"));
                entity.setId(rs.getInt("SDT"));
                entity.setId(rs.getInt("Email"));
                entity.setId(rs.getInt("GhiChu"));
                entity.setId(rs.getInt("TrangThai"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        NhaCungCapDAO dao = new NhaCungCapDAO();
        //dao.insert(new NhaCungCap("1","2","3","4","5",true));
        dao.update(new NhaCungCap(1,"trần sáng","2","098861518","4","5",false));
    }

}
