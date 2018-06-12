package com.ehealth.hmms.dao;

import java.util.List;

import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.LabDialysis;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinicData;

public interface ThDao {

	Boolean saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception;

	Boolean saveOrUpdateDeptWiseIpOpDetails(List<DepartmentWiseOpIp> departmentWiseOpIpList) throws Exception;
	
	Boolean saveOrUpdateServiceAreaOthers(ServiceAreaOthers serviceAreaOthers) throws Exception;
	
	Boolean saveOrUpdateFundExpenditure(FundExpenditure fundExpenditure) throws Exception;

	Boolean saveAndUpdateSpecialityClinicData(List<SpecialityClinicData> specialityClinicDataList)throws Exception;
	
	Boolean saveAndUpdateLabDialysis(LabDialysis labDialysis) throws Exception;
	Boolean saveAndUpdateSpecialityClinicData(SpecialityClinicData specialityClinicData) throws Exception ;

	OpIpDetails fetchOpIpDetails(Long hospitalId)throws Exception;
}
