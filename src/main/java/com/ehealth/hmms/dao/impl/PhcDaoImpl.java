package com.ehealth.hmms.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import com.ehealth.hmms.dao.HibernatePersistence;
import com.ehealth.hmms.dao.PhcDao;
import com.ehealth.hmms.pojo.CategoryDetails;
import com.ehealth.hmms.pojo.CategoryMaster;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.MonthlyDataFhcChc;
import com.ehealth.hmms.pojo.Result;
import com.ehealth.hmms.util.Constants;

@Repository
@Transactional
public class PhcDaoImpl implements PhcDao {

	 @Autowired
	    private SessionFactory sessionFactory;
	/**
	 * method to save phc static data
	 */
	public List<CategoryDetails> getPhcStaticData(String hospitalId) throws Exception {

		Session session =this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();

		//Transaction transaction = null;
		List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		//List<Object> resultSet = new ArrayList<Object>();
		try {
			//transaction = session.beginTransaction();
			String strQuery = "select cm.category_name,cd.sanctioned_post,in_position,nhm,contract,total_staff_available"
					+ " from category_details cd inner join category_master cm on cd.category_id=cm.id"
					+ "  where hospital_id=:hospitalid";

			Query query = session.createSQLQuery(strQuery);
			query.setLong("hospitalid", new Long(hospitalId));
			//resultSet = query.list();
			Iterator iterator = query.list().iterator();

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
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new Exception("Exception : " + e.getMessage());

		} finally {
			session.close();
		}
		return categoryDetails;
	}
	
