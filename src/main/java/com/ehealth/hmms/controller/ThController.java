package com.ehealth.hmms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.ServiceAreaThDhGh;
import com.ehealth.hmms.service.ThService;


@RestController
@RequestMapping("/th")
public class ThController {
	
	@Autowired
	private ThService thService;
	
	
	
	/**
	 * @param thService the thService to set
	 */
	public void setThService(ThService thService) {
		this.thService = thService;
	}



	@RequestMapping(value = "/saveAndUpdateOpIpDetails", method = RequestMethod.POST,headers="Accept=application/json")
	public Result saveAndUpdateOpIpDetails( @RequestBody ServiceAreaThDhGh opIpDetails) throws Exception{
		
		 
		//ThService thService = new ThServiceImpl();
		Result result =  thService.saveAndUpdateOpIpDetails(opIpDetails);
		
		return result;

	}
		
}
