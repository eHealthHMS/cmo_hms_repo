package com.ehealth.hmms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ehealth.hmms.dao.AuthenticationDao;
//import com.ehealth.hmms.dao.HibernatePersistence;
import com.ehealth.hmms.pojo.Users;

@Repository
@Transactional
public class AuthenticationDaoImpl implements AuthenticationDao {
	 @Autowired
	 private SessionFactory sessionFactory;

	public Users authenticate(Users user) throws Exception {

		
		Session session = this.sessionFactory.getCurrentSession();//HibernatePersistence.getSessionFactory().openSession();
		Users userResult=null;
	//	Transaction transaction = null;
		  try {
	//	 transaction = session.beginTransaction();
		 
//		Query query = session.createQuery("from User where loginName=:login and password=:password");
//		query.setString("login", user.getLoginName());
//		query.setString("password", user.getPassword());
//		List<User> users = query.list();

		 
		 Criteria criteria = session .createCriteria(Users.class);

         criteria.add(Restrictions.eq("loginName", user.getLoginName()));
         
         criteria.add(Restrictions.eq("password", user.getPassword()));
         
    //     criteria.setFetchMode("PayoutHeader", FetchMode.JOIN)
         List<Users> users  = criteria.list();//uniqueResult();

		
		if(users!=null && !users.isEmpty()) {
			userResult = users.get(0);
			
		}
	 } catch (HibernateException e) {
//         if (transaction!=null) { 
//        	 transaction.rollback();
//         }
        throw new HibernateException("Hibernate Exception : " +  e.getMessage() );
      } catch (Exception e) {
//    	  if (transaction!=null) { 
//         	 transaction.rollback();
//          }
         throw new Exception("Exception : " +  e.getMessage() );

      }

		return userResult;
	}
	

	}
	

