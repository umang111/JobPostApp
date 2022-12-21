package com.companyapp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companyapp.app.entity.Company;
import com.companyapp.app.exceptions.ResourceNotFoundException;
import com.companyapp.app.service.companyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private companyService companyService;
	
	@PostMapping()
	public ResponseEntity<Void> addCompany(@RequestBody Company company){
		
		companyService.addCompnay(company);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/getCompany/{companyId}")
	public ResponseEntity<Company> getCompanyById(@PathVariable("companyId") Integer companyId) throws ResourceNotFoundException{
		Company getCompany=companyService.getCompanyById(companyId);
		return ResponseEntity.ok(getCompany);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Company>> findAllCompany(){
		
		List<Company> getAllCompany=companyService.findAllCompany();
		return ResponseEntity.ok(getAllCompany);
	}
}
