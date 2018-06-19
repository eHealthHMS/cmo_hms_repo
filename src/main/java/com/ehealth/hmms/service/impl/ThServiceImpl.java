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
import com.ehealth.hmms.pojo.MonthlyDataTh;
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


	
	public Result saveAndUpdateThTransactionalData(MonthlyDataTh monthlyDataTh) throws Exception{
		logger.info("Entered ThServiceImpl: saveAndUpdateLabDialysis");

		Result result = new Result();
		try {

			Boolean resultFlag = thDao.saveAndUpdateThTransactionalData(monthlyDataTh);


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



	public Result saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	public Result saveOrUpdateDeptWiseIpOpDetails(List<DepartmentWiseOpIp> departmentWiseOpIpList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	public Result saveOrUpdateServiceAreaOthers(ServiceAreaOthers serviceAreaOthers) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	public Result saveOrUpdateFundExpenditure(FundExpenditure fundExpenditure) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	public Result saveAndUpdateSpecialityClinicData(List<SpecialityClinicData> specialityClinicData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	public Result saveAndUpdateLabDialysis(LabDialysis labDialysis) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
