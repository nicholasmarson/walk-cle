package com.techelevator.model;

import java.util.List;

public class LocationData {

    private int locationDataId;
    private String locationDataName;
    private String locationDataDescription;
    private List<String> locationDataDays;
    private List<String> locationDataOpeningTimes;
    private List<String> locationDataClosingTimes;
    private String locationDataImgUrl;
    private String locationDataInfoUrl;

    public LocationData() {}
    public LocationData(int locationDataId, String locationDataName, String locationDataDescription, List<String> locationDataDays, List<String> locationDataOpeningTimes, List<String> locationDataClosingTimes, String locationDataImgUrl, String locationDataInfoUrl) {
        this.locationDataId = locationDataId;
        this.locationDataName = locationDataName;
        this.locationDataDescription = locationDataDescription;
        this.locationDataDays = locationDataDays;
        this.locationDataOpeningTimes = locationDataOpeningTimes;
        this.locationDataClosingTimes = locationDataClosingTimes;
        this.locationDataImgUrl = locationDataImgUrl;
        this.locationDataInfoUrl = locationDataInfoUrl;
    }

    public int getLocationDataId() {
        return locationDataId;
    }

    public void setLocationDataId(int locationDataId) {
        this.locationDataId = locationDataId;
    }

    public String getLocationDataName() {
        return locationDataName;
    }

    public void setLocationDataName(String locationDataName) {
        this.locationDataName = locationDataName;
    }

    public String getLocationDataDescription() {
        return locationDataDescription;
    }

    public void setLocationDataDescription(String locationDataDescription) {
        this.locationDataDescription = locationDataDescription;
    }

    public List<String> getLocationDataDays() {
        return locationDataDays;
    }

    public void setLocationDataDays(List<String> locationDataDays) {
        this.locationDataDays = locationDataDays;
    }

    public List<String> getLocationDataOpeningTimes() {
        return locationDataOpeningTimes;
    }

    public void setLocationDataOpeningTimes(List<String> locationDataOpeningTimes) {
        this.locationDataOpeningTimes = locationDataOpeningTimes;
    }

    public List<String> getLocationDataClosingTimes() {
        return locationDataClosingTimes;
    }

    public void setLocationDataClosingTimes(List<String> locationDataClosingTimes) {
        this.locationDataClosingTimes = locationDataClosingTimes;
    }

    public String getLocationDataImgUrl() {
        return locationDataImgUrl;
    }

    public void setLocationDataImgUrl(String locationDataImgUrl) {
        this.locationDataImgUrl = locationDataImgUrl;
    }

    public String getLocationDataInfoUrl() {
        return locationDataInfoUrl;
    }

    public void setLocationDataInfoUrl(String locationDataInfoUrl) {
        this.locationDataInfoUrl = locationDataInfoUrl;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "locationDataId=" + locationDataId +
                ", locationDataName='" + locationDataName + '\'' +
                ", locationDataDescription='" + locationDataDescription + '\'' +
                ", locationDataDays=" + locationDataDays +
                ", locationDataOpeningTimes=" + locationDataOpeningTimes +
                ", locationDataClosingTimes=" + locationDataClosingTimes +
                ", locationDataImgUrl='" + locationDataImgUrl + '\'' +
                ", locationDataInfoUrl='" + locationDataInfoUrl + '\'' +
                '}';
    }
}
