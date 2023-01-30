package com.management.hotel.repository.impl;

import com.management.hotel.entity.GroupEntity;
import com.management.hotel.factory.MySqlConnectFactory;
import com.management.hotel.repository.GroupRepository;
import com.management.hotel.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public GroupEntity findById(Long id) {

        GroupEntity result = new GroupEntity();
        String sql = "SELECT * FROM tblgroup WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps;
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setQueryTimeout(1);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {

                result.setId(resultSet.getLong("id"));
                result.setCode(resultSet.getString("code"));
                result.setName(resultSet.getString("name"));
                result.setRole(roleRepository.findByGroupId(id));

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
    public Boolean checkExist(Long id) {

        Boolean result = false;
        String sql = "SELECT * FROM tblgroup WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps;
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setQueryTimeout(1);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                result = true;
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


}
