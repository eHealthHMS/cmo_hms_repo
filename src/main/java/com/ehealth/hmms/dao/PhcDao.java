package com.ehealth.hmms.dao;

import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Users;

public interface PhcDao {


	
	Integer saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc)  throws Exception;
	
//	Hospital getMonthlyData(Integer hospitalId)  throws Exception;
}
