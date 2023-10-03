package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.LocationType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.exception.DaoException;

import javax.xml.crypto.Data;

@Component
public class JdbcLocationTypeDao implements LocationTypeDao {
    private final JdbcTemplate jdbcTemplate;
    public JdbcLocationTypeDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<LocationType> getLocationTypes() {
        List<LocationType> locationTypes = new ArrayList<>();
        String sql = "SELECT * FROM locationType";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                LocationType locationType = mapRowToLocationType(results);
                locationTypes.add(locationType);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }

        return locationTypes;
    }

    @Override
    public LocationType getLocationTypeById(int locationTypeId) {
        LocationType locationType = null;
        String sql = "SELECT * FROM locationType WHERE location_type_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, locationTypeId);
            if (results.next()) {
                locationType = mapRowToLocationType(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return locationType;
    }

    @Override
    public LocationType getLocationTypeByName(String locationTypeName) {
        LocationType locationType = null;
        String sql = "SELECT * FROM locationType WHERE location_type_name = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, locationTypeName);
            if (results.next()) {
                locationType = mapRowToLocationType(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return locationType;
    }

    @Override
    public int getLocationTypeIdByName(String locationTypeName) {
        int locationTypeId = -1;
        String sql = "SELECT location_type_id FROM locationType WHERE location_type_name = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, locationTypeName);
            if (results.next()) {
                locationTypeId = results.getInt("location_type_id");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return locationTypeId;
    }

    @Override
    public LocationType createLocationType(LocationType locationType) {
        LocationType newLocationType = null;
        String sql = "INSERT INTO locationType (location_type_name) VALUES (?) RETURNING location_type_id";

        try {
            int locationTypeId = jdbcTemplate.queryForObject(sql, int.class, locationType.getLocationTypeName());
            newLocationType = getLocationTypeById(locationTypeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newLocationType;
    }

    @Override
    public LocationType updateLocationType(LocationType locationType) {
        LocationType updatedLocationType = null;
        String sql = "UPDATE locationType SET location_type_name = ? WHERE location_type_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, locationType.getLocationTypeName(), locationType.getLocationTypeId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedLocationType = getLocationTypeById(locationType.getLocationTypeId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedLocationType;
    }

    @Override
    public int deleteLocationTypeById(int locationTypeId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM locationType WHERE location_type_id = ?";

        try {
            numberOfRows = jdbcTemplate.update(sql, locationTypeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numberOfRows;
    }

    private LocationType mapRowToLocationType(SqlRowSet rs) {
        LocationType locationType = new LocationType();
        locationType.setLocationTypeId(rs.getInt("location_type_id"));
        locationType.setLocationTypeName(rs.getString("location_type_name"));
        return locationType;
    }
}
