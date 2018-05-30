package com.ehealth.hmms.service;

import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;


public interface AuthenticationService {

//	String markAttendence(String tokenId);
//	
//	List<Attendence> getTokens();
	
	Result authenticate(Users user) throws Exception;
	
	Result authenticateUserForDashBoard(Users user) throws Exception;
	
	
}
