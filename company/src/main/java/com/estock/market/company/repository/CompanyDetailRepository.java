package com.estock.market.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estock.market.company.model.Company;

@Repository
public interface CompanyDetailRepository extends JpaRepository<Company,String> {
	
	Company findByCompanyCode(String companyCode);
	
	void deleteByCompanyCode(String companyCode);

}
