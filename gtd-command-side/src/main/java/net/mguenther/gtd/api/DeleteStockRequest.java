package net.mguenther.gtd.api;


/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public class DeleteStockRequest {


	private String  companyCode;
	

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
		return "DeleteStockRequest [companyCode=" + companyCode + "]";
	}

   

}
