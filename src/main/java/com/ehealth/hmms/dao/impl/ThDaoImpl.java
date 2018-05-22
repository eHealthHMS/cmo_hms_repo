package com.ehealth.hmms.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ehealth.hmms.dao.HibernatePersistence;
import com.ehealth.hmms.dao.ThDao;
import com.ehealth.hmms.pojo.ServiceAreaThDhGh;

public class ThDaoImpl implements ThDao{

	    
	public Boolean saveAndUpdateOpIpDetails(ServiceAreaThDhGh OpIpDetails) throws Exception {
		
		Session session = HibernatePersistence.getSessionFactory().openSession();
		Transaction transaction = null;
		Boolean resultFlag = false;
		Long hospTrackId = null;
		  try {
			  	Query query1 = session.createSQLQuery("update");
			  	
				transaction = session.beginTransaction();
				Long totOutPatients = OpIpDetails.getTotOutPatients();
				Long totInPatients = OpIpDetails.getTotInPatients();
				Long patientsDischarged = OpIpDetails.getPatientsDischarged();
				Long patientsExpired = OpIpDetails.getPatientsExpired();
				Long patientsReferred = OpIpDetails.getPatientsReferred();
				Long medEmergency = OpIpDetails.getMedEmergency();
				Long surgEmergency = OpIpDetails.getSurgEmergency();
				Long emrPatinetTreated = OpIpDetails.getEmrPatinetTreated();
				Long emrPatientAdmited = OpIpDetails.getEmrPatientAdmited();
				Long emrPatientReferred = OpIpDetails.getEmrPatientReferred();
				Long emergencyCare = OpIpDetails.getEmergencyCare();
				Long emrRta = OpIpDetails.getEmrRta();
				
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
}
