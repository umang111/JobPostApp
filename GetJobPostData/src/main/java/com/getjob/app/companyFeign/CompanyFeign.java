package com.getjob.app.companyFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.getjob.app.dtos.Company;


@FeignClient(name = "company-service",url = "http://localhost:9001/company")
public interface CompanyFeign {

	@GetMapping("/getCompany/{companyId}")
	public Company getCompanyById(@PathVariable("companyId") Integer companyId);
}
