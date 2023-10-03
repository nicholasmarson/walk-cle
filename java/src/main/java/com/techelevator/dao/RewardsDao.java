package com.techelevator.dao;
public interface RewardsDao {

    int getAmountOfBarsVisited();
    int getAmountOfParksVisited();
    int getAmountOfStadiumsVisited();
    int getAmountOfTotalPlacesVisited();
    boolean allBarsVisited();
    boolean allParksVisited();
    boolean allStadiumsVisited();
    boolean allPlacesVisited();


    void updateBarCheckIn();

    void updateParkCheckIn();

    void updateStadiumCheckIn();

    void updateAllBarsVisited();

    void updateAllParksVisited();

    void updateAllStadiumsVisited();
}
