package com.ehealth.hmms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.ThDao;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.service.ThService;
import com.ehealth.hmms.util.Constants;


@Service
public class ThServiceImpl implements ThService{

	@Autowired
	private ThDao thDao;
	
	

//	public ThServiceImpl() {
//		thDao = new ThDaoImpl();
//	}

	/**
	 * @param thDao the thDao to set
	 */
	public void setThDao(ThDao thDao) {
		this.thDao = thDao;
	}

	public Result saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception {
		Result result = new Result();
		try {

			HospitalMonthlyTracker hospitalMonthlyTracker = opIpDetails.getHospitalMonthlyTracker();
			Long hospitalId = hospitalMonthlyTracker.getHospital().getId();

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
}
