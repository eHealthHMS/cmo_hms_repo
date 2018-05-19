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
@Table(name = "category_details")
public class CategoryDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "sanctioned_post")
	private Long sanctionedPost;
	
	@Column(name = "in_position")
	private Long inPosition;
	
	@Column(name = "nhm")
	private Long nhm;
	
	@Column(name = "contract")
	private Long contract;
	
	@Column(name = "trainees")
	private Long trainees;
	
	@Column(name = "out_patient")
	private Long outPatient;
	
	@Column(name = "in_patient")
	private Long inPatient;
	
	@Column(name = "total_staff_available")
	private Long totalStaffAvailable;
	
	private HospitalMaster hospital;
	
	private CategoryMaster categoryMaster;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSanctionedPost() {
		return sanctionedPost;
	}

	public void setSanctionedPost(Long sanctionedPost) {
		this.sanctionedPost = sanctionedPost;
	}

	public Long getInPosition() {
		return inPosition;
	}

	public void setInPosition(Long inPosition) {
		this.inPosition = inPosition;
	}

	public Long getNhm() {
		return nhm;
	}

	public void setNhm(Long nhm) {
		this.nhm = nhm;
	}

	public Long getContract() {
		return contract;
	}

	public void setContract(Long contract) {
		this.contract = contract;
	}

	public Long getTrainees() {
		return trainees;
	}

	public void setTrainees(Long trainees) {
		this.trainees = trainees;
	}

	public Long getOutPatient() {
		return outPatient;
	}

	public void setOutPatient(Long outPatient) {
		this.outPatient = outPatient;
	}

	public Long getInPatient() {
		return inPatient;
	}

	public void setInPatient(Long inPatient) {
		this.inPatient = inPatient;
	}

	public Long getTotalStaffAvailable() {
		return totalStaffAvailable;
	}

	public void setTotalStaffAvailable(Long totalStaffAvailable) {
		this.totalStaffAvailable = totalStaffAvailable;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public CategoryMaster getCategoryMaster() {
		return categoryMaster;
	}

	public void setCategoryMaster(CategoryMaster categoryMaster) {
		this.categoryMaster = categoryMaster;
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
