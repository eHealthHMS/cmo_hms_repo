package com.ehealth.hmms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.service.PhcService;


@RestController
@RequestMapping("/phc")
public class PhcController {
	
	final static Logger logger = Logger.getLogger(AuthenticationController.class);
	
	@Autowired
	private PhcService phcService ;
	
	/**
	 * @return the phcService
	 */
//	public PhcService getPhcService() {
//		return phcService;
//	}
	/**
	 * @param phcService the phcService to set
	 */
	public void setPhcService(PhcService phcService) {
		this.phcService = phcService;
	}
	@RequestMapping(value = "/saveTransactionalData", method = RequestMethod.POST,headers="Accept=application/json")
	public Result savePhcTransactionalData( @RequestBody MonthlyDataFhcChc dataFhcChc) throws Exception{
		
		 
		//PhcService phcService = new PhcServiceImpl();
		Result result =  phcService.savePhcTransactionalData(dataFhcChc);
		
		return result;

	}
		//tested
	@RequestMapping(value = "/getStaticData/{hospitalId}", method = RequestMethod.GET,headers="Accept=application/json")
	public Result getPhcStaticData(@PathVariable("hospitalId") String hospitalId) throws Exception{
		//System.out.println("123");
		//PhcService phcService = new PhcServiceImpl();
		Result result =  phcService.getPhcStaticData(hospitalId);
		
		return result;

	}
			 //coding done;testing pending
	@RequestMapping(value = "/getDynamicData/{hospitalId}", method = RequestMethod.GET,headers="Accept=application/json")
	public Result getPhcDynamicDataForDashboard(@PathVariable("hospitalId") String hospitalId) throws Exception{
				 
		//PhcService phcService = new PhcServiceImpl();
		Result result =  phcService.getPhcDynamicDataForDashboard(hospitalId);
		
		return result;

	}
	
	
	

	
}
