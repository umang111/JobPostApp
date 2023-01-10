package com.getjob.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetJob {

	private JobPost jobPost;
	private Company companyDetails;
	private JobType jobType;
}
