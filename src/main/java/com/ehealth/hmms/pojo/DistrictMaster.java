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
@Table(name = "district_master")
public class DistrictMaster implements Serializable{

	private static final long serialVersionUID = -7585510060242432629L;

	@Id 
	@SequenceGenerator(name="district_master_sequence",sequenceName="district_master_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="district_master_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "district_lg_code")
	private Long districtLgCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDistrictLgCode() {
		return districtLgCode;
	}

	public void setDistrictLgCode(Long districtLgCode) {
		this.districtLgCode = districtLgCode;
	}
}
