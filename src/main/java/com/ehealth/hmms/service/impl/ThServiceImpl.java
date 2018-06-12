package com.ehealth.hmms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.ThDao;
import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.LabDialysis;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinicData;
import com.ehealth.hmms.service.ThService;
import com.ehealth.hmms.util.Constants;


@Service
public class ThServiceImpl implements ThService{

	@Autowired
	private ThDao thDao;
	

	/**
	 * @param thDao the thDao to set
	 */
	public void setThDao(ThDao thDao) {
		this.thDao = thDao;
	}

	public Result saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception {
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
		return result;
	}
	
	public Result saveOrUpdateDeptWiseIpOpDetails(List<DepartmentWiseOpIp> departmentWiseOpIpList) throws Exception{
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
		return result;
		
	}
	
	public Result saveOrUpdateServiceAreaOthers(ServiceAreaOthers serviceAreaOthers) throws Exception{
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
		return result;
		
	}
	
	public Result saveOrUpdateFundExpenditure(FundExpenditure fundExpenditure) throws Exception{
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
		return result;
	}
	
	public Result saveAndUpdateSpecialityClinicData(List<SpecialityClinicData> specialityClinicDataList) throws Exception{
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
		return result;
	}
	
	public Result saveAndUpdateLabDialysis(LabDialysis labDialysis) throws Exception{
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
		return result;
	}
	
}
