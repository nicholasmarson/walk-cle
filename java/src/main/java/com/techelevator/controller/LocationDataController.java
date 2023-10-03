package com.techelevator.controller;


import com.techelevator.dao.LocationDataDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.LocationData;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/locationdata")
public class LocationDataController {

    private LocationDataDao locationDataDao;
    public LocationDataController(LocationDataDao locationDataDao) {
        this.locationDataDao = locationDataDao;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<LocationData> getAllLocationData() {
        return locationDataDao.getLocationData();
    }

    @RequestMapping(path="/id/{locationDataId}", method=RequestMethod.GET)
    public LocationData getLocationDataById(@PathVariable int locationDataId) {
        LocationData locationData = locationDataDao.getLocationDataById(locationDataId);
        if (locationData == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location Data Not Found");
        } else {
            return locationData;
        }
    }

    @RequestMapping(path="/name/{locationDataName}", method=RequestMethod.GET)
    public LocationData getLocationDataByName(@PathVariable String locationDataName) {
        LocationData locationData = locationDataDao.getLocationDataByName(locationDataName);
        if (locationData == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location Data Not Found");
        } else {
            return locationData;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/create", method=RequestMethod.POST)
    public LocationData createLocationData(@Valid @RequestBody LocationData locationData) {
        return locationDataDao.createLocationData(locationData);
    }

    @RequestMapping(path="/update/{locationDataId}", method=RequestMethod.PUT)
    public LocationData updateLocationData(@Valid @RequestBody LocationData locationData, @PathVariable int locationDataId) {
        locationData.setLocationDataId(locationDataId);
        try {
            return locationDataDao.updateLocationData(locationData);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location Data Not Found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/delete/{locationDataId}", method=RequestMethod.DELETE)
    public void deleteLocationData(@PathVariable int locationDataId) {
        locationDataDao.deleteLocationDataById(locationDataId);
    }
}
