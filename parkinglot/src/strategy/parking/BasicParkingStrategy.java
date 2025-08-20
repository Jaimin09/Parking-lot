package strategy.parking;

import model.Floor;
import model.Spot;
import model.Vehicle;
import model.VehicleType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BasicParkingStrategy implements ParkingStrategy {

    @Override
    public Optional<Spot> findSpot(Vehicle vehicle, List<Floor> floors) {
        for(Floor floor : floors) {
            Map<String, Spot> availableSpots = floor.getAvailableSpots();
            Optional<Spot> spot = availableSpots.entrySet()
                    .stream()
                    // IMP NOTE: Do not call compareAndSet() if the spot is not valid
                    .filter(entry -> exactVehicleSizeMatch(entry.getValue().getVehicleType(), vehicle.getType()) && entry.getValue().tryOccupy())
                    .findFirst()
                    .map(Map.Entry::getValue);

            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }

    private boolean exactVehicleSizeMatch(VehicleType type1, VehicleType type2) {
        return type2.equals(type1);
    }
}