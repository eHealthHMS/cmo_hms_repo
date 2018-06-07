package com.ehealth.hmms.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "drug_availability_status")
public class DrugAvailabilityStatus implements Serializable {

	private static final long serialVersionUID = -7379826388643124405L;

	@Id 
	@SequenceGenerator(name="drug_availability_sequence",sequenceName="drug_availability_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="drug_availability_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "status_type")
	private String statusType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	
	

}
