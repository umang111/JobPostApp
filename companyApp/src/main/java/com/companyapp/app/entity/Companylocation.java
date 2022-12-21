package com.companyapp.app.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company_locations")
public class Companylocation {

	@Id
	@Column(name = "location_id")
	private int locationId;
	
	@Column(name = "location")
	private String location;
	
	@ManyToOne()
	@JoinColumn(referencedColumnName = "company_id",name = "company_id")
	@JsonIgnore
	private Company company;
	
	@PrePersist
	void receivedTime() {
		this.locationId = Integer.parseInt(((UUID.randomUUID().toString()).replaceAll("[^1-9]", "")).substring(0,4));
	}
}
