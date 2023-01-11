package com.getjob.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobType {

	private int typeId;
	private String type;
	private String aboutType;
}
