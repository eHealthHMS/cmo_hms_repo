package com.ehealth.hmms.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ehealth.hmms.dao.HibernatePersistence;
import com.ehealth.hmms.dao.ThDao;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.ServiceAreaThDhGh;
import com.ehealth.hmms.pojo.SpecialityClinicData;

public class ThDaoImpl implements ThDao{

	// Saving or updating the OP and IP details of taluk hospital.
	
	public Boolean saveAndUpdateOpIpDetails(ServiceAreaThDhGh OpIpDetails) throws Exception {
		
		Session session = HibernatePersistence.getSessionFactory().openSession();
		Transaction transaction = null;
		Boolean resultFlag = false;
		HospitalMonthlyTracker hospitalMonthlyTracker = OpIpDetails.getHospitalMonthlyTracker();
		Long hospitalId = hospitalMonthlyTracker.getHospital().getId();
		PhcDaoImpl phcDaoImpl = new PhcDaoImpl();
		Long hospTrackId = phcDaoImpl.saveHospitalMonthlyTracker(hospitalId);
		  try {
			  	
				transaction = session.beginTransaction();
				Long totOutPatients = OpIpDetails.getTotOutPatients();
				Long totInPatients = OpIpDetails.getTotInPatients();
				Long patientsDischarged = OpIpDetails.getPatientsDischarged();
				Long patientsExpired = OpIpDetails.getPatientsExpired();
				Long patientsReferred = OpIpDetails.getPatientsReferred();
				Long emergencyCare = OpIpDetails.getEmergencyCare();
				Long medEmergency = OpIpDetails.getMedEmergency();
				Long surgEmergency = OpIpDetails.getSurgEmergency();
				Long emrRta = OpIpDetails.getEmrRta();
				Long emrPatinetTreated = OpIpDetails.getEmrPatinetTreated();
				Long emrPatientAdmited = OpIpDetails.getEmrPatientAdmited();
				Long emrPatientReferred = OpIpDetails.getEmrPatientReferred();
				
				Query query = session.createSQLQuery("update service_area_th_gh_dh set totOutPatients =:totOutPatients, totInPatients=:totInPatients, patientsDischarged=:patientsDischarged, patientsExpired=: patientsExpired, patientsReferred =:patientsReferred, medEmergency =:medEmergency, surgEmergency =:surgEmergency, emrPatinetTreated =:emrPatinetTreated,emrPatientAdmited =:emrPatientAdmited, emrPatientReferred=:emrPatientReferred, emergencyCare=:emergencyCare, emrRta=:emrRta where hospmonthlytrack_id =:hospTrackId;");
				 resultFlag = true;
	
			 }
		  catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("Exception : " + e.getMessage());
	
		} finally {
			session.close();
		}
		return resultFlag;
			
		}
	
	// Saving or updating the speciality clinics of taluk hospital.
	
		public Boolean saveAndUpdateSpecialityClinicData(SpecialityClinicData specialityClinicData) throws Exception {
			
			Session session = HibernatePersistence.getSessionFactory().openSession();
			Transaction transaction = null;
			Boolean resultFlag = false;
			HospitalMonthlyTracker hospitalMonthlyTracker = specialityClinicData.getHospitalMonthlyTracker();
			Long hospitalId = hospitalMonthlyTracker.getHospital().getId();
			PhcDaoImpl phcDaoImpl = new PhcDaoImpl();
			Long hospmonthlytrack_id = phcDaoImpl.saveHospitalMonthlyTracker(hospitalId);
	
			  try {
				  	
					transaction = session.beginTransaction();
					Long maleCount = specialityClinicData.getMaleCount();
					Long femalCount = specialityClinicData.getFemalCount();
					Long total = specialityClinicData.getTotal();
					Long specialityClinicId = specialityClinicData.getSpecialityClinic().getId();
					
					
					Query query = session.createSQLQuery("update specialityclinic_data set maleCount=: maleCount, femalCount=:femalCount, total=:total where specialityclinic_id=:specialityClinicId;");
					 resultFlag = true;
				 }
			  catch (HibernateException e) {
				if (transaction != null) {
					transaction.rollback();
				}
				throw new HibernateException("Hibernate Exception : " + e.getMessage());
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				throw new Exception("Exception : " + e.getMessage());
		
			} finally {
				session.close();
			}
			return resultFlag;
				
			}
		
	
}
