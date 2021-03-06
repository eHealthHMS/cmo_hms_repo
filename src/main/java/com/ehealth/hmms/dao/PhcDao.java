package com.ehealth.hmms.dao;

import java.util.List;
import java.util.Map;

import com.ehealth.hmms.pojo.CategoryDetails;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;

public interface PhcDao {

	Result saveInstitutionalData(MonthlyDataFhcChc dataFhcChc) throws Exception;

	MonthlyDataFhcChc fetchPhcRecord(Long hospitalId) throws Exception;

	List<CategoryDetails> getPhcStaticData(Long nin) throws Exception;

	// List<MonthlyDataFhcChc> getPhcDynamicDataForDashboard(String hospitalId)
	// throws Exception;
	
	HospitalMonthlyTracker updateHospitalMonthlyTracker(HospitalMonthlyTracker hospitalMonthlyTracker,Boolean isFinal) throws Exception;
	
	List<Map<String,String>> getPhcDynamicDataForState() throws Exception;
	
	
	List<Map<String,String>> getPhcDynamicDataForDistrict(Long districtId) throws Exception;

	 List<Map<String,String>>  getPhcDynamicDataFromHospitalId(Long hospitalId) throws Exception;

	HospitalMonthlyTracker getHospitalTrakerForSave(HospitalMonthlyTracker hospitalMonthlyTracker) throws Exception;

	Result saveMeetings(MonthlyDataFhcChc dataFhcChc) throws Exception;

	Result saveSubCentreDetails(MonthlyDataFhcChc dataFhcChc) throws Exception;

	Result saveFieldWorks(MonthlyDataFhcChc dataFhcChc) throws Exception;

	Map<String, String> getDataForMap(Long hospitalId) throws Exception;

	List<CategoryDetails> getPhcStaticDataForDistrict(Long districtId) throws Exception;
	HospitalMonthlyTracker createHospitalMonthlyTracker(Long hospitalId)throws Exception;

	HospitalMonthlyTracker getMonthlyTrackerForCurrentMonth(Long hospitalId)throws Exception;

	// Long saveHospitalMonthlyTracker(Long hospitalId) throws Exception;

}
