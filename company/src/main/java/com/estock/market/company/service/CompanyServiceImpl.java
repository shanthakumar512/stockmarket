/**
 * 
 */
package com.estock.market.company.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.estock.market.company.controllers.StockClient;
import com.estock.market.company.dto.CompanyDetails;
import com.estock.market.company.dto.Stock;
import com.estock.market.company.model.Company;
import com.estock.market.company.repository.CompanyDetailRepository;

/**
 * @author User
 *
 */
@Component
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyDetailRepository companyRepository;
	
	@Autowired
	StockClient stockclient;
	
 
	@Override
	public boolean registerCompany(Company companyDetail) {
	// to do validation for min turnover
		{
			companyRepository.save(companyDetail);
			return true;
		}
	}

	@Override
	public List<CompanyDetails> getAllCompanyDetails() {
		// TODO Auto-generated method stub
		
		List<Company> companyList = companyRepository.findAll();
		
		List<Stock>	stockDetails = new ArrayList<>();
		
		stockDetails = stockclient.retriveAllStocks();
		Map<String, Stock> stocksMap = stockDetails.stream().collect(Collectors.toMap(Stock::getCompanyCode, Function.identity()));
		
		List<CompanyDetails> companyDetails = new ArrayList<>();
		
		
		
		if(!CollectionUtils.isEmpty(companyList) && !CollectionUtils.isEmpty(stockDetails)) {
			
			for(Company companyDetail: companyList) {
				
				companyDetails.add(buildCompanyDetails(companyDetail, stocksMap.get(companyDetail.getCompanyCode())));
				
			}
				
			return 	companyDetails;
		}
 		
		return null;
	}

	@Override
	public CompanyDetails getCompanyDetailBycComapnyCode(String companyCode) {
		
		Company company = companyRepository.findByCompanyCode(companyCode);
		
		Stock stock = stockclient.retriveStockByCompanyCode(companyCode);
		
		CompanyDetails companyDetail =  buildCompanyDetails(company,stock);
				
		return companyDetail;
	}

	private CompanyDetails buildCompanyDetails(Company company, Stock stock) {
		return CompanyDetails.builder()
							.companyCEO(company.getCompanyCEO())
							.companyCode(company.getCompanyCode())
							.companyName(company.getCompanyName())
							.companyTurnOver(company.getCompanyTurnOver())
							.companyWebsite(company.getCompanyWebsite())
							.listedExchange(company.getListedExchange())
							.latestStockPrice(stock.getStockPrice()).build();
	}

	@Override
	public boolean deleteCompany(String companyCode) throws Exception {
		
		boolean stockDeleted = stockclient.deleteStockByCompanyCode(companyCode);
		if(stockDeleted) {
			companyRepository.deleteByCompanyCode(companyCode);
			return true;
		} else {
			throw new Exception();
		}
	
	}

}
