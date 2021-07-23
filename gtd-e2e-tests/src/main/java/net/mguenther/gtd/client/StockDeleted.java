package net.mguenther.gtd.client;

import java.util.Locale;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public class StockDeleted{
	
	   private final String companyCode;

    public StockDeleted(final String comapnyCode) {
    	this.companyCode=comapnyCode;
	}
    
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "StockDeleted{ companyCode=%s}", getCompanyCode());
    }

	public String getCompanyCode() {
		return companyCode;
	}
}
