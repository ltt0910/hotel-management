package com.management.hotel.repository.impl;

import com.management.hotel.entity.RoleEntity;
import com.management.hotel.factory.MySqlConnectFactory;
import com.management.hotel.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {


    @Override
    public RoleEntity findById(Long id) {
        return null;
    }

    @Override
    public List<RoleEntity> findByGroupId(Long id) {

        String sql = "SELECT * FROM tblgroup " +
                "INNER JOIN tblrole_group ON tblgroup.id = tblrole_group.group_id " +
                "INNER JOIN tblrole ON tblrole_group.role_id = tblrole.id " +
                "WHERE tblgroup.id = ? ";

        Connection conn = null;
        PreparedStatement ps;
        List<RoleEntity> result = new ArrayList<>();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                RoleEntity entity = new RoleEntity();
                entity.setId(resultSet.getLong("tblrole.id"));
                entity.setCode(resultSet.getString("tblrole.code"));
                entity.setName(resultSet.getString("tblrole.name"));
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
}
