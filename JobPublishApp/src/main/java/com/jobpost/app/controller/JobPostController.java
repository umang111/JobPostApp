package com.jobpost.app.controller;

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

import com.jobpost.app.entity.JobPost;
import com.jobpost.app.exception.ResourceNotFoundException;
import com.jobpost.app.service.JobPostService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/job")
@Slf4j
public class JobPostController {

	@Autowired
	private JobPostService jobPostService;
	
	@PostMapping("/publish")
	public ResponseEntity<Void> publishJob(@RequestBody JobPost jobPost) {
		jobPostService.publishJob(jobPost);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/findById/{jobId}")
	public ResponseEntity<JobPost> getJobPostById(@PathVariable("jobId") Integer jobId) throws ResourceNotFoundException {
		
		JobPost getJobPost=jobPostService.getJobById(jobId);
		return ResponseEntity.ok(getJobPost);
	}
	
	@GetMapping("/findByJobLocation/{jobLocation}")
	public ResponseEntity<List<JobPost>> getJobByJobLocation(@PathVariable("jobLocation") String jobLocation){
		
		List<JobPost> JobByJobLocation=jobPostService.getJobByJobLocation(jobLocation);
		return ResponseEntity.ok(JobByJobLocation);
	}
	
	@GetMapping("/findByWorkExperience/{workExperience}")
	public ResponseEntity<List<JobPost>> getJobByWorkExperience(@PathVariable("workExperience") String workExperience){
		
		List<JobPost> JobByWorkExperience=jobPostService.getJobByWorkExperience(workExperience);
		return ResponseEntity.ok(JobByWorkExperience);
	}
	
	@GetMapping("/findBySkill/{skill}")
	public ResponseEntity<List<JobPost>> getJobByskill(@PathVariable("skill") String skill){
		
		List<JobPost> JobByskill=jobPostService.getJobByskill(skill);
		return ResponseEntity.ok(JobByskill);
	}
}






























