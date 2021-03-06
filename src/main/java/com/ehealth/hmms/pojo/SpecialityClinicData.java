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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "specialityclinic_data")
public class SpecialityClinicData implements Serializable {

	private static final long serialVersionUID = -1248473620447378766L;
	
	@Id 
	@SequenceGenerator(name="speciality_clinic_data_sequence",sequenceName="speciality_clinic_data_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="speciality_clinic_data_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "malecount")
	private Long maleCount;
	
	@Column(name = "femalecount")
	private Long femalCount;
	
	@Column(name = "tgcount")
	private Long tgCount;
	
	@Column(name = "total")
	private Long total;
	
	@OneToOne( fetch=FetchType.EAGER)
	@JoinColumn(name = "specialityclinic_id", referencedColumnName="id")
	private SpecialityClinic specialityClinic;
	
	@OneToOne( fetch=FetchType.EAGER)
	@JoinColumn(name = "hosp_track_id", referencedColumnName="id")
	private HospitalMonthlyTracker hospitalMonthlyTracker;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMaleCount() {
		return maleCount;
	}

	public void setMaleCount(Long maleCount) {
		this.maleCount = maleCount;
	}

	public Long getFemalCount() {
		return femalCount;
	}

	public void setFemalCount(Long femalCount) {
		this.femalCount = femalCount;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	public Long getTgCount() {
		return tgCount;
	}

	public void setTgCount(Long tgCount) {
		this.tgCount = tgCount;
	}
	
	public SpecialityClinic getSpecialityClinic() {
		return specialityClinic;
	}

	public void setSpecialityClinic(SpecialityClinic specialityClinic) {
		this.specialityClinic = specialityClinic;
	}

	public HospitalMonthlyTracker getHospitalMonthlyTracker() {
		return hospitalMonthlyTracker;
	}

	public void setHospitalMonthlyTracker(HospitalMonthlyTracker hospitalMonthlyTracker) {
		this.hospitalMonthlyTracker = hospitalMonthlyTracker;
	}

}
