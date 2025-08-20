package model;

import java.time.Instant;

public class Ticket {
    String ticketId;
    String vehicleNumber;
    VehicleType vehicleType;
    Spot spot;
    Instant entryTime;
    Instant exitTime;

    public Ticket(String ticketId, String vehicleNumber, VehicleType vehicleType, Spot spot, Instant entryTime) {
        this.ticketId = ticketId;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.spot = spot;
        this.entryTime = entryTime;
        this.exitTime = null; // Exit time is initially null
    }

    public void setExitTime(Instant exitTime) {
        this.exitTime = exitTime;
    }

    public Instant getEntryTime() {
        return entryTime;
    }

    public Instant getExitTime() {
        return exitTime;
    }

    public Spot getSpot() {
        return spot;
    }
}