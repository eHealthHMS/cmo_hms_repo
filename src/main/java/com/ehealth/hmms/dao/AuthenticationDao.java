package com.ehealth.hmms.dao;

import com.ehealth.hmms.pojo.Users;

public interface AuthenticationDao {

	Users authenticate(Users user) throws Exception;

	// Hospital getMonthlyData(Integer hospitalId) throws Exception;
}
