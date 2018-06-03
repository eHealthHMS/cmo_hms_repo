package com.ehealth.hmms.dao.impl;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import com.ehealth.hmms.dao.HibernatePersistence;
import com.ehealth.hmms.dao.ThDao;
import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinicData;

@Repository
@Transactional
public class ThDaoImpl implements ThDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public Boolean saveOrUpdateDeptWiseIpOpDetails(DepartmentWiseOpIp departmentWiseOpIp) throws Exception{
		
		Session session = this.sessionFactory.getCurrentSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class, departmentWiseOpIp.getHospitalMonthlyTrackerId().getId());
		if(hospitalMonthlyTracker == null) {
			hospitalMonthlyTracker = departmentWiseOpIp.getHospitalMonthlyTrackerId();
			hospitalMonthlyTracker.setReport_date(new Date());
			session.saveOrUpdate(hospitalMonthlyTracker);
		}
		session.saveOrUpdate(departmentWiseOpIp);
		return true;
	}
	
	public Boolean saveOrUpdateServiceAreaOthers(ServiceAreaOthers serviceAreaOthers) throws Exception{
		
		Session session = this.sessionFactory.getCurrentSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class, serviceAreaOthers.getHospitalMonthlyTracker().getId());
		if(hospitalMonthlyTracker == null) {
			hospitalMonthlyTracker = serviceAreaOthers.getHospitalMonthlyTracker();
			hospitalMonthlyTracker.setReport_date(new Date());
			session.saveOrUpdate(hospitalMonthlyTracker);
		}
		session.saveOrUpdate(serviceAreaOthers);
		return true;
	}
	// Saving or updating the OP and IP details of taluk hospital.
	
	public Boolean saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception {
		
		Session session = this.sessionFactory.getCurrentSession();
		//Transaction transaction = null;
		Boolean resultFlag = false;
		HospitalMonthlyTracker hospitalMonthlyTracker = opIpDetails.getHospitalMonthlyTracker();
		Long hospitalId = hospitalMonthlyTracker.getHospital().getId();
		PhcDaoImpl phcDaoImpl = new PhcDaoImpl();
		Long hospTrackId = phcDaoImpl.saveHospitalMonthlyTracker(hospitalId);
		  try {
			  	
			//	transaction = session.beginTransaction();
/*				Long totOutPatients = opIpDetails.getTotOutPatients();
				Long totInPatients = opIpDetails.getTotInPatients();
				Long patientsDischarged = opIpDetails.getPatientsDischarged();
				Long patientsExpired = opIpDetails.getPatientsExpired();
				Long patientsReferred = opIpDetails.getPatientsReferred();
				Long emergencyCare = opIpDetails.getEmergencyCare();
				Long medEmergency = opIpDetails.getMedEmergency();
				Long surgEmergency = opIpDetails.getSurgEmergency();
				Long emrRta = opIpDetails.getEmrRta();
				Long emrPatinetTreated = opIpDetails.getEmrPatinetTreated();
				Long emrPatientAdmited = opIpDetails.getEmrPatientAdmited();
				Long emrPatientReferred = opIpDetails.getEmrPatientReferred();*/
				
				Query query = session.createSQLQuery("update service_area_th_gh_dh set totOutPatients =:totOutPatients, totInPatients=:totInPatients, patientsDischarged=:patientsDischarged, patientsExpired=: patientsExpired, patientsReferred =:patientsReferred, medEmergency =:medEmergency, surgEmergency =:surgEmergency, emrPatinetTreated =:emrPatinetTreated,emrPatientAdmited =:emrPatientAdmited, emrPatientReferred=:emrPatientReferred, emergencyCare=:emergencyCare, emrRta=:emrRta where hospmonthlytrack_id =:hospTrackId;");
				 resultFlag = true;
	
			 }
		  catch (HibernateException e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new Exception("Exception : " + e.getMessage());
	
		} 
		return resultFlag;
			
		}
	
	// Saving or updating the speciality clinics of taluk hospital.
	
		public Boolean saveAndUpdateSpecialityClinicData(SpecialityClinicData specialityClinicData) throws Exception {
			
			Session session = this.sessionFactory.getCurrentSession();//HibernatePersistence.getSessionFactory().openSession();
	//		Transaction transaction = null;
			Boolean resultFlag = false;
			HospitalMonthlyTracker hospitalMonthlyTracker = specialityClinicData.getHospitalMonthlyTracker();
			Long hospitalId = hospitalMonthlyTracker.getHospital().getId();
			PhcDaoImpl phcDaoImpl = new PhcDaoImpl();
			Long hospmonthlytrack_id = phcDaoImpl.saveHospitalMonthlyTracker(hospitalId);
	
			  try {
				  	
				//	transaction = session.beginTransaction();
					Long maleCount = specialityClinicData.getMaleCount();
					Long femalCount = specialityClinicData.getFemalCount();
					Long total = specialityClinicData.getTotal();
					Long specialityClinicId = specialityClinicData.getSpecialityClinic().getId();
					
					
					Query query = session.createSQLQuery("update specialityclinic_data set maleCount=: maleCount, femalCount=:femalCount, total=:total where specialityclinic_id=:specialityClinicId;");
					 resultFlag = true;
				 }
			  catch (HibernateException e) {
//				if (transaction != null) {
//					transaction.rollback();
//				}
				throw new HibernateException("Hibernate Exception : " + e.getMessage());
			} catch (Exception e) {
//				if (transaction != null) {
//					transaction.rollback();
//				}
				throw new Exception("Exception : " + e.getMessage());
		
			} 
			return resultFlag;
				
			}
		
	
}
