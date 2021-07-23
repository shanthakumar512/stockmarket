package net.mguenther.gtd.client;

import java.util.Locale;
import java.util.UUID;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public class StockCreate{

    
    private final String companyCode;
    
    private final String stockPrice;
    
    

    public StockCreate(final String eventId, final long timestamp, final String stockPrice, final String companyCode) {
        this.stockPrice = stockPrice;
        this.companyCode = companyCode;
    }


    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "StockCreated{eventId=%s, getStockPrice=%s, companyCode=%s}", getStockPrice(), getCompanyCode());
    }

	public String getStockPrice() {
		return stockPrice;
	}

	public String getCompanyCode() {
		return companyCode;
	}
}
