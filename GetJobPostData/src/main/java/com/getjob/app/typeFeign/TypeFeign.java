package com.getjob.app.typeFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.getjob.app.dtos.JobType;

@FeignClient(name = "type-service", url = "http://localhost:8084/type")
public interface TypeFeign {

	@GetMapping("/findById/{typeId}")
	public JobType findJobTypeByid(@PathVariable int typeId);
}