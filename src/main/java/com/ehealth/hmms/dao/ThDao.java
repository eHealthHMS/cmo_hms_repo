package com.ehealth.hmms.dao;

import java.util.List;
import java.util.Map;

import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.IdlingMajorEquipment;
import com.ehealth.hmms.pojo.LabDialysis;
import com.ehealth.hmms.pojo.MonthlyDataTh;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinicData;
import com.ehealth.hmms.pojo.SurgeryDetailsThDhGh;

public interface ThDao {

	MonthlyDataTh fetchMonthlyDataTh(Long hospitalId) throws Exception;

	Map<String, String> getThalukBasicData(Long nin) throws Exception;

	Boolean saveAndUpdateThTransactionalData(MonthlyDataTh monthlyDataTh) throws Exception;
	
	OpIpDetails fetchOpIpDetails(Long hospitalId) throws Exception;
	
	List<DepartmentWiseOpIp> fetchDeptOpIpDetails(Long hospitalId) throws Exception;
	
	List<SurgeryDetailsThDhGh> fetchSurgeryDetailsThDhGh(Long hospitalId) throws Exception;
	
	List<SpecialityClinicData> fetchSpecialityClinicData(Long hospitalId) throws Exception;

	LabDialysis fetchLabDialysis(Long hospitalId) throws Exception;
	
	FundExpenditure fetchFundExpenditure(Long hospitalId) throws Exception;
	 
	ServiceAreaOthers fetchServiceAreaOthers(Long hospitalId) throws Exception;
	
	List<IdlingMajorEquipment> fetchIdlingMajorEquipment(Long hospitalId) throws Exception;

	HospitalMonthlyTracker saveHospitalMonthlyTracker(Long hospitalId) throws Exception;
	
	
	
}
