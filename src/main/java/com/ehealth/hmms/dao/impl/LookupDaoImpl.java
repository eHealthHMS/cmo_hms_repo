package com.ehealth.hmms.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ehealth.hmms.dao.LookupDao;

@Repository
@Transactional
public class LookupDaoImpl implements LookupDao {

	final static Logger logger = Logger.getLogger(LookupDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public String getConfiguration(String key) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		String value = null;
		try {
			logger.info("Entered LookupDaoImpl:getConfiguration");
			String sql = "select value from configuration_master where key= :key";

			Query query = session.createSQLQuery(sql);

			query.setParameter("key", key);

			Object configurationVal = query.uniqueResult();

			if (configurationVal != null) {
				value = configurationVal.toString();
			}
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Exception : " + e.getMessage());

		}
		logger.info("Exited LookupDaoImpl:getConfiguration");
		return value;
	}

}
