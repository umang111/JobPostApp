package com.companyapp.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companyapp.app.entity.Company;
import com.companyapp.app.entity.Companylocation;
import com.companyapp.app.exceptions.ResourceNotFoundException;
import com.companyapp.app.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class companyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	public void addCompnay(Company company) {
		company.setCompanyId(Integer.parseInt(((UUID.randomUUID().toString()).replaceAll("[^1-9]", "")).substring(0,4)));
		companyRepository.save(company);
		log.info("company saved successfully");
		
	}

	public Company getCompanyById(Integer companyId) throws ResourceNotFoundException {
		Optional<Company> getCompany=companyRepository.findById(companyId);
		if (!getCompany.isPresent()) {
			throw new ResourceNotFoundException("company Data not found for id :" +companyId);
		}
		Company company =new Company();
		company.setCompanyId(companyId);
		company.setCompanyName(getCompany.get().getCompanyName());
		company.setCompanySize(getCompany.get().getCompanySize());
		List<Companylocation> GetLiatOfcompanyLocation=getCompany.get().getCompanyLocations()
		.stream().map(oneCompanyLocation->SetLocationData(oneCompanyLocation)).collect(Collectors.toList());
		company.setCompanyLocations(GetLiatOfcompanyLocation);
		return company;
	}

	private Companylocation SetLocationData(Companylocation oneCompanyLocation) {
		
		Companylocation companylocation=new Companylocation();
		companylocation.setLocationId(oneCompanyLocation.getLocationId());
		companylocation.setLocation(oneCompanyLocation.getLocation());
		return companylocation;
	}

	public List<Company> findAllCompany() {
		List<Company> listOfCompany=companyRepository.findAll();
		return listOfCompany;
	}

}
