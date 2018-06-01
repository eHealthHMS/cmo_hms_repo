package com.ehealth.hmms.service.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.AuthenticationDao;
import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;
import com.ehealth.hmms.service.AuthenticationService;
import com.ehealth.hmms.service.PhcService;
import com.ehealth.hmms.util.Constants;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	final static Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);
	@Autowired
	private AuthenticationDao authenticationDao ;
	
	@Autowired
	private PhcService phcService;
	
	@Autowired
	private PhcDao phcDao;

	/**
	 * @param phcService the phcService to set
	 */
	public void setPhcService(PhcService phcService) {
		this.phcService = phcService;
	}

	/**
	 * @param authenticationDao the authenticationDao to set
	 */
	public void setAuthenticationDao(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}
	
	/**
	 * @param phcDao the phcDao to set
	 */
	public void setPhcDao(PhcDao phcDao) {
		this.phcDao = phcDao;
	}
	
	
	public Result authenticate(Users user) throws Exception {
		Result result = new Result();
	//	authenticationDao = new AuthenticationDaoImpl();
		try {
			Users userResult = authenticationDao.authenticate(user);
			if (userResult != null) {
				HospitalMaster hospitalMaster = userResult.getHospitalid();		
				result.setHospitalMaster(hospitalMaster);
				Date todaysDate = new Date();
				SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
				   String date = dmyFormat.format(todaysDate);
				if (todaysDate.getDate() <= Constants.ENDDATE) {
					result.setEditable(true);							
				} else {
					result.setEditable(false);	
				} 
				//result.setHospitalName(hospitalMaster.getHospitalName());
				Long hospitalId = hospitalMaster.getId();
				//int month =  date.getMonth();	
				MonthlyDataFhcChc MonthlyPhcResult = phcDao.fetchPhcRecord(hospitalId,date);
				result.setValue(MonthlyPhcResult);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Invalid Credentials");
			}

			result.setStatus(Constants.SUCCESS_STATUS);

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		return result;
	}
	//for viewing dashboard
	public Result authenticateUserForDashBoard(Users user)  throws Exception{
		 Result result = new Result();
		// authenticationDao = new AuthenticationDaoImpl();
		try {
		Users  userResult = authenticationDao.authenticate(user);
		
		if(userResult!=null ) 
		{
			// phcServiceImpl = new PhcServiceImpl();
			 
			
			//HospitalMaster hospitalMaster = userResult.getHospital();
			//result.setHospitalName(hospitalMaster.getHospitalName());
			
//			authenticationDao
//			HospitalMaster hospitalMaster = userResult.getHospital();
//			result.setHospitalName(hospitalMaster.getHospitalName());
			
		}else	{
			result.setStatus(Constants.FAILURE_STATUS);
			result.setErrorMessage("Invalid Credentials");
		}
		
		result.setStatus(Constants.SUCCESS_STATUS);
		
			}
			catch(Exception e) {
				result.setStatus(Constants.FAILURE_STATUS);
			}
		
		
	return result;
	}
	
	
	
	
	
	
}
