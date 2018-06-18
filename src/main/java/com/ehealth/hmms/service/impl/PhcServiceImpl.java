package com.ehealth.hmms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.service.PhcService;
import com.ehealth.hmms.util.Constants;

@Service
public class PhcServiceImpl implements PhcService {

	 @Autowired
	private PhcDao phcDao;



	/**
	 * @param phcDao the phcDao to set
	 */
	public void setPhcDao(PhcDao phcDao) {
		this.phcDao = phcDao;
	}

	public Result savePhcTransactionalData(MonthlyDataFhcChc dataFhcChc) throws Exception {
		Result result = new Result();
		
		try {
			HospitalMonthlyTracker hospitalMonthlyTracker=phcDao.getHospitalTrakerForSave(dataFhcChc.getHospitalMonthlyTracker());
			dataFhcChc.setHospitalMonthlyTracker(hospitalMonthlyTracker);
				Integer type = dataFhcChc.getType();
				switch (type) {
				case 1: {
					 result = phcDao.saveInstitutionalData(dataFhcChc);
					break;
				}
				case 2: {
					result = phcDao.saveMeetings(dataFhcChc);
					break;
				}
				case 3: {
					result = phcDao.saveSubCentreDetails(dataFhcChc);
					break;
				}
				case 4: {
					result = phcDao.saveFieldWorks(dataFhcChc);
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
					//	phcDao.updateHospitalMonthlyTracker(dataFhcChc.getHospitalMonthlyTracker(),true);
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
		return result;
	}

//	public Result getPhcStaticData(String hospitalId) throws Exception {
//		Result result = new Result();
//		try {
//			List<CategoryDetails> categoryDetailsList = phcDao.getPhcStaticData(hospitalId);
//
//			if (categoryDetailsList != null && !categoryDetailsList.isEmpty()) {
//
//				result.setValue(categoryDetailsList);
//				result.setStatus(Constants.SUCCESS_STATUS);
//
//			} else {
//				result.setStatus(Constants.FAILURE_STATUS);
//				result.setErrorMessage("Data not available");
//			}
//		} catch (Exception e) {
//			result.setStatus(Constants.FAILURE_STATUS);
//		}
//		return result;
//
//	}
//	
//	
//	
//	
//	
//	
//	
	public Result getPhcDynamicDataFromHospitalId(Long hospitalId)  throws Exception {
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
		return result;

	}
	
	
	
	public Map<String,String>  getDataForMap( Long hospitalId) throws Exception {
	
	return phcDao.getDataForMap(hospitalId);
	

}
		

}
