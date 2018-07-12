package com.ehealth.hmms.pojo;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hospital_image")
public class HospitalImage  implements Serializable {
	
	private static final long serialVersionUID = -7788619177798333712L;

	@Id 
	//@SequenceGenerator(name="role_sequence",sequenceName="role_seq", allocationSize=1)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="role_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@OneToOne( fetch=FetchType.EAGER)
	@JoinColumn(name = "hospital_id", referencedColumnName="gid")
	private HospitalMaster hospital;

	@Column(name = "image")
	private Blob image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the hospital
	 */
	public HospitalMaster getHospital() {
		return hospital;
	}

	/**
	 * @param hospital the hospital to set
	 */
	public void setHospital(HospitalMaster hospital) {
		this.hospital = hospital;
	}

	/**
	 * @return the image
	 */
	public Blob getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Blob image) {
		this.image = image;
	}

	
	
}
