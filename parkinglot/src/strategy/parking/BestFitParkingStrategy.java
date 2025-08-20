package strategy.parking;

import model.Floor;
import model.Spot;
import model.Vehicle;
import model.VehicleType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BestFitParkingStrategy implements ParkingStrategy {

    @Override
    public Optional<Spot> findSpot(Vehicle vehicle, List<Floor> floors) {
        Optional<Spot> liberalSpotOpt = Optional.empty();

        for(Floor floor : floors) {
            Map<String, Spot> availableSpots = floor.getAvailableSpots();
            Optional<Spot> spot = availableSpots.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().tryOccupy() && exactVehicleSizeMatch(entry.getValue().getVehicleType(), vehicle.getType()))
                    .findFirst()
                    .map(Map.Entry::getValue);

            if (spot.isPresent()) {
                return spot;
            } else if( liberalSpotOpt.isEmpty()) {
                liberalSpotOpt = availableSpots.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().tryOccupy() && liberalVehicleSizeMatch(entry.getValue().getVehicleType(), vehicle.getType()))
                        .findFirst()
                        .map(Map.Entry::getValue);
            }
        }
        return liberalSpotOpt;
    }

    private boolean exactVehicleSizeMatch(VehicleType type1, VehicleType type2) {
        return type1.getSize() == type2.getSize();
    }

    private boolean liberalVehicleSizeMatch(VehicleType type1, VehicleType type2) {
        return type1.getSize() >= type2.getSize();
    }
}
