package model;

import java.util.concurrent.atomic.AtomicBoolean;

public class Spot {
    String spotId;
    AtomicBoolean isOccupied = new AtomicBoolean(false);
    Vehicle parkedVehicle;
    VehicleType vehicleType;

    public Spot(String spotId, VehicleType vehicleType){
        this.spotId = spotId;
        this.vehicleType = vehicleType;
        this.parkedVehicle = null;
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isOccupied() {
        return isOccupied.get();
    }

    // NOTE: Use of compare & set to ensure thread safety for 2 vehicles trying to occupy the same spot
    // Do not call compareAndSet() if the spot is not valid
    public boolean tryOccupy() {
        return isOccupied.compareAndSet(false, true);
    }

    public void occupySpot(Vehicle vehicle){
        this.parkedVehicle = vehicle;
    }

    public void freeSpot(){
        this.parkedVehicle = null;
        this.isOccupied.set(false);
    }
}