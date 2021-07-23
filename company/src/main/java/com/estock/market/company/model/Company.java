/**
 * 
 */
package com.estock.market.company.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



/**
 * @author User
 *
 */
//@Document(collection="companyDetails")
@Entity
@Table(	name = "company", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "companyCode")})
public class Company {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String companyCode;
	
	private String companyName;
	private String companyCEO;
	private String companyWebsite;
	
	private Integer companyTurnOver;
	private String listedExchange;
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
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the companyCEO
	 */
	public String getCompanyCEO() {
		return companyCEO;
	}
	/**
	 * @param companyCEO the companyCEO to set
	 */
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	/**
	 * @return the companyWebsite
	 */
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	/**
	 * @param companyWebsite the companyWebsite to set
	 */
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	/**
	 * @return the companyTurnOver
	 */
	public Integer getCompanyTurnOver() {
		return companyTurnOver;
	}
	/**
	 * @param companyTurnOver the companyTurnOver to set
	 */
	public void setCompanyTurnOver(Integer companyTurnOver) {
		this.companyTurnOver = companyTurnOver;
	}
	/**
	 * @return the listedExchange
	 */
	public String getListedExchange() {
		return listedExchange;
	}
	/**
	 * @param listedExchange the listedExchange to set
	 */
	public void setListedExchange(String listedExchange) {
		this.listedExchange = listedExchange;
	}
	
}
