package com.ehealth.hmms.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.service.ThService;
import com.ehealth.hmms.service.impl.ThServiceImpl;


@RestController
@RequestMapping("/th")
public class ThController {
	
	
	@RequestMapping(value = "/saveAndUpdateOpIpDetails", method = RequestMethod.POST,headers="Accept=application/json")
	public Result saveAndUpdateOpIpDetails( @RequestBody OpIpDetails opIpDetails) throws Exception{
		
		 
		ThService thService = new ThServiceImpl();
		Result result =  thService.saveAndUpdateOpIpDetails(opIpDetails);
		
		return result;

	}
		
}
