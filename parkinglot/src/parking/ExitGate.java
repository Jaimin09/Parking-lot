package parking;

import model.GateType;
import model.Vehicle;

public class ExitGate extends  Gate {
    public ExitGate(String gateNumber) {
        super(gateNumber, GateType.EXIT);
    }

    public void exit(Vehicle vehicle) {
        ParkingLot.getInstance().exit(vehicle);
    }
}
