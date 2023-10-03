package com.techelevator.dao;

import com.techelevator.model.Location;

import java.util.List;

public interface LocationDao {
    List<Location> getLocations();
    Location getLocationById(int locationId);
    Location getLocationByName(String locationName);
    int getLocationIdByName(String locationName);
    List<Location> getAllLocationsByLocationTypeName(String locationTypeName);


    Location createLocation(Location location);
    Location updateLocation(Location location);
    int deleteLocationById(int LocationId);
}
