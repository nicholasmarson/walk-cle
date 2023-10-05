package com.techelevator.dao;
public interface RewardsDao {

    int getAmountOfBarsVisited(String username);

    int getAmountOfParksVisited(String username);

    int getAmountOfStadiumsVisited(String username);

    int getAmountOfTotalPlacesVisited(String username);

    boolean allBarsVisited(String username);

    boolean allParksVisited(String username);

    boolean allStadiumsVisited(String username);

    boolean allPlacesVisited(String username);

    void updateBarCheckIn(String username, int locationId);

    void updateParkCheckIn(String username, int locationId);

    void updateStadiumCheckIn(String username, int locationId);
}
