package com.jobpost.app.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_post")
public class JobPost {

	@Id
	@Column(name = "job_id")
	private int jobId;
	
	@Column(name = "type_id")
	private int typeId;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name = "job_discription")
	private String jobDiscription;
	
	@Column(name = "work_experience")
	private String workExperience;
	
	@Column(name = "posted_date")
	private LocalDateTime postedDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "job_id", name = "job_id")
	private List<JobLocations> jobLocations;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "job_id", name = "job_id")
	private List<Skills> requiredSkills;
	
	@PrePersist
	void receivedTime() {
		this.postedDate = LocalDateTime.now();
	}
}
