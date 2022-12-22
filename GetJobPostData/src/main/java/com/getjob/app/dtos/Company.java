package com.getjob.app.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	private int companyId;
	private String companyName;
	private String companySize;
	
	private List<Companylocation> companyLocations;
}
