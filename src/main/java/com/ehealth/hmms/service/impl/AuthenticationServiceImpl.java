package com.ehealth.hmms.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.AuthenticationDao;
import com.ehealth.hmms.dao.LookupDao;
import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.dao.ThDao;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.MonthlyDataTh;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;
import com.ehealth.hmms.service.AuthenticationService;
import com.ehealth.hmms.service.PhcService;
import com.ehealth.hmms.util.Constants;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	final static Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);
	@Autowired
	private AuthenticationDao authenticationDao;

	@Autowired
	private PhcService phcService;

	@Autowired
	private PhcDao phcDao;

	@Autowired
	private ThDao thDao;

	@Autowired
	private LookupDao lookupDao;

	/**
	 * @param phcService
	 *            the phcService to set
	 */
	public void setPhcService(PhcService phcService) {
		this.phcService = phcService;
	}

	/**
	 * @param authenticationDao
	 *            the authenticationDao to set
	 */
	public void setAuthenticationDao(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}

	/**
	 * @param phcDao
	 *            the phcDao to set
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

	public ThDao getThDao() {
		return thDao;
	}

	public void setThDao(ThDao thDao) {
		this.thDao = thDao;
	}

	// For fetching data of fhc/chc/th for Android form
	public Result authenticate(Users user) throws Exception {
		Result result = new Result();

		try {
			logger.info("Entered AuthenticationServiceImpl:authenticate");
			Users userResult = authenticationDao.authenticate(user);
			if (userResult != null) {
				HospitalMaster hospitalMaster = userResult.getHospitalid();
				Long hospitalTypeId = hospitalMaster.getHospitalTypeMaster().getId();
				result.setHospitalType(hospitalTypeId);
				Date todaysDate = new Date();
				String date = lookupDao.getConfiguration(Constants.DB_END_DATE_KEY);
				if (todaysDate.getDate() <= Integer.parseInt(date)) {
					result.setEditable(true);
				} else {
					result.setEditable(false);
				}
				Long hospitalId = hospitalMaster.getId();
				if (hospitalTypeId == 15 || hospitalTypeId == 16 || hospitalTypeId == 17 || hospitalTypeId == 20) {
					logger.info("Entered autenticate service for fetching phc/chc data while user login");
					MonthlyDataFhcChc monthlyPhcResult = phcDao.fetchPhcRecord(hospitalId);
					HospitalMonthlyTracker hospitalMonthlyTracker = new HospitalMonthlyTracker();
					if(monthlyPhcResult.getHospitalMonthlyTracker().getId() == null)
					{
						thDao.saveHospitalMonthlyTracker(hospitalId);
						hospitalMonthlyTracker = thDao.fetchHospitalMonthlyTracker(hospitalId);
						monthlyPhcResult.setHospitalMonthlyTracker(hospitalMonthlyTracker);
					}
					else
					{
						hospitalMonthlyTracker = monthlyPhcResult.getHospitalMonthlyTracker();
					}
					result.setHospitalMonthlyTracker(hospitalMonthlyTracker);
					result.getHospitalMonthlyTracker().setHospital(hospitalMaster);
					result.setValue(monthlyPhcResult);
					result.setStatus(Constants.SUCCESS_STATUS);
				} else if (hospitalTypeId == 18 || hospitalTypeId == 19) {
					logger.info(
							"Entered authenticate service for fetching the taluk hospital related data on user login");
					MonthlyDataTh monthlyDataTh = thDao.fetchMonthlyDataTh(hospitalId);
					monthlyDataTh.getHospitalMonthlyTracker().setHospital(hospitalMaster);
					HospitalMonthlyTracker hospitalMonthlyTracker = new HospitalMonthlyTracker();
					if(monthlyDataTh.getHospitalMonthlyTracker().getId() == null)
					{
						thDao.saveHospitalMonthlyTracker(hospitalId);
						hospitalMonthlyTracker = thDao.fetchHospitalMonthlyTracker(hospitalId);
						monthlyDataTh.setHospitalMonthlyTracker(hospitalMonthlyTracker);
					}
					else
					{
						hospitalMonthlyTracker = monthlyDataTh.getHospitalMonthlyTracker();
					}
					result.setHospitalMonthlyTracker(hospitalMonthlyTracker);
					result.getHospitalMonthlyTracker().setHospital(hospitalMaster);
					result.setValue(monthlyDataTh);
					result.setStatus(Constants.SUCCESS_STATUS);
					if(monthlyDataTh.getType() == 11)
					{
						result.getHospitalMonthlyTracker().setFinalSubmitDone(true);
					}
					else
					{
						result.getHospitalMonthlyTracker().setFinalSubmitDone(false);
					}
				}
		
			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Invalid Credentials");
			}
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
			logger.error(e);
		}
		logger.info("Exited AuthenticationServiceImpl:authenticate");
		return result;
	}
	
	
	public Result changePassword(Users user) throws Exception {
		Result result = new Result();

		try {
			logger.info("Entered AuthenticationServiceImpl:changePassword");
			Users userResult = authenticationDao.authenticate(user);
			if (userResult != null) {
				userResult.setPassword(user.getNewPassword());
				authenticationDao.changePassword(userResult);
				
			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Invalid Credentials");
			}
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
			logger.error(e);
		}
		logger.info("Exited AuthenticationServiceImpl:changePassword");
		return result;
	}
	
	private Result getDashboardDataForMOs(HospitalMaster hospitalMaster) throws Exception {
		Result result = new Result();
		Integer typeId = hospitalMaster.getHospitalTypeMaster().getId().intValue();
		
		switch(typeId) 
		{
		//phc,fhc,chc,24*7
		case 15:
		case 16:
		case 17:
		case 20:{
			result =phcService.getPhcDynamicDataFromHospitalId(hospitalMaster.getNin());

			
			break;
		}
		default:
			result.setStatus(Constants.FAILURE_STATUS);
			break;
		}
		return result;
	}
	

	//for viewing dashboard
	public Result authenticateUserForDashBoard(Users user)  throws Exception{
		 Result result = new Result();
		try {
		Users  userResult = authenticationDao.authenticate(user);
		
		if(userResult!=null ) 
		{
			HospitalMaster hospitalMaster = userResult.getHospitalid();
			if(hospitalMaster!=null  && userResult.getRoleid().getId()==3) {
				result = getDashboardDataForMOs(hospitalMaster);
				
			}else {
					result.setStatus(Constants.FAILURE_STATUS);
				}

			} else {

				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Invalid Credentials");
			}

			result.setStatus(Constants.SUCCESS_STATUS);

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}

		logger.info("Exited AuthenticationServiceImpl:authenticateUserForDashBoard");

		return result;
	}

}
