package com.getjob.app.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPost {

	private int jobId;
	private int typeId;
	private int companyId;
	private String jobTitle;
	private String jobDiscription;
	private String workExperience;
	private LocalDateTime postedDate;
	
	private List<JobLocations> jobLocations;
	
	private List<Skills> requiredSkills;
	
}
