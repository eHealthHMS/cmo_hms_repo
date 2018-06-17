package com.ehealth.hmms.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable {

	private static final long serialVersionUID = -7788619177798333712L;

	@Id 
	@SequenceGenerator(name="users_sequence",sequenceName="users_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="users_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "login_name")
	private String loginName;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private String status;
	
	 
	@Column(name = "app_version")
	private String appVersion;


	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "hospital_id", referencedColumnName="gid")
	private HospitalMaster hospitalid;
	
	@ManyToOne(targetEntity=Role.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "roleid", insertable=false, updatable=false)
	private Role roleid;

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

	public Role getRoleid() {
		return roleid;
	}

	public void setRoleid(Role roleid) {
		this.roleid = roleid;
	}

	public HospitalMaster getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(HospitalMaster hospitalid) {
		this.hospitalid = hospitalid;
	}
	
	  public String getAppVersion() {
	    return appVersion;
	 
	  }
	  public void setAppVersion(String appVersion) {
	    this.appVersion = appVersion;
	 
	  }
	 
	  
	 
}
