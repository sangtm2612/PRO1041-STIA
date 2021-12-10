/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Models.HoaDonChiTiet;
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
public class HoaDonChiTietDAO extends StiaDAO<HoaDonChiTiet, Integer> {

    final String INSERT_SQL = "INSERT INTO dbo.HoaDonChiTiet(SoLuong,TrangThai,Id_HoaDon,Id_CTHangHoa)VALUES(?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.HoaDonChiTiet SET SoLuong=?, TrangThai=? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM HoaDonChiTiet WHERE TrangThai = 1";
    final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDonChiTiet WHERE Id = ? AND TrangThai = 1";
    final String SELECT_All_IDHD_SQL = "SELECT * FROM HoaDonChiTiet WHERE Id_HoaDon = ? AND TrangThai = 1";

    @Override
    public void insert(HoaDonChiTiet entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getSoLuong(), entity.isTrangThai(), entity.getId_HoaDon(), entity.getId_CTHangHoa());
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonChiTietDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(HoaDonChiTiet entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getSoLuong(), entity.isTrangThai(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonChiTietDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HoaDonChiTiet> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDonChiTiet selectById(Integer id) {
        List<HoaDonChiTiet> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public List<HoaDonChiTiet> selectAllByIdHD(int id) {
        return selectBySql(SELECT_All_IDHD_SQL, id);
    }

    @Override
    protected List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDonChiTiet entity = new HoaDonChiTiet();
                entity.setId(rs.getInt("Id"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                entity.setId_HoaDon(rs.getInt("Id_HoaDon"));
                entity.setId_CTHangHoa(rs.getInt("Id_CTHangHoa"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
