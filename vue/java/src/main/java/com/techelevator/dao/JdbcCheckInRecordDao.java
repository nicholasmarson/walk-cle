package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.CheckInRecord;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCheckInRecordDao implements CheckInRecordDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCheckInRecordDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CheckInRecord> getCheckInRecords(String username) {
        List<CheckInRecord> records = new ArrayList<>();
        String sql = "SELECT checkins.checkin_id, checkins.username, checkins.location_id, location.location_name, checkins.checked_in, checkins.checkin_timestamp FROM checkins JOIN location ON checkins.location_id = location.location_id WHERE checkins.username = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                CheckInRecord checkInRecord = new CheckInRecord();
                checkInRecord.setCheckInId(results.getInt("checkin_id"));
                checkInRecord.setUsername(results.getString("username"));
                checkInRecord.setLocationId(results.getInt("location_id"));
                checkInRecord.setLocationName(results.getString("location_name"));
                checkInRecord.setCheckedIn(results.getBoolean("checked_in"));
                checkInRecord.setTimestamp(results.getTimestamp("checkin_timestamp"));
                records.add(checkInRecord);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }

        return records;
    }
}
