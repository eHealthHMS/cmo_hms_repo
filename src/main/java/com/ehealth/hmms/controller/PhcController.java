package com.ehealth.hmms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;
import com.ehealth.hmms.service.AuthenticationService;
import com.ehealth.hmms.service.PhcService;
import com.ehealth.hmms.service.impl.AuthenticationServiceImpl;
import com.ehealth.hmms.service.impl.PhcServiceImpl;


@RestController
@RequestMapping("/phc")
public class PhcController {
	
	
	@RequestMapping(value = "/saveFuctionalComponents", method = RequestMethod.POST,headers="Accept=application/json")
	public Result savePhcFuctionalComponents( @RequestBody MonthlyDataFhcChc dataFhcChc) throws Exception{
		
		 
		PhcService phcService = new PhcServiceImpl();
		Integer resultValue =  phcService.savePhcFuctionalComponents(dataFhcChc);
		
		return userResult;

	}
	 
	
	

	
}
