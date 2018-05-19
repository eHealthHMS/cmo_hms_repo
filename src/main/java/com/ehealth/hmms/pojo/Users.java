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
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable {

	private static final long serialVersionUID = -7788619177798333712L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "login_name")
	private String loginName;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private String status;


	private HospitalMaster hospital;
	
	private Role role;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public Role getRoleid() {
		return role;
	}

	public void setRoleid(Role roleid) {
		this.role = roleid;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gid")
	public HospitalMaster getHospital() {
		return hospital;
	}

	public void setHospital(HospitalMaster hospital) {
		this.hospital = hospital;
	}

}
