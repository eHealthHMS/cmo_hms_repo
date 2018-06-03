package com.ehealth.hmms.dao;

import java.util.List;

import com.ehealth.hmms.pojo.CategoryDetails;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;

public interface PhcDao {


	
	Result saveInstitutionalData(MonthlyDataFhcChc dataFhcChc,Long trackerId)  throws Exception;
	MonthlyDataFhcChc fetchPhcRecord(Long hospitalId, String date)throws Exception;
	
//	Hospital getMonthlyData(Integer hospitalId)  throws Exception;
	
	List<CategoryDetails> getPhcStaticData(String hospitalId) throws Exception;
	
	List<MonthlyDataFhcChc> getPhcDynamicDataForDashboard(String hospitalId) throws Exception;
	
	MonthlyDataFhcChc getPhcDynamicDataFromHospitalId(Long hospitalId) throws Exception;
	
	Long getHospitalTrakerForSave(HospitalMonthlyTracker hospitalMonthlyTracker) throws Exception;
	
	Result saveMeetings(MonthlyDataFhcChc dataFhcChc,Long trackerId) throws Exception;
	
	Result saveSubCentreDetails(MonthlyDataFhcChc dataFhcChc,Long trackerId) throws Exception ;
	
	Result saveFieldWorks(MonthlyDataFhcChc dataFhcChc,Long trackerId) throws Exception ;
	
}
