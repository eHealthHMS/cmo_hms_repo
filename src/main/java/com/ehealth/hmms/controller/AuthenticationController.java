package com.ehealth.hmms.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;
import com.ehealth.hmms.service.AuthenticationService;
import com.ehealth.hmms.service.impl.AuthenticationServiceImpl;


@RestController
@RequestMapping("/hmms")
public class AuthenticationController {

	

	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST,headers="Accept=application/json")
	public Result authenticateUserForDataEntry( @RequestBody Users user) throws Exception{
		
		 
		AuthenticationService authenticationService = new AuthenticationServiceImpl();
		Result userResult =  authenticationService.authenticate(user);
		
		
		
		return userResult;

	}
	
	@RequestMapping(value = "/loginForDashboard", method = RequestMethod.POST,headers="Accept=application/json")
	public Result authenticateUserForDashBoard( @RequestBody Users user) throws Exception{
		
		 
		AuthenticationService authenticationService = new AuthenticationServiceImpl();
		Result userResult =  authenticationService.authenticateUserForDashBoard(user);
		
		
		
		return userResult;

	}
	
	
	
//	 
//	 @RequestMapping(value = "/test", method = RequestMethod.GET)
//		public void authenticateTest() {
//		//
//		 System.out.println("hai");//return "hai";
//	 } 
//	 
//	
	
}
