package com.techelevator.dao;

import com.techelevator.model.LocationType;

import java.util.List;

public interface LocationTypeDao {
    List<LocationType> getLocationTypes();
    LocationType getLocationTypeById(int locationTypeId);
    LocationType getLocationTypeByName(String locationTypeName);
    int getLocationTypeIdByName(String locationTypeName);


    LocationType createLocationType(LocationType locationType);
    LocationType updateLocationType(LocationType locationType);
    int deleteLocationTypeById(int LocationTypeId);

}
