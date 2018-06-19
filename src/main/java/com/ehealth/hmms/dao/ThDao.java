package com.ehealth.hmms.dao;

import java.util.List;
import java.util.Map;

import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.LabDialysis;
import com.ehealth.hmms.pojo.MonthlyDataTh;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinicData;

public interface ThDao {

	Boolean saveAndUpdateOpIpDetails(OpIpDetails opIpDetails, HospitalMonthlyTracker hospitalMonthlyTrackerId) throws Exception;

	Boolean saveOrUpdateDeptWiseIpOpDetails(List<DepartmentWiseOpIp> departmentWiseOpIpList, HospitalMonthlyTracker hospitalMonthlyTrackerId) throws Exception;

	Boolean saveOrUpdateServiceAreaOthers(ServiceAreaOthers serviceAreaOthers, HospitalMonthlyTracker hospitalMonthlyTrackerId) throws Exception;

	Boolean saveOrUpdateFundExpenditure(FundExpenditure fundExpenditure, HospitalMonthlyTracker hospitalMonthlyTrackerId) throws Exception;

	Boolean saveAndUpdateSpecialityClinicData(List<SpecialityClinicData> specialityClinicDataList, HospitalMonthlyTracker hospitalMonthlyTrackerId) throws Exception;

	Boolean saveAndUpdateLabDialysis(LabDialysis labDialysis, HospitalMonthlyTracker hospitalMonthlyTrackerId) throws Exception;

	MonthlyDataTh fetchMonthlyDataTh(Long hospitalId) throws Exception;

	Map<String, String> getThalukBasicData(Long nin) throws Exception;

	Boolean saveAndUpdateThTransactionalData(MonthlyDataTh monthlyDataTh) throws Exception;

}
