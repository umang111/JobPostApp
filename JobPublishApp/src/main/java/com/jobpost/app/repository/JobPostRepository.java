package com.jobpost.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobpost.app.entity.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

	List<JobPost> findAllByJobLocationsJobLocationIgnoreCase(String jobLocation);

	List<JobPost> findAllByWorkExperienceIgnoreCase(String workExperience);
	
	List<JobPost> findAllByRequiredSkillsSkillIgnoreCase(String skill);

}
