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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ehealth.hmms.dao.HibernatePersistence;
import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.pojo.CategoryDetails;
import com.ehealth.hmms.pojo.CategoryMaster;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.pojo.Users;
import com.ehealth.hmms.util.Constants;

@Repository
public class PhcDaoImpl implements PhcDao {

	/**
	 * method to save phc static data
	 */
	public List<CategoryDetails> getPhcStaticData(String hospitalId) throws Exception {

		Session session = HibernatePersistence.getSessionFactory().openSession();

		Transaction transaction = null;
		List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		List<Object> resultSet = new ArrayList<Object>();
		try {
			transaction = session.beginTransaction();
			String strQuery = "select cm.category_name,cd.sanctioned_post,in_position,nhm,contract,total_staff_available"
					+ " from category_details cd inner join category_master cm on cd.category_id=cm.id"
					+ "  where hospital_id=:hospitalid";

			Query query = session.createSQLQuery(strQuery);
			query.setLong("hospitalid", new Long(hospitalId));
			resultSet = query.list();
			Iterator iterator = resultSet.iterator();

			while (iterator.hasNext()) {

				// Map row = (Map) iterator.next();
				Object[] row = (Object[]) iterator.next();

				CategoryMaster categoryMaster = new CategoryMaster();
				CategoryDetails categoryDetailsResult = new CategoryDetails();
				categoryMaster.setCategoryName((String) row[0]);

				// categoryDetailsResult.setSanctionedPost(castObjectToLong)
				categoryDetailsResult.setSanctionedPost(castObjectToLong(row[1]));
				categoryDetailsResult.setInPosition(castObjectToLong(row[2]));
				categoryDetailsResult.setNhm(castObjectToLong(row[3]));
				categoryDetailsResult.setContract(castObjectToLong(row[4]));
				categoryDetailsResult.setTotalStaffAvailable(castObjectToLong(row[5]));
				categoryDetailsResult.setCategoryMaster(categoryMaster);
				categoryDetails.add(categoryDetailsResult);

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

	private Long castObjectToLong(Object object) {

		return new Long((Integer) ((object != null) ? object : 0));

	}

	/**
	 * method to save phc functional components monthly
	 */
	public Result saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc) throws Exception {

		Session session = HibernatePersistence.getSessionFactory().openSession();
		Result result = new Result();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			HospitalMonthlyTracker hospitalMonthlyTracker = dataFhcChc.getHospitalMonthlyTracker();// new
			Long hospitalId = hospitalMonthlyTracker.getHospital().getId();

			HospitalMonthlyTracker trackerForCurrentMonth = getMonthlyTrackerForCurrentMonth(hospitalId,
					Calendar.getInstance().get(Calendar.MONTH));
			Long trackerid = 0L;
			if (trackerForCurrentMonth == null) {
				trackerid = saveHospitalMonthlyTracker(dataFhcChc);

			}
			if (trackerid == 0) {
				result.setStatus(Constants.FAILURE_STATUS);
				return result;
			} else {
				// Query query = "";
			}

			// functionalComponents - 1//fieldActivities - 2//subcentre - 3
			Integer type = dataFhcChc.getType();
			switch (type) {
			case 1: {

				break;
			}
			case 2: {
				break;
			}
			case 3: {
				break;
			}
			default: {
				result.setStatus(Constants.FAILURE_STATUS);
				return result;

			}
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
		return result;
	}

	/**
	 * save hospital monthly tracker
	 * 
	 * @param dataFhcChc
	 * @return
	 * @throws Exception
	 */
	private Long saveHospitalMonthlyTracker(MonthlyDataFhcChc dataFhcChc) throws Exception {
		Session session = HibernatePersistence.getSessionFactory().openSession();
		Long trackerId = 0L;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			HospitalMonthlyTracker hospitalMonthlyTracker = dataFhcChc.getHospitalMonthlyTracker();
			Long hospitalId = hospitalMonthlyTracker.getHospital().getId();
			HospitalMonthlyTracker trackerForCurrentMonth = new HospitalMonthlyTracker();
			HospitalMaster hospitalMaster = new HospitalMaster();
			hospitalMaster.setId(hospitalId);
			trackerForCurrentMonth.setHospital(hospitalMaster);
			trackerForCurrentMonth.setLastModified(Calendar.getInstance().getTime());
			trackerForCurrentMonth.setReportMonth(new Long(Calendar.getInstance().get(Calendar.MONTH)));
			trackerId = (Long) session.save(trackerForCurrentMonth);

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
		return trackerId;
	}

	/**
	 * get hospital monthly tracker for a month
	 * 
	 * @param hospitalId
	 * @param currentMonth
	 * @return
	 * @throws Exception
	 */
	private HospitalMonthlyTracker getMonthlyTrackerForCurrentMonth(Long hospitalId, int currentMonth)
			throws Exception {

		Session session = HibernatePersistence.getSessionFactory().openSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery(
					"from HospitalMonthlyTracker mt where mt.reportMonth=:month and mt.hospital.id=:hospitalId");
			query.setLong("month", currentMonth);
			query.setLong("hospitalId", hospitalId);

			List<HospitalMonthlyTracker> hospitalMonthlyTrackers = query.list();

			if (hospitalMonthlyTrackers != null && !hospitalMonthlyTrackers.isEmpty()) {
				hospitalMonthlyTracker = hospitalMonthlyTrackers.get(0);
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
		return hospitalMonthlyTracker;
	}

	public List<MonthlyDataFhcChc> getPhcDynamicData(String hospitalId) throws Exception {
		Session session = HibernatePersistence.getSessionFactory().openSession();

		Transaction transaction = null;
		List<MonthlyDataFhcChc> dataFhcChcs = null;
		// List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		//List<Object> resultSet = new ArrayList<Object>();
		try {
			transaction = session.beginTransaction();
			HospitalMonthlyTracker trackerForCurrentMonth = getMonthlyTrackerForCurrentMonth(new Long(hospitalId),
					Calendar.getInstance().get(Calendar.MONTH));

			Long trackerid = 0L;
			if (trackerForCurrentMonth == null) {
				// to do

			} else {

				trackerid = trackerForCurrentMonth.getId();

				Criteria cr = session.createCriteria(MonthlyDataFhcChc.class, "MonthlyDataFhcChc")
						.createCriteria("MonthlyDataFhcChc.hospitalMonthlyTracker", "trackerTable")
						.add(Restrictions.eq("trackerTable.id", trackerid))
						.setProjection(Projections.projectionList()
								.add(Projections.property("forenoonOpTotal"), "forenoonOpTotal")
								.add(Projections.property("afternoonOpTotal"), "afternoonOpTotal")
								.add(Projections.property("totalPrecheck"), "totalPrecheck")
								.add(Projections.property("patientLabTest"), "patientLabTest")
								.add(Projections.property("totallabTest"), "totallabTest")
								.add(Projections.property("housevisitMo"), "housevisitMo")
								.add(Projections.property("housevisitHs"), "housevisitHs")
								.add(Projections.property("housevisitPhns"), "housevisitPhns")
								.add(Projections.property("housevisitHi"), "housevisitHi")
								.add(Projections.property("housevisitPhl"), "housevisitPhl")
								.add(Projections.property("housevisitJhi"), "housevisitJhi")
								.add(Projections.property("housevisitJphn"), "housevisitJphn"))
						.setResultTransformer(Transformers.aliasToBean(MonthlyDataFhcChc.class));

				dataFhcChcs = cr.list();

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
		return dataFhcChcs;
	}
	
	
	public List<MonthlyDataFhcChc> getPhcDynamicDataTrend(String hospitalId) throws Exception {
		Session session = HibernatePersistence.getSessionFactory().openSession();

		Transaction transaction = null;
		List<MonthlyDataFhcChc> dataFhcChcs = null;
		// List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		//List<Object> resultSet = new ArrayList<Object>();
		try {
			transaction = session.beginTransaction();
			HospitalMonthlyTracker trackerForCurrentMonth = getMonthlyTrackerForCurrentMonth(new Long(hospitalId),
					Calendar.getInstance().get(Calendar.MONTH));

			Long trackerid = 0L;
			if (trackerForCurrentMonth == null) {
				// to do

			} else {

				trackerid = trackerForCurrentMonth.getId();

				Criteria cr = session.createCriteria(MonthlyDataFhcChc.class, "MonthlyDataFhcChc")
						.createCriteria("MonthlyDataFhcChc.hospitalMonthlyTracker", "trackerTable")
						.add(Restrictions.eq("trackerTable.id", trackerid))
						.setProjection(Projections.projectionList()
								.add(Projections.property("forenoonOpTotal"), "forenoonOpTotal")
								.add(Projections.property("afternoonOpTotal"), "afternoonOpTotal")
								.add(Projections.property("totalPrecheck"), "totalPrecheck")
								.add(Projections.property("patientLabTest"), "patientLabTest")
								.add(Projections.property("totallabTest"), "totallabTest")
								.add(Projections.property("housevisitMo"), "housevisitMo")
								.add(Projections.property("housevisitHs"), "housevisitHs")
								.add(Projections.property("housevisitPhns"), "housevisitPhns")
								.add(Projections.property("housevisitHi"), "housevisitHi")
								.add(Projections.property("housevisitPhl"), "housevisitPhl")
								.add(Projections.property("housevisitJhi"), "housevisitJhi")
								.add(Projections.property("housevisitJphn"), "housevisitJphn"))
						.setResultTransformer(Transformers.aliasToBean(MonthlyDataFhcChc.class));

				dataFhcChcs = cr.list();

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
		return dataFhcChcs;
	}

}
