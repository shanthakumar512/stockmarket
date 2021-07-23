package net.mguenther.gtd.domain.commands;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
abstract public class StockCommand {

    private final String companyCode;
    
    
	public StockCommand(String companyCode) {
		super();
		this.companyCode = companyCode;
	}


	/**
	 * @return the companyId
	 */
	public String getCompanyCode() {
		return companyCode;
	}

}
