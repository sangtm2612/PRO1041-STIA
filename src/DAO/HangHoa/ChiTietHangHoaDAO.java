/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.HangHoa;

import DAO.StiaDAO;
import Models.HangHoa.ChiTietHangHoa;
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
public class ChiTietHangHoaDAO extends StiaDAO<ChiTietHangHoa, Integer> {

    final String INSERT_SQL = "INSERT INTO dbo.ChiTietHangHoa(SoLuong,GiaNhap,GiaBan,GhiChu,TrangThai,Id_KichThuoc,Id_MauSac,Id_HangHoa,Id_ApSuat,Id_DonViTinh,Id_LoaiHang,Id_ChieuDay) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE dbo.ChiTietHangHoa SET SoLuong = ?, GiaNhap = ?, GiaBan = ?, GhiChu = ?, TrangThai = ?, Id_KichThuoc = ?, Id_MauSac = ?, Id_HangHoa = ?, Id_ApSuat = ?, Id_DonViTinh = ?, Id_LoaiHang = ?, Id_ChieuDay = ? WHERE Id = ?";
    final String DELETE_SQL = "";
    final String SELECT_ALL_SQL = "SELECT * FROM ChiTietHangHoa WHERE TrangThai = 1";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ChiTietHangHoa WHERE Id = ? And TrangThai = 1";

    @Override
    public void insert(ChiTietHangHoa entity) {
        try {
            jdbcHelper.Update(INSERT_SQL,entity.getSoLuong(),entity.getGiaNhap(),entity.getGiaBan(),entity.getGhiChu(),entity.isTrangThai(),entity.getId_KichThuoc(),entity.getId_MauSac(),entity.getId_HangHoa(),entity.getId_ApSuat(),entity.getId_DonViTinh(),entity.getId_LoaiHang(),entity.getId_ChieuDay());
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHangHoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(ChiTietHangHoa entity) {
        try {
            jdbcHelper.Update(UPDATE_SQL,entity.getSoLuong(),entity.getGiaNhap(),entity.getGiaBan(),entity.getGhiChu(),entity.isTrangThai(),entity.getId_KichThuoc(),entity.getId_MauSac(),entity.getId_HangHoa(),entity.getId_ApSuat(),entity.getId_DonViTinh(),entity.getId_LoaiHang(),entity.getId_ChieuDay(),entity.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHangHoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ChiTietHangHoa> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChiTietHangHoa selectById(Integer Id) {
        List<ChiTietHangHoa> list = selectBySql(SELECT_BY_ID_SQL, Id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<ChiTietHangHoa> selectBySql(String sql, Object... args) {
        List<ChiTietHangHoa> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                ChiTietHangHoa entity = new ChiTietHangHoa();
                entity.setId(rs.getInt("Id"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setGiaNhap(rs.getInt("GiaNhap"));
                entity.setGiaBan(rs.getInt("GiaBan"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setTrangThai(rs.getBoolean("TrangThai"));
                entity.setId_KichThuoc(rs.getInt("Id_KichThuoc"));
                entity.setId_MauSac(rs.getInt("Id_MauSac"));
                entity.setId_HangHoa(rs.getInt("Id_HangHoa"));
                entity.setId_ApSuat(rs.getInt("Id_ApSuat"));
                entity.setId_DonViTinh(rs.getInt("Id_DonViTinh"));
                entity.setId_LoaiHang(rs.getInt("Id_LoaiHang"));
                entity.setId_ChieuDay(rs.getInt("Id_ChieuDay"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    

}
