package com.ehealth.hmms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;
import com.ehealth.hmms.service.AuthenticationService;

@RestController
@RequestMapping("/hmms")
public class AuthenticationController {

	final static Logger logger = Logger.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * @param authenticationService
	 *            the authenticationService to set
	 */
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result authenticateUserForDataEntry(@RequestBody Users user) throws Exception {

		Result userResult = authenticationService.authenticate(user);
		return userResult;

	}

	@RequestMapping(value = "/loginForDashboard", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result authenticateUserForDashBoard(@RequestBody Users user) throws Exception {

		// AuthenticationService authenticationService = new
		// AuthenticationServiceImpl();
		Result userResult = authenticationService.authenticateUserForDashBoard(user);

		return userResult;

	}

	// @RequestMapping(value = "/test/{hospitalId}", method =
	// RequestMethod.GET,headers="Accept=application/json")
	// public String gettestData(@PathVariable("hospitalId") String hospitalId)
	// throws Exception{
	//
	//
	// return "connected";
	//
	// }

	// @RequestMapping(value = "/loginForDashboard", method =
	// RequestMethod.POST,headers="Accept=application/json")
	// public Result authenticateUserForDashBoard( @RequestBody Users user) throws
	// Exception{
	//
	//
	// AuthenticationService authenticationService = new
	// AuthenticationServiceImpl();
	// Result userResult = authenticationService.authenticateUserForDashBoard(user);
	//
	//
	//
	// return userResult;
	//
	// }

	@RequestMapping(value = "/loginForTest", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result authenticateUserForTest(@RequestBody Users user) throws Exception {

		// AuthenticationService authenticationService = new
		// AuthenticationServiceImpl();
		Result userResult = new Result();// authenticationService.authenticate(user);

		// userResult.setHospitalName("test Hos");
		userResult.setStatus('S');

		return userResult;

	}

}
