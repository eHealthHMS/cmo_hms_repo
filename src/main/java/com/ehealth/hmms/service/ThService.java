package com.ehealth.hmms.service;

import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.Result;

public interface ThService {
	
	Result saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception;
	
	Result saveOrUpdateDeptWiseIpOpDetails(DepartmentWiseOpIp departmentWiseOpIp) throws Exception;

}
