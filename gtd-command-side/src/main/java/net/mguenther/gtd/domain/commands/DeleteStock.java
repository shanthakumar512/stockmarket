package net.mguenther.gtd.domain.commands;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public class DeleteStock extends StockCommand {

    public DeleteStock(final String comapnyCode) {
    	super(comapnyCode);
    }

	@Override
	public String toString() {
		return "DeleteStock [getCompanyCode()=" + getCompanyCode() + "]";
	}


}
