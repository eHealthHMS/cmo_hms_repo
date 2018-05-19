package com.ehealth.hmms.service.impl;

import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.dao.impl.PhcDaoImpl;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.service.PhcService;
import com.ehealth.hmms.util.Constants;

@Service
public class PhcServiceImpl implements PhcService {

	// @Autowired
	private PhcDao phcDao;

	public Result saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc) throws Exception {
		Result result = new Result();
		phcDao = new PhcDaoImpl();
		try {
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

}
