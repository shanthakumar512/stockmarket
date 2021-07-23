package net.mguenther.gtd.client;

import javax.persistence.Entity;
import javax.persistence.Id;

import net.mguenther.gtd.domain.event.StockCreated;

@Entity
public class Stock {
	
	@Id
	private String id;
	
	private String companyCode;
	
	private String stockPrice;
	
	private long date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
	
	Stock(){
		
	}

	Stock( final StockCreated event){
		 this.companyCode= event.getComapnyCode();
		 this.date = event.getTimestamp();
		 this.stockPrice = event.getStockPrice();
	}
}
