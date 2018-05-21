package com.ehealth.hmms.dao;

import com.ehealth.hmms.pojo.MonthlyDataFhcChc;

public interface PhcDao {


	
	Integer saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc)  throws Exception;

	MonthlyDataFhcChc fetchPhcRecord(Long hospitalId, int month)throws Exception;
	
//	Hospital getMonthlyData(Integer hospitalId)  throws Exception;
}
