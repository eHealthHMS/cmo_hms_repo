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
@Table(name = "surgerydetails_thghdh")
public class SurgeryDetailsThDhGh implements Serializable {

	private static final long serialVersionUID = -298595611083363309L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "majorsurgery")
	private Long majorSurgery;
	
	@Column(name = "minorsurgery")
	private Long minorSurgery;
	
	private CategoryMaster categoryMaster;
	
	private HospitalMonthlyTracker hospitalMonthlyTracker;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMajorSurgery() {
		return majorSurgery;
	}

	public void setMajorSurgery(Long majorSurgery) {
		this.majorSurgery = majorSurgery;
	}

	public Long getMinorSurgery() {
		return minorSurgery;
	}

	public void setMinorSurgery(Long minorSurgery) {
		this.minorSurgery = minorSurgery;
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
	@JoinColumn(name = "id")
	public HospitalMonthlyTracker getHospitalMaster() {
		return hospitalMonthlyTracker;
	}

	public void setHospitalMaster(HospitalMonthlyTracker hospitalMonthlyTracker) {
		this.hospitalMonthlyTracker = hospitalMonthlyTracker;
	}
	
	
	
}
