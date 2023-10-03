package com.techelevator.dao;

import com.techelevator.model.LocationData;

import java.util.List;

public interface LocationDataDao {

    List<LocationData> getLocationData();
    LocationData getLocationDataById(int locationDataId);
    LocationData getLocationDataByName(String locationDataName);
    LocationData createLocationData(LocationData locationData0);
    LocationData updateLocationData(LocationData locationData);
    int deleteLocationDataById(int locationDataId);
}
