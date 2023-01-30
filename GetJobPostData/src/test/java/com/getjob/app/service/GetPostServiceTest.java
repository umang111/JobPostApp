package com.getjob.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.getjob.app.companyFeign.CompanyFeign;
import com.getjob.app.dtos.Company;
import com.getjob.app.dtos.Companylocation;
import com.getjob.app.dtos.GetJob;
import com.getjob.app.dtos.JobLocations;
import com.getjob.app.dtos.JobPost;
import com.getjob.app.dtos.JobType;
import com.getjob.app.dtos.Skills;
import com.getjob.app.exception.ResourceNotFoundException;
import com.getjob.app.jobpostFeign.JobPostFeign;
import com.getjob.app.typeFeign.TypeFeign;

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
class GetPostServiceTest {

	public static final int JOB_ID = 11;
	public static final int JOB_TYPE_ID = 100;
	public static final int COMPANY_ID = 20;
	public static final int COMPANY_LOCATION_ID = 20;
	public static final int JOB_LOCATION_ID = 12;
	public static final int SKILL_ID = 6;
	public static final String JOB_TITLE = "title";
	public static final String JOB_DISCRIPTION = "Discription";
	public static final String WORK_EXPERIENCE = "fresher";
	public static final String POSTED_DATE = null;
	public static final String JOB_LOCATION = "Noida";
	public static final String SKILL = "java";
	public static final String COMPANY_NAME = "Zensar";
	public static final String COMPANY_LOCATION = "Pune";
	public static final String COMPANY_SIZE = "10000+";
	public static final String JOB_TYPE = "Technical";
	public static final String ABOUT_TYPE = "Technical Job";

	@InjectMocks
	private GetPostService getPostService;

	@Mock
	private JobPostFeign jobPostFeign;

	@Mock
	private CompanyFeign companyFeign;

	@Mock
	private TypeFeign typeFeign;

	private JobPost jobPost;

	private Company company;

	private Companylocation companylocation;

	private GetJob getJob;

	private JobType jobType;

	@BeforeEach
	public void setup() {
		jobPost = getJobPostT();
		company = getCompany();
		companylocation = getCompanyLocation();
		jobType = getJobType();
		getJob = GetgetJob();
	}

	@Test
	public void test_getJobDetailsById() throws ResourceNotFoundException {

		when(jobPostFeign.getJobPostById(anyInt())).thenReturn(jobPost);
		when(companyFeign.getCompanyById(anyInt())).thenReturn(company);
		when(typeFeign.findJobTypeByid(anyInt())).thenReturn(jobType);

		GetJob getJob = getPostService.getJobDetailsById(JOB_ID);
		assertNotNull(getJob);

	}

	@Test
	public void test_getJobPost_success() throws ResourceNotFoundException {
		when(jobPostFeign.getJobPostById(anyInt())).thenReturn(jobPost);
		JobPost getJobPost = getPostService.getJobPost(JOB_ID);
		assertNotNull(getJobPost);
	}

	@Test
	public void test_getJobPost_failed() throws ResourceNotFoundException {
		when(jobPostFeign.getJobPostById(anyInt())).thenReturn(null);
		assertThrows(ResourceNotFoundException.class, () -> {
			getPostService.getJobPost(JOB_ID);
		});
	}

	@Test
	public void test_getCompanyDetails_success() throws ResourceNotFoundException {
		when(companyFeign.getCompanyById(anyInt())).thenReturn(company);
		Company company = getPostService.getCompanyDetails(COMPANY_ID);
		assertEquals(17, company.getCompanyId());
	}

	@Test
	public void test_getCompanyDetails_failed() throws ResourceNotFoundException {
		when(companyFeign.getCompanyById(anyInt())).thenReturn(null);
		assertThrows(ResourceNotFoundException.class, () -> {
			getPostService.getCompanyDetails(JOB_ID);
		});
	}

	@Test
	public void test_getJobType() throws ResourceNotFoundException {
		when(typeFeign.findJobTypeByid(anyInt())).thenReturn(jobType);
		JobType jobType = getPostService.getJobType(JOB_TYPE_ID);
		assertNotNull(jobType);
	}

	@Test
	public void test_getJobType_failed() throws ResourceNotFoundException {
		when(typeFeign.findJobTypeByid(anyInt())).thenReturn(null);
		assertThrows(ResourceNotFoundException.class, () -> {
			getPostService.getJobType(JOB_TYPE_ID);
		});
	}

	@Test
	public void test_getJobByLocation_success() throws ResourceNotFoundException {
		List<JobPost> jobPostList = new ArrayList<>();
		jobPostList.add(jobPost);
		when(jobPostFeign.getJobByJobLocation(COMPANY_LOCATION)).thenReturn(jobPostList);
		when(companyFeign.getCompanyById(COMPANY_ID)).thenReturn(company);
		when(typeFeign.findJobTypeByid(JOB_TYPE_ID)).thenReturn(jobType);
		List<GetJob> result_getJob = getPostService.getJobByLocation(COMPANY_LOCATION);
		assertNotNull(result_getJob);

	}

	@Test
	public void test_getJobByLocation_failed() throws ResourceNotFoundException {
		when(jobPostFeign.getJobByJobLocation(COMPANY_LOCATION)).thenReturn(Collections.singletonList(new JobPost()));
		assertThrows(RuntimeException.class, () -> {
			getPostService.getJobByLocation(COMPANY_LOCATION);
		});

	}

	private JobPost getJobPostT() {

		JobPost jobPost = new JobPost();
		jobPost.setJobId(JOB_ID);
		jobPost.setTypeId(JOB_TYPE_ID);
		jobPost.setCompanyId(COMPANY_ID);
		jobPost.setJobTitle(JOB_TITLE);
		jobPost.setJobDiscription(JOB_DISCRIPTION);
		jobPost.setWorkExperience(WORK_EXPERIENCE);
		jobPost.setPostedDate(LocalDateTime.now());

		List<JobLocations> jobLocations = new ArrayList<>();
		jobLocations.add(getJobLocation());

		List<Skills> skills = new ArrayList<>();
		skills.add(getskill());

		return jobPost;
	}

	private JobLocations getJobLocation() {

		JobLocations listJobLocation = new JobLocations();
		listJobLocation.setJobLocationId(JOB_LOCATION_ID);
		listJobLocation.setJobLocation(JOB_LOCATION);

		return listJobLocation;
	}

	private Skills getskill() {

		Skills skills = new Skills();
		skills.setSkillId(SKILL_ID);
		skills.setSkill(SKILL);

		return skills;
	}

	private Company getCompany() {
		Company company = new Company();
		List<Companylocation> companyLocationList = new ArrayList<>();
		company.setCompanyId(17);
		company.setCompanyName(COMPANY_NAME);
		company.setCompanyLocations(null);
		company.setCompanySize(COMPANY_SIZE);
		company.setCompanyLocations(companyLocationList);
		return company;

	}

	private Companylocation getCompanyLocation() {

		Companylocation companyLocation = new Companylocation();
		companyLocation.setLocationId(COMPANY_LOCATION_ID);
		companyLocation.setLocation(COMPANY_LOCATION);
		return companyLocation;
	}

	private JobType getJobType() {

		JobType jobType = new JobType();
		jobType.setTypeId(18);
		jobType.setType(JOB_TYPE);
		jobType.setAboutType(ABOUT_TYPE);
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
