package com.techelevator.model;

public class Location {
    private int locationId;
    private int locationTypeId;
    private String locationName;
    private double locationLatitude;
    private double locationLongitude;
    public Location() {}
    public Location(int locationId, int locationTypeId, String locationName, int locationLatitude, int locationLongitude) {
        this.locationId = locationId;
        this.locationTypeId = locationTypeId;
        this.locationName = locationName;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
    }
    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) { this.locationId = locationId; }
    public int getLocationTypeId() { return locationTypeId; }
    public void setLocationTypeId(int locationTypeId) { this.locationTypeId = locationTypeId; }
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public double getLocationLatitude() { return locationLatitude; }
    public void setLocationLatitude(double locationLatitude) { this.locationLatitude = locationLatitude; }
    public double getLocationLongitude() { return locationLongitude; }
    public void setLocationLongitude(double locationLongitude) { this.locationLongitude = locationLongitude; }
    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationTypeId=" + locationTypeId +
                ", locationName='" + locationName + '\'' +
                ", locationLatitude=" + locationLatitude +
                ", locationLongitude=" + locationLongitude +
                '}';
    }
}
