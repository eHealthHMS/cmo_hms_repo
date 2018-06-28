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
@RequestMapping("/phc")
public class PhcController {

	final static Logger logger = Logger.getLogger(PhcController.class);

	@Autowired
	private PhcService phcService;

	/**
	 * @param phcService
	 *            the phcService to set
	 */
	public void setPhcService(PhcService phcService) {
		this.phcService = phcService;
	}

	@RequestMapping(value = "/saveTransactionalData", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result savePhcTransactionalData(@RequestBody MonthlyDataFhcChc dataFhcChc) throws Exception {
		logger.info("Entered PhcController:savePhcTransactionalData");
		Result result = phcService.savePhcTransactionalData(dataFhcChc);
		logger.info("Exited PhcController:savePhcTransactionalData");
		return result;

	}

	/**
	 * method to get the data for
	 * 
	 * @param hospitalId
	 * @return
	 * @throws Exception
	 */
	// coding done;testing pending
	@RequestMapping(value = "/getDataForMap/{hospitalId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Map<String, String> getDataForMap(@PathVariable("hospitalId") Long hospitalId) throws Exception {
		logger.info("Entered PhcController:getDataForMap");
		Map<String, String> result = phcService.getDataForMap(hospitalId);
		logger.info("Exited PhcController:getDataForMap");
		return result;

	}

	// tested
	@RequestMapping(value = "/getStaticDataForMO/{nin}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Result getPhcStaticDataForMO(@PathVariable("nin") Long nin) throws Exception {
		Result result = phcService.getPhcStaticData(nin);

		return result;

	}

	@RequestMapping(value = "/getStaticDataForDistrict/{districtId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Result getPhcStaticDataForDistrict(@PathVariable("districtId") Long nin) throws Exception {
		Result result = phcService.getPhcStaticDataForDistrict(nin);

		return result;

	}
	
	@RequestMapping(value = "/getStaticDataForState", method = RequestMethod.GET, headers = "Accept=application/json")
	public Result getPhcStaticDataForState() throws Exception {
		//Result result = phcService.getPhcStaticDataForDistrict(nin);

		return null;

	}
	// //coding done;testing pending
	// @RequestMapping(value = "/getDynamicData/{hospitalId}", method =
	// RequestMethod.GET,headers="Accept=application/json")
	// public Result getPhcDynamicDataForDashboard(@PathVariable("hospitalId")
	// String hospitalId) throws Exception{
	//
	// //PhcService phcService = new PhcServiceImpl();
	// Result result = phcService.getPhcDynamicDataForDashboard(hospitalId);
	//
	// return result;
	//
	// }

}
