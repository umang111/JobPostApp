package com.companyapp.app.entity;

import java.util.List;
import java.util.UUID;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company_details")
public class Company {

	@Id
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_size")
	private String companySize;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "company_id",name = "company_id")
	private List<Companylocation> companyLocations;
	
	@PrePersist
	void receivedTime() {
		this.companyId = Integer.parseInt(((UUID.randomUUID().toString()).replaceAll("[^1-9]", "")).substring(0,6));
	}
}
