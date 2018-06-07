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
@Table(name = "hospital_type_master")
public class HospitalTypeMaster implements Serializable{

	private static final long serialVersionUID = 789153631959537L;

	@Id 
	@SequenceGenerator(name="hospital_type_sequence",sequenceName="hospital_type_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="hospital_type_sequence")
	@Column(name="gid", unique=true, nullable=false)
	private Long id;

	@Column(name = "hospital_type")
	private String hospitalType;

	@Column(name = "type_description")
	private String type_description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public String getType_description() {
		return type_description;
	}

	public void setType_description(String type_description) {
		this.type_description = type_description;
	}

}
