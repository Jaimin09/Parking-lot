package strategy.fee;

import java.time.Duration;
import model.Ticket;
import java.time.Instant;

public class HourlyFeeStrategy implements FeeStrategy {
    private static final float RATE = 10.0f;

    @Override
    public float calculateFee(Ticket ticket) {
        Instant entryTime = ticket.getEntryTime();
        Instant exitTime = ticket.getExitTime();

        long hours = Duration.between(entryTime, exitTime).toMillis()*100;
        return hours * RATE;
    }
}