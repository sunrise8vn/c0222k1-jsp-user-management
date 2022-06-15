package com.cg.service;

import com.cg.model.City;
import com.cg.utils.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityServiceImpl implements CityService {

    private static final String SELECT_ALL_CITIES = "" +
            "SELECT " +
                "c.id," +
                "c.name " +
            "FROM cities AS c;";

    private static String EXIST_CITY_ID = "" +
            "SELECT COUNT(*) AS count " +
            "FROM cities AS c " +
            "WHERE c.id = ?;";

    @Override
    public List<City> findAll() {
        List<City> cityList = new ArrayList<>();

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(SELECT_ALL_CITIES);
            ResultSet rs =  statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                 cityList.add(new City(id, name));
            }

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }

        return cityList;
    }

    @Override
    public boolean existById(int cityId) {
        boolean exist = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(EXIST_CITY_ID);
            statement.setInt(1, cityId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");

                if (count > 0) {
                    exist = true;
                }
            }

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }

        return exist;
    }

    @Override
    public boolean create(City city) {
        return false;
    }

    @Override
    public boolean update(City city) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
