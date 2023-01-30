package com.getjob.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.getjob.app.dtos.Company;
import com.getjob.app.dtos.Companylocation;
import com.getjob.app.dtos.GetJob;
import com.getjob.app.dtos.JobLocations;
import com.getjob.app.dtos.JobPost;
import com.getjob.app.dtos.JobType;
import com.getjob.app.dtos.Skills;
import com.getjob.app.exception.ResourceNotFoundException;
import com.getjob.app.service.GetPostService;

@WebMvcTest
class GetPostControllerTest {

	@MockBean
	private GetPostService getPostService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GetPostController getPostController;

	private JobPost jobPost;

	private JobLocations jobLocations;

	private Skills skill;

	private Company company;

	private JobType jobType;

	private GetJob getJob;

	@BeforeEach
	public void setup() {
		jobPost = getJobPost();
		jobLocations = getJobLocation();
		jobType = getJobType();
		skill = getskill();
		company = getCompany();
		getJob = GetgetJob();

	}

	@Test
	public void test_getJobDetailsById() throws Exception, ResourceNotFoundException {
		when(getPostService.getJobDetailsById(11)).thenReturn(GetgetJob());
		mockMvc.perform(get("/getpost/11"));
	}

	@Test
	public void test_getJobByLocation() throws Exception, ResourceNotFoundException {

		GetJob getJob = new GetJob();
		getJob = GetgetJob();
		List<GetJob> getJobList = new ArrayList<>();
		getJobList.add(getJob);
		getJobList.add(getJob);

		when(getPostService.getJobByLocation("Noida")).thenReturn(getJobList);
		mockMvc.perform(get("/getpost/findByJobLocation/Noida"));
	}
	
	@Test
	public void test_getJobByIdFallBackMethod() throws Exception, ResourceNotFoundException {
		getPostController.getJobByIdFallBackMethod(0, null);
	}

	private JobPost getJobPost() {

		JobPost jobPost = new JobPost();
		jobPost.setJobId(11);
		jobPost.setTypeId(100);
		jobPost.setCompanyId(20);
		jobPost.setJobTitle("title");
		jobPost.setJobDiscription("Discription");
		jobPost.setWorkExperience("fresher");
		jobPost.setPostedDate(LocalDateTime.now());

		List<JobLocations> jobLocations = new ArrayList<>();
		jobLocations.add(getJobLocation());

		List<Skills> skills = new ArrayList<>();
		skills.add(getskill());

		return jobPost;
	}

	private JobLocations getJobLocation() {

		JobLocations listJobLocation = new JobLocations();
		listJobLocation.setJobLocationId(12);
		listJobLocation.setJobLocation("Noida");

		return listJobLocation;
	}

	private Skills getskill() {

		Skills skills = new Skills();
		skills.setSkillId(6);
		skills.setSkill("java");

		return skills;
	}

	private Company getCompany() {
		Company company = new Company();
		List<Companylocation> companyLocationList = new ArrayList<>();
		company.setCompanyId(17);
		company.setCompanyName("Zensar");
		companyLocationList.add(getCompanyLocation());
		company.setCompanyLocations(companyLocationList);
		company.setCompanySize("10000");
		company.setCompanyLocations(companyLocationList);
		return company;

	}

	private Companylocation getCompanyLocation() {

		Companylocation companyLocation = new Companylocation();
		companyLocation.setLocationId(16);
		companyLocation.setLocation("Pune");
		return companyLocation;
	}

	private JobType getJobType() {

		JobType jobType = new JobType();
		jobType.setTypeId(18);
		jobType.setType("Technical");
		jobType.setAboutType("Technical Jobs");
		return jobType;
	}

	private GetJob GetgetJob() {
		GetJob getjob = new GetJob();
		getjob.setCompanyDetails(company);
		getjob.setJobPost(jobPost);
		getjob.setJobType(jobType);

		return getjob;
	}

}
