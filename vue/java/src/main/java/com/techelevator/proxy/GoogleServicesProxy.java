package com.techelevator.proxy;

import com.techelevator.service.GoogleMapsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GoogleServicesProxy {

    private GoogleMapsService googleMapsService = new GoogleMapsService();

    @RequestMapping(path="/test", method = RequestMethod.GET)
    public void test() {
        googleMapsService.getCandidates();
    }

    @RequestMapping(path="/nearbyplaces", method = RequestMethod.GET)
    public ResponseEntity<String> getNearbyPlaces() {
        return googleMapsService.getNearbyPlaces();
    }
}