	// Fetch monthly record of PHC for getting data in android.
	public MonthlyDataFhcChc fetchPhcRecord(Long hospitalId, int month) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();;//HibernatePersistence.getSessionFactory().openSession();
	//	Transaction transaction = null;
		MonthlyDataFhcChc MonthlyPhcResult = null;
		  try {
			//	 transaction = session.beginTransaction();
				Query query = session.createSQLQuery("select m.forenoon_op, m.afternoon_op,m.total_precheck,m.total_postconsultncounsel, m.patient_labtest, m.swas_clinic,m.aswasam_clinic,m.ncd_clinic,m.tot_sc_immunizatnclinic, m.tot_other_sc_clinic, m.tot_other_sc_clinic, m.tot_outreach, m.tot_ncd_clinic,m.iec_healthpromo_activities,m.nutrition_committee_meetings,m.hospmonthlytrack_id, m.housevisit_mo, m.housevisit_hs, m.housevisit_phns, m.housevisit_hi, m.housevisit_phl, m.housevisit_jhi, m.housevisit_jphn from monthlydata_fhc_chc m inner join hospital_monthlytracker h on h.id = m.hospmonthlytrack_id where h.report_month =: month and h.hospital_id =: hospitalId;");
				
				List<MonthlyDataFhcChc> phcList = query.list();
				if(phcList!=null && !phcList.isEmpty()) {
					MonthlyPhcResult = phcList.get(0);
				}			
			 } catch (HibernateException e) {
//		         if (transaction!=null) { 
//		        	 transaction.rollback();
//		         }
		        throw new HibernateException("Hibernate Exception : " +  e.getMessage() );
		      } catch (Exception e) {
//		    	  if (transaction!=null) { 
//		         	 transaction.rollback();
//		          }
		         throw new Exception("Exception : " +  e.getMessage() );
		      }
				  finally {
		         session.close(); 
		      }
		return MonthlyPhcResult;
	}

	private Long castObjectToLong(Object object) {

		return new Long((Integer) ((object != null) ? object : 0));

	}

	/**
	 * method to save phc functional components monthly
	 */
	public Result saveFunctionalComponents(MonthlyDataFhcChc dataFhcChc) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();;//HibernatePersistence.getSessionFactory().openSession();
		Result result = new Result();
		//Transaction transaction = null;
		try {
		//	transaction = session.beginTransaction();

			HospitalMonthlyTracker hospitalMonthlyTracker = dataFhcChc.getHospitalMonthlyTracker();// new
			Long hospitalId = hospitalMonthlyTracker.getHospital().getId();

			HospitalMonthlyTracker trackerForCurrentMonth = getMonthlyTrackerForCurrentMonth(hospitalId,
					Calendar.getInstance().get(Calendar.MONTH));
			Long trackerid = 0L;
			if (trackerForCurrentMonth == null) {
				trackerid = saveHospitalMonthlyTracker(hospitalId);

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
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
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
	public Long saveHospitalMonthlyTracker(Long hospitalId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();//HibernatePersistence.getSessionFactory().openSession();
		Long trackerId = 0L;
		//Transaction transaction = null;
		try {
		//	transaction = session.beginTransaction();
			
			HospitalMonthlyTracker trackerForCurrentMonth = new HospitalMonthlyTracker();
			HospitalMaster hospitalMaster = new HospitalMaster();
			hospitalMaster.setId(hospitalId);
			trackerForCurrentMonth.setHospital(hospitalMaster);
			trackerForCurrentMonth.setLastModified(Calendar.getInstance().getTime());
			trackerForCurrentMonth.setReport_date(getReportDate());//setReportMonth(new Long(Calendar.getInstance().get(Calendar.MONTH)));
			trackerId = (Long) session.save(trackerForCurrentMonth);

		} catch (HibernateException e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
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

		Session session = this.sessionFactory.getCurrentSession();//HibernatePersistence.getSessionFactory().openSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		//Transaction transaction = null;
		try {
			//transaction = session.beginTransaction();

			Query query = session.createQuery(
					"from HospitalMonthlyTracker mt where mt.reportMonth=:month and mt.hospital.id=:hospitalId");
			query.setLong("month", currentMonth);
			query.setLong("hospitalId", hospitalId);

			List<HospitalMonthlyTracker> hospitalMonthlyTrackers = query.list();

			if (hospitalMonthlyTrackers != null && !hospitalMonthlyTrackers.isEmpty()) {
				hospitalMonthlyTracker = hospitalMonthlyTrackers.get(0);
			}

		} catch (HibernateException e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new Exception("Exception : " + e.getMessage());

		} finally {
			session.close();
		}
		return hospitalMonthlyTracker;
	}
	
	
	/**
	 * get hospital monthly tracker for a month
	 * 
	 * @param hospitalId
	 * @param currentMonth
	 * @return
	 * @throws Exception
	 */
	private HospitalMonthlyTracker getMonthlyTrackerForTrend(Long hospitalId, int currentMonth)
			throws Exception {

		Session session = this.sessionFactory.getCurrentSession();//HibernatePersistence.getSessionFactory().openSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		//Transaction transaction = null;
		try {
			//transaction = session.beginTransaction();

			Query query = session.createQuery(
					"from HospitalMonthlyTracker mt where mt.reportMonth=:month and mt.hospital.id=:hospitalId");
			query.setLong("month", currentMonth);
			query.setLong("hospitalId", hospitalId);

			List<HospitalMonthlyTracker> hospitalMonthlyTrackers = query.list();

			if (hospitalMonthlyTrackers != null && !hospitalMonthlyTrackers.isEmpty()) {
				hospitalMonthlyTracker = hospitalMonthlyTrackers.get(0);
			}

		} catch (HibernateException e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new Exception("Exception : " + e.getMessage());

		} finally {
			session.close();
		}
		return hospitalMonthlyTracker;
	}
	
	
	private Date getReportDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	//for dashboard
//coding done// testing pending
	public List<MonthlyDataFhcChc> getPhcDynamicDataForDashboard(String hospitalId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();//HibernatePersistence.getSessionFactory().openSession();

		//Transaction transaction = null;
		List<MonthlyDataFhcChc> dataFhcChcs = null;
		// List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		//List<Object> resultSet = new ArrayList<Object>();
		try {
		//	transaction = session.beginTransaction();
//			HospitalMonthlyTracker trackerForCurrentMonth = getMonthlyTrackerForCurrentMonth(new Long(hospitalId),
//					Calendar.getInstance().get(Calendar.MONTH));
//
//			Long trackerid = -1L;
//			int i=-1;
//			if (trackerForCurrentMonth == null) {
//				// to do
//
//			} 
//			else {

		//	Long	trackerid = trackerForCurrentMonth.getId();
			
			String strQuery  = "select forenoon_op_tot,afternoon_op_tot,total_precheck,patient_lab_test,total_lab_test,total_attendee,"
					+ "housevisit_mo,housevisit_hs,housevisit_phns,housevisit_hi,housevisit_phn,housevisit_jhi,housevisit_jphn,"
					+ "housevisit_asha,regular_sc_clinic from monthlydata_fhc_chc md inner join hospital_monthlytracker mt "
					+ " on md.hospmonthlytrack_id  = mt.id inner join  hospital_master hm  on mt.hospital_id=hm.gid where "
					+ "hm.hospital_code=:hospitalCode and mt.report_date=:reportDate";

//			String strQuery  = "select forenoonOpTotal,afternoonOpTotal,totalPrecheck,patientLabTest,totalLabTest,totalAttendee,"
//					+ "houseVisitMo,houseVisitHs,houseVisitPhns,houseVisitHi,houseVisitPhn,houseVisitJhi,houseVisitJphn,"
//					+ "houseVisitAsha,regularScClinic,m.hospitalMonthlyTracker.hospital.subCenterCount from MonthlyDataFhcChc m "
//					+ " where m.hospitalMonthlyTracker.hospital.id=:hospitalid and m.hospitalMonthlyTracker.report_date=:reportDate";
			
			Query query = session.createSQLQuery(strQuery);
			//query.setDate("reportDate", getReportDate());
			query.setString("hospitalCode", hospitalId);	
			
			//resultSet = query.list();
			Iterator iterator = query.list().iterator();

			while (iterator.hasNext()) {

				// Map row = (Map) iterator.next();
				Object[] row = (Object[]) iterator.next();
				MonthlyDataFhcChc monthlyDataFhcChc = new MonthlyDataFhcChc();
				monthlyDataFhcChc.setForenoonOpTotal(castObjectToLong(row[0]));
				monthlyDataFhcChc.setAfternoonOpTotal(castObjectToLong(row[1]));
				monthlyDataFhcChc.setTotalPrecheck(castObjectToLong(row[2]));
				monthlyDataFhcChc.setPatientLabTest(castObjectToLong(row[3]));
				monthlyDataFhcChc.setTotalLabTest(castObjectToLong(row[4]));
				
				monthlyDataFhcChc.setTotalAttendee(castObjectToLong(row[5]));
				monthlyDataFhcChc.setHouseVisitMo(castObjectToLong(row[6]));
				monthlyDataFhcChc.setHouseVisitHs(castObjectToLong(row[7]));
				
				monthlyDataFhcChc.setHouseVisitPhns(castObjectToLong(row[8]));
				monthlyDataFhcChc.setHouseVisitHi(castObjectToLong(row[9]));
				monthlyDataFhcChc.setHouseVisitPhn(castObjectToLong(row[10]));
				monthlyDataFhcChc.setHouseVisitJhi(castObjectToLong(row[11]));
				monthlyDataFhcChc.setHouseVisitJphn(castObjectToLong(row[12]));
				monthlyDataFhcChc.setHouseVisitAsha(castObjectToLong(row[13]));
				monthlyDataFhcChc.setRegularScClinic(castObjectToLong(row[14]));
				
				
//				categoryDetailsResult.setSanctionedPost(castObjectToLong(row[1]));
//				categoryDetailsResult.setInPosition(castObjectToLong(row[2]));
				
			}
			//.get(Calendar.MONTH)
			
//				Criteria cr = session.createCriteria(MonthlyDataFhcChc.class, "MonthlyDataFhcChc")
//						.createCriteria("MonthlyDataFhcChc.hospitalMonthlyTracker", "trackerTable")
//					//	.createCriteria("trackerTable.hospital", "hospitalTable") //needs to nbe enabled
//						.add(Restrictions.eq("trackerTable.id", new Long(hospitalId))) //needs to be commented
//						//.add(Restrictions.eq("hospital.hospitalCode", hospitalId))//needs to nbe enabled
//					//	.add(Restrictions.eq("trackerTable.report_date",getReportDate() ))
//						.setProjection(Projections.projectionList()
//								.add(Projections.property("forenoonOpTotal"), "forenoonOpTotal")
//								.add(Projections.property("afternoonOpTotal"), "afternoonOpTotal")
//								.add(Projections.property("totalPrecheck"), "totalPrecheck")
//								.add(Projections.property("patientLabTest"), "patientLabTest")
//								.add(Projections.property("totallabTest"), "totallabTest")
//								.add(Projections.property("housevisitMo"), "housevisitMo")
//								.add(Projections.property("housevisitHs"), "housevisitHs")
//								.add(Projections.property("housevisitPhns"), "housevisitPhns")
//								.add(Projections.property("housevisitHi"), "housevisitHi")
//								.add(Projections.property("housevisitPhl"), "housevisitPhl")
//								.add(Projections.property("housevisitJhi"), "housevisitJhi")
//								.add(Projections.property("housevisitJphn"), "housevisitJphn"))
//						.setResultTransformer(Transformers.aliasToBean(MonthlyDataFhcChc.class));
				
				//dataFhcChcs = cr.list();

//			}
		} catch (HibernateException e) {
			//if (transaction != null) {
	//			transaction.rollback();
		//	}
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new Exception("Exception : " + e.getMessage());

		} finally {
			session.close();
		}
		return dataFhcChcs;
	}
	
	
	public List<MonthlyDataFhcChc> getPhcDynamicDataTrend(String hospitalId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();//HibernatePersistence.getSessionFactory().openSession();

		//Transaction transaction = null;
		List<MonthlyDataFhcChc> dataFhcChcs = null;
		// List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		//List<Object> resultSet = new ArrayList<Object>();
		try {
		//transaction = session.beginTransaction();
			Calendar today = Calendar.getInstance();
			HospitalMonthlyTracker trackerForCurrentMonth = getMonthlyTrackerForCurrentMonth(new Long(hospitalId),
					today.get(Calendar.MONTH));

			Long trackerid = 0L;
			 
			for (int i=0;i<Constants.trendPeriod;i++) {
				today.add(Calendar.MONTH, -1);
			}
			
			List<Date> dates = new ArrayList<Date>();
			
			if (trackerForCurrentMonth == null) {
				// to do

			} else {
				
				String query="select forenoonOpTotal,afternoonOpTotal,totalPrecheck,patientLabTest,totallabTest,housevisitMo,housevisitHs,"
						+ "housevisitPhns,housevisitHi,housevisitPhl,housevisitJhi,housevisitJphn from MonthlyDataFhcChc where hospitalMonthlyTracker.reportMonth in ()";
				

				trackerid = trackerForCurrentMonth.getId();

				Criteria cr = session.createCriteria(MonthlyDataFhcChc.class, "MonthlyDataFhcChc")
						.createCriteria("MonthlyDataFhcChc.hospitalMonthlyTracker", "trackerTable")
					//	.add(Restrictions.in(propertyName, values)("trackerTable.id", trackerid))
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
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			throw new Exception("Exception : " + e.getMessage());

		} finally {
			session.close();
		}
		return dataFhcChcs;
	}

}
