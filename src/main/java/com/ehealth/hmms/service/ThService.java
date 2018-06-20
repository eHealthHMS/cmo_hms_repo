package com.ehealth.hmms.service;

import java.util.List;
import java.util.Map;

import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.LabDialysis;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinicData;

public interface ThService {
	
	Result saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception;
	
	Result saveOrUpdateDeptWiseIpOpDetails(List<DepartmentWiseOpIp> departmentWiseOpIpList) throws Exception;
	
	Result saveOrUpdateServiceAreaOthers(ServiceAreaOthers serviceAreaOthers) throws Exception;
	
	Result saveOrUpdateFundExpenditure(FundExpenditure fundExpenditure) throws Exception;

	Result saveAndUpdateSpecialityClinicData(List<SpecialityClinicData> specialityClinicData)throws Exception;
	
	Result saveAndUpdateLabDialysis(LabDialysis labDialysis) throws Exception;
	
	Map<String,String>  getThalukBasicData(Long nin)throws Exception;
	
	Map<String,String> getDashboardSummaryForThaluk(Long nin) throws Exception;

}
