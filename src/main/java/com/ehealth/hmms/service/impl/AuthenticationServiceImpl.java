package com.ehealth.hmms.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.AuthenticationDao;
import com.ehealth.hmms.dao.LookupDao;
import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
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
	
	@Autowired
	private LookupDao lookupDao;

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
	
	
	public LookupDao getLookupDao() {
		return lookupDao;
	}

	public void setLookupDao(LookupDao lookupDao) {
		this.lookupDao = lookupDao;
	}
	
	public Result authenticate(Users user) throws Exception {
		Result result = new Result();
	//	authenticationDao = new AuthenticationDaoImpl();
		try {
			Users userResult = authenticationDao.authenticate(user);
			if (userResult != null) {
				HospitalMaster hospitalMaster = userResult.getHospitalid();		
				result.setHospitalType(hospitalMaster.getHospitalTypeMaster().getId());
				Date todaysDate = new Date();				
				String date = lookupDao.getConfiguration(Constants.DB_END_DATE_KEY);
				if (todaysDate.getDate() <= Integer.parseInt(date)) {
					result.setEditable(true);							
				} else {
					result.setEditable(false);	
				} 
				Long hospitalId = hospitalMaster.getId();
				MonthlyDataFhcChc MonthlyPhcResult = phcDao.fetchPhcRecord(hospitalId);
				HospitalMonthlyTracker hospitalMonthlyTracker = MonthlyPhcResult.getHospitalMonthlyTracker();	
				if(hospitalMonthlyTracker==null) {
					hospitalMonthlyTracker = new HospitalMonthlyTracker();
				}
				
				hospitalMonthlyTracker.setHospital(hospitalMaster);
				MonthlyPhcResult.setHospitalMonthlyTracker(hospitalMonthlyTracker);
				result.setValue(MonthlyPhcResult);
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Invalid Credentials");
			}

			

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		return result;
	}


	//for viewing dashboard
	public Result authenticateUserForDashBoard(Users user)  throws Exception{
		 Result result = null;
		// authenticationDao = new AuthenticationDaoImpl();
		try {
		Users  userResult = authenticationDao.authenticate(user);
		
		if(userResult!=null ) 
		{
			
			//if(userResult.getHospitalid())
			HospitalMaster hospitalMaster = userResult.getHospitalid();
			if(hospitalMaster!=null) {
				result =phcService.getPhcDynamicDataFromHospitalId(hospitalMaster.getId());//DataForDashboard(hospitalMaster.getId());
			}else {
				result.setStatus(Constants.FAILURE_STATUS);
				//result.setErrorMessage("Invalid Credentials");
			}
			
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
