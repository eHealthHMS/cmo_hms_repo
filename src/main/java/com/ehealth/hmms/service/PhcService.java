package com.ehealth.hmms.service;

import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;


public interface PhcService {

//	String markAttendence(String tokenId);
//	
//	List<Attendence> getTokens();
	
	Result saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc) throws Exception;
	
}
