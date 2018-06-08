package com.ehealth.hmms.dao;

import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.OpIpDetails;

public interface ThDao {

	Boolean saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception;

	Boolean saveOrUpdateDeptWiseIpOpDetails(DepartmentWiseOpIp departmentWiseOpIp) throws Exception;

	//Boolean saveOrUpdateSpecialityClinic(SpecialityClinicData specialityClinicData)throws Exception;
}
