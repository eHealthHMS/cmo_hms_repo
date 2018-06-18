package com.ehealth.hmms.service.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.AuthenticationDao;
import com.ehealth.hmms.dao.LookupDao;
import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.HospitalTypeMaster;
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
	
	
//	public String averageHouseVisits(Long houseVisits,int nonWorkingDays) {
//	  
//		Long averageVisits = 0L;
//		if(houseVisits!=null && nonWorkingDays!=0) {
//			averageVisits= houseVisits/nonWorkingDays;
//	   }
//		return averageVisits.toString();
//	}
	
	
//	public int countNonWorkingDays() {
//	    Calendar calendar = Calendar.getInstance();
//	    // Note that month is 0-based in calendar, bizarrely.
//	    
//		calendar.add(Calendar.MONTH, -1);
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
//	   /// calendar.set(year, month - 1, 1);
//	    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//	    int count = 1;// 1 for second saturday
//	    for (int day = 1; day <= daysInMonth; day++) {
//	       // calendar.set(year, month - 1, day);
//	    	 calendar.set(Calendar.DAY_OF_MONTH,day);
//	        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//	        if (dayOfWeek == Calendar.SUNDAY) {// || dayOfweek == Calendar.SATURDAY) {
//	            count++;
//	           
//	        }
//	    }
//	    return count;
//	}

	
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
		// authenticationDao = new AuthenticationDaoImpl();
		try {
		Users  userResult = authenticationDao.authenticate(user);
		
		if(userResult!=null ) 
		{
			
			//if(userResult.getHospitalid())
			HospitalMaster hospitalMaster = userResult.getHospitalid();
			if(hospitalMaster!=null  && userResult.getRoleid().getId()==3) {
				
				
				result = getDashboardDataForMOs(hospitalMaster);
				
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
