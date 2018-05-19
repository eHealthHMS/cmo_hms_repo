package com.ehealth.hmms.service.impl;

import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.AuthenticationDao;
import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.dao.impl.AuthenticationDaoImpl;
import com.ehealth.hmms.dao.impl.PhcDaoImpl;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;
import com.ehealth.hmms.service.AuthenticationService;
import com.ehealth.hmms.service.PhcService;
import com.ehealth.hmms.util.Constants;

@Service
public class PhcServiceImpl implements PhcService{

//	@Autowired
	private PhcDao phcDao  ;

	
	public Result saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc)  throws Exception{
		// Result result = new Result();
		 phcDao = new PhcDaoImpl();
		try {
		Integer result = phcDao.saveFunctionalComponents(dataFhcChc);
		
		if(userResult!=null ) 
		{
			HospitalMaster hospitalMaster = userResult.getHospital();
			result.setHospitalName(hospitalMaster.getHospitalName());
			
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
	
	
	
	public Result 	(Users user)  throws Exception{
		 Result result = new Result();
		 authenticationDao = new AuthenticationDaoImpl();
		try {
		Users  userResult = authenticationDao.authenticate(user);
		
		if(userResult!=null ) 
		{
			HospitalMaster hospitalMaster = userResult.getHospital();
			result.setHospitalName(hospitalMaster.getHospitalName());
			
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
