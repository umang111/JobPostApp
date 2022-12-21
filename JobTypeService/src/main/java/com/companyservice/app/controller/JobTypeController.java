package com.companyservice.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companyservice.app.entity.JobType;
import com.companyservice.app.exceptions.ResourceNotFoundException;
import com.companyservice.app.service.JobTypeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/type")
@Slf4j
public class JobTypeController {
	
	@Autowired
	private JobTypeService jobTypeService;

	@PostMapping()
	public ResponseEntity<Void> postJobType(@RequestBody JobType type) {
		jobTypeService.saveToDB(type);
		log.info("Job Type saved successfully");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<JobType>> getAllJobType(){
		log.info("Fetching all Type to DB");
		List<JobType> getAllJobType=jobTypeService.findAllJobType();
		return ResponseEntity.ok(getAllJobType);
	}
	
	@GetMapping("/findById/{typeId}")
	public ResponseEntity<JobType> findJobTypeByid(@PathVariable int typeId) throws ResourceNotFoundException {
		log.info("Finding Type by Id: "+typeId);
		JobType findById=jobTypeService.findTypaById(typeId);
		return ResponseEntity.ok(findById);
	}
	
	@PutMapping("/update/{typeId}")
	public ResponseEntity<Void> updateJobType(@PathVariable("typeId") Integer typeId, @RequestBody JobType type) throws ResourceNotFoundException{
		log.info("updating Job Type of Id:"+type);
		jobTypeService.updateType(typeId,type);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	//deletebyid
}










