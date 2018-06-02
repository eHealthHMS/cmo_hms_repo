package com.ehealth.hmms.dao.impl;

import java.util.List;

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
import com.ehealth.hmms.pojo.SpecialityClinicData;

@Repository
@Transactional
public class ThDaoImpl implements ThDao{
	
	 @Autowired
	    private SessionFactory sessionFactory;

	public Boolean saveOrUpdateDeptWiseIpOpDetails(DepartmentWiseOpIp departmentWiseOpIp) throws Exception{
		
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(departmentWiseOpIp);
		return true;
	}
	// Saving or updating the OP and IP details of taluk hospital.
	
	public Boolean saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception {
		
		Session session = this.sessionFactory.getCurrentSession();

		Boolean resultFlag = false;

		  try {
			  	
			  Long forenoonOpMale = opIpDetails.getForenoonOpMale(); 
			    Long forenoonOpFemale = opIpDetails.getAfternoonOpMale(); 
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
			    Long hospTrackId = opIpDetails.getHospitalMonthlyTracker().getId(); 
			     
			   
			        String sql ="select * from op_ip_th_gh_dh op where op.hospmonthlytrack_id =:hospTrackId"; 
			        Query query = session.createSQLQuery(sql); 
			        query.setParameter("hospTrackId", hospTrackId); 
			        List<OpIpDetails> opIpQueryDetails = query.list(); 
			        
			        if (opIpQueryDetails != null && !opIpQueryDetails.isEmpty()) { 
			          if(opIpQueryDetails.get(0) != null) 
			          { 
			        	  Query query1 = session.createSQLQuery("update op_ip_th_gh_dh set forenoon_op_male =:forenoonOpMale,forenoon_op_female=:forenoonOpFemale,forenoon_op_tg=:forenoonOpTg,forenoon_op_total=:forenoonOpTotal,afternoon_op_male=:afternoonOpMale,afternoon_op_tg=:afternoonOpTg,afternoon_op_total=:afternoonOpTotal,afternoon_op_female=:afternoonOpFemale,ip_admissions_male=:ipAdmissionsMale,ip_admissions_female=:ipAdmissionsFemale,ip_admissions_tg=:ipAdmissionsTg,ip_admissions_total=:ipAdmissionsTotal,ip_patients_discharged=:ipPatientsDischarged,ip_patients_expired=:ipPatientsExpired ,ip_patients_referred =:ipPatientsReferred,emr_patinet_attended =:emrPatinetAttended,emr_patient_admited =:emrPatientAdmited,emr_patient_referred =:emrPatientReferred,emr_rta_trauma =:emrRtaTrauma where hospmonthlytrack_id =:hospTrackId"); 
			        	  resultFlag = true; 
			          } 
			        } 
			          else 
			          { 
			            session.save(opIpQueryDetails); 
			            resultFlag = true; 
			          } 
	
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
