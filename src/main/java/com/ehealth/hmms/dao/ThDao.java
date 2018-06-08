package com.ehealth.hmms.dao;

import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.SpecialityClinicData;

public interface ThDao {

	Boolean saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception;

	Boolean saveAndUpdateSpecialityClinicData(SpecialityClinicData specialityClinicData) throws Exception ;
}
