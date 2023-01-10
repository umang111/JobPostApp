package com.getjob.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getjob.app.dtos.Company;
import com.getjob.app.dtos.GetJob;
import com.getjob.app.dtos.JobPost;
import com.getjob.app.dtos.JobType;
import com.getjob.app.exception.ResourceNotFoundException;
import com.getjob.app.service.GetPostService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/getpost")
@Slf4j
public class GetPostController {

	@Autowired
	private GetPostService getPostService;

	@GetMapping("/{jobId}")
	@CircuitBreaker(name = "getJobByIdBreaker", fallbackMethod = "getJobByIdFallBackMethod")
	public ResponseEntity<GetJob> getJobDetailsById(@PathVariable("jobId") Integer jobId)
			throws ResourceNotFoundException {
		GetJob getJobDetails = getPostService.getJobDetailsById(jobId);
		return ResponseEntity.ok(getJobDetails);
	}

	@GetMapping("/findByJobLocation/{jobLocation}")
	public ResponseEntity<List<GetJob>> getJobByLocation(@PathVariable("jobLocation") String jobLocation)
			throws ResourceNotFoundException {
		List<GetJob> getAll = getPostService.getJobByLocation(jobLocation);
		return ResponseEntity.ok(getAll);
	}
	
	public ResponseEntity<GetJob> getJobByIdFallBackMethod(Integer jobId, Exception ex){
		
		log.info("Fallback is executed because service is down : ",ex.getMessage());
		GetJob getjob=new GetJob();
		
		JobPost jobPost=new JobPost();
		jobPost.setCompanyId(0);
		jobPost.setJobDiscription("job discription");
		jobPost.setJobId(0);
		jobPost.setJobLocations(null);
		jobPost.setJobTitle("jobtitle");
		jobPost.setPostedDate(null);
		jobPost.setRequiredSkills(null);
		jobPost.setTypeId(0);
		jobPost.setWorkExperience("work experience");
		
		Company company=new Company();
		company.setCompanyId(0);
		company.setCompanyLocations(null);
		company.setCompanyName("company name");
		company.setCompanySize(null);
		
		JobType jobType=new JobType();
		jobType.setTypeId(0);
		jobType.setType("type");
		jobType.setAboutType("about type");
		
		getjob.setCompanyDetails(company);
		getjob.setJobPost(jobPost);
		getjob.setJobType(jobType);
		
		return ResponseEntity.ok(getjob);

	}
}
