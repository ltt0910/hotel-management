package com.management.hotel.repository.impl;

import com.management.hotel.entity.RoomEntity;
import com.management.hotel.factory.MySqlConnectFactory;
import com.management.hotel.repository.RoomRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    @Override
    public List<RoomEntity> getEmptyRoom(String keyword) {

        StringBuilder sql = new StringBuilder("SELECT * FROM tblroom WHERE enable = 1 and status = 1");
        if (keyword != null && keyword != "") {
            sql.append(" and description like '%" + keyword + "%'");
        }
        Connection conn = null;
        PreparedStatement ps;
        List<RoomEntity> result = new ArrayList<>();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setQueryTimeout(1);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                RoomEntity entity = new RoomEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setCode(resultSet.getLong("code"));
                entity.setDescription(resultSet.getString("description"));
                entity.setStatus(resultSet.getBoolean("status"));
                entity.setImage(resultSet.getString("image"));
                entity.setType(resultSet.getInt("type"));
                entity.setPricePerDay(resultSet.getInt("price_per_day"));

                result.add(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public List<RoomEntity> getRentedRoom(String keyword) {

        StringBuilder sql = new StringBuilder("SELECT * FROM tblroom WHERE enable = 1 and status = 0");
        if (keyword != null && keyword != "") {
            sql.append(" and description like '%" + keyword + "%'");
        }
        Connection conn = null;
        PreparedStatement ps;
        List<RoomEntity> result = new ArrayList<>();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setQueryTimeout(1);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                RoomEntity entity = new RoomEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setCode(resultSet.getLong("code"));
                entity.setDescription(resultSet.getString("description"));
                entity.setStatus(resultSet.getBoolean("status"));
                entity.setImage(resultSet.getString("image"));
                entity.setType(resultSet.getInt("type"));
                entity.setPricePerDay(resultSet.getInt("price_per_day"));

                result.add(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public List<RoomEntity> findAll(String keyword) {

        StringBuilder sql = new StringBuilder("SELECT * FROM tblroom WHERE enable = 1");

        if (keyword != null && keyword != "") {
            sql.append(" and description like '%" + keyword + "%'");
        }
        Connection conn = null;
        PreparedStatement ps;
        List<RoomEntity> result = new ArrayList<>();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setQueryTimeout(1);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                RoomEntity entity = new RoomEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setCode(resultSet.getLong("code"));
                entity.setDescription(resultSet.getString("description"));
                entity.setStatus(resultSet.getBoolean("status"));
                entity.setImage(resultSet.getString("image"));
                entity.setType(resultSet.getInt("type"));
                entity.setPricePerDay(resultSet.getInt("price_per_day"));

                result.add(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public void updateStatusByCode(RoomEntity entity) throws SQLException {
        String sql = "UPDATE tblroom SET status = ? where code = ?";
        Connection conn = null;
        PreparedStatement ps;

        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, entity.getStatus());
            ps.setLong(2,entity.getCode());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public RoomEntity getRoomByCode(Long code) throws SQLException {
        String sql = "SELECT * FROM tblroom WHERE code = ?";
        Connection conn = null;
        PreparedStatement ps;
        Boolean result = false;
        RoomEntity res = new RoomEntity();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setLong(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res.setId(rs.getLong("id"));
                res.setCode(rs.getLong("code"));
                res.setDescription(rs.getString("description"));
                res.setStatus(rs.getBoolean("status"));
                res.setImage(rs.getString("image"));
                res.setType(rs.getInt("type"));
                res.setPricePerDay(rs.getInt("price_per_day"));
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.setAutoCommit(true);
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }
}
