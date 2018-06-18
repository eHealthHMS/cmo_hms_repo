package com.ehealth.hmms.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ehealth.hmms.dao.AuthenticationDao;
import com.ehealth.hmms.pojo.Users;

@Repository
@Transactional
public class AuthenticationDaoImpl implements AuthenticationDao {
	@Autowired
	private SessionFactory sessionFactory;
	final static Logger logger = Logger.getLogger(AuthenticationDaoImpl.class);

	public Users authenticate(Users user) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		Users userResult = null;
		try {
			logger.info("Entered AuthenticationDaoImpl:authenticate");
			Criteria criteria = session.createCriteria(Users.class);

			criteria.add(Restrictions.eq("loginName", user.getLoginName()));

			criteria.add(Restrictions.eq("password", user.getPassword()));
			List<Users> users = criteria.list();// uniqueResult();

			if (users != null && !users.isEmpty()) {
				userResult = users.get(0);

			}
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited AuthenticationDaoImpl:authenticate");
		return userResult;
	}

}
