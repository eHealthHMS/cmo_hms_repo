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
@Table(name = "lab_dialysis")
public class LabDialysis implements Serializable{
	
	private static final long serialVersionUID = -3042387409531442341L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "lab_patients_tested")
	private Long labPatientsTested;
	
	@Column(name = "lab_total_tests")
	private Long labTotalTests;
	
	@Column(name = "lab_usg_func_machines")
	private Boolean labUsgFuncMachines;
	
	@Column(name = "lab_usg_count")
	private Long labUsgCount;
	
	@Column(name = "lab_ecgmachine")
	private Long labEcgMachine;
	
	@Column(name = "lab_ecg_count")
	private Long labEcgCount;

	@Column(name = "dia_shift_functioning")
	private Long diaShiftFunctioning;
	
	@Column(name = "dia_patient_count")
	private Long diaPatientCount; 	
	
	@Column(name = "dia_count")
	private Long diaCount; 
	
	private HospitalMonthlyTracker hospitalMonthlyTracker;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLabPatientsTested() {
		return labPatientsTested;
	}

	public void setLabPatientsTested(Long labPatientsTested) {
		this.labPatientsTested = labPatientsTested;
	}

	public Long getLabTotalTests() {
		return labTotalTests;
	}

	public void setLabTotalTests(Long labTotalTests) {
		this.labTotalTests = labTotalTests;
	}

	public Boolean getLabUsgFuncMachines() {
		return labUsgFuncMachines;
	}

	public void setLabUsgFuncMachines(Boolean labUsgFuncMachines) {
		this.labUsgFuncMachines = labUsgFuncMachines;
	}

	public Long getLabUsgCount() {
		return labUsgCount;
	}

	public void setLabUsgCount(Long labUsgCount) {
		this.labUsgCount = labUsgCount;
	}

	public Long getLabEcgMachine() {
		return labEcgMachine;
	}

	public void setLabEcgMachine(Long labEcgMachine) {
		this.labEcgMachine = labEcgMachine;
	}

	public Long getLabEcgCount() {
		return labEcgCount;
	}

	public void setLabEcgCount(Long labEcgCount) {
		this.labEcgCount = labEcgCount;
	}

	public Long getDiaShiftFunctioning() {
		return diaShiftFunctioning;
	}

	public void setDiaShiftFunctioning(Long diaShiftFunctioning) {
		this.diaShiftFunctioning = diaShiftFunctioning;
	}

	public Long getDiaPatientCount() {
		return diaPatientCount;
	}

	public void setDiaPatientCount(Long diaPatientCount) {
		this.diaPatientCount = diaPatientCount;
	}

	public Long getDiaCount() {
		return diaCount;
	}

	public void setDiaCount(Long diaCount) {
		this.diaCount = diaCount;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public HospitalMonthlyTracker getHospitalMonthlyTracker() {
		return hospitalMonthlyTracker;
	}

	public void setHospitalMonthlyTracker(HospitalMonthlyTracker hospitalMonthlyTracker) {
		this.hospitalMonthlyTracker = hospitalMonthlyTracker;
	}

}
