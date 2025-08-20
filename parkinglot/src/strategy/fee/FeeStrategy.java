package strategy.fee;

import model.Ticket;

public interface FeeStrategy {
    float calculateFee(Ticket ticket);
}