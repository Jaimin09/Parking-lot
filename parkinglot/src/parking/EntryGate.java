package parking;

import model.GateType;
import model.Vehicle;

public class EntryGate extends Gate {
    public EntryGate(String gateNumber) {
        super(gateNumber, GateType.ENTRY);
    }

    public void entry(Vehicle vehicle) {
        ParkingLot.getInstance().entry(vehicle);
    }
}
