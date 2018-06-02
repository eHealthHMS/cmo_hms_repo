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

import com.ehealth.hmms.dao.LookupDao;
import com.ehealth.hmms.pojo.Configuration;
import com.ehealth.hmms.pojo.Users;
import com.ehealth.hmms.util.Constants;

@Repository
@Transactional
public class LookupDaoImpl implements LookupDao {

	 @Autowired
	 private SessionFactory sessionFactory;

		public Configuration getConfiguration(String key) throws Exception {

			
			Session session = this.sessionFactory.getCurrentSession();
			Configuration configuration=null;
			  try {
			 
			 Criteria criteria = session .createCriteria(Configuration.class);

	         criteria.add(Restrictions.eq("key", key));
	         
	         List<Configuration> configurations  = criteria.list();

			
			if(configurations!=null && !configurations.isEmpty()) {
				configuration = configurations.get(0);
				
			}
		 } catch (HibernateException e) {
	        throw new HibernateException("Hibernate Exception : " +  e.getMessage() );
	      } catch (Exception e) {
	         throw new Exception("Exception : " +  e.getMessage() );

	      }

			return configuration;
		}
	 
}
