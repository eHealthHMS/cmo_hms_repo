package com.ehealth.hmms.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

public class MonthlyDataTh {

	/*
	 * Type 6: op ip 
	 * type 7: surgery 
	 * type 8: speciality clinic 
	 * type 9: lab and facility 
	 * type 10: fund
	 */
	@Transient
	private Integer type;

	private String deviceId;

	private HospitalMonthlyTracker hospitalMonthlyTracker;

	private OpIpDetails talukOpIpDetails;
	
	private List<DepartmentWiseOpIp> departmentWiseOpIp = new ArrayList<DepartmentWiseOpIp>(9);

	private List<SurgeryDetailsThDhGh> SurgeryDetailsThDhGh = new ArrayList<SurgeryDetailsThDhGh>(5);

	private List<SpecialityClinicData> specialityClinicData = new ArrayList<SpecialityClinicData>(7);


	private LabDialysis talukFacilityDetails;
	private FundExpenditure talukFundDetails;
	private ServiceAreaOthers talukOtherServiceDetails;

	private boolean idlingEquipment;
	private List<IdlingMajorEquipment> idlingMajorEquipments = new ArrayList<IdlingMajorEquipment>();
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public HospitalMonthlyTracker getHospitalMonthlyTracker() {
		return hospitalMonthlyTracker;
	}
	public void setHospitalMonthlyTracker(HospitalMonthlyTracker hospitalMonthlyTracker) {
		this.hospitalMonthlyTracker = hospitalMonthlyTracker;
	}
	public OpIpDetails getTalukOpIpDetails() {
		return talukOpIpDetails;
	}
	public void setTalukOpIpDetails(OpIpDetails talukOpIpDetails) {
		this.talukOpIpDetails = talukOpIpDetails;
	}

	public List<DepartmentWiseOpIp> getDepartmentWiseOpIp() {
		return departmentWiseOpIp;
	}
	public void setDepartmentWiseOpIp(List<DepartmentWiseOpIp> departmentWiseOpIp) {
		this.departmentWiseOpIp = departmentWiseOpIp;
	}
	public List<SurgeryDetailsThDhGh> getSurgeryDetailsThDhGh() {
		return SurgeryDetailsThDhGh;
	}
	public void setSurgeryDetailsThDhGh(List<SurgeryDetailsThDhGh> surgeryDetailsThDhGh) {
		SurgeryDetailsThDhGh = surgeryDetailsThDhGh;
	}
	public List<SpecialityClinicData> getSpecialityClinicData() {
		return specialityClinicData;
	}
	public void setSpecialityClinicData(List<SpecialityClinicData> specialityClinicData) {
		this.specialityClinicData = specialityClinicData;
	}
	public LabDialysis getTalukFacilityDetails() {
		return talukFacilityDetails;
	}
	public void setTalukFacilityDetails(LabDialysis talukFacilityDetails) {
		this.talukFacilityDetails = talukFacilityDetails;
	}
	public FundExpenditure getTalukFundDetails() {
		return talukFundDetails;
	}
	public void setTalukFundDetails(FundExpenditure talukFundDetails) {
		this.talukFundDetails = talukFundDetails;
	}
	public ServiceAreaOthers getTalukOtherServiceDetails() {
		return talukOtherServiceDetails;
	}
	public void setTalukOtherServiceDetails(ServiceAreaOthers talukOtherServiceDetails) {
		this.talukOtherServiceDetails = talukOtherServiceDetails;
	}
	public boolean isIdlingEquipment() {
		return idlingEquipment;
	}
	public void setIdlingEquipment(boolean idlingEquipment) {
		this.idlingEquipment = idlingEquipment;
	}
	public List<IdlingMajorEquipment> getIdlingMajorEquipments() {
		return idlingMajorEquipments;
	}
	public void setIdlingMajorEquipments(List<IdlingMajorEquipment> idlingMajorEquipments2) {
		this.idlingMajorEquipments = idlingMajorEquipments2;
	}

}
