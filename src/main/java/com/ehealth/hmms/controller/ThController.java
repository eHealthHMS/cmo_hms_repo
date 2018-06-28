package com.ehealth.hmms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.MonthlyDataTh;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.service.ThService;

@RestController
@RequestMapping("/th")
public class ThController {

	final static Logger logger = Logger.getLogger(ThController.class);

	@Autowired
	private ThService thService;

	/**
	 * @param thService
	 *            the thService to set
	 */
	public void setThService(ThService thService) {
		this.thService = thService;
	}

	@RequestMapping(value = "/saveAndUpdateThTransactionalData", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result saveAndUpdateThData(@RequestBody MonthlyDataTh monthlyDataTh) throws Exception {
		logger.info("Entered ThController:saveAndUpdateLabDialysis");
		Result result = thService.saveAndUpdateThTransactionalData(monthlyDataTh);
		logger.info("Exited ThController:saveAndUpdateLabDialysis");
		return result;

	}
	

	@RequestMapping(value = "/fetchOpIpDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result fetchOpIpDetails(@RequestBody Long hospitalId) throws Exception {
		logger.info("Entered ThController: fetchOpIpDetails");
		Result result = thService.fetchOpIpDetails(hospitalId);
		logger.info("Exited ThController: fetchOpIpDetails");
		return result;
	}
	

	@RequestMapping(value = "/fetchDeptOpIpDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result fetchDeptOpIpDetails(@RequestBody Long hospitalId) throws Exception {
		logger.info("Entered ThController: fetchDeptOpIpDetails");
		Result result = thService.fetchDeptOpIpDetails(hospitalId);
		logger.info("Exited ThController: fetchDeptOpIpDetails");
		return result;
	}
	
	@RequestMapping(value = "/fetchSurgeryDetailsThDhGh", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result fetchSurgeryDetailsThDhGh(@RequestBody Long hospitalId) throws Exception {
		logger.info("Entered ThController: fetchDeptOpIpDetails");
		Result result = thService.fetchSurgeryDetailsThDhGh(hospitalId);
		logger.info("Exited ThController: fetchDeptOpIpDetails");
		return result;
	}
	
	@RequestMapping(value = "/fetchSpecialityClinicData", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result fetchSpecialityClinicData(@RequestBody Long hospitalId) throws Exception {
		logger.info("Entered ThController: fetchDeptOpIpDetails");
		Result result = thService.fetchSpecialityClinicData(hospitalId);
		logger.info("Exited ThController: fetchDeptOpIpDetails");
		return result;
	}
	
	@RequestMapping(value = "/fetchLabDialysis", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result fetchLabDialysis(@RequestBody Long hospitalId) throws Exception {
		logger.info("Entered ThController: fetchDeptOpIpDetails");
		Result result = thService.fetchLabDialysis(hospitalId);
		logger.info("Exited ThController: fetchDeptOpIpDetails");
		return result;
	}

	@RequestMapping(value = "/fetchFundExpenditure", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result fetchFundExpenditure(@RequestBody Long hospitalId) throws Exception {
		logger.info("Entered ThController: fetchDeptOpIpDetails");
		Result result = thService.fetchFundExpenditure(hospitalId);
		logger.info("Exited ThController: fetchDeptOpIpDetails");
		return result;
	}
	
	@RequestMapping(value = "/fetchServiceAreaOthers", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result fetchServiceAreaOthers(@RequestBody Long hospitalId) throws Exception {
		logger.info("Entered ThController: fetchDeptOpIpDetails");
		Result result = thService.fetchServiceAreaOthers(hospitalId);
		logger.info("Exited ThController: fetchDeptOpIpDetails");
		return result;
	}
	
	@RequestMapping(value = "/fetchIdlingMajorEquipment", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result fetchIdlingMajorEquipment(@RequestBody Long hospitalId) throws Exception {
		logger.info("Entered ThController: fetchDeptOpIpDetails");
		Result result = thService.fetchIdlingMajorEquipment(hospitalId);
		logger.info("Exited ThController: fetchDeptOpIpDetails");
		return result;
	}
}
