package com.ehealth.hmms.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ehealth.hmms.dao.ThDao;
import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.LabDialysis;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinicData;

@Repository
@Transactional
public class ThDaoImpl implements ThDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Boolean saveOrUpdateDeptWiseIpOpDetails(List<DepartmentWiseOpIp> departmentWiseOpIpList) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		for(DepartmentWiseOpIp departmentWiseOpIp : departmentWiseOpIpList) {
		if (departmentWiseOpIp.getHospitalMonthlyTrackerId().getId() != null) {
			hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class,
					departmentWiseOpIp.getHospitalMonthlyTrackerId().getId());
		}
		if (hospitalMonthlyTracker == null) {
			hospitalMonthlyTracker = departmentWiseOpIp.getHospitalMonthlyTrackerId();
			// hospitalMonthlyTracker.setId(id)
			hospitalMonthlyTracker.setLastModified(new Date());
			hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
			session.saveOrUpdate(hospitalMonthlyTracker);
			departmentWiseOpIp.setHospitalMonthlyTrackerId(hospitalMonthlyTracker);
		} else {
			hospitalMonthlyTracker.setLastModified(new Date());
			session.saveOrUpdate(hospitalMonthlyTracker);
			departmentWiseOpIp.setHospitalMonthlyTrackerId(hospitalMonthlyTracker);
		}
		session.saveOrUpdate(departmentWiseOpIp);
		}
		return true;
	}

	
	public Boolean saveOrUpdateServiceAreaOthers(ServiceAreaOthers serviceAreaOthers) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		if (serviceAreaOthers.getHospitalMonthlyTracker().getId() != null) {
			hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class,
					serviceAreaOthers.getHospitalMonthlyTracker().getId());
		}
		if (hospitalMonthlyTracker == null) {
			hospitalMonthlyTracker = serviceAreaOthers.getHospitalMonthlyTracker();
			hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
			serviceAreaOthers.setHospitalMonthlyTracker(hospitalMonthlyTracker);
		} else {
			hospitalMonthlyTracker.setLastModified(new Date());
			serviceAreaOthers.setHospitalMonthlyTracker(hospitalMonthlyTracker);
		}
		session.saveOrUpdate(serviceAreaOthers);
		return true;
	}

	public Boolean saveOrUpdateFundExpenditure(FundExpenditure fundExpenditure) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		if (fundExpenditure.getHospitalMonthlyTracker().getId() != null) {
			hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class,
					fundExpenditure.getHospitalMonthlyTracker().getId());
		}
		if (hospitalMonthlyTracker == null) {
			hospitalMonthlyTracker = fundExpenditure.getHospitalMonthlyTracker();
			hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
			fundExpenditure.setHospitalMonthlyTracker(hospitalMonthlyTracker);

		} else {
			hospitalMonthlyTracker.setLastModified(new Date());
			fundExpenditure.setHospitalMonthlyTracker(hospitalMonthlyTracker);
		}
		session.saveOrUpdate(fundExpenditure);
		return true;
	}
	
	// Saving or updating the OP and IP details of taluk hospital.

	public Boolean saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();

		Boolean resultFlag = false;

		try {

			Long forenoonOpMale = opIpDetails.getForenoonOpMale();
			Long forenoonOpFemale = opIpDetails.getForenoonOpFemale();
			Long forenoonOpTg = opIpDetails.getForenoonOpTg();
			Long forenoonOpTotal = opIpDetails.getForenoonOpTotal();
			Long afternoonOpMale = opIpDetails.getAfternoonOpMale();
			Long afternoonOpFemale = opIpDetails.getAfternoonOpFemale();
			Long afternoonOpTg = opIpDetails.getAfternoonOpTg();
			Long afternoonOpTotal = opIpDetails.getAfternoonOpTotal();
			Long ipPatientsDischarged = opIpDetails.getIpPatientsDischarged();
			Long ipPatientsExpired = opIpDetails.getIpPatientsExpired();
			Long ipPatientsReferred = opIpDetails.getIpPatientsReferred();
			Long ipAdmissionsMale = opIpDetails.getIpAdmissionsMale();
			Long ipAdmissionsFemale = opIpDetails.getIpAdmissionsFemale();
			Long ipAdmissionsTotal = opIpDetails.getIpAdmissionsTotal();
			Long ipAdmissionsTg = opIpDetails.getIpAdmissionsTg();
			Long emrPatientReferred = opIpDetails.getEmrPatientReferred();
			Long emrRtaTrauma = opIpDetails.getEmrRtaTrauma();
			Long emrPatinetAttended = opIpDetails.getEmrPatinetAttended();
			Long emrPatientAdmited = opIpDetails.getEmrPatientAdmited();
			Long hospTrackId = opIpDetails.getHospitalMonthlyTracker().getId();

			String sql = "select * from op_ip_th_gh_dh op where op.hospmonthlytrack_id =:hospTrackId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospTrackId", hospTrackId);
			List<OpIpDetails> opIpQueryDetails = query.list();

			if (opIpQueryDetails != null && !opIpQueryDetails.isEmpty()) {
				if (opIpQueryDetails.get(0) != null) {
					String updateSql = "update op_ip_th_gh_dh set forenoon_op_male =:forenoonOpMale,forenoon_op_female=:forenoonOpFemale,"
							+ "forenoon_op_tg=:forenoonOpTg,forenoon_op_total=:forenoonOpTotal,afternoon_op_male=:afternoonOpMale,afternoon_op_tg=:afternoonOpTg,"
							+ "afternoon_op_total=:afternoonOpTotal,afternoon_op_female=:afternoonOpFemale,ip_admissions_male=:ipAdmissionsMale,"
							+ "ip_admissions_female=:ipAdmissionsFemale,ip_admissions_tg=:ipAdmissionsTg,ip_admissions_total=:ipAdmissionsTotal,"
							+ "ip_patients_discharged=:ipPatientsDischarged,ip_patients_expired=:ipPatientsExpired ,ip_patients_referred =:ipPatientsReferred,"
							+ "emr_patinet_attended =:emrPatinetAttended,emr_patient_admited =:emrPatientAdmited,emr_patient_referred =:emrPatientReferred,"
							+ "emr_rta_trauma =:emrRtaTrauma where hospmonthlytrack_id =:hospTrackId";
					Query updateQuery = session.createSQLQuery(updateSql);
					updateQuery.setParameter("forenoonOpMale", forenoonOpMale);
					updateQuery.setParameter("forenoonOpFemale", forenoonOpFemale);
					updateQuery.setParameter("forenoonOpTotal", forenoonOpTotal);
					updateQuery.setParameter("forenoonOpTg", forenoonOpTg);
					updateQuery.setParameter("afternoonOpMale", afternoonOpMale);
					updateQuery.setParameter("afternoonOpFemale", afternoonOpFemale);
					updateQuery.setParameter("afternoonOpTg", afternoonOpTg);
					updateQuery.setParameter("afternoonOpTotal", afternoonOpTotal);
					updateQuery.setParameter("ipPatientsDischarged", ipPatientsDischarged);
					updateQuery.setParameter("ipPatientsExpired", ipPatientsExpired);
					updateQuery.setParameter("ipPatientsReferred", ipPatientsReferred);
					updateQuery.setParameter("ipAdmissionsMale", ipAdmissionsMale);
					updateQuery.setParameter("ipAdmissionsFemale", ipAdmissionsFemale);
					updateQuery.setParameter("ipAdmissionsTotal", ipAdmissionsTotal);
					updateQuery.setParameter("ipAdmissionsTg", ipAdmissionsTg);
					updateQuery.setParameter("emrPatientReferred", emrPatientReferred);
					updateQuery.setParameter("emrRtaTrauma", emrRtaTrauma);
					updateQuery.setParameter("emrPatinetAttended", emrPatinetAttended);
					updateQuery.setParameter("emrPatientAdmited", emrPatientAdmited);
					updateQuery.setParameter("hospTrackId", hospTrackId);
					updateQuery.executeUpdate();
					resultFlag = true;
				}
			} else {
				session.save(opIpDetails);
				resultFlag = true;
			}

		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Exception : " + e.getMessage());
		}
		return resultFlag;

	}

	// Saving or updating the speciality clinics of taluk hospital.

	
		public Boolean saveAndUpdateSpecialityClinicData(List<SpecialityClinicData> specialityClinicDataList) throws Exception {
			
			Session session = this.sessionFactory.getCurrentSession();
			for(SpecialityClinicData specialityClinicData : specialityClinicDataList) {
			HospitalMonthlyTracker hospitalMonthlyTracker = null;
			if(specialityClinicData.getHospitalMonthlyTracker().getId()!=null) {
			 hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class, specialityClinicData.getHospitalMonthlyTracker().getId());
			}
			if(hospitalMonthlyTracker == null) {
				hospitalMonthlyTracker = specialityClinicData.getHospitalMonthlyTracker();
				hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
				specialityClinicData.setHospitalMonthlyTracker(hospitalMonthlyTracker);
				
			} else {
				hospitalMonthlyTracker.setLastModified(new Date());
				specialityClinicData.setHospitalMonthlyTracker(hospitalMonthlyTracker);
			}
			session.saveOrUpdate(specialityClinicData);
			}
			return true;
		}
		
		public Boolean saveAndUpdateLabDialysis(LabDialysis labDialysis) throws Exception {
			
			Session session = this.sessionFactory.getCurrentSession();
			HospitalMonthlyTracker hospitalMonthlyTracker = null;
			if(labDialysis.getHospitalMonthlyTracker().getId()!=null) {
			 hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class, labDialysis.getHospitalMonthlyTracker().getId());
			}
			if(hospitalMonthlyTracker == null) {
				hospitalMonthlyTracker = labDialysis.getHospitalMonthlyTracker();
				hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
				labDialysis.setHospitalMonthlyTracker(hospitalMonthlyTracker);
				
			} else {
				hospitalMonthlyTracker.setLastModified(new Date());
				labDialysis.setHospitalMonthlyTracker(hospitalMonthlyTracker);
			}
			session.saveOrUpdate(labDialysis);
			return true;
		}
		
		
		
		/*private Date getFirstDateOfMonth() throws ParseException {
			Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);
		    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
	           String dmy = dmyFormat.format(c.getTime());
	        Date reportDate=dmyFormat.parse(dmy);
	        return reportDate;
		}*/

	/*public Boolean saveAndUpdateSpecialityClinicData(SpecialityClinicData specialityClinicData) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		Boolean resultFlag = false;
		HospitalMonthlyTracker hospitalMonthlyTracker = specialityClinicData.getHospitalMonthlyTracker();
		Long hospitalId = hospitalMonthlyTracker.getHospital().getId();
		Long hospmonthlytrackId = specialityClinicData.getHospitalMonthlyTracker().getId();
		if (hospmonthlytrackId == null) {
			hospmonthlytrackId = saveHospitalMonthlyTracker(hospitalId);
		}

		try {
			Long maleCount = specialityClinicData.getMaleCount();
			Long femalCount = specialityClinicData.getFemalCount();
			Long tgcount = specialityClinicData.getTgCount();
			Long total = specialityClinicData.getTotal();
			Long specialityClinicId = specialityClinicData.getSpecialityClinic().getId();

			String sql = "select * from specialityclinic_data sc where sc.specialityclinic_id=:specialityClinicId and sc.hosp_track_id =:hospmonthlytrackId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("specialityClinicId", specialityClinicId);
			query.setParameter("hospmonthlytrackId", hospmonthlytrackId);
			List<SpecialityClinicData> specialityClinicDetails = query.list();

			if (specialityClinicDetails != null && !specialityClinicDetails.isEmpty()) {
				if (specialityClinicDetails.get(0) != null) {
					String updateSql = "update specialityclinic_data set maleCount=:maleCount, femaleCount=:femalCount,tgcount =:tgcount,total=:total where specialityclinic_id=:specialityClinicId and hosp_track_id =:hospmonthlytrackId";
					Query updateQuery = session.createSQLQuery(updateSql);
					updateQuery.setParameter("specialityClinicId", specialityClinicId);
					updateQuery.setParameter("maleCount", maleCount);
					updateQuery.setParameter("femalCount", femalCount);
					updateQuery.setParameter("tgcount", tgcount);
					updateQuery.setParameter("total", total);
					updateQuery.setParameter("hospmonthlytrackId", hospmonthlytrackId);
					updateQuery.executeUpdate();
					resultFlag = true;
				}
			} else {
				session.save(specialityClinicData);
				resultFlag = true;
			}
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Exception : " + e.getMessage());

>>>>>>> master
		}
		return resultFlag;

<<<<<<< HEAD
	}*/
