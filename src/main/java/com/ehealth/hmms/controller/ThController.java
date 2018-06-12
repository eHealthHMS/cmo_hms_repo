package com.ehealth.hmms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinicData;
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
	public Result saveAndUpdateOpIpDetails( @RequestBody OpIpDetails opIpDetails) throws Exception{
		
		Result result =  thService.saveAndUpdateOpIpDetails(opIpDetails);
		
		return result;

	}
	
	@RequestMapping(value = "/saveOrUpdateDeptWiseIpOpDetails", method = RequestMethod.POST,headers="Accept=application/json")
	public Result saveOrUpdateDeptWiseIpOpDetails( @RequestBody List<DepartmentWiseOpIp> departmentWiseOpIpList) throws Exception{
		
		 
		//ThService thService = new ThServiceImpl();
		Result result =  thService.saveOrUpdateDeptWiseIpOpDetails(departmentWiseOpIpList);
		
		return result;

	}
	
	@RequestMapping(value = "/saveOrUpdateServiceAreaOthers", method = RequestMethod.POST,headers="Accept=application/json")
	public Result saveOrUpdateServiceAreaOthers( @RequestBody ServiceAreaOthers serviceAreaOthers) throws Exception{
		
		 
		//ThService thService = new ThServiceImpl();
		Result result =  thService.saveOrUpdateServiceAreaOthers(serviceAreaOthers);
		
		return result;

	}
	
	@RequestMapping(value = "/saveOrUpdateFundExpenditure", method = RequestMethod.POST,headers="Accept=application/json")
	public Result saveOrUpdateFundExpenditure( @RequestBody FundExpenditure fundExpenditure) throws Exception{
		
		 
		//ThService thService = new ThServiceImpl();
		Result result =  thService.saveOrUpdateFundExpenditure(fundExpenditure);
		
		return result;

	}
	
			

		
}
