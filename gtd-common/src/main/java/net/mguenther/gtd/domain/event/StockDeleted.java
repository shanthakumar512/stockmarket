package net.mguenther.gtd.domain.event;

import java.util.Locale;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public class StockDeleted extends StockEvent {

    public StockDeleted(final String comapnyCode) {
		super(comapnyCode);
	}

    public StockDeleted(final String eventId, final long timestamp, final String comapnyCode) {
        super(eventId,timestamp,comapnyCode);
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "StockDeleted{eventId=%s, companyCode=%s}", getEventId(), getComapnyCode());
    }
}
