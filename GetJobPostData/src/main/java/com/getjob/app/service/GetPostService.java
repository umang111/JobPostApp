package com.getjob.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getjob.app.companyFeign.CompanyFeign;
import com.getjob.app.dtos.Company;
import com.getjob.app.dtos.GetJob;
import com.getjob.app.dtos.JobPost;
import com.getjob.app.dtos.JobType;
import com.getjob.app.exception.ResourceNotFoundException;
import com.getjob.app.jobpostFeign.JobPostFeign;
import com.getjob.app.typeFeign.TypeFeign;

@Service
public class GetPostService {

	@Autowired
	private JobPostFeign jobPostFeign;

	@Autowired
	private CompanyFeign companyFeign;

	@Autowired
	private TypeFeign typeFeign;

	public GetJob getJobDetailsById(Integer jobId) throws ResourceNotFoundException {

		GetJob getJobDetails = new GetJob();

		JobPost JobPost = getJobPost(jobId);
		Company company = getCompanyDetails(JobPost.getCompanyId());
		JobType jobType = getJobType(JobPost.getTypeId());

		getJobDetails.setJobPost(JobPost);
		getJobDetails.setCompanyDetails(company);
		getJobDetails.setJobType(jobType);

		return getJobDetails;
	}

	private JobPost getJobPost(Integer jobId) throws ResourceNotFoundException {
		JobPost jobPost = jobPostFeign.getJobPostById(jobId);
		if (!Optional.of(jobPost).isPresent()) {
			throw new ResourceNotFoundException("Job Post not found for Id: " + jobId);
		}
		return jobPost;
	}

	private Company getCompanyDetails(Integer companyId) throws ResourceNotFoundException {
		
		Company company = companyFeign.getCompanyById(companyId);
		if (!Optional.of(company).isPresent()) {
			throw new ResourceNotFoundException("Company not found for Id: " + companyId);
		}
		return company;
	}

	private JobType getJobType(Integer typeId) throws ResourceNotFoundException {

		JobType jobType = typeFeign.findJobTypeByid(typeId);
		if (!Optional.of(jobType).isPresent()) {
			throw new ResourceNotFoundException("Job Type not found for Id: " + typeId);
		}
		return jobType;
	}

	public List<GetJob> getJobByLocation(String jobLocation) throws ResourceNotFoundException {

		List<JobPost> getAllPosts = jobPostFeign.getJobByJobLocation(jobLocation);
		List<GetJob> getAllJobs = getAllPosts.stream().map(oneJobPost -> {
			try {
				return setDataForGetJob(oneJobPost);
			} catch (ResourceNotFoundException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList());
		return getAllJobs;
	}

	private GetJob setDataForGetJob(JobPost oneJobPost) throws ResourceNotFoundException {

		GetJob getJob = new GetJob();
		getJob.setJobPost(oneJobPost);
		Company company = getCompanyDetails(oneJobPost.getCompanyId());
		JobType jobType = getJobType(oneJobPost.getTypeId());
		getJob.setCompanyDetails(company);
		getJob.setJobType(jobType);
		return getJob;
	}

}
