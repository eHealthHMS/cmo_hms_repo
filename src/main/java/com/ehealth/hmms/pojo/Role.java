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
@Table(name = "role")
public class Role  implements Serializable {
	
	private static final long serialVersionUID = -7788619177798333712L;


	@Id 
	@SequenceGenerator(name="role_sequence",sequenceName="role_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="role_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "description")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
