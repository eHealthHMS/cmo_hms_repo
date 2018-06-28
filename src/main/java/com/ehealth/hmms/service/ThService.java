package com.ehealth.hmms.service;

import java.util.Map;

import com.ehealth.hmms.pojo.MonthlyDataTh;
import com.ehealth.hmms.pojo.Result;

public interface ThService {
	
	Map<String,String>  getThalukBasicData(Long nin)throws Exception;

	Result saveAndUpdateThTransactionalData(MonthlyDataTh monthlyDataTh)throws Exception;

	Result fetchOpIpDetails(Long hospitalId) throws Exception;
	
	Result fetchDeptOpIpDetails(Long hospitalId) throws Exception;
	
	Result fetchSurgeryDetailsThDhGh(Long hospitalId) throws Exception;
	
	Result fetchSpecialityClinicData(Long hospitalId) throws Exception;

	Result fetchLabDialysis(Long hospitalId) throws Exception;
	
	Result fetchFundExpenditure(Long hospitalId) throws Exception;
	 
	Result fetchServiceAreaOthers(Long hospitalId) throws Exception;
	
	Map<String,String> getDashboardSummaryForThaluk(Long nin) throws Exception;

	Result fetchIdlingMajorEquipment(Long hospitalId) throws Exception;
}
