package com.techelevator.controller;

import com.techelevator.dao.LocationDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/locations")
public class LocationController {
    private LocationDao locationDao;
    public LocationController(LocationDao locationDao) { this.locationDao = locationDao; }

    @RequestMapping(method=RequestMethod.GET)
    public List<Location> getAllLocations() {
        return locationDao.getLocations();
    }


    @RequestMapping(path="/id/{locationId}", method=RequestMethod.GET)
    public Location getLocationById(@PathVariable int locationId) {
        Location location = locationDao.getLocationById(locationId);
        if (location == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location Not Found");
        } else {
            return location;
        }
    }

    @RequestMapping(path="/name/", method=RequestMethod.GET)
    public Location getLocationByName(@PathVariable String locationName) {
        Location location = locationDao.getLocationByName(locationName);
        if (location == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location Not Found");
        } else {
            return location;
        }
    }

    @RequestMapping(path="/{locationTypeName}", method=RequestMethod.GET)
    public List<Location> getAllLocationsByLocationTypeName(@PathVariable String locationTypeName) {
        return locationDao.getAllLocationsByLocationTypeName(locationTypeName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method=RequestMethod.POST)
    public Location createLocation(@Valid @RequestBody Location location) {
        return locationDao.createLocation(location);
    }

    @RequestMapping(path="/{locationId}", method=RequestMethod.PUT)
    public Location updateLocation(@Valid @RequestBody Location location, @PathVariable int locationId) {
        location.setLocationId(locationId);
        try {
            return locationDao.updateLocation(location);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location Not Found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/{locationId}", method=RequestMethod.DELETE)
    public void deleteLocation(@PathVariable int locationId) {
        locationDao.deleteLocationById(locationId);
    }

}
