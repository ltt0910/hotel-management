package com.management.hotel.repository.impl;

import com.management.hotel.entity.TransactionEntity;
import com.management.hotel.factory.MySqlConnectFactory;
import com.management.hotel.repository.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @Override
    public TransactionEntity createTransaction(TransactionEntity entity) throws SQLException {
        String sql = "INSERT INTO tbltransaction(checkin_date,customer_identification, customer_name, customer_phone,status,room_id,price) VALUES(?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps;

        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, entity.getCheckIn());
            ps.setString(2, entity.getCustomerIdentification());
            ps.setString(3, entity.getCustomerName());
            ps.setString(4, entity.getCustomerPhone());
            ps.setBoolean(5, false);
            ps.setLong(6, entity.getRoomId());
            ps.setLong(7, 0);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            entity = new TransactionEntity();
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
        return entity;
    }

    @Override
    public TransactionEntity findByCodeAndStatus(TransactionEntity entity) throws SQLException {
        String sql = "SELECT * FROM tbltransaction WHERE room_id = ? and status = ?";
        Connection conn = null;
        PreparedStatement ps;
        Boolean result = false;
        TransactionEntity res = new TransactionEntity();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setLong(1, entity.getRoomId());
            ps.setLong(2, 0);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res.setRoomId(rs.getLong("room_id"));
                res.setPrice(rs.getLong("price"));
                res.setCustomerIdentification(rs.getString("customer_identification"));
                res.setStatus(rs.getBoolean("status"));
                res.setCheckIn(rs.getTimestamp("checkin_date"));
                res.setCustomerName(rs.getString("customer_name"));
                res.setCustomerPhone(rs.getString("customer_phone"));
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

    @Override
    public TransactionEntity update(TransactionEntity entity) throws SQLException {
        String sql = "UPDATE tbltransaction SET checkout_date = ?, price = ?, status = ? where room_id = ? and status = ?";
        Connection conn = null;
        PreparedStatement ps;

        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, entity.getCheckOut());
            ps.setLong(2, entity.getPrice());
            ps.setBoolean(3, entity.getStatus());
            ps.setLong(4, entity.getRoomId());
            ps.setBoolean(5, false);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            entity = new TransactionEntity();
            conn.rollback();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }

    @Override
    public List<TransactionEntity> findAll() throws SQLException {

        String sql = "SELECT * FROM tbltransaction INNER JOIN tblroom ON tbltransaction.room_id = tblroom.id";
        Connection conn = null;
        PreparedStatement ps;
        List<TransactionEntity> res = new ArrayList<>();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TransactionEntity entity = new TransactionEntity();
                entity.setRoomId(rs.getLong("room_id"));
                entity.setPrice(rs.getLong("price"));
                entity.setCustomerIdentification(rs.getString("customer_identification"));
                entity.setStatus(rs.getBoolean("status"));
                entity.setCheckIn(rs.getTimestamp("checkin_date"));
                entity.setCheckOut(rs.getTimestamp("checkout_date"));
                entity.setCustomerName(rs.getString("customer_name"));
                entity.setCustomerPhone(rs.getString("customer_phone"));
                entity.setCode(rs.getLong("tblroom.code"));

                res.add(entity);
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

    @Override
    public List<TransactionEntity> findToday() throws SQLException {
        String sql = "SELECT * FROM tbltransaction WHERE YEARWEEK(checkin_date) = YEARWEEK(CURDATE());";
        Connection conn = null;
        PreparedStatement ps;
        List<TransactionEntity> res = new ArrayList<>();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TransactionEntity entity = new TransactionEntity();
                entity.setRoomId(rs.getLong("room_id"));
                entity.setPrice(rs.getLong("price"));
                entity.setCustomerIdentification(rs.getString("customer_identification"));
                entity.setStatus(rs.getBoolean("status"));
                entity.setCheckIn(rs.getTimestamp("checkin_date"));
                entity.setCheckOut(rs.getTimestamp("checkout_date"));
                entity.setCustomerName(rs.getString("customer_name"));
                entity.setCustomerPhone(rs.getString("customer_phone"));

                res.add(entity);
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

    @Override
    public List<TransactionEntity> findThisMonth() throws SQLException {
        String sql = "SELECT * FROM tbltransaction WHERE MONTH(checkin_date) = MONTH(CURDATE()) AND YEAR(checkin_date) = YEAR(CURDATE());";
        Connection conn = null;
        PreparedStatement ps;
        List<TransactionEntity> res = new ArrayList<>();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TransactionEntity entity = new TransactionEntity();
                entity.setRoomId(rs.getLong("room_id"));
                entity.setPrice(rs.getLong("price"));
                entity.setCustomerIdentification(rs.getString("customer_identification"));
                entity.setStatus(rs.getBoolean("status"));
                entity.setCheckIn(rs.getTimestamp("checkin_date"));
                entity.setCheckOut(rs.getTimestamp("checkout_date"));
                entity.setCustomerName(rs.getString("customer_name"));
                entity.setCustomerPhone(rs.getString("customer_phone"));

                res.add(entity);
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
