package model;

public enum VehicleType {
    CAR(2),
    BIKE(1),
    TRUCK(3);

    private int size;

    VehicleType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
