package com.getjob.app.jobpostFeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.getjob.app.dtos.JobPost;

@FeignClient(name = "job-publish-app", url = "http://localhost:8084/job")
public interface JobPostFeign {

	@GetMapping("/findById/{jobId}")
	public JobPost getJobPostById(@PathVariable("jobId") Integer jobId);

	@GetMapping("/findByJobLocation/{jobLocation}")
	public List<JobPost> getJobByJobLocation(@PathVariable("jobLocation") String jobLocation);
}