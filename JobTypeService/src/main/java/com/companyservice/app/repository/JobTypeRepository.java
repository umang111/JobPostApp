package com.companyservice.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.companyservice.app.entity.JobType;

public interface JobTypeRepository extends JpaRepository<JobType, Integer>{

}
