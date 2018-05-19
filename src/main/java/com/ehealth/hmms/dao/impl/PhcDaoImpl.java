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
		Users userResult=null;
		Transaction transaction = null;
		  try {
		 transaction = session.beginTransaction();
		 
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
		return null;
	}
	

	}
	

