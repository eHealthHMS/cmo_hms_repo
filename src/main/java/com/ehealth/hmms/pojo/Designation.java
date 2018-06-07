package com.ehealth.hmms.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "designation")
public class Designation implements Serializable{

	private static final long serialVersionUID = 4262809330188696334L;

	@Id 
	@SequenceGenerator(name="designation_sequence",sequenceName="designation_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="designation_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "designation_code")
	private String designationCode;
	
	@Column(name = "designation_description") 
	private String designationDescription;
	
	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignationCode() {
		return designationCode;
	}

	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}

	public String getDesignationDescription() {
		return designationDescription;
	}

	public void setDesignationDescription(String designationDescription) {
		this.designationDescription = designationDescription;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
