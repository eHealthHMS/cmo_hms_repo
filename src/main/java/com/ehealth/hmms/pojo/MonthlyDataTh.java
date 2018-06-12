package com.ehealth.hmms.pojo;

import java.util.ArrayList;

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

	private DepartmentWiseOpIp[] departmentWiseOpIp = new DepartmentWiseOpIp[9];

	private SurgeryDetailsThDhGh[] surgeryDetailsThDhGh = new SurgeryDetailsThDhGh[5];

	private SpecialityClinicData[] specialityClinicData = new SpecialityClinicData[7];

	private LabDialysis talukFacilityDetails;
	private FundExpenditure talukFundDetails;
	private ServiceAreaOthers talukOtherServiceDetails;

	private boolean idlingEquipment;
	private ArrayList<IdlingMajorEquipment> idlingMajorEquipments = new ArrayList<IdlingMajorEquipment>();
	
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
	public DepartmentWiseOpIp[] getDepartmentWiseOpIp() {
		return departmentWiseOpIp;
	}
	public void setDepartmentWiseOpIp(DepartmentWiseOpIp[] departmentWiseOpIp) {
		this.departmentWiseOpIp = departmentWiseOpIp;
	}
	public SurgeryDetailsThDhGh[] getSurgeryDetailsThDhGh() {
		return surgeryDetailsThDhGh;
	}
	public void setSurgeryDetailsThDhGh(SurgeryDetailsThDhGh[] surgeryDetailsThDhGh) {
		this.surgeryDetailsThDhGh = surgeryDetailsThDhGh;
	}
	public SpecialityClinicData[] getSpecialityClinicData() {
		return specialityClinicData;
	}
	public void setSpecialityClinicData(SpecialityClinicData[] specialityClinicData) {
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
	public ArrayList<IdlingMajorEquipment> getIdlingMajorEquipments() {
		return idlingMajorEquipments;
	}
	public void setIdlingMajorEquipments(ArrayList<IdlingMajorEquipment> idlingMajorEquipments) {
		this.idlingMajorEquipments = idlingMajorEquipments;
	}

}
