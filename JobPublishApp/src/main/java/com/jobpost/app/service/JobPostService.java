package com.jobpost.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobpost.app.entity.JobLocations;
import com.jobpost.app.entity.JobPost;
import com.jobpost.app.entity.Skills;
import com.jobpost.app.exception.ResourceNotFoundException;
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

	public JobPost getJobById(Integer jobId) throws ResourceNotFoundException {

		Optional<JobPost> jobPostOptional=jobPostRepository.findById(jobId);
		if (!jobPostOptional.isPresent()) {
			throw new ResourceNotFoundException("Job Not found with Id: "+jobId);
		}
		List<JobLocations> jobLocationsList=jobPostOptional.get().getJobLocations()
				.stream().map(oneLocation->getLocationDetails(oneLocation)).collect(Collectors.toList());
		
		List<Skills> skillsList=jobPostOptional.get().getRequiredSkills()
				.stream().map(oneSkill->getSkillsDetails(oneSkill)).collect(Collectors.toList());
		
		JobPost getJobPost=new JobPost();
		getJobPost.setJobId(jobPostOptional.get().getJobId());
		getJobPost.setTypeId(jobPostOptional.get().getTypeId());
		getJobPost.setCompanyId(jobPostOptional.get().getCompanyId());
		getJobPost.setJobTitle(jobPostOptional.get().getJobTitle());
		getJobPost.setJobDiscription(jobPostOptional.get().getJobDiscription());
		getJobPost.setWorkExperience(jobPostOptional.get().getWorkExperience());
		getJobPost.setPostedDate(jobPostOptional.get().getPostedDate());
		getJobPost.setJobLocations(jobLocationsList);
		getJobPost.setRequiredSkills(skillsList);
		return getJobPost;
	}

	private JobLocations getLocationDetails(JobLocations oneLocation) {
		JobLocations jobLocation = new JobLocations();
		jobLocation.setJobLocationId(oneLocation.getJobLocationId());
		jobLocation.setJobLocation(oneLocation.getJobLocation());
		return jobLocation;
	}
	
	private Skills getSkillsDetails(Skills oneSkill) {

		Skills skill =new Skills();
		skill.setSkillId(oneSkill.getSkillId());
		skill.setSkill(oneSkill.getSkill());
		return skill;
	}

	public List<JobPost> getJobByJobLocation(String jobLocation) {
		
		List<JobPost> getJobByLocation=jobPostRepository.findAllByJobLocationsJobLocationIgnoreCase(jobLocation);
		return getJobByLocation;
	}

	public List<JobPost> getJobByWorkExperience(String workExperience) {
		
		List<JobPost> getJobworkExperience=jobPostRepository.findAllByWorkExperienceIgnoreCase(workExperience);
		return getJobworkExperience;
	}

	public List<JobPost> getJobByskill(String skill) {
		
		List<JobPost> getJobByskill=jobPostRepository.findAllByRequiredSkillsSkillIgnoreCase(skill);
		return getJobByskill;
	}
}















