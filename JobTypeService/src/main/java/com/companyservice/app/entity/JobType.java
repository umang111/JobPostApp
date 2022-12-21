package com.companyservice.app.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_type")
public class JobType {

	@Id
	@Column(name = "type_id")
	private int typeId;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "about")
	private String aboutType;
	
	@PrePersist
	void receivedTime() {
		this.typeId = Integer.parseInt(((UUID.randomUUID().toString()).replaceAll("[^1-9]", "")).substring(0,3));
	}
	
}
