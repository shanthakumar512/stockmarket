package com.estock.market.company.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stock {
	
	private String id;
	
	private String companyCode;
	
	private Double stockPrice;
	
	private long date;

}
