package parking;

import model.GateType;

public class Gate {
    String gateNumber;
    GateType gateType;

    Gate(String gateNumber, GateType gateType) {
        this.gateNumber = gateNumber;
        this.gateType = gateType;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public GateType getGateType() {
        return gateType;
    }
}
