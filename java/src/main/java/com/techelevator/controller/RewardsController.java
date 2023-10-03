package com.techelevator.controller;

import com.techelevator.dao.RewardsDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rewards")
public class RewardsController {

    private RewardsDao rewardsDao;
    public RewardsController(RewardsDao rewardsDao) {
        this.rewardsDao = rewardsDao;
    }

    @RequestMapping(path="/barsvisited", method = RequestMethod.GET)
    public Integer getBarCount() {
        return rewardsDao.getAmountOfBarsVisited();
    }

    @RequestMapping(path="/parksvisited", method = RequestMethod.GET)
    public Integer getParkCount() {
        return rewardsDao.getAmountOfParksVisited();
    }

    @RequestMapping(path="/stadiumsvisited", method = RequestMethod.GET)
    public Integer getStadiumCount() {
        return rewardsDao.getAmountOfStadiumsVisited();
    }

    @RequestMapping(path="/placesvisited", method = RequestMethod.GET)
    public Integer getPlacesCount() {
        return rewardsDao.getAmountOfTotalPlacesVisited();
    }

    @RequestMapping(path="/bars", method = RequestMethod.GET)
    public boolean getAllBarsReward() {
        return rewardsDao.allBarsVisited();
    }

    @RequestMapping(path="/parks", method = RequestMethod.GET)
    public boolean getAllParksReward() {
        return rewardsDao.allParksVisited();
    }

    @RequestMapping(path="/stadiums", method = RequestMethod.GET)
    public boolean getAllStadiumsReward() {
        return rewardsDao.allStadiumsVisited();
    }

    @RequestMapping(path="/places", method = RequestMethod.GET)
    public boolean getAllPlacesReward() {
        return rewardsDao.allPlacesVisited();
    }




}
