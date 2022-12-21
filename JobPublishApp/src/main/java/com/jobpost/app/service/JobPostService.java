package com.jobpost.app.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobpost.app.entity.JobPost;
import com.jobpost.app.repository.JobPostRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobPostService {

	@Autowired
	private JobPostRepository jobPostRepository;

	public void publishJob(JobPost jobPost) {

		jobPost.setJobId(Integer.parseInt(((UUID.randomUUID().toString()).replaceAll("[^1-9]", "")).substring(0,6)));
		jobPostRepository.save(jobPost);
		log.info("Job Saved Successfully");
	}

	public JobPost getJobById(Integer jobId) {

		return null;
	}
	
}
