package com.cg.service;

import com.cg.dto.UserDTO;
import com.cg.model.User;
import com.cg.utils.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static String SELECT_ALL_USERS = "" +
            "SELECT " +
                "u.id, " +
                "u.full_name, " +
                "u.phone, " +
                "u.address, " +
                "c.name " +
            "FROM users AS u " +
            "JOIN cities AS c " +
            "ON u.city_id = c.id;";

    private static String SELECT_USER_BY_ID = "" +
            "SELECT " +
                "u.id, " +
                "u.full_name, " +
                "u.phone, " +
                "u.address " +
            "FROM users AS u " +
            "WHERE u.id = ?;";

    private static String INSERT_USER = "" +
            "INSERT INTO users(full_name, phone, city_id, address, age)" +
            "VALUES (?, ?, ?, ?, ?);";

    private static String SP_INSERT_USER = "CALL {sp_insert_user(?, ?, ?, ?, ?)}";

    private static String UPDATE_USER_BY_ID = "" +
            "UPDATE users AS u " +
            "SET " +
                "u.full_name = ?, " +
                "u.phone = ?, " +
                "u.address = ? " +
            "WHERE u.id = ?;";

    @Override
    public List<User> findAll() {

        List<User> userList = new ArrayList<>();

//        try {
//            Connection connection = MySQLConnUtils.getConnection();
//
//            PreparedStatement statement = connection.prepareCall(SELECT_ALL_USERS);
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String fullName = rs.getString("full_name");
//                String phone = rs.getString("phone");
//                String address = rs.getString("address");
////                int cityId = rs.getInt("city_id");
//                String cityName = rs.getString("code");
//
//                userList.add(new User(id, fullName, phone, cityName, address));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return userList;
    }

    @Override
    public List<UserDTO> findAllUserDTO() {
        List<UserDTO> userList = new ArrayList<>();

        try {
            Connection connection = MySQLConnUtils.getConnection();

            PreparedStatement statement = connection.prepareCall(SELECT_ALL_USERS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String cityName = rs.getString("name");

                userList.add(new UserDTO(id, fullName, phone, cityName, address));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public User findById(int userId) {
        User user = null;
        try {
            Connection connection = MySQLConnUtils.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareCall(SELECT_USER_BY_ID);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                user = new User(id, fullName, phone, address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean create(User user) {
        boolean success = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();

//            PreparedStatement statement = connection.prepareCall(INSERT_USER);
//            statement.setString(1, user.getFullName());
//            statement.setString(2, user.getPhone());
//            statement.setInt(3, user.getCityId());
//            statement.setString(4, user.getAddress());
//            statement.setInt(5, user.getAge());

            CallableStatement statement = connection.prepareCall(SP_INSERT_USER);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getPhone());
            statement.setInt(3, user.getCityId());
            statement.setString(4, user.getAddress());
            statement.setInt(5, user.getAge());

            ResultSet rs =  statement.executeQuery();

            while (rs.next()) {
                success = rs.getBoolean("success");
                String message = rs.getString("message");
            }

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }

        return success;
    }

    @Override
    public boolean update(User user) {
        boolean success = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(UPDATE_USER_BY_ID);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getAddress());
            statement.setInt(4, user.getId());
            statement.execute();

            success = true;

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }

        return success;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
