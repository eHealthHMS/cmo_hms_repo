package com.ehealth.hmms.service;

import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.ServiceAreaThDhGh;

public interface ThService {
	
	Result saveAndUpdateOpIpDetails(ServiceAreaThDhGh serviceAreaThDhGh) throws Exception;

}
