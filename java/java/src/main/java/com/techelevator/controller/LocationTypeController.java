package com.techelevator.controller;

import com.techelevator.dao.LocationTypeDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.LocationType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/locationtypes")
public class LocationTypeController {
    private LocationTypeDao locationTypeDao;
    public LocationTypeController(LocationTypeDao locationTypeDao) { this.locationTypeDao = locationTypeDao; }

    @RequestMapping(method=RequestMethod.GET)
    public List<LocationType> getAllLocationTypes() {
        return locationTypeDao.getLocationTypes();
    }

    @RequestMapping(path="/id/{locationTypeId}", method=RequestMethod.GET)
    public LocationType getLocationTypeById(@PathVariable int locationTypeId) {
        LocationType locationType = locationTypeDao.getLocationTypeById(locationTypeId);
        if (locationType == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location Type Not Found");
        } else {
            return locationType;
        }
    }

    @RequestMapping(path="/name/{locationTypeName}", method=RequestMethod.GET)
    public LocationType getLocationTypeByName(@PathVariable String locationTypeName) {
        LocationType locationType = locationTypeDao.getLocationTypeByName(locationTypeName);
        if (locationType == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location Type Not Found");
        } else {
            return locationType;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/create", method=RequestMethod.POST)
    public LocationType createLocationType(@Valid @RequestBody LocationType locationType) {
        return locationTypeDao.createLocationType(locationType);
    }

    @RequestMapping(path="/update/{locationTypeId}", method=RequestMethod.PUT)
    public LocationType updateLocationType(@Valid @RequestBody LocationType locationType, @PathVariable int locationTypeId) {
        locationType.setLocationTypeId(locationTypeId);
        try {
            return locationTypeDao.updateLocationType(locationType);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location Type Not Found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/delete/{locationTypeId}", method=RequestMethod.DELETE)
    public void deleteLocationType(@PathVariable int locationTypeId) {
        locationTypeDao.deleteLocationTypeById(locationTypeId);
    }
}
