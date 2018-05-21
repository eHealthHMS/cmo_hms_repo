package com.ehealth.hmms.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
