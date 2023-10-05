package com.techelevator.model;

public class LocationType {
    private int locationTypeId;
    private String locationTypeName;

    public LocationType() {}

    public LocationType(int locationTypeId, String locationTypeName) {
        this.locationTypeId = locationTypeId;
        this.locationTypeName = locationTypeName;
    }

    public int getLocationTypeId() {
        return locationTypeId;
    }

    public void setLocationTypeId(int locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    public String getLocationTypeName() {
        return locationTypeName;
    }

    public void setLocationTypeName(String locationTypeName) {
        this.locationTypeName = locationTypeName;
    }

    @Override
    public String toString() {
        return "LocationType{" +
                "LocationTypeId=" + locationTypeId +
                ", LocationTypeName='" + locationTypeName + '\'' +
                '}';
    }
}