package net.mguenther.gtd.api;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public class CreateStockRequest {

    private String companyCode;
    
    private String comapnyStockPrice;

	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Override
	public String toString() {
		return "CreateStockRequest [companyCode=" + companyCode + ", comapnyStockPrice=" + comapnyStockPrice + "]";
	}

	/**
	 * @return the comapnyStockPrice
	 */
	public String getComapnyStockPrice() {
		return comapnyStockPrice;
	}

	/**
	 * @param comapnyStockPrice the comapnyStockPrice to set
	 */
	public void setComapnyStockPrice(String comapnyStockPrice) {
		this.comapnyStockPrice = comapnyStockPrice;
	}
     
}
