package com.ehealth.hmms.dao;

import java.util.List;

import com.ehealth.hmms.pojo.CategoryDetails;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;

public interface PhcDao {

	Result saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc)  throws Exception;
	
	MonthlyDataFhcChc fetchPhcRecord(Long hospitalId, String date)throws Exception;

	List<CategoryDetails> getPhcStaticData(String hospitalId) throws Exception;
	
	List<MonthlyDataFhcChc> getPhcDynamicDataForDashboard(String hospitalId) throws Exception;

}
