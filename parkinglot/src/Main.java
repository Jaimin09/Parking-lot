import model.*;
import parking.EntryGate;
import parking.ExitGate;
import parking.Gate;
import parking.ParkingLot;
import strategy.parking.BestFitParkingStrategy;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Map<String, Spot> FloorOneSpots = new HashMap<>();
        FloorOneSpots.put("A1", new Spot("A1", VehicleType.CAR));
        FloorOneSpots.put("D1", new Spot("D1", VehicleType.BIKE));
        FloorOneSpots.put("E1", new Spot("E1", VehicleType.TRUCK));
        Floor floorOne = new Floor("1", FloorOneSpots);

        Map<String, Spot> FloorTwoSpots = new HashMap<>();
        FloorTwoSpots.put("A1", new Spot("B1", VehicleType.CAR));
        FloorTwoSpots.put("C1", new Spot("C1", VehicleType.BIKE));
        Floor floorTwo = new Floor("2", FloorTwoSpots);

        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addFloor(floorOne);
        parkingLot.addFloor(floorTwo);

        // Parking strategy changed
//        parkingLot.setParkingStrategy(new BestFitParkingStrategy());

        EntryGate entryGate1 = new EntryGate("1");
        EntryGate entryGate2 = new EntryGate("2");

        ExitGate exitGate1 = new ExitGate("1");
        ExitGate exitGate2 = new ExitGate("2");

        // Simulating vehicle entry
        Vehicle car = new Vehicle("123", VehicleType.CAR);
        entryGate1.entry(car);

        Vehicle car2 = new Vehicle("234", VehicleType.CAR);
        Vehicle car3 = new Vehicle("345", VehicleType.CAR);

        Thread t1 = new Thread(() -> entryGate1.entry(car2));
        Thread t2 = new Thread(() -> entryGate2.entry(car3));
        t1.start();
        t2.start();

        Vehicle bike = new Vehicle("456", VehicleType.BIKE);
        entryGate1.entry(bike);

        Vehicle bike2 = new Vehicle("567", VehicleType.BIKE);
        entryGate2.entry(bike2);

        parkingLot.exit(car);
        entryGate1.entry(car3); // Allow car3 now

        Vehicle truck = new Vehicle("789", VehicleType.TRUCK);
        entryGate2.entry(truck);

        Vehicle truck2 = new Vehicle("890", VehicleType.TRUCK);
        entryGate2.entry(truck2);

        // Simulating vehicle exit
        parkingLot.exit(car2);
        parkingLot.exit(bike);
        parkingLot.exit(bike2);
        parkingLot.exit(truck);
    }
}

// IMP: Used AtomicBoolean with compare and set to ensure thread safety.
// This avoids blockage for other gates during the entry process.

// Further enhancements:
// 1. Use factory pattern to create gates & vehicles
// 2. Can use Spot/Floor manager
