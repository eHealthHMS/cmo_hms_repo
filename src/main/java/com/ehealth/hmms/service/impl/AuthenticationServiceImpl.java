package com.ehealth.hmms.service.impl;

import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.AuthenticationDao;
import com.ehealth.hmms.dao.impl.AuthenticationDaoImpl;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;
import com.ehealth.hmms.service.AuthenticationService;
import com.ehealth.hmms.util.Constants;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

//	@Autowired
	private AuthenticationDao authenticationDao ;

	
	public Result authenticate(Users user)  throws Exception{
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
