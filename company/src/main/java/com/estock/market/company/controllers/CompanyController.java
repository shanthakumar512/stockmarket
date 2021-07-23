/**
 * 
 */
package com.estock.market.company.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estock.market.company.dto.CompanyDetails;
import com.estock.market.company.model.Company;
import com.estock.market.company.service.CompanyService;

/**
 * @author User
 *
 */

@RestController
@RequestMapping("/api/v1.0/market")
public class CompanyController {
	
	
	@Autowired
	CompanyService companyService;
	
	@PostMapping("/company/register")
	public ResponseEntity<String> registerCompany (@RequestBody Company companyDetail){
		companyService.registerCompany(companyDetail);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/company/delete/{companyCode}")
	public ResponseEntity<String> deleteCompany (@RequestParam String companyCode) throws Exception{
		companyService.deleteCompany(companyCode);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/company/getall")
	public ResponseEntity<List<CompanyDetails>> findAllCompanyDetails (){
		
		companyService.getAllCompanyDetails();
		List<CompanyDetails> companyDetails= null;
		return new ResponseEntity<>(companyDetails, HttpStatus.OK);
		
	}
	
	@GetMapping("/company/info/{companyCode}")
	public ResponseEntity<CompanyDetails> findCompanyByCompanyCode (@RequestParam String companyCode){
		
		companyService.getAllCompanyDetails();
		CompanyDetails companyDetails =null;
		return new ResponseEntity<>(companyDetails,HttpStatus.OK);
		
	}
	

}
