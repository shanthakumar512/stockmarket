package net.mguenther.gtd.domain.event;

import java.util.Locale;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public class StockCreated extends StockEvent {

    
    private final String companyCode;
    
    private final String stockPrice;

    public StockCreated(final String eventId, final long timestamp, final String stockPrice, final String companyCode) {
        super(eventId, timestamp,companyCode);
        this.stockPrice = stockPrice;
        this.companyCode = companyCode;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "StockCreated{eventId=%s, stockId=%s, companyCode=%s}", getEventId(), getCompanyCode());
    }

	public String getStockPrice() {
		return stockPrice;
	}

	public String getCompanyCode() {
		return companyCode;
	}
}
