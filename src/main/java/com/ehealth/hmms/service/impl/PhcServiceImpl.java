package com.ehealth.hmms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.pojo.CategoryDetails;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.service.PhcService;
import com.ehealth.hmms.util.Constants;

@Service
public class PhcServiceImpl implements PhcService {

	final static Logger logger = Logger.getLogger(PhcServiceImpl.class);

	 @Autowired
	private PhcDao phcDao;



	/**
	 * @param phcDao the phcDao to set
	 */
	public void setPhcDao(PhcDao phcDao) {
		this.phcDao = phcDao;
	}

	public Result savePhcTransactionalData(MonthlyDataFhcChc dataFhcChc) throws Exception {
		logger.info("Entered PhcServiceImpl:savePhcTransactionalData");

		Result result = new Result();
		
		try {
			HospitalMonthlyTracker hospitalMonthlyTracker=phcDao.getHospitalTrakerForSave(dataFhcChc.getHospitalMonthlyTracker());
			dataFhcChc.setHospitalMonthlyTracker(hospitalMonthlyTracker);
				Integer type = dataFhcChc.getType();
				switch (type) {
				case 1: {
					 result = phcDao.saveInstitutionalData(dataFhcChc);
					 phcDao.updateHospitalMonthlyTracker(dataFhcChc.getHospitalMonthlyTracker(),false);
					break;
				}
				case 2: {
					result = phcDao.saveMeetings(dataFhcChc);
					 phcDao.updateHospitalMonthlyTracker(dataFhcChc.getHospitalMonthlyTracker(),false);
					break;
				}
				case 3: {
					result = phcDao.saveSubCentreDetails(dataFhcChc);
					 phcDao.updateHospitalMonthlyTracker(dataFhcChc.getHospitalMonthlyTracker(),false);
					break;
				}
				case 4: {
					result = phcDao.saveFieldWorks(dataFhcChc);
					 phcDao.updateHospitalMonthlyTracker(dataFhcChc.getHospitalMonthlyTracker(),false);
					break;
				}
				case 5: {
					result = phcDao.saveInstitutionalData(dataFhcChc);
					if(result.getStatus().equals(Constants.FAILURE_STATUS)) {
						break;
					}
					result = phcDao.saveMeetings(dataFhcChc);
					if(result.getStatus().equals(Constants.FAILURE_STATUS)) {
						
						break;
					}
					result = phcDao.saveSubCentreDetails(dataFhcChc);
					if(result.getStatus().equals(Constants.FAILURE_STATUS)) {
						break;
					}
					result = phcDao.saveFieldWorks(dataFhcChc);
					
					if(result.getStatus().equals(Constants.SUCCESS_STATUS)) {
						//todo update final submit status
					//	hospitalMonthlyTracker.
						phcDao.updateHospitalMonthlyTracker(dataFhcChc.getHospitalMonthlyTracker(),true);
						break;
					}
					break;
				}
				default: {
					result.setStatus(Constants.FAILURE_STATUS);
					return result;

				}
				}

		
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited PhcServiceImpl:savePhcTransactionalData");
		return result;
	}

	
	public Result getPhcDynamicDataForDistrict(Long districtId) throws Exception {
		logger.info("Entered PhcServiceImpl:getPhcDynamicDataForDistrict");
		Result result = new Result();
		try {
			List<Map<String,String>> resultList = phcDao.getPhcDynamicDataForDistrict(districtId);

			if (resultList != null && !resultList.isEmpty()) {
				
				result.setValue(resultList);

				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited PhcServiceImpl:getPhcDynamicDataForDistrict");

		return result;

	}
	
	public Result getPhcDynamicDataForState() throws Exception {
		logger.info("Entered PhcServiceImpl: getPhcDynamicDataForState");
		Result result = new Result();
		try {
			List<Map<String,String>> resultList = phcDao.getPhcDynamicDataForState();

			if (resultList != null && !resultList.isEmpty()) {
				
				result.setValue(resultList);

				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited PhcServiceImpl: getPhcDynamicDataForState");

		return result;

	}
	
	
	public Result getPhcStaticDataForDistrict(Long districtId) throws Exception {
		Result result = new Result();
		try {
			List<CategoryDetails> categoryDetailsList = phcDao.getPhcStaticDataForDistrict(districtId);

			if (categoryDetailsList != null && !categoryDetailsList.isEmpty()) {

				result.setValue(categoryDetailsList);
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		return result;

	}
	
	public Result getPhcStaticData(Long nin) throws Exception {
		Result result = new Result();
		try {
			List<CategoryDetails> categoryDetailsList = phcDao.getPhcStaticData(nin);

			if (categoryDetailsList != null && !categoryDetailsList.isEmpty()) {

				result.setValue(categoryDetailsList);
				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		return result;

	}
	
	public Result getPhcDynamicDataFromHospitalId(Long hospitalId) throws Exception {
		logger.info("Entered PhcServiceImpl:getPhcDynamicDataFromHospitalId");
		Result result = new Result();
		try {
			List<Map<String,String>> resultList = phcDao.getPhcDynamicDataFromHospitalId(hospitalId);

			if (resultList != null && !resultList.isEmpty()) {
				
				result.setValue(resultList);

				result.setStatus(Constants.SUCCESS_STATUS);

			} else {
				result.setStatus(Constants.FAILURE_STATUS);
				result.setErrorMessage("Data not available");
			}
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		logger.info("Exited PhcServiceImpl:getPhcDynamicDataFromHospitalId");

		return result;

	}
	
	
	
	public Map<String,String>  getDataForMap( Long hospitalId) throws Exception {
	
	return phcDao.getDataForMap(hospitalId);
	

}
		

}
