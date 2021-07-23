package com.estock.market.company.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyDetails {

	private String companyCode;
	private String companyName;
	private String companyCEO;
	private String companyWebsite;
	private Integer companyTurnOver;
	private String listedExchange;	
	private Double latestStockPrice;
	
}
