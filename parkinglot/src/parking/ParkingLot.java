package parking;

import model.Floor;
import model.Spot;
import model.Ticket;
import model.Vehicle;
import strategy.fee.FeeStrategy;
import strategy.fee.HourlyFeeStrategy;
import strategy.parking.BasicParkingStrategy;
import strategy.parking.ParkingStrategy;

import java.time.Instant;
import java.util.*;

public class ParkingLot {
    ParkingStrategy parkingStrategy;
    FeeStrategy feeStrategy;
    List<Floor> floors = new ArrayList<>();
    Map<String, Ticket> activeTickets = new HashMap<>();
    private static ParkingLot instance;

    private ParkingLot() {
        this.parkingStrategy = new BasicParkingStrategy();
        this.feeStrategy = new HourlyFeeStrategy();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void entry(Vehicle vehicle) {
        Optional<Spot> spot = parkingStrategy.findSpot(vehicle, floors);
        if (spot.isEmpty()) {
            System.out.println("Sorry, no slot available for: " + vehicle.getType());
            return;
        }
        Spot availableSpot = spot.get();
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle.getNumber(), vehicle.getType(), availableSpot, Instant.now());
        activeTickets.put(vehicle.getNumber(), ticket);

        availableSpot.occupySpot(vehicle);
        System.out.println("Parked vehicle: " + vehicle.getNumber() + " at spot: " + availableSpot.getSpotId());
    }

    public void exit(Vehicle vehicle) {
        Ticket ticket = activeTickets.get(vehicle.getNumber());
        if (ticket == null) {
            System.out.println("Tickt not available for: " + vehicle.getNumber());
            return;
        }
        ticket.setExitTime(Instant.now());
        float fee = feeStrategy.calculateFee(ticket);

        activeTickets.remove(vehicle.getNumber());
        Spot spot = ticket.getSpot();
        spot.freeSpot();
        System.out.println("Vehicle " + vehicle.getNumber() + " exited. Fee: " + fee);
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }
}
