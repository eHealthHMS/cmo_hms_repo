package com.ehealth.hmms.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;


public interface AuthenticationService {

//	String markAttendence(String tokenId);
//	
//	List<Attendence> getTokens();
	
	Result authenticate(Users user) throws Exception;
	
	Integer savePhcFuctionalComponents( MonthlyDataFhcChc dataFhcChc) throws Exception;
	
}
