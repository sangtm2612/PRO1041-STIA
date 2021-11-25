/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Models.HoaDon;
import Utils.jdbcHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sangt
 */
public class HoaDonDAO extends StiaDAO<HoaDon, Integer> {

    final String INSERT_SQL = "INSERT INTO dbo.HoaDon(ThanhTien,DatCoc,NgayTao,GhiChu,DiaChi,TrangThaiHD,TrangThaiTT,Id_NhanVien,Id_KhachHang)VALUES(?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.HoaDon SET ThanhTien=?, DatCoc=?, TrangThaiHD=?, TrangThaiTT=? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE Id = ?";
    final String SELECT_BY_Name_SQL = "SELECT * FROM HoaDon WHERE Id = ?";

    @Override
    public void insert(HoaDon entity) {
        try {
            jdbcHelper.Update(INSERT_SQL, entity.getThanhTien(), entity.getDatCoc(), entity.getNgayTao(), entity.getGhiChu(), entity.getDiaChi(), entity.isTrangThaiHD(), entity.isTrangThaiTT(), entity.getId_NhanVien(), entity.getId_KhachHang());
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(HoaDon entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL, entity.getThanhTien(), entity.getNgayTao(), entity.getGhiChu(), entity.isTrangThaiHD(), entity.isTrangThaiTT(), entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDon selectById(Integer id) {
        List<HoaDon> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setId(rs.getInt("Id"));
                entity.setThanhTien(rs.getInt("ThanhTien"));
                entity.setDatCoc(rs.getInt("DatCoc"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setTrangThaiHD(rs.getBoolean("TrangThaiHD"));
                entity.setTrangThaiTT(rs.getBoolean("TrangThaiTT"));
                entity.setId_NhanVien(rs.getInt("Id_NhanVien"));
                entity.setId_KhachHang(rs.getInt("Id_KhachHang"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
