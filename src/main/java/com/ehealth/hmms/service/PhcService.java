package com.ehealth.hmms.service;

import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;


public interface PhcService {

//	String markAttendence(String tokenId);
//	
//	List<Attendence> getTokens();
	
	Result saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc) throws Exception;
		
	Result getPhcStaticData(String hospitalId) throws Exception;
	
}
