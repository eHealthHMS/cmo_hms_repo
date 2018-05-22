package com.ehealth.hmms.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.AuthenticationDao;
import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.dao.impl.AuthenticationDaoImpl;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;
import com.ehealth.hmms.service.AuthenticationService;
import com.ehealth.hmms.util.Constants;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	// @Autowired
	private AuthenticationDao authenticationDao;
	private PhcDao phcDao;

	@SuppressWarnings("deprecation")
	public Result authenticate(Users user) throws Exception {
		Result result = new Result();
		authenticationDao = new AuthenticationDaoImpl();
		try {
			Users userResult = authenticationDao.authenticate(user);
			if (userResult != null) {
				HospitalMaster hospitalMaster = userResult.getHospital();			
				Date date = new Date();
				if (date.getDate() <= Constants.ENDDATE) {
					result.setMode(true);							
				} else {
					result.setMode(false);	
				} 
				result.setHospitalName(hospitalMaster.getHospitalName());
				Long hospitalId = hospitalMaster.getId();
				int month =  date.getMonth();	
				MonthlyDataFhcChc MonthlyPhcResult = phcDao.fetchPhcRecord(hospitalId,month);
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
		 authenticationDao = new AuthenticationDaoImpl();
		try {
		Users  userResult = authenticationDao.authenticate(user);
		
		if(userResult!=null ) 
		{
			
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
