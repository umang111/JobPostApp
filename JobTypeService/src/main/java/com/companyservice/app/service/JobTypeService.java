package com.companyservice.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companyservice.app.entity.JobType;
import com.companyservice.app.exceptions.ResourceNotFoundException;
import com.companyservice.app.repository.JobTypeRepository;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

@Service
@Slf4j
public class JobTypeService {

	@Autowired
	private JobTypeRepository jobTypeRepository;
	
	public void saveToDB(JobType type) {
		jobTypeRepository.save(type);
		log.info("Job Type saved successfully");
	}

	public List<JobType> findAllJobType() {
		List<JobType> getAllJobType=jobTypeRepository.findAll();
		return getAllJobType;
	}

	public JobType findTypaById(int typeId) throws ResourceNotFoundException {
		
		Optional<JobType> findtypeById=jobTypeRepository.findById(typeId);
		if (!findtypeById.isPresent()) {
			throw new ResourceNotFoundException("Job Type not found for id: "+typeId);
		}
		JobType jobType=new JobType();
		jobType.setTypeId(typeId);
		jobType.setType(findtypeById.get().getType());
		jobType.setAboutType(findtypeById.get().getAboutType());
		return jobType;
	}

	public void updateType(Integer typeId, JobType type) throws ResourceNotFoundException {

		Optional<JobType> findtypeById=jobTypeRepository.findById(typeId);
		if (!findtypeById.isPresent()) {
			throw new ResourceNotFoundException("Job Type not found for id: "+typeId);
		}
		JobType jobType=new JobType();
		jobType.setTypeId(typeId);
		jobType.setType(type.getType());
		jobType.setAboutType(type.getAboutType());
		jobTypeRepository.save(jobType);
	}


}
