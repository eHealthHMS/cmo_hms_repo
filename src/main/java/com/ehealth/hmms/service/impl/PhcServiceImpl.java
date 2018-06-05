package com.ehealth.hmms.service.impl;

import java.util.List;

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
			Long trackerId=phcDao.getHospitalTrakerForSave(dataFhcChc.getHospitalMonthlyTracker());
			if(trackerId==0) {
				result.setStatus(Constants.FAILURE_STATUS);
			}else {
				Integer type = dataFhcChc.getType();
				switch (type) {
				case 1: {
					 result = phcDao.saveInstitutionalData(dataFhcChc,trackerId);
					break;
				}
				case 2: {
					result = phcDao.saveMeetings(dataFhcChc, trackerId);
					break;
				}
				case 3: {
					result = phcDao.saveSubCentreDetails(dataFhcChc, trackerId);
					break;
				}
				case 4: {
					result = phcDao.saveFieldWorks(dataFhcChc, trackerId);
					break;
				}
				case 5: {
					result = phcDao.saveInstitutionalData(dataFhcChc,trackerId);
					if(result.getStatus().equals(Constants.FAILURE_STATUS)) {
						break;
					}
					result = phcDao.saveMeetings(dataFhcChc, trackerId);
					if(result.getStatus().equals(Constants.FAILURE_STATUS)) {
						break;
					}
					result = phcDao.saveSubCentreDetails(dataFhcChc, trackerId);
					if(result.getStatus().equals(Constants.FAILURE_STATUS)) {
						break;
					}
					result = phcDao.saveFieldWorks(dataFhcChc, trackerId);
					
					if(result.getStatus().equals(Constants.SUCCESS_STATUS)) {
						//todo update final submit status
						break;
					}
					break;
				}
				default: {
					result.setStatus(Constants.FAILURE_STATUS);
					return result;

				}
				}
			}
		
			
			
			

//			MonthlyDataFhcChc dataFhcChcToSave = new MonthlyDataFhcChc();
//
//			HospitalMonthlyTracker hospitalMonthlyTracker = dataFhcChc.getHospitalMonthlyTracker();// new
//																									// HospitalMonthlyTracker();
//			Long hospitalId = hospitalMonthlyTracker.getHospital().getId();
		
		//result = phcDao.saveFunctionalComponents(dataFhcChc);
		
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
		}
		return result;
	}

	public Result getPhcStaticData(String hospitalId) throws Exception {
		Result result = new Result();
		try {
			List<CategoryDetails> categoryDetailsList = phcDao.getPhcStaticData(hospitalId);

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
	
	
	public Result getPhcDynamicDataForDashboard(String hospitalId) throws Exception {
		Result result = new Result();
		try {
			List<MonthlyDataFhcChc> dataFhcChcs = phcDao.getPhcDynamicDataForDashboard(hospitalId);

			if (dataFhcChcs != null && !dataFhcChcs.isEmpty()) {
				
				result.setValue(dataFhcChcs);
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
	
	public Result getPhcDynamicDataFromHospitalId(Long hospitalId)  throws Exception {
		Result result = new Result();
		try {
			MonthlyDataFhcChc dataFhcChc = phcDao.getPhcDynamicDataFromHospitalId(hospitalId);

			if (dataFhcChc != null ) {
				
				result.setValue(dataFhcChc);
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
	
	
		

}
