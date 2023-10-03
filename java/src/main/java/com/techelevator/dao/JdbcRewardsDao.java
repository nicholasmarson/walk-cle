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
    //see amount of bars visited
    @Override
    public int getAmountOfBarsVisited() {
        int amountOfBarsVisited = 0;
        String sql = "SELECT amount_bars_visited FROM rewards";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                amountOfBarsVisited = results.getInt("amount_bars_visited");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return amountOfBarsVisited;
    }

    //see amount of parks visited
    @Override
    public int getAmountOfParksVisited() {
        int amountOfParksVisited = 0;
        String sql = "SELECT amount_parks_visited FROM rewards";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                amountOfParksVisited = results.getInt("amount_parks_visited");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return amountOfParksVisited;
    }

    //see amount of stadiums visited
    @Override
    public int getAmountOfStadiumsVisited() {
        int amountOfStadiumsVisited = 0;
        String sql = "SELECT amount_stadiums_visited FROM rewards";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                amountOfStadiumsVisited = results.getInt("amount_stadium_visited");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return amountOfStadiumsVisited;
    }

    //check total amount of places visited
    @Override
    public int getAmountOfTotalPlacesVisited() {
        int amountOfTotalPlacesVisited = 0;
        String sql = "SELECT SUM(amount_bars_visited + amount_parks_visited + amount_stadiums_visited) AS total_places_visited FROM rewards";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                amountOfTotalPlacesVisited = results.getInt("amount_bars_visited");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return amountOfTotalPlacesVisited;
    }

    //check if all bars visited
    @Override
    public boolean allBarsVisited() {
        boolean allBarsVisited = false;
        String sql = "SELECT all_bars_visited FROM rewards";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                allBarsVisited = results.getBoolean("all_bars_visited");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allBarsVisited;
    }

    //check if all parks visited
    @Override
    public boolean allParksVisited() {
        boolean allParksVisited = false;
        String sql = "SELECT all_parks_visited FROM rewards";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                allParksVisited = results.getBoolean("all_parks_visited");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allParksVisited;
    }

    //check if all stadiums visited
    @Override
    public boolean allStadiumsVisited() {
        boolean allStadiumsVisited = false;
        String sql = "SELECT all_stadiums_visited FROM rewards";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                allStadiumsVisited = results.getBoolean("all_stadiums_visited");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allStadiumsVisited;
    }

    //check if all locations visited
    @Override
    public boolean allPlacesVisited() {
        boolean allPlacesVisited = false;
        String sql = "SELECT all_places_visited FROM rewards";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                boolean allBarsVisited = results.getBoolean("all_bars_visited");
                boolean allParksVisited = results.getBoolean("all_parks_visited");
                boolean allStadiumsVisited = results.getBoolean("all_stadiums_visited");

                // Check if all three conditions are true
                if (allBarsVisited && allParksVisited && allStadiumsVisited) {
                    allPlacesVisited = true;
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allPlacesVisited;
    }

    @Override
    public void updateBarCheckIn() {
        String sql = "UPDATE rewards SET amount_bars_visited = amount_bars_visited + 1";

        try {
            jdbcTemplate.update(sql);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public void updateParkCheckIn() {
        String sql = "UPDATE rewards SET amount_park_visited = amount_parks_visited + 1";

        try {
            jdbcTemplate.update(sql);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public void updateStadiumCheckIn() {
        String sql = "UPDATE rewards SET amount_stadium_visited = amount_stadium_visited + 1";

        try {
            jdbcTemplate.update(sql);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public void updateAllBarsVisited() {
        String sql = "UPDATE rewards SET all_bars_visited = true WHERE amount_bars_visited >= 5";

        try {
            jdbcTemplate.update(sql);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public void updateAllParksVisited() {
        String sql = "UPDATE rewards SET all_parks_visited = true WHERE amount_parks_visited >= 2";

        try {
            jdbcTemplate.update(sql);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public void updateAllStadiumsVisited() {
        String sql = "UPDATE rewards SET all_stadiums_visited = true WHERE amount_stadiums_visited >= 3";

        try {
            jdbcTemplate.update(sql);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

}
