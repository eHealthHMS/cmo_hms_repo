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

/**
 * @author Krishnapriya V S
 *
 */
@Entity
@Table(name = "department_wise_op_ip")
public class DepartmentWiseOpIp implements Serializable{

	private static final long serialVersionUID = -2634396201900455069L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "total_op_count")
	private Long totalOpCount;
	
	@Column(name = "total_ip_count")
	private Long totalIpCount;
	
	private HospitalMonthlyTracker hospitalMonthlyTrackerId;
	
	private CategoryMaster categoryMasterId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTotalOpCount() {
		return totalOpCount;
	}

	public void setTotalOpCount(Long totalOpCount) {
		this.totalOpCount = totalOpCount;
	}

	public Long getTotalIpCount() {
		return totalIpCount;
	}

	public void setTotalIpCount(Long totalIpCount) {
		this.totalIpCount = totalIpCount;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public HospitalMonthlyTracker getHospitalMonthlyTrackerId() {
		return hospitalMonthlyTrackerId;
	}

	public void setHospitalMonthlyTrackerId(HospitalMonthlyTracker hospitalMonthlyTrackerId) {
		this.hospitalMonthlyTrackerId = hospitalMonthlyTrackerId;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public CategoryMaster getCategoryMasterId() {
		return categoryMasterId;
	}

	public void setCategoryMasterId(CategoryMaster categoryMasterId) {
		this.categoryMasterId = categoryMasterId;
	}
	
	
	
	
}