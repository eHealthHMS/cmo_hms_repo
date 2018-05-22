package com.ehealth.hmms.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ehealth.hmms.dao.HibernatePersistence;
import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.pojo.CategoryDetails;
import com.ehealth.hmms.pojo.CategoryMaster;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Users;

@Repository
public class PhcDaoImpl implements PhcDao {

	public List<CategoryDetails> getPhcStaticData(String hospitalId) throws Exception {

		Session session = HibernatePersistence.getSessionFactory().openSession();

		Transaction transaction = null;
		List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		List<Object> resultSet = new ArrayList<Object>();
		try {
			transaction = session.beginTransaction();
			String query = "select cm.category_name,cd.sanctioned_post,in_position,nhm,contract,total_staff_available"
					+ " from category_details cd inner join category_master cm on cd.category_master_id=cm.id"
					+ "  where hospital_id:hospitalid";
			resultSet = session.createSQLQuery(query).list();
			Iterator iterator = resultSet.iterator();
			
			while(iterator.hasNext()) {
				
				 Map row = (Map)iterator.next();
				 
				CategoryMaster categoryMaster = new CategoryMaster();
				CategoryDetails categoryDetailsResult = new CategoryDetails();
				categoryMaster.setCategoryName((String)row.get("category_name"));
				categoryDetailsResult.setSanctionedPost((Long)row.get("sanctioned_post"));
				categoryDetailsResult.setInPosition((Long)row.get("in_position"));
				categoryDetailsResult.setNhm((Long)row.get("nhm"));
				categoryDetailsResult.setContract((Long)row.get("contract"));
				categoryDetailsResult.setTotalStaffAvailable((Long)row.get("total_staff_available"));
					
				categoryDetailsResult.setCategoryMaster(categoryMaster);
				categoryDetails.add(categoryDetailsResult);
				// row.set(row.get("category_name"));
				
			}

		} catch (HibernateException e) {
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
		return categoryDetails;
	}
	
	// Fetch monthly record of PHC for getting data in android.
	public MonthlyDataFhcChc fetchPhcRecord(Long hospitalId, int month) throws Exception {
		Session session = HibernatePersistence.getSessionFactory().openSession();
		Transaction transaction = null;
		MonthlyDataFhcChc MonthlyPhcResult = null;
		  try {
				 transaction = session.beginTransaction();
				Query query = session.createSQLQuery("select m.forenoon_op, m.afternoon_op,m.total_precheck,m.total_postconsultncounsel, m.patient_labtest, m.swas_clinic,m.aswasam_clinic,m.ncd_clinic,m.tot_sc_immunizatnclinic, m.tot_other_sc_clinic, m.tot_other_sc_clinic, m.tot_outreach, m.tot_ncd_clinic,m.iec_healthpromo_activities,m.nutrition_committee_meetings,m.hospmonthlytrack_id, m.housevisit_mo, m.housevisit_hs, m.housevisit_phns, m.housevisit_hi, m.housevisit_phl, m.housevisit_jhi, m.housevisit_jphn from monthlydata_fhc_chc m inner join hospital_monthlytracker h on h.id = m.hospmonthlytrack_id where h.report_month =: month and h.hospital_id =: hospitalId;");
				
				List<MonthlyDataFhcChc> phcList = query.list();
				if(phcList!=null && !phcList.isEmpty()) {
					MonthlyPhcResult = phcList.get(0);
				}			
			 } catch (HibernateException e) {
		         if (transaction!=null) { 
		        	 transaction.rollback();
		         }
		        throw new HibernateException("Hibernate Exception : " +  e.getMessage() );
		      } catch (Exception e) {
		    	  if (transaction!=null) { 
		         	 transaction.rollback();
		          }
		         throw new Exception("Exception : " +  e.getMessage() );
		      }
				  finally {
		         session.close(); 
		      }
		return MonthlyPhcResult;
	}

	public Integer saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc) throws Exception {

		Session session = HibernatePersistence.getSessionFactory().openSession();
		int resultFlag = 0;
		Users userResult = null;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);

			Criteria criteria = session.createCriteria(HospitalMonthlyTracker.class);
			criteria.add(Restrictions.eq("reportMonth", month));
			criteria.add(Restrictions.eq("reportMonth", dataFhcChc.getHospitalMonthlyTracker().getHospital().getId()));

			
			
			// MonthlyDataFhcChc objectToUpdate = (MonthlyDataFhcChc)
			// session.get(MonthlyDataFhcChc.class, idOfObjectToUpdate);
			// objectToUpdate.setField1(newValue1);
			// objectToUpdate.setField2(newValue2);
			// session.saveOrUpdate(dataFhcChc);
			resultFlag = 1;

		} catch (HibernateException e) {
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
