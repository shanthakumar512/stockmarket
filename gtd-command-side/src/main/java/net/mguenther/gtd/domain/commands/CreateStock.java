package net.mguenther.gtd.domain.commands;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public class CreateStock extends StockCommand {

    
	private final String stockPrice;

    public CreateStock(final String stockPrice, final String companyCode) {
        super(companyCode);
        this.stockPrice = stockPrice;
    }

	public String getStockPrice() {
		return stockPrice;
	}
}
