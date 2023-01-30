package com.management.hotel.repository.impl;

import com.management.hotel.entity.UserEntity;
import com.management.hotel.factory.MySqlConnectFactory;
import com.management.hotel.repository.GroupRepository;
import com.management.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<UserEntity> findAll() {

        String sql = "SELECT * FROM tbluser WHERE enable = 1";
        Connection conn = null;
        PreparedStatement ps;
        List<UserEntity> result = new ArrayList<>();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql);
            ps.setQueryTimeout(1);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity();

                userEntity.setId(resultSet.getLong("id"));
                userEntity.setEnable(resultSet.getBoolean("enable"));
                userEntity.setFullName(resultSet.getString("fullname"));
                userEntity.setUsername(resultSet.getString("username"));
                userEntity.setCreateDate(resultSet.getTimestamp("created_date"));
                userEntity.setModifyDate(resultSet.getTimestamp("modified_date"));
                userEntity.setCreateBy(resultSet.getString("created_by"));
                userEntity.setModifyBy(resultSet.getString("modified_by"));
                userEntity.setGroup(groupRepository.findById(resultSet.getLong("group_id")));

                result.add(userEntity);
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
    public UserEntity findById(Long id) {
        String sql = "SELECT * FROM tbluser WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps;
        UserEntity result = null;
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
//            ps.setBoolean(2, true);
            ps.setQueryTimeout(1);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                result = new UserEntity();

                result.setId(resultSet.getLong("id"));
                result.setEnable(resultSet.getBoolean("enable"));
                result.setFullName(resultSet.getString("fullname"));
                result.setUsername(resultSet.getString("username"));
                result.setCreateDate(resultSet.getTimestamp("created_date"));
                result.setModifyDate(resultSet.getTimestamp("modified_date"));
                result.setCreateBy(resultSet.getString("created_by"));
                result.setModifyBy(resultSet.getString("modified_by"));
                result.setGroup(groupRepository.findById(resultSet.getLong("group_id")));

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
    public List<UserEntity> findByGroupId(Long id) {

        String sql = "SELECT * FROM tbluser WHERE enable = ? and group_id = ?";
        Connection conn = null;
        PreparedStatement ps;
        List<UserEntity> result = new ArrayList<>();
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setLong(2, id);
            ps.setQueryTimeout(1);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                UserEntity user = new UserEntity();

                user.setId(resultSet.getLong("id"));
                user.setEnable(resultSet.getBoolean("enable"));
                user.setFullName(resultSet.getString("fullname"));
                user.setUsername(resultSet.getString("username"));
                user.setCreateDate(resultSet.getTimestamp("created_date"));
                user.setModifyDate(resultSet.getTimestamp("modified_date"));
                user.setCreateBy(resultSet.getString("created_by"));
                user.setModifyBy(resultSet.getString("modified_by"));
                user.setGroup(groupRepository.findById(resultSet.getLong("group_id")));

                result.add(user);
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
    public UserEntity findByUsername(String username) {

        String sql = "SELECT * FROM tbluser WHERE enable = ? and username = ? inner join tblgroup on tbluser.group_id = tblgroup.id";
        Connection conn = null;
        PreparedStatement ps;
        UserEntity result = null;
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setString(2, username);
            ps.setQueryTimeout(1);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                result = new UserEntity();

                result.setId(resultSet.getLong("id"));
                result.setEnable(resultSet.getBoolean("enable"));
                result.setFullName(resultSet.getString("fullname"));
                result.setPassword(resultSet.getString("password"));
                result.setUsername(resultSet.getString("username"));
                result.setCreateDate(resultSet.getTimestamp("created_date"));
                result.setModifyDate(resultSet.getTimestamp("modified_date"));
                result.setCreateBy(resultSet.getString("created_by"));
                result.setModifyBy(resultSet.getString("modified_by"));
                result.setBirthday(resultSet.getTimestamp("birthday"));
                result.setEmail(resultSet.getString("email"));
                result.setPhone(resultSet.getString("phone"));
                result.setSex(resultSet.getBoolean("sex"));
                result.setImage(resultSet.getString("image"));
                result.set
                result.setGroup(groupRepository.findById(resultSet.getLong("group_id")));

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
    public UserEntity create(UserEntity user) throws SQLException {

        String sql = "INSERT INTO tbluser(enable, fullname, password, username, group_id) VALUES(?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps;

        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setQueryTimeout(1);
            ps.setBoolean(1, user.getEnable());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUsername());
            ps.setLong(5, user.getGroup().getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            user = new UserEntity();
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
        return user;
    }

    @Override
    public Boolean checkExistById(UserEntity user) {

        String sql = "SELECT * FROM tbluser WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps;
        Boolean result = false;
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, user.getId());
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

    @Override
    public Boolean checkExistByUserName(UserEntity user) {

        String sql = "SELECT * FROM tbluser WHERE username = ?";
        Connection conn = null;
        PreparedStatement ps;
        Boolean result = false;
        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
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

    public UserEntity updateA(UserEntity user) throws SQLException {
        String sql = "UPDATE tbluser SET fullname = ?, enable = ?, group_id = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps;

        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setQueryTimeout(1);
            ps.setString(1, user.getFullName());
            ps.setBoolean(2, user.getEnable());
            ps.setLong(3, user.getGroup().getId());
            ps.setLong(4, user.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            user = new UserEntity();
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
        return user;
    }

    @Override
    public UserEntity updateB(UserEntity user) throws SQLException {
        String sql = "UPDATE tbluser SET fullname = ?, username = ?, enable = ?, group_id = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps;

        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setQueryTimeout(1);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getUsername());
            ps.setBoolean(3, user.getEnable());
            ps.setLong(4, user.getGroup().getId());
            ps.setLong(5, user.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            user = new UserEntity();
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
        return user;
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "UPDATE tbluser SET enable = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps;

        try {
            conn = MySqlConnectFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setLong(2, id);
            ps.setQueryTimeout(1);
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
}
