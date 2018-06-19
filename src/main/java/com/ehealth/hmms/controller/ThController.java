package com.ehealth.hmms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.LabDialysis;
import com.ehealth.hmms.pojo.MonthlyDataTh;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
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

	@RequestMapping(value = "/saveAndUpdateOpIpDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result saveAndUpdateOpIpDetails(@RequestBody OpIpDetails opIpDetails) throws Exception {
		logger.info("Entered ThController:saveAndUpdateOpIpDetails");
		Result result = thService.saveAndUpdateOpIpDetails(opIpDetails);
		logger.info("Exited ThController:saveAndUpdateOpIpDetails");
		return result;

	}

	@RequestMapping(value = "/saveOrUpdateDeptWiseIpOpDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result saveOrUpdateDeptWiseIpOpDetails(@RequestBody List<DepartmentWiseOpIp> departmentWiseOpIpList)
			throws Exception {

		logger.info("Entered ThController:saveAndUpdateOpIpDetails");
		Result result = thService.saveOrUpdateDeptWiseIpOpDetails(departmentWiseOpIpList);
		logger.info("Exited ThController:saveAndUpdateOpIpDetails");
		return result;

	}

	@RequestMapping(value = "/saveOrUpdateServiceAreaOthers", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result saveOrUpdateServiceAreaOthers(@RequestBody ServiceAreaOthers serviceAreaOthers) throws Exception {
		logger.info("Entered ThController:saveOrUpdateServiceAreaOthers");
		Result result = thService.saveOrUpdateServiceAreaOthers(serviceAreaOthers);
		logger.info("Exited ThController:saveOrUpdateServiceAreaOthers");
		return result;

	}

	@RequestMapping(value = "/saveOrUpdateFundExpenditure", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result saveOrUpdateFundExpenditure(@RequestBody FundExpenditure fundExpenditure) throws Exception {
		logger.info("Entered ThController:saveOrUpdateFundExpenditure");
		Result result = thService.saveOrUpdateFundExpenditure(fundExpenditure);
		logger.info("Exited ThController:saveOrUpdateFundExpenditure");
		return result;

	}

	@RequestMapping(value = "/saveAndUpdateLabDialysis", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result saveAndUpdateLabDialysis(@RequestBody LabDialysis labDialysis) throws Exception {
		logger.info("Entered ThController:saveAndUpdateLabDialysis");
		Result result = thService.saveAndUpdateLabDialysis(labDialysis);
		logger.info("Exited ThController:saveAndUpdateLabDialysis");
		return result;

	}
	
	@RequestMapping(value = "/saveAndUpdateThTransactionalData", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result saveAndUpdateThData(@RequestBody MonthlyDataTh monthlyDataTh) throws Exception {
		logger.info("Entered ThController:saveAndUpdateLabDialysis");
		Result result = thService.saveAndUpdateThTransactionalData(monthlyDataTh);
		logger.info("Exited ThController:saveAndUpdateLabDialysis");
		return result;

	}

}
