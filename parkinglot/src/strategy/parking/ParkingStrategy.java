package strategy.parking;

import model.Floor;
import model.Spot;
import model.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<Spot> findSpot(Vehicle vehicle, List<Floor> floors);
}