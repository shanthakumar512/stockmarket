package com.estock.market.company.controllers;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.estock.market.company.dto.Stock;

@FeignClient(name="gtd-es-api-gateway")
public interface StockClient {
	
	@GetMapping("stock/getdetail/{companyCode}")
	public Stock retriveStockByCompanyCode(@PathVariable("companyCode") String companyCode);
	
	@GetMapping("/stock/getAlldetail")
	public List<Stock> retriveAllStocks();
	
	@DeleteMapping("stock/delete/{companyCode}")
	public boolean deleteStockByCompanyCode(@PathVariable("companyCode") String companyCode);
	

}
