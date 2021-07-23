package net.mguenther.gtd.domain.event;

import java.util.UUID;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
abstract public class StockEvent {

    private final String eventId;
    private final long timestamp;
    private final String comapnyCode;
    

    public StockEvent( final String comapnyCode) {
        this(UUID.randomUUID().toString().substring(0, 7), System.currentTimeMillis(), comapnyCode);
    }

    public StockEvent(final String eventId, final long timestamp, final String comapnyCode) {
        this.eventId = eventId;
        this.timestamp = timestamp;
        this.comapnyCode = comapnyCode;
    }

    public String getEventId() {
        return eventId;
    }

    public long getTimestamp() {
        return timestamp;
    }

	public String getComapnyCode() {
		return comapnyCode;
	}

}
