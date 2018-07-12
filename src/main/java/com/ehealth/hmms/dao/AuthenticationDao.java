package com.ehealth.hmms.dao;

import com.ehealth.hmms.pojo.HospitalImage;
import com.ehealth.hmms.pojo.Users;

public interface AuthenticationDao {

	Users authenticate(Users user) throws Exception;
	
	Boolean uploadImage(HospitalImage hospitalImage) throws Exception;

	Users changePassword(Users user) throws Exception;
}
