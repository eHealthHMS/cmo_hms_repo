package com.ehealth.hmms.dao;

import java.util.List;

import com.ehealth.hmms.pojo.CategoryDetails;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;

public interface PhcDao {


	
	Integer saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc)  throws Exception;
	
//	Hospital getMonthlyData(Integer hospitalId)  throws Exception;
	
	List<CategoryDetails> getPhcStaticData(String hospitalId) throws Exception;
}
