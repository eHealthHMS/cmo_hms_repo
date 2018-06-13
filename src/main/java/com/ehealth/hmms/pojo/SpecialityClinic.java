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
@Table(name = "speciality_clinic")
public class SpecialityClinic  implements Serializable{

	private static final long serialVersionUID = -171207112203674417L;

	@Id 
	@SequenceGenerator(name="speciality_clinic_sequence",sequenceName="speciality_clinic_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="speciality_clinic_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "clinic")
	private String clinic;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClinic() {
		return clinic;
	}

	public void setClinic(String clinic) {
		this.clinic = clinic;
	}


}
