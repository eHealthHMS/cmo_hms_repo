package com.ehealth.hmms.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.service.PhcService;
import com.ehealth.hmms.service.impl.PhcServiceImpl;


@RestController
@RequestMapping("/phc")
public class PhcController {
	
	
	@RequestMapping(value = "/saveTransactionalData", method = RequestMethod.POST,headers="Accept=application/json")
	public Result savePhcTransactionalData( @RequestBody MonthlyDataFhcChc dataFhcChc) throws Exception{
		
		 
		PhcService phcService = new PhcServiceImpl();
		Result result =  phcService.savePhcTransactionalData(dataFhcChc);
		
		return result;

	}
		
	@RequestMapping(value = "/getStaticData/{hospitalId}", method = RequestMethod.GET,headers="Accept=application/json")
	public Result getPhcStaticData(@PathVariable("hospitalId") String hospitalId) throws Exception{
		
		 
		PhcService phcService = new PhcServiceImpl();
		Result result =  phcService.getPhcStaticData(hospitalId);
		
		return result;

	}
	
	
	 
//	@RequestMapping(value = "/getDashboardData/{hospitalId}", method = RequestMethod.GET,headers="Accept=application/json")
//	public Result getPhcStaticData(@PathVariable("hospitalId") String hospitalId) throws Exception{
//		
//		 
//		PhcService phcService = new PhcServiceImpl();
//		Result result =  phcService.getPhcStaticData(hospitalId);
//		
//		return result;
//
//	}
	

	
}
