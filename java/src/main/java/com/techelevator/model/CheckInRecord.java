package com.techelevator.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CheckInRecord {

    private int checkInId;
    private String username;
    private int locationId;
    private String locationName;
    private boolean checkedIn;
    private Timestamp timestamp;

    public CheckInRecord() {
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public CheckInRecord(int checkin_id, String username, int location_id, String locationName, boolean checkedIn, Timestamp timestamp) {
        this.checkInId = checkin_id;
        this.username = username;
        this.locationId = locationId;
        this.locationName = locationName;
        this.checkedIn = checkedIn;
        this.timestamp = timestamp;
    }

    public int getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(int checkInId) {
        this.checkInId = checkInId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
