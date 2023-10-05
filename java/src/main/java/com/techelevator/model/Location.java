package com.techelevator.model;

import java.util.List;

public class Location {
    private int locationId;
    private String locationTypeName;
    private String locationName;
    private double locationLatitude;
    private double locationLongitude;
    private String locationDescription;
    private String locationSunOpen;
    private String locationSunClose;
    private String locationMonOpen;
    private String locationMonClose;
    private String locationTueOpen;
    private String locationTueClose;
    private String locationWedOpen;
    private String locationWedClose;
    private String locationThuOpen;
    private String locationThuClose;
    private String locationFriOpen;
    private String locationFriClose;
    private String locationSatOpen;
    private String locationSatClose;
    private String locationImgUrl;
    private String locationInfoUrl;


    public Location() {}

    public Location(int locationId, String locationTypeName, String locationName, double locationLatitude, double locationLongitude, String locationDescription, String locationSunOpen, String locationSunClose, String locationMonOpen, String locationMonClose, String locationTueOpen, String locationTueClose, String locationWedOpen, String locationWedClose, String locationThuOpen, String locationThuClose, String locationFriOpen, String locationFriClose, String locationSatOpen, String locationSatClose, String locationImgUrl, String locationInfoUrl) {
        this.locationId = locationId;
        this.locationTypeName = locationTypeName;
        this.locationName = locationName;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.locationDescription = locationDescription;
        this.locationSunOpen = locationSunOpen;
        this.locationSunClose = locationSunClose;
        this.locationMonOpen = locationMonOpen;
        this.locationMonClose = locationMonClose;
        this.locationTueOpen = locationTueOpen;
        this.locationTueClose = locationTueClose;
        this.locationWedOpen = locationWedOpen;
        this.locationWedClose = locationWedClose;
        this.locationThuOpen = locationThuOpen;
        this.locationThuClose = locationThuClose;
        this.locationFriOpen = locationFriOpen;
        this.locationFriClose = locationFriClose;
        this.locationSatOpen = locationSatOpen;
        this.locationSatClose = locationSatClose;
        this.locationImgUrl = locationImgUrl;
        this.locationInfoUrl = locationInfoUrl;
    }


    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationTypeName() {
        return locationTypeName;
    }

    public void setLocationTypeName(String locationTypeName) {
        this.locationTypeName = locationTypeName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getLocationSunOpen() {
        return locationSunOpen;
    }

    public void setLocationSunOpen(String locationSunOpen) {
        this.locationSunOpen = locationSunOpen;
    }

    public String getLocationSunClose() {
        return locationSunClose;
    }

    public void setLocationSunClose(String locationSunClose) {
        this.locationSunClose = locationSunClose;
    }

    public String getLocationMonOpen() {
        return locationMonOpen;
    }

    public void setLocationMonOpen(String locationMonOpen) {
        this.locationMonOpen = locationMonOpen;
    }

    public String getLocationMonClose() {
        return locationMonClose;
    }

    public void setLocationMonClose(String locationMonClose) {
        this.locationMonClose = locationMonClose;
    }

    public String getLocationTueOpen() {
        return locationTueOpen;
    }

    public void setLocationTueOpen(String locationTueOpen) {
        this.locationTueOpen = locationTueOpen;
    }

    public String getLocationTueClose() {
        return locationTueClose;
    }

    public void setLocationTueClose(String locationTueClose) {
        this.locationTueClose = locationTueClose;
    }

    public String getLocationWedOpen() {
        return locationWedOpen;
    }

    public void setLocationWedOpen(String locationWedOpen) {
        this.locationWedOpen = locationWedOpen;
    }

    public String getLocationWedClose() {
        return locationWedClose;
    }

    public void setLocationWedClose(String locationWedClose) {
        this.locationWedClose = locationWedClose;
    }

    public String getLocationThuOpen() {
        return locationThuOpen;
    }

    public void setLocationThuOpen(String locationThuOpen) {
        this.locationThuOpen = locationThuOpen;
    }

    public String getLocationThuClose() {
        return locationThuClose;
    }

    public void setLocationThuClose(String locationThuClose) {
        this.locationThuClose = locationThuClose;
    }

    public String getLocationFriOpen() {
        return locationFriOpen;
    }

    public void setLocationFriOpen(String locationFriOpen) {
        this.locationFriOpen = locationFriOpen;
    }

    public String getLocationFriClose() {
        return locationFriClose;
    }

    public void setLocationFriClose(String locationFriClose) {
        this.locationFriClose = locationFriClose;
    }

    public String getLocationSatOpen() {
        return locationSatOpen;
    }

    public void setLocationSatOpen(String locationSatOpen) {
        this.locationSatOpen = locationSatOpen;
    }

    public String getLocationSatClose() {
        return locationSatClose;
    }

    public void setLocationSatClose(String locationSatClose) {
        this.locationSatClose = locationSatClose;
    }

    public String getLocationImgUrl() {
        return locationImgUrl;
    }

    public void setLocationImgUrl(String locationImgUrl) {
        this.locationImgUrl = locationImgUrl;
    }

    public String getLocationInfoUrl() {
        return locationInfoUrl;
    }

    public void setLocationInfoUrl(String locationInfoUrl) {
        this.locationInfoUrl = locationInfoUrl;
    }


    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationTypeName='" + locationTypeName + '\'' +
                ", locationName='" + locationName + '\'' +
                ", locationLatitude=" + locationLatitude +
                ", locationLongitude=" + locationLongitude +
                ", locationDescription='" + locationDescription + '\'' +
                ", locationSunOpen='" + locationSunOpen + '\'' +
                ", locationSunClose='" + locationSunClose + '\'' +
                ", locationMonOpen='" + locationMonOpen + '\'' +
                ", locationMonClose='" + locationMonClose + '\'' +
                ", locationTueOpen='" + locationTueOpen + '\'' +
                ", locationTueClose='" + locationTueClose + '\'' +
                ", locationWedOpen='" + locationWedOpen + '\'' +
                ", locationWedClose='" + locationWedClose + '\'' +
                ", locationThuOpen='" + locationThuOpen + '\'' +
                ", locationThuClose='" + locationThuClose + '\'' +
                ", locationFriOpen='" + locationFriOpen + '\'' +
                ", locationFriClose='" + locationFriClose + '\'' +
                ", locationSatOpen='" + locationSatOpen + '\'' +
                ", locationSatClose='" + locationSatClose + '\'' +
                ", locationImgUrl='" + locationImgUrl + '\'' +
                ", locationInfoUrl='" + locationInfoUrl + '\'' +
                '}';
    }
}
