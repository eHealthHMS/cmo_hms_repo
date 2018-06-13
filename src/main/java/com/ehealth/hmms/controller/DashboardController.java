package com.ehealth.hmms.controller;

import java.util.Map;

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
@RequestMapping("/dashboard")
public class DashboardController {

	final static Logger logger = Logger.getLogger(AuthenticationController.class);

	@Autowired
	private PhcService phcService;

	/**
	 * @param phcService
	 *            the phcService to set
	 */
	public void setPhcService(PhcService phcService) {
		this.phcService = phcService;
	}

	@RequestMapping(value = "/getDataForMap/{hospitalId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Map<String, String> getDataForMap(@PathVariable("hospitalId") Long hospitalId) throws Exception {

		Map<String, String> result = phcService.getDataForMap(hospitalId);

		return result;

	}
	
	@RequestMapping(value = "/getDetailedDataForMap/{hospitalId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Result getDetailedDataForMap(@PathVariable("hospitalId") Long hospitalId) throws Exception {

		Result result = phcService.getPhcDynamicDataFromHospitalId(hospitalId);//(hospitalId);

		return result;

	}
	

}
