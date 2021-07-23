package net.mguenther.gtd.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.mguenther.gtd.domain.event.StockCreated;
import net.mguenther.gtd.domain.event.StockDeleted;
import net.mguenther.gtd.domain.event.StockEvent;

@Entity
@Table(name="STOCK" )
public class Stock implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6696979311313086078L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String companyCode;
	
	private String stockPrice;
	
	private long date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public void project(final StockEvent event) {
		if (event instanceof StockDeleted)
			project((StockDeleted) event);
		else
			throw new IllegalStateException("Unrecognized event: " + event.toString());
	}
  
  private void project(final StockDeleted event) {
    this.date = event.getTimestamp();
}
}
