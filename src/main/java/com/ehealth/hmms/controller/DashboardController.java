package com.ehealth.hmms.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.service.PhcService;
import com.ehealth.hmms.service.ThService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	final static Logger logger = Logger.getLogger(DashboardController.class);

	@Autowired
	private PhcService phcService;

	@Autowired
	private ThService thService;

	/**
	 * @param phcService
	 *            the phcService to set
	 */
	public void setPhcService(PhcService phcService) {
		this.phcService = phcService;
	}

	@RequestMapping(value = "/getDataForMap/{hospitalId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Map<String, String> getDataForMap(@PathVariable("hospitalId") Long hospitalId) throws Exception {
		logger.info("Entered DashboardController:getDataForMap");
		Map<String, String> result = phcService.getDataForMap(hospitalId);
		logger.info("Exited DashboardController:getDataForMap");
		return result;

	}

	@RequestMapping(value = "/getDetailedDataForMap/{hospitalId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Result getDetailedDataForMap(@PathVariable("hospitalId") Long hospitalId) throws Exception {
		logger.info("Entered DashboardController:getDetailedDataForMap");
		Result result = phcService.getPhcDynamicDataFromHospitalId(hospitalId);// (hospitalId);
		logger.info("Exited DashboardController:getDetailedDataForMap");
		return result;

	}

	@RequestMapping(value = "/getThalukBasicData/{hospitalId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Map<String, String> getThalukBasicData(@PathVariable("hospitalId") Long hospitalId) throws Exception {
		logger.info("Entered DashboardController:getThalukBasicData");
		Map<String, String> result = thService.getThalukBasicData(hospitalId);// (hospitalId);
		logger.info("Exited DashboardController:getThalukBasicData");
		return result;

	}

}
