package com.companyapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.companyapp.app.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer>{

}
