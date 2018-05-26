package com.ehealth.hmms.service;

import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;


public interface PhcService {

//	String markAttendence(String tokenId);
//	
//	List<Attendence> getTokens();
	
	Result savePhcTransactionalData(MonthlyDataFhcChc dataFhcChc) throws Exception;
	
	
	Result getPhcStaticData(String hospitalId) throws Exception;
	Result getPhcDynamicDataForDashboard(String hospitalId) throws Exception;
	
	
}
