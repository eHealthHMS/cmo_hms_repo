package com.ehealth.hmms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.ThDao;
import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.LabDialysis;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinicData;
import com.ehealth.hmms.service.ThService;
import com.ehealth.hmms.util.Constants;


@Service
public class ThServiceImpl implements ThService{
	
	final static Logger logger = Logger.getLogger(ThServiceImpl.class);

	@Autowired
	private ThDao thDao;
	

	/**
	 * @param thDao the thDao to set
	 */
	public void setThDao(ThDao thDao) {
		this.thDao = thDao;
	}

	public Result saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception {
		logger.info("Entered ThServiceImpl: saveAndUpdateOpIpDetails");

		Result result = new Result();
		try {

			Boolean resultFlag = thDao.saveAndUpdateOpIpDetails(opIpDetails);

			if (resultFlag.equals(true)) {
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: saveAndUpdateOpIpDetails");

		return result;
	}
	
	public Result saveOrUpdateDeptWiseIpOpDetails(List<DepartmentWiseOpIp> departmentWiseOpIpList) throws Exception{
		logger.info("Entered ThServiceImpl: saveOrUpdateDeptWiseIpOpDetails");

		Result result = new Result();
		try {

			Boolean resultFlag = thDao.saveOrUpdateDeptWiseIpOpDetails(departmentWiseOpIpList);


			if (resultFlag.equals(true)) {
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: saveOrUpdateDeptWiseIpOpDetails");

		return result;
		
	}
	
	public Result saveOrUpdateServiceAreaOthers(ServiceAreaOthers serviceAreaOthers) throws Exception{
		logger.info("Entered ThServiceImpl: saveOrUpdateServiceAreaOthers");

		Result result = new Result();
		try {

			Boolean resultFlag = thDao.saveOrUpdateServiceAreaOthers(serviceAreaOthers);


			if (resultFlag.equals(true)) {
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		
		logger.info("Exited ThServiceImpl: saveOrUpdateServiceAreaOthers");

		return result;
		
	}
	
	public Result saveOrUpdateFundExpenditure(FundExpenditure fundExpenditure) throws Exception{
		logger.info("Entered ThServiceImpl: saveOrUpdateFundExpenditure");

		Result result = new Result();
		try {

			Boolean resultFlag = thDao.saveOrUpdateFundExpenditure(fundExpenditure);

			if (resultFlag.equals(true)) {
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: saveOrUpdateFundExpenditure");

		return result;
	}
	
	public Result saveAndUpdateSpecialityClinicData(List<SpecialityClinicData> specialityClinicDataList) throws Exception{
		logger.info("Entered ThServiceImpl: saveAndUpdateSpecialityClinicData");

		Result result = new Result();
		try {

			Boolean resultFlag = thDao.saveAndUpdateSpecialityClinicData(specialityClinicDataList);


			if (resultFlag.equals(true)) {
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: saveAndUpdateSpecialityClinicData");

		return result;
	}
	
	public Result saveAndUpdateLabDialysis(LabDialysis labDialysis) throws Exception{
		logger.info("Entered ThServiceImpl: saveAndUpdateLabDialysis");

		Result result = new Result();
		try {

			Boolean resultFlag = thDao.saveAndUpdateLabDialysis(labDialysis);


			if (resultFlag.equals(true)) {
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: saveAndUpdateLabDialysis");

		return result;
	}
	
	public Map<String,String> getThalukBasicData(Long nin) throws Exception {
		
		return thDao.getThalukBasicData(nin);
	}
	
}