/*
	public Boolean saveOrUpdateSpecialityClinic(SpecialityClinicData specialityClinicData) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		if (specialityClinicData.getHospitalMonthlyTracker().getId() != null) {
			hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class,
					specialityClinicData.getHospitalMonthlyTracker().getId());
		}
		if (hospitalMonthlyTracker == null) {
			hospitalMonthlyTracker = specialityClinicData.getHospitalMonthlyTracker();
			hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
			specialityClinicData.setHospitalMonthlyTracker(hospitalMonthlyTracker);
=======
	}*/

	private Date getFirstDateOfMonth() throws ParseException {
		Calendar c = Calendar.getInstance(); // this takes current date
		c.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dmy = dmyFormat.format(c.getTime());
		Date reportDate = dmyFormat.parse(dmy);
		return reportDate;
	}

	public Long saveHospitalMonthlyTracker(Long hospitalId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
		Long trackerId = 0L;
		// Transaction transaction = null;
		try {
			// transaction = session.beginTransaction();

			HospitalMonthlyTracker trackerForCurrentMonth = new HospitalMonthlyTracker();
			HospitalMaster hospitalMaster = new HospitalMaster();
			hospitalMaster.setId(hospitalId);
			trackerForCurrentMonth.setHospital(hospitalMaster);
			trackerForCurrentMonth.setLastModified(Calendar.getInstance().getTime());
			trackerForCurrentMonth.setReport_date(getReportDate());// setReportMonth(new
																	// Long(Calendar.getInstance().get(Calendar.MONTH)));
			trackerId = (Long) session.save(trackerForCurrentMonth);

		} catch (HibernateException e) {
			// if (transaction != null) {
			// transaction.rollback();
			// }
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			// if (transaction != null) {
			// transaction.rollback();
			// }
			throw new Exception("Exception : " + e.getMessage());

		}

		return trackerId;
	}

	public Date getReportDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.AM_PM);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}

}
