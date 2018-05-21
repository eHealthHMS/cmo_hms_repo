package com.ehealth.hmms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.dao.impl.PhcDaoImpl;
import com.ehealth.hmms.pojo.CategoryDetails;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.service.PhcService;
import com.ehealth.hmms.util.Constants;

@Service
public class PhcServiceImpl implements PhcService {

	// @Autowired
	private PhcDao phcDao;

	public PhcServiceImpl() {
		phcDao = new PhcDaoImpl();
	}

	public Result saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc) throws Exception {
		Result result = new Result();

		try {

			MonthlyDataFhcChc dataFhcChcToSave = new MonthlyDataFhcChc();

			HospitalMonthlyTracker hospitalMonthlyTracker = dataFhcChc.getHospitalMonthlyTracker();// new
																									// HospitalMonthlyTracker();
			Long hospitalId = hospitalMonthlyTracker.getHospital().getId();
			
			

			Integer type = dataFhcChc.getType();
			switch (type) {
			case 1: {

				break;
			}
			case 2: {
				break;
			}
			case 3: {
				break;
			}
			default: {
				result.setStatus(Constants.FAILURE_STATUS);
				return result;

			}
			}

			Integer resultFlag = phcDao.saveFunctionalComponents(dataFhcChc);

			if (resultFlag.equals(1)) {
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		return result;
	}

	public Result getPhcStaticData(String hospitalId) throws Exception {
		Result result = new Result();
		try {
			List<CategoryDetails> categoryDetailsList = phcDao.getPhcStaticData(hospitalId);

			if (categoryDetailsList != null) {

				result.setValue(categoryDetailsList);
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Invalid Credentials");
			}
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		return result;

	}

}
