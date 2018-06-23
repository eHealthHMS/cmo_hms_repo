package com.ehealth.hmms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.dao.ThDao;
import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.IdlingMajorEquipment;
import com.ehealth.hmms.pojo.LabDialysis;
import com.ehealth.hmms.pojo.MonthlyDataTh;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinicData;
import com.ehealth.hmms.pojo.SurgeryDetailsThDhGh;
import com.ehealth.hmms.service.ThService;
import com.ehealth.hmms.util.Constants;


@Service
public class ThServiceImpl implements ThService{
	
	final static Logger logger = Logger.getLogger(ThServiceImpl.class);

	@Autowired
	private ThDao thDao;
	
	@Autowired
	private PhcDao phcDao;

	/**
	 * @param thDao the thDao to set
	 */
	public void setThDao(ThDao thDao) {
		this.thDao = thDao;
	}

	public PhcDao getPhcDao() {
		return phcDao;
	}

	public void setPhcDao(PhcDao phcDao) {
		this.phcDao = phcDao;
	}

	public Result saveAndUpdateThTransactionalData(MonthlyDataTh monthlyDataTh) throws Exception{
		logger.info("Entered ThServiceImpl: saveAndUpdateLabDialysis");

		Result result = new Result();
		try {
			HospitalMaster hospital = monthlyDataTh.getHospitalMonthlyTracker().getHospital();
			if(monthlyDataTh.getHospitalMonthlyTracker() == null || (monthlyDataTh.getHospitalMonthlyTracker() != null && monthlyDataTh.getHospitalMonthlyTracker().getId() !=null && monthlyDataTh.getHospitalMonthlyTracker().getId() == -1) || (monthlyDataTh.getHospitalMonthlyTracker() != null && monthlyDataTh.getHospitalMonthlyTracker().getId() == null))
			{
				HospitalMonthlyTracker hospitalMonthlyTracker = new HospitalMonthlyTracker();
				hospitalMonthlyTracker.setHospital(hospital);
				hospitalMonthlyTracker = phcDao.getHospitalTrakerForSave(hospitalMonthlyTracker);
				monthlyDataTh.setHospitalMonthlyTracker(hospitalMonthlyTracker);
			}
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

	public Result fetchOpIpDetails(Long hospitalId) throws Exception {
		logger.info("Entered ThServiceImpl: fetchOpIpDetails");

		Result result = new Result();
		try {
			OpIpDetails resultList = thDao.fetchOpIpDetails(hospitalId);

			if (resultList != null) {	
				result.setValue(resultList);
				result.setStatus(Constants.SUCCESS_STATUS);
			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: fetchOpIpDetails");

		return result;
	}

	public Result fetchDeptOpIpDetails(Long hospitalId) throws Exception {
		logger.info("Entered ThServiceImpl: fetchDeptOpIpDetails");

		Result result = new Result();
		try {
			List<DepartmentWiseOpIp> resultList = thDao.fetchDeptOpIpDetails(hospitalId);

			if (resultList != null) {	
				result.setValue(resultList);
				result.setStatus(Constants.SUCCESS_STATUS);
			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: fetchDeptOpIpDetails");
		return result;
	}

	public  Result fetchSurgeryDetailsThDhGh(Long hospitalId) throws Exception {
		logger.info("Entered ThServiceImpl: fetchSurgeryDetailsThDhGh");

		Result result = new Result();
		try {
			List<SurgeryDetailsThDhGh> resultList = thDao.fetchSurgeryDetailsThDhGh(hospitalId);

			if (resultList != null) {	
				result.setValue(resultList);
				result.setStatus(Constants.SUCCESS_STATUS);
			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: fetchSurgeryDetailsThDhGh");
		return result;
	}

	public Result fetchSpecialityClinicData(Long hospitalId) throws Exception {
		logger.info("Entered ThServiceImpl: fetchSpecialityClinicData");

		Result result = new Result();
		try {
			List<SpecialityClinicData> resultList = thDao.fetchSpecialityClinicData(hospitalId);

			if (resultList != null) {	
				result.setValue(resultList);
				result.setStatus(Constants.SUCCESS_STATUS);
			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: fetchSpecialityClinicData");
		return result;
	}

	public Result fetchLabDialysis(Long hospitalId) throws Exception {
		logger.info("Entered ThServiceImpl: fetchLabDialysis");

		Result result = new Result();
		try {
			LabDialysis resultList = thDao.fetchLabDialysis(hospitalId);

			if (resultList != null) {	
				result.setValue(resultList);
				result.setStatus(Constants.SUCCESS_STATUS);
			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: fetchLabDialysis");
		return result;
	}

	public Result fetchFundExpenditure(Long hospitalId) throws Exception {
		logger.info("Entered ThServiceImpl: fetchFundExpenditure");

		Result result = new Result();
		try {
			FundExpenditure resultList = thDao.fetchFundExpenditure(hospitalId);

			if (resultList != null) {	
				result.setValue(resultList);
				result.setStatus(Constants.SUCCESS_STATUS);
			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: fetchFundExpenditure");
		return result;
	}

	public Result fetchServiceAreaOthers(Long hospitalId) throws Exception {
		logger.info("Entered ThServiceImpl: fetchServiceAreaOthers");

		Result result = new Result();
		try {
			ServiceAreaOthers resultList = thDao.fetchServiceAreaOthers(hospitalId);

			if (resultList != null) {	
				result.setValue(resultList);
				result.setStatus(Constants.SUCCESS_STATUS);
			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: fetchServiceAreaOthers");
		return result;
	}

	public Result fetchIdlingMajorEquipment(Long hospitalId) throws Exception {
		logger.info("Entered ThServiceImpl: fetchIdlingMajorEquipment");

		Result result = new Result();
		try {
			List<IdlingMajorEquipment> resultList = thDao.fetchIdlingMajorEquipment(hospitalId);

			if (resultList != null) {	
				result.setValue(resultList);
				result.setStatus(Constants.SUCCESS_STATUS);
			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}

		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited ThServiceImpl: fetchIdlingMajorEquipment");
		return result;
	}

	public Map<String, String> getThalukBasicData(Long nin) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
