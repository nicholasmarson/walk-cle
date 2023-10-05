package com.techelevator.dao;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.exception.DaoException;

@Component
public class JdbcRewardsDao implements RewardsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRewardsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int getAmountOfBarsVisited(String username) {
        int amountOfBarsVisited = 0;
        String sql = "SELECT amount_bars_visited FROM rewards WHERE username = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                amountOfBarsVisited = results.getInt("amount_bars_visited");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return amountOfBarsVisited;
    }

    @Override
    public int getAmountOfParksVisited(String username) {
        int amountOfParksVisited = 0;
        String sql = "SELECT amount_parks_visited FROM rewards WHERE username = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                amountOfParksVisited = results.getInt("amount_parks_visited");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return amountOfParksVisited;
    }

    @Override
    public int getAmountOfStadiumsVisited(String username) {
        int amountOfStadiumsVisited = 0;
        String sql = "SELECT amount_stadiums_visited FROM rewards WHERE username = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                amountOfStadiumsVisited = results.getInt("amount_stadiums_visited");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return amountOfStadiumsVisited;
    }

    @Override
    public int getAmountOfTotalPlacesVisited(String username) {
        int amountOfTotalPlacesVisited = 0;
        String sql = "SELECT SUM(amount_bars_visited + amount_parks_visited + amount_stadiums_visited) AS total_places_visited FROM rewards WHERE username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                amountOfTotalPlacesVisited = results.getInt("total_places_visited"); // Use "total_places_visited" here
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return amountOfTotalPlacesVisited;
    }

    @Override
    public boolean allBarsVisited(String username) {
        String sql = "SELECT amount_bars_visited FROM rewards WHERE username = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                int amountBarsVisited = results.getInt("amount_bars_visited");
                boolean allBarsVisited = (amountBarsVisited >= 5);

                updateAllBarsVisited(allBarsVisited);

                return allBarsVisited;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return false;
    }

    @Override
    public boolean allParksVisited(String username) {
        String sql = "SELECT amount_parks_visited FROM rewards WHERE username = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                int amountParksVisited = results.getInt("amount_parks_visited");
                boolean allParksVisited = (amountParksVisited >= 2);

                updateAllParksVisited(allParksVisited);

                return allParksVisited;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return false;
    }

    @Override
    public boolean allStadiumsVisited(String username) {
        String sql = "SELECT amount_stadiums_visited FROM rewards WHERE username = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                int amountStadiumsVisited = results.getInt("amount_stadiums_visited");
                boolean allStadiumsVisited = (amountStadiumsVisited >= 3);

                updateAllStadiumsVisited(allStadiumsVisited);

                return allStadiumsVisited;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return false;
    }

    @Override
    public boolean allPlacesVisited(String username) {
        boolean allPlacesVisited = false;
        String sql = "SELECT all_bars_visited, all_parks_visited, all_stadiums_visited FROM rewards WHERE username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                boolean allBarsVisited = results.getBoolean("all_bars_visited");
                boolean allParksVisited = results.getBoolean("all_parks_visited");
                boolean allStadiumsVisited = results.getBoolean("all_stadiums_visited");

                // Check if all three conditions are true
                if (allBarsVisited && allParksVisited && allStadiumsVisited) {
                    allPlacesVisited = true;

                    String updateSql = "UPDATE rewards SET all_places_visited = true";
                    jdbcTemplate.update(updateSql);
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allPlacesVisited;
    }

    @Override
    public void updateBarCheckIn(String username, int locationId) {
        String updateRewardsSql = "UPDATE rewards SET amount_bars_visited = amount_bars_visited + 1 WHERE username = ?;";
        String insertCheckinSql = "INSERT INTO checkins (username, location_id, checked_in, checkin_timestamp) VALUES (?, ?, true, ?);";

        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());

        try {
            jdbcTemplate.update(updateRewardsSql, username);
            jdbcTemplate.update(insertCheckinSql, username, locationId, currentTimestamp);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public void updateParkCheckIn(String username, int locationId) {
        String updateRewardsSql = "UPDATE rewards SET amount_parks_visited = amount_parks_visited + 1 WHERE username = ?;";
        String insertCheckinSql = "INSERT INTO checkins (username, location_id, checked_in, checkin_timestamp) VALUES (?, ?, true, ?);";

        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());

        try {
            jdbcTemplate.update(updateRewardsSql, username);
            jdbcTemplate.update(insertCheckinSql, username, locationId, currentTimestamp);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public void updateStadiumCheckIn(String username, int locationId) {
        String sql = "UPDATE rewards SET amount_stadiums_visited = amount_stadiums_visited + 1 WHERE username = ?;";
        String insertCheckinSql = "INSERT INTO checkins (username, location_id, checked_in, checkin_timestamp) VALUES (?, ?, true, ?);";

        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());

        try {
            jdbcTemplate.update(sql, username);
            jdbcTemplate.update(insertCheckinSql, username, locationId, currentTimestamp);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    private void updateAllBarsVisited(boolean value) {
        String updateSql = "UPDATE rewards SET all_bars_visited = ?;";
        try {
            jdbcTemplate.update(updateSql, value);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to update all_bars_visited", e);
        }
    }

    private void updateAllParksVisited(boolean value) {
        String updateSql = "UPDATE rewards SET all_parks_visited = ?;";
        try {
            jdbcTemplate.update(updateSql, value);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to update all_parks_visited", e);
        }
    }

    private void updateAllStadiumsVisited(boolean value) {
        String updateSql = "UPDATE rewards SET all_stadiums_visited = ?;";
        try {
            jdbcTemplate.update(updateSql, value);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to update all_parks_visited", e);
        }
    }
}
