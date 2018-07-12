package com.ehealth.hmms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.HospitalImage;
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
		logger.info("Entered AuthenticationController:authenticateUserForDataEntry");
		Result userResult = authenticationService.authenticate(user);
		logger.info("Exited AuthenticationController:authenticateUserForDataEntry");
		return userResult;

	}
	
	@RequestMapping(value = "/loginForDashboard", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result authenticateUserForDashBoard(@RequestBody Users user) throws Exception {

		logger.info("Entered AuthenticationController:authenticateUserForDashBoard");
		Result userResult = authenticationService.authenticateUserForDashBoard(user);
		logger.info("Exited AuthenticationController:authenticateUserForDashBoard");
		return userResult;

	}

	

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result changePassword(@RequestBody Users user) throws Exception {

		logger.info("Entered AuthenticationController:changePassword");
		Result userResult =  authenticationService.changePassword(user);
		logger.info("Exited AuthenticationController:changePassword");

		return userResult;

	}
	
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result uploadImage(@RequestBody HospitalImage hospitalImage) throws Exception {

		logger.info("Entered AuthenticationController: uploadImage");
		Result userResult =  authenticationService.uploadImage(hospitalImage);
		logger.info("Exited AuthenticationController: uploadImage");

		return userResult;

	}
}
