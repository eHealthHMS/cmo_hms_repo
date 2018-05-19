package com.ehealth.hmms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ehealth.hmms.dao.HibernatePersistence;
import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Users;

@Repository
public class PhcDaoImpl implements PhcDao {
	
	

	public Integer saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc  ) throws Exception {

		
		Session session = HibernatePersistence.getSessionFactory().openSession();
		 int resultFlag = 0 ;
		Users userResult=null;
		Transaction transaction = null;
		  try {
		 transaction = session.beginTransaction();
		 session.saveOrUpdate(dataFhcChc);
		 resultFlag = 1 ;   
//		 Criteria criteria = session.createCriteria(Users.class);
//         criteria.add(Restrictions.eq("loginName", user.getLoginName()));
//         
//         criteria.add(Restrictions.eq("password", user.getPassword()));
//         
//    //     criteria.setFetchMode("PayoutHeader", FetchMode.JOIN)
//         List<Users> users  = criteria.list();//uniqueResult();
//
//		
//		if(users!=null && !users.isEmpty()) {
//			userResult = users.get(0);
//			
//		}
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
		return resultFlag;
	}
	
	public MonthlyDataFhcChc fetchPhcRecord(MonthlyDataFhcChc monthlyDataPhc) throws Exception {
		Session session = HibernatePersistence.getSessionFactory().openSession();
		Transaction transaction = null;
		  try {
				 transaction = session.beginTransaction();
				Query query = session.createSQLQuery("select m.forenoon_op, m.afternoon_op,m.total_precheck,m.total_postconsultncounsel, m.patient_labtest, m.swas_clinic,m.aswasam_clinic,m.ncd_clinic,m.tot_sc_immunizatnclinic, m.tot_other_sc_clinic, m.tot_other_sc_clinic, m.tot_outreach, m.tot_ncd_clinic,m.iec_healthpromo_activities,m.nutrition_committee_meetings,m.hospmonthlytrack_id, m.housevisit_mo, m.housevisit_hs, m.housevisit_phns, m.housevisit_hi, m.housevisit_phl, m.housevisit_jhi, m.housevisit_jphn from monthlydata_fhc_chc m inner join hospital_type_master h on h.id = m.hospmonthlytrack_id;");
				
				List<MonthlyDataFhcChc> phcList = query.list();
				
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
		return phcList;
	}

	}
	

