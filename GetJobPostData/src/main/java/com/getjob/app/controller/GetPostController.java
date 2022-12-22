package com.getjob.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getjob.app.dtos.GetJob;
import com.getjob.app.exception.ResourceNotFoundException;
import com.getjob.app.service.GetPostService;

@RestController
@RequestMapping("/getpost")
public class GetPostController {

	@Autowired
	private GetPostService getPostService;
	
	@GetMapping("/{jobId}")
	public ResponseEntity<GetJob> getJobDetailsById(@PathVariable("jobId") Integer jobId ) throws ResourceNotFoundException {
		GetJob getJobDetails=getPostService.getJobDetailsById(jobId);
		return ResponseEntity.ok(getJobDetails);
	}
	
	
}
