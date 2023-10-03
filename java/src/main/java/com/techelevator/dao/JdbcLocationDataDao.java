package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.LocationData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLocationDataDao implements LocationDataDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcLocationDataDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<LocationData> getLocationData() {
        List<LocationData> locationDataList = new ArrayList<>();
        String sql = "SELECT * FROM locationData";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                LocationData locationData = mapRowToLocationData(results);
                locationDataList.add(locationData);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return locationDataList;
    }

    @Override
    public LocationData getLocationDataById(int locationDataId) {
        LocationData locationData = null;
        String sql = "SELECT * FROM locationData WHERE location_data_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, locationDataId);
            if (results.next()) {
                locationData = mapRowToLocationData(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return locationData;
    }

    @Override
    public LocationData getLocationDataByName(String locationDataName) {
        LocationData locationData = null;
        String sql = "SELECT * FROM locationData WHERE location_data_name = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, locationDataName);
            if (results.next()) {
                locationData = mapRowToLocationData(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return locationData;
    }

    @Override
    public LocationData createLocationData(LocationData locationData) {
        LocationData newLocationData = null;
        String sql = "INSERT INTO locationData (location_data_name, location_data_description, location_data_days, location_data_opening_times, location_data_closing_times, location_data_img_url, location_data_info_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING location_data_id";

        try {
            int locationDataId = jdbcTemplate.queryForObject(sql, int.class,
                    locationData.getLocationDataName(), locationData.getLocationDataDescription(),
                    locationData.getLocationDataDays(), locationData.getLocationDataOpeningTimes(),
                    locationData.getLocationDataClosingTimes(), locationData.getLocationDataImgUrl(),
                    locationData.getLocationDataInfoUrl());
            newLocationData = getLocationDataById(locationDataId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newLocationData;
    }

    @Override
    public LocationData updateLocationData(LocationData locationData) {
        LocationData updatedLocationData = null;
        String sql = "UPDATE locationData " +
                "SET location_data_name = ?, location_data_description = ?, " +
                "location_data_days = ?, location_data_opening_times = ?, " +
                "location_data_closing_times = ?, location_data_img_url = ?, " +
                "location_data_info_url = ? " +
                "WHERE location_data_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, locationData.getLocationDataName(),
                    locationData.getLocationDataDescription(), locationData.getLocationDataDays(),
                    locationData.getLocationDataOpeningTimes(), locationData.getLocationDataClosingTimes(),
                    locationData.getLocationDataImgUrl(), locationData.getLocationDataInfoUrl(),
                    locationData.getLocationDataId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedLocationData = getLocationDataById(locationData.getLocationDataId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedLocationData;
    }

    @Override
    public int deleteLocationDataById(int locationDataId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM locationData WHERE location_data_id = ?";

        try {
            numberOfRows = jdbcTemplate.update(sql, locationDataId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numberOfRows;
    }

    private LocationData mapRowToLocationData(SqlRowSet rs) {
        LocationData locationData = new LocationData();
        locationData.setLocationDataId(rs.getInt("location_data_id"));
        locationData.setLocationDataName(rs.getString("location_data_name"));
        locationData.setLocationDataDescription(rs.getString("location_data_description"));
        locationData.setLocationDataDays(Arrays.asList(rs.getString("location_data_days").split(",")));
        locationData.setLocationDataOpeningTimes(Arrays.asList(rs.getString("location_data_opening_times").split(",")));
        locationData.setLocationDataClosingTimes(Arrays.asList(rs.getString("location_data_closing_times").split(",")));
        locationData.setLocationDataImgUrl(rs.getString("location_data_img_url"));
        locationData.setLocationDataInfoUrl(rs.getString("location_data_info_url"));
        return locationData;
    }
}
