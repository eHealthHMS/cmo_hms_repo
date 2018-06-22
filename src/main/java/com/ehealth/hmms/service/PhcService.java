package com.ehealth.hmms.service;

import java.util.Map;

import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;


public interface PhcService {

//	String markAttendence(String tokenId);
//	
//	List<Attendence> getTokens();
	
	Result savePhcTransactionalData(MonthlyDataFhcChc dataFhcChc) throws Exception;
	
	
	Result getPhcDynamicDataForDistrict(Long districtId) throws Exception;
	
	Result getPhcDynamicDataForState() throws Exception;
	
//	Result getPhcDynamicDataForDashboard(String hospitalId) throws Exception;
	Result getPhcDynamicDataFromHospitalId(Long hospitalId) throws Exception;
	Map<String,String>  getDataForMap( Long hospitalId) throws Exception;
	
	
	
	
	
}
