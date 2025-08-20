package model;

import java.util.Map;

public class Floor {
    String floorNumber;
    Map<String, Spot> availableSpots;

    public Floor(String floorNumber, Map<String, Spot> availableSpots) {
        this.floorNumber = floorNumber;
        this.availableSpots = availableSpots;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public Map<String, Spot> getAvailableSpots() {
        return availableSpots;
    }

    public void addSpot(Spot spot) {
        availableSpots.put(spot.getSpotId(), spot);
    }

    public void removeSpot(String spotId) {
        availableSpots.remove(spotId);
    }
}
