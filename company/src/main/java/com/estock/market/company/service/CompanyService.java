package com.estock.market.company.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.estock.market.company.dto.CompanyDetails;
import com.estock.market.company.model.Company;

@Component
public interface CompanyService {
	
	public boolean registerCompany(Company companyDetail);
	
	public List<CompanyDetails> getAllCompanyDetails();
	
	public CompanyDetails getCompanyDetailBycComapnyCode(String companyCode);
	
	public boolean deleteCompany(String companyCode) throws Exception;

}
