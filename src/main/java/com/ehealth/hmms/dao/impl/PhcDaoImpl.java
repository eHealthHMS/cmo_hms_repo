package com.ehealth.hmms.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	final static Logger logger = Logger.getLogger(PhcDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * method to fetch the consolidated phc static data for an institution
	 * @return
	 * @throws Exception
	 */
	public List<CategoryDetails> getPhcStaticData(String hospitalId) throws Exception {
		logger.info("Entered PhcDaoImpl : getPhcStaticData");
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();

		List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		try {

			String hibernateQuery = "from CategoryDetails cd inner join cd.hospital hm where hm.nin=:nin";//cd.hospital=:nin";
			Query query = session.createQuery(hibernateQuery);
			query.setLong("nin", nin);
			Iterator iterator = query.list().iterator();
			
			while (iterator.hasNext()) {
				Object[] row = (Object[]) iterator.next();
				CategoryDetails categoryDetailsResult = (CategoryDetails) row[0];
				categoryDetails.add(categoryDetailsResult);
			}

		} catch (HibernateException e) {
			
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			
			throw new Exception("Exception : " + e.getMessage());

		}
		return categoryDetails;
	}
	
	/**
	 * method to fetch the consolidated phc static data for a district
	 * @return
	 * @throws Exception
	 */
	public List<CategoryDetails> getPhcStaticDataForDistrict(Long districtId) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();

		List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		try {
//select hm.gid,sum() from category_details cd inner join
			
			String hibernateQuery = "select cm.id,cm.categoryName,sum(cd.sanctionedPost),sum(cd.inPosition),sum(cd.nhm),sum(cd.contract),sum(cd.totalStaffAvailable)  from CategoryDetails cd inner join "
					+ "cd.hospital hm inner join cd.categoryMaster cm where  hm.hospitalTypeMaster in (15,16,17,20)  and hm.districtMaster=:districtId "
					+ " group by cm.id";//hm.id ";//cd.hospital=:nin";
			Query query = session.createQuery(hibernateQuery);
			query.setLong("districtId", districtId);
			Iterator iterator = query.list().iterator();
			
			while (iterator.hasNext()) {
				Object[] row = (Object[]) iterator.next();
				CategoryMaster categoryMaster = new CategoryMaster();
				categoryMaster.setId((Long)row[0]);
				categoryMaster.setCategoryName(castObjectToString(row[1]));
				
				CategoryDetails categoryDetailsResult = new CategoryDetails();
				categoryDetailsResult.setCategoryMaster(categoryMaster);
				categoryDetailsResult.setSanctionedPost((Long)row[2]);
				categoryDetails.add(categoryDetailsResult);
			}

		} catch (HibernateException e) {
			
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			
			throw new Exception("Exception : " + e.getMessage());

		}
		return categoryDetails;
	}
	
	/**
	 * method to fetch the consolidated phc static data for state
	 * @return
	 * @throws Exception
	 */
	
	public List<CategoryDetails> getPhcStaticDataForState() throws Exception {

		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();

		List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		try {

			String hibernateQuery = "select  from CategoryDetails cd inner join cd.hospital hm where hm.nin=:nin and hm.hospitaltype_id in (15,16,17,20)  and hm.district_id=:districtId ";//cd.hospital=:nin";
			Query query = session.createQuery(hibernateQuery);
			//query.setLong("districtId", districtId);
			Iterator iterator = query.list().iterator();
			
			while (iterator.hasNext()) {
				Object[] row = (Object[]) iterator.next();
				CategoryDetails categoryDetailsResult = (CategoryDetails) row[0];
				categoryDetails.add(categoryDetailsResult);
			}

		} catch (HibernateException e) {
			
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl:saveOrUpdateDeptWiseIpOpDetails: " + e);
			throw new Exception("Exception : " + e.getMessage());

		}
		logger.info("Exited PhcDaoImpl : getPhcStaticData");
		return categoryDetails;
	}

	// Fetch monthly record of PHC for getting data in android.
	public MonthlyDataFhcChc fetchPhcRecord(Long hospitalId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
		logger.info("Entered PhcDaoImpl : fetchPhcRecord");
		// Transaction transaction = null;
		MonthlyDataFhcChc monthlyDataFhcChc = new MonthlyDataFhcChc();

		try {
			// transaction = session.beginTransaction();
			// Query query = session.createSQLQuery("select
			// m.forenoon_op_male,m.forenoon_op_female,m.forenoon_op_tg,m.forenoon_op_tot,m.afternoon_op_male,m.afternoon_op_female,m.afternoon_op_tg,m.afternoon_op_tot,m.total_precheck,m.total_postconsultncounsel,m.patient_lab_test,m.total_lab_test,m.swas_clinic_new,m.aswasam_clinic_new,m.swas_clinic_followup,m.aswasam_clinic_followup,m.ncd_clinic_new,m.ncd_clinic_followup,m.tot_sc_immunizatnclinic,m.tot_other_sc_clinic,m.tot_outreach,m.tot_ncd_clinic,m.iec_healthpromo_activities,m.whsnc_meeting,m.regular_sc_clinic,m.jagratha_activities,m.total_attendee,m.houseVisitMo,m.housevisit_hs,m.housevisit_phns,m.housevisit_hi,m.housevisit_phn,m.housevisit_jhi,m.housevisit_jphn,m.housevisit_asha,m.last_hmc_meeting,m.monthly_staff_conf,m.post_dmo_conf,m.half_day_zonal,m.full_day_zonal,m.idsp_meetingconductd
			// from monthlydata_fhc_chc m inner join hospital_monthlytracker h on h.id =
			// m.hospmonthlytrack_id where h.report_date =:to_date(date,'yyyy-mm-dd') and
			// h.hospital_id =:hospitalId;");
			String sql = "select m.forenoon_op_male,m.forenoon_op_female,m.forenoon_op_tg,m.forenoon_op_tot,m.afternoon_op_male,m.afternoon_op_female,m.afternoon_op_tg,m.afternoon_op_tot,m.total_precheck,m.total_postconsultncounsel,m.patient_lab_test,m.total_lab_test,m.swas_clinic_new,m.aswasam_clinic_new,m.swas_clinic_followup,m.aswasam_clinic_followup,m.ncd_clinic_new,m.ncd_clinic_followup,m.tot_sc_immunizatnclinic,m.tot_other_sc_clinic,m.tot_outreach,m.tot_ncd_clinic,m.iec_healthpromo_activities,m.whsnc_meeting,m.regular_sc_clinic,m.jagratha_activities,m.total_attendee,m.housevisit_mo,m.housevisit_hs,m.housevisit_phns,m.housevisit_hi,m.housevisit_phn,m.housevisit_jhi,m.housevisit_jphn,m.housevisit_asha,m.last_hmc_meeting,m.monthly_staff_conf,m.post_dmo_conf,m.half_day_zonal,m.full_day_zonal,m.idsp_meetingconductd,m.hospmonthlytrack_id from monthlydata_fhc_chc m inner join hospital_monthlytracker h on h.id = m.hospmonthlytrack_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";

			Query query = session.createSQLQuery(sql);

			query.setParameter("hospitalId", hospitalId);

			query.setParameter("date", getReportDate(-1));

			List<MonthlyDataFhcChc> phcList = query.list();

			if (phcList != null && !phcList.isEmpty()) {
				Iterator iterator = phcList.iterator();

				while (iterator.hasNext()) {
					Object[] row = (Object[]) iterator.next();

					monthlyDataFhcChc.setForenoonOpMale(castObjectToLong(row[0]));
					monthlyDataFhcChc.setForenoonOpFemale(castObjectToLong(row[1]));
					monthlyDataFhcChc.setForenoonOpTg(castObjectToLong(row[2]));
					monthlyDataFhcChc.setForenoonOpTotal(castObjectToLong(row[3]));
					monthlyDataFhcChc.setAfternoonOpMale(castObjectToLong(row[4]));
					monthlyDataFhcChc.setAfternoonOpFemale(castObjectToLong(row[5]));
					monthlyDataFhcChc.setAfternoonOpTg(castObjectToLong(row[6]));
					monthlyDataFhcChc.setAfternoonOpTotal(castObjectToLong(row[7]));
					monthlyDataFhcChc.setTotalPrecheck(castObjectToLong(row[8]));
					monthlyDataFhcChc.setTotalPostConsultnCounsel(castObjectToLong(row[9]));
					monthlyDataFhcChc.setPatientLabTest(castObjectToLong(row[10]));
					monthlyDataFhcChc.setTotalLabTest(castObjectToLong(row[11]));
					monthlyDataFhcChc.setSwasClinicNew(castObjectToLong(row[12]));
					monthlyDataFhcChc.setAswasamClinicNew(castObjectToLong(row[13]));
					monthlyDataFhcChc.setSwasClinicFollowup(castObjectToLong(row[14]));
					monthlyDataFhcChc.setAswasamClinicFollowup(castObjectToLong(row[15]));
					monthlyDataFhcChc.setNcdClinicNew(castObjectToLong(row[16]));
					monthlyDataFhcChc.setNcdClinicFollowup(castObjectToLong(row[17]));
					monthlyDataFhcChc.setTotScImmunizatnClinic(castObjectToLong(row[18]));
					monthlyDataFhcChc.setTotOtherScClinic(castObjectToLong(row[19]));
					monthlyDataFhcChc.setTotOutreach(castObjectToLong(row[20]));
					monthlyDataFhcChc.setTotNcdClinic(castObjectToLong(row[21]));
					monthlyDataFhcChc.setIecHealthpromoActivities(castObjectToLong(row[22]));
					monthlyDataFhcChc.setWhsncMeeting(castObjectToLong(row[23]));
					monthlyDataFhcChc.setRegularScClinic(castObjectToLong(row[24]));
					monthlyDataFhcChc.setJagrathaActivities(castObjectToLong(row[25]));
					monthlyDataFhcChc.setTotalAttendee(castObjectToLong(row[26]));
					monthlyDataFhcChc.setHouseVisitMo(castObjectToLong(row[27]));
					monthlyDataFhcChc.setHouseVisitHs(castObjectToLong(row[28]));
					monthlyDataFhcChc.setHouseVisitPhns(castObjectToLong(row[29]));
					monthlyDataFhcChc.setHouseVisitHi(castObjectToLong(row[30]));
					monthlyDataFhcChc.setHouseVisitPhn(castObjectToLong(row[31]));
					monthlyDataFhcChc.setHouseVisitJhi(castObjectToLong(row[32]));
					monthlyDataFhcChc.setHouseVisitJphn(castObjectToLong(row[33]));
					monthlyDataFhcChc.setHouseVisitAsha(castObjectToLong(row[34]));
					monthlyDataFhcChc.setLastHmcMeeting((Date) row[35]);
					monthlyDataFhcChc.setMonthlyStaffConf(castObjectToBoolean(row[36]));
					monthlyDataFhcChc.setPostDmoConf(castObjectToBoolean(row[37]));
					monthlyDataFhcChc.setHalfDayZonal(castObjectToBoolean(row[38]));
					monthlyDataFhcChc.setFullDayZonal(castObjectToBoolean(row[39]));
					monthlyDataFhcChc.setIdspMeetingConductd(castObjectToLong(row[40]));
					monthlyDataFhcChc.setHospitalMonthlyTracker(castObjectToHmt(row[41]));
				}
			}
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in PhcDaoImpl:fetchPhcRecord: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited PhcDaoImpl : fetchPhcRecord");
		return monthlyDataFhcChc;
	}

	private Long castObjectToLong(Object object) {

		if(object!=null) {
			return new Long((Integer)object);
		}else {
			return null;
		}
		
	}
	

	private String castObjectToString(Object object) {

		if(object!=null) {
			return object.toString();
		}else {
			return null;
		}
		
	}

	private Boolean castObjectToBoolean(Object object) {

		return new Boolean((Boolean) ((object != null) ? object : false));

	}

	private HospitalMonthlyTracker castObjectToHmt(Object object) {
		HospitalMonthlyTracker hospMonTrack = new HospitalMonthlyTracker();
		hospMonTrack.setId(new Long((Integer) ((object != null) ? object : 0)));
		return hospMonTrack;
	}

	/**
	  * method to save phc functional components monthly
	  */
	public Result saveInstitutionalData(MonthlyDataFhcChc dataFhcChc) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		logger.info("Entered PhcDaoImpl : saveInstitutionalData");
		Result result = new Result();
		try {
		if(dataFhcChc.getHospitalMonthlyTracker().getId()==null) {
		
				session.save(dataFhcChc);
				result.setStatus(Constants.SUCCESS_STATUS);
			
		}else {
			Query query = session
					.createQuery("from MonthlyDataFhcChc where hospitalMonthlyTracker.id=:hospitalMonthlyTrackerId");

			query.setLong("hospitalMonthlyTrackerId", dataFhcChc.getHospitalMonthlyTracker().getId());
			List<MonthlyDataFhcChc> dataFhcChcs = query.list();
			if (dataFhcChcs != null && !dataFhcChcs.isEmpty()) {

				MonthlyDataFhcChc dataFhcChcFromDb = dataFhcChcs.get(0);

				dataFhcChcFromDb.setForenoonOpFemale(dataFhcChc.getForenoonOpFemale());
				dataFhcChcFromDb.setForenoonOpMale(dataFhcChc.getForenoonOpMale());
				dataFhcChcFromDb.setForenoonOpTg(dataFhcChc.getForenoonOpTg());
				dataFhcChcFromDb.setForenoonOpTotal(dataFhcChc.getForenoonOpTotal());

				dataFhcChcFromDb.setAfternoonOpFemale(dataFhcChc.getAfternoonOpFemale());
				dataFhcChcFromDb.setAfternoonOpMale(dataFhcChc.getAfternoonOpMale());
				dataFhcChcFromDb.setAfternoonOpTg(dataFhcChc.getAfternoonOpTg());
				dataFhcChcFromDb.setAfternoonOpTotal(dataFhcChc.getAfternoonOpTotal());

				dataFhcChcFromDb.setTotalPrecheck(dataFhcChc.getTotalPrecheck());
				dataFhcChcFromDb.setTotalPostConsultnCounsel(dataFhcChc.getTotalPostConsultnCounsel());
				dataFhcChcFromDb.setPatientLabTest(dataFhcChc.getPatientLabTest());
				dataFhcChcFromDb.setTotalLabTest(dataFhcChc.getTotalLabTest());

				dataFhcChcFromDb.setSwasClinicNew(dataFhcChc.getSwasClinicNew());
				dataFhcChcFromDb.setSwasClinicFollowup(dataFhcChc.getSwasClinicFollowup());

				dataFhcChcFromDb.setAswasamClinicNew(dataFhcChc.getAswasamClinicNew());
				dataFhcChcFromDb.setAswasamClinicFollowup(dataFhcChc.getAswasamClinicFollowup());

				dataFhcChcFromDb.setNcdClinicNew(dataFhcChc.getNcdClinicNew());
				dataFhcChcFromDb.setNcdClinicFollowup(dataFhcChc.getNcdClinicFollowup());

				session.update(dataFhcChcFromDb);
				result.setStatus(Constants.SUCCESS_STATUS);
				}
			}

		} catch (HibernateException e) {
			result.setStatus(Constants.FAILURE_STATUS);
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			result.setStatus(Constants.FAILURE_STATUS);
			logger.error("Exception occured in ThDaoImpl: saveInstitutionalData: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());

		}
		logger.info("Exited PhcDaoImpl : saveInstitutionalData");
		return result;
	}

	public Result saveMeetings(MonthlyDataFhcChc dataFhcChc) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		Result result = new Result();
		logger.info("Entered PhcDaoImpl : saveMeetings");
		try {

			Query query = session
					.createQuery("from MonthlyDataFhcChc where hospitalMonthlyTracker.id=:hospitalMonthlyTrackerId");

			query.setLong("hospitalMonthlyTrackerId", dataFhcChc.getHospitalMonthlyTracker().getId());
			List<MonthlyDataFhcChc> dataFhcChcs = query.list();
			if (dataFhcChcs != null && !dataFhcChcs.isEmpty()) {

				MonthlyDataFhcChc dataFhcChcFromDb = dataFhcChcs.get(0);

				dataFhcChcFromDb.setMonthlyStaffConf(dataFhcChc.getMonthlyStaffConf());
				dataFhcChcFromDb.setPostDmoConf(dataFhcChc.getPostDmoConf());
				dataFhcChcFromDb.setHalfDayZonal(dataFhcChc.getHalfDayZonal());
				dataFhcChcFromDb.setFullDayZonal(dataFhcChc.getFullDayZonal());
				//todo
				dataFhcChcFromDb.setIdspMeetingConductd(dataFhcChc.getIdspMeetingConductd());

				dataFhcChcFromDb.setLastHmcMeeting(dataFhcChc.getLastHmcMeeting());

				session.update(dataFhcChcFromDb);
				result.setStatus(Constants.SUCCESS_STATUS);
			} else {
				session.save(dataFhcChc);
				result.setStatus(Constants.SUCCESS_STATUS);
			}

		} catch (HibernateException e) {
			result.setStatus(Constants.FAILURE_STATUS);
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in PhcDaoImpl : saveMeetings:" + e.getMessage());
			result.setStatus(Constants.FAILURE_STATUS);
			throw new Exception("Exception : " + e.getMessage());

		}
		logger.info("Exited PhcDaoImpl : saveMeetings");
		return result;
	}

	public Result saveSubCentreDetails(MonthlyDataFhcChc dataFhcChc) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		Result result = new Result();
		logger.info("Entered PhcDaoImpl : saveSubCentreDetails");
		try {

			Query query = session
					.createQuery("from MonthlyDataFhcChc where hospitalMonthlyTracker.id=:hospitalMonthlyTrackerId");

			query.setLong("hospitalMonthlyTrackerId", dataFhcChc.getHospitalMonthlyTracker().getId());
			List<MonthlyDataFhcChc> dataFhcChcs = query.list();
			if (dataFhcChcs != null && !dataFhcChcs.isEmpty()) {

				MonthlyDataFhcChc dataFhcChcFromDb = dataFhcChcs.get(0);

				dataFhcChcFromDb.setTotScImmunizatnClinic(dataFhcChc.getTotScImmunizatnClinic());
				dataFhcChcFromDb.setTotOtherScClinic(dataFhcChc.getTotOtherScClinic());
				dataFhcChcFromDb.setTotOutreach(dataFhcChc.getTotOutreach());
				dataFhcChcFromDb.setTotNcdClinic(dataFhcChc.getTotNcdClinic());
				dataFhcChcFromDb.setIecHealthpromoActivities(dataFhcChc.getIecHealthpromoActivities());
				dataFhcChcFromDb.setWhsncMeeting(dataFhcChc.getWhsncMeeting());
				dataFhcChcFromDb.setRegularScClinic(dataFhcChc.getRegularScClinic());
				dataFhcChcFromDb.setJagrathaActivities(dataFhcChc.getJagrathaActivities());
				dataFhcChcFromDb.setTotalAttendee(dataFhcChc.getTotalAttendee());

				session.update(dataFhcChcFromDb);
				result.setStatus(Constants.SUCCESS_STATUS);
			} else {
				session.save(dataFhcChc);
				result.setStatus(Constants.SUCCESS_STATUS);
			}

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in PhcDaoImpl : saveSubCentreDetails :"+ e.getMessage());
			throw new Exception("Exception : " + e.getMessage());

		}
		logger.info("Exited PhcDaoImpl : saveSubCentreDetails");
		return result;
	}

	public Result saveFieldWorks(MonthlyDataFhcChc dataFhcChc) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		Result result = new Result();
		logger.info("Entered PhcDaoImpl : saveFieldWorks");
		try {
			Query query = session
					.createQuery("from MonthlyDataFhcChc where hospitalMonthlyTracker.id=:hospitalMonthlyTrackerId");

			query.setLong("hospitalMonthlyTrackerId", dataFhcChc.getHospitalMonthlyTracker().getId());
			List<MonthlyDataFhcChc> dataFhcChcs = query.list();
			if (dataFhcChcs != null && !dataFhcChcs.isEmpty()) {

				MonthlyDataFhcChc dataFhcChcFromDb = dataFhcChcs.get(0);

				dataFhcChcFromDb.setHouseVisitMo(dataFhcChc.getHouseVisitMo());
				dataFhcChcFromDb.setHouseVisitHs(dataFhcChc.getHouseVisitHs());
				dataFhcChcFromDb.setHouseVisitPhns(dataFhcChc.getHouseVisitPhns());
				dataFhcChcFromDb.setHouseVisitHi(dataFhcChc.getHouseVisitHi());
				dataFhcChcFromDb.setHouseVisitPhn(dataFhcChc.getHouseVisitPhn());
				dataFhcChcFromDb.setHouseVisitJhi(dataFhcChc.getHouseVisitJhi());
				dataFhcChcFromDb.setHouseVisitJphn(dataFhcChc.getHouseVisitJphn());
				dataFhcChcFromDb.setHouseVisitAsha(dataFhcChc.getHouseVisitAsha());

				session.update(dataFhcChcFromDb);
				result.setStatus(Constants.SUCCESS_STATUS);
				// Iterator iterator = phcList.iterator();
			} else {
				session.save(dataFhcChc);
				result.setStatus(Constants.SUCCESS_STATUS);
			}

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in PhcDaoImpl : saveFieldWorks:" + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());

		}
		logger.info("Exited PhcDaoImpl : saveFieldWorks");
		return result;
	}

	/**
	 * method to save phc functional components monthly
	 */
	public HospitalMonthlyTracker getHospitalTrakerForSave(HospitalMonthlyTracker hospitalMonthlyTracker) throws Exception {
		logger.info("Entered PhcDaoImpl : getHospitalTrakerForSave");
		HospitalMonthlyTracker trackerForCurrentMonth =null;
		try {

			if (hospitalMonthlyTracker != null && hospitalMonthlyTracker.getHospital() != null) {
				Long hospitalId = hospitalMonthlyTracker.getHospital().getId();
				trackerForCurrentMonth = getMonthlyTrackerForCurrentMonth(hospitalId);

				if (trackerForCurrentMonth == null) {
					trackerForCurrentMonth = createHospitalMonthlyTracker(hospitalId);
				} 
			}

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in PhcDaoImpl : getHospitalTrakerForSave:"+e.getMessage());
			throw new Exception("Exception : " + e.getMessage());

		}
		logger.info("Exited PhcDaoImpl : getHospitalTrakerForSave");
		return trackerForCurrentMonth;
	}

	/**
	 * save hospital monthly tracker
	 * 
	 * @param dataFhcChc
	 * @return
	 * @throws Exception
	 */
	public HospitalMonthlyTracker createHospitalMonthlyTracker(Long hospitalId) throws Exception {
	

			HospitalMonthlyTracker trackerForCurrentMonth = new HospitalMonthlyTracker();
			HospitalMaster hospitalMaster = new HospitalMaster();
			hospitalMaster.setId(hospitalId);
			trackerForCurrentMonth.setHospital(hospitalMaster);
			trackerForCurrentMonth.setLastModified(new Date());
			trackerForCurrentMonth.setCreatedDate(new Date());
			trackerForCurrentMonth.setReport_date(getReportDate(-1));// setReportMonth(new
							return trackerForCurrentMonth;									   // Long(Calendar.getInstance().get(Calendar.MONTH)));
			
	}
	
	
	/**
	 * method to update hospital monthly tracker
	 */
	public HospitalMonthlyTracker updateHospitalMonthlyTracker(HospitalMonthlyTracker hospitalMonthlyTracker,Boolean isFinal) throws Exception {
		logger.info("Entered PhcDaoImpl : updateHospitalMonthlyTracker");
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
		HospitalMonthlyTracker hospitalMonthlyTrackerResult = null;
		try {

			hospitalMonthlyTrackerResult = (HospitalMonthlyTracker)session.get(HospitalMonthlyTracker.class, hospitalMonthlyTracker.getId());
			
			if(hospitalMonthlyTrackerResult!=null) {
				hospitalMonthlyTrackerResult.setLastModified(Calendar.getInstance().getTime());
				hospitalMonthlyTrackerResult.setFinalSubmitDone(isFinal);
				session.update(hospitalMonthlyTrackerResult);
			}
			
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in PhcDaoImpl : updateHospitalMonthlyTracker:" +e.getMessage());
			throw new Exception("Exception : " + e.getMessage());

		}
		logger.info("Exited PhcDaoImpl : updateHospitalMonthlyTracker");
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
	public HospitalMonthlyTracker getMonthlyTrackerForCurrentMonth(Long hospitalId) throws Exception {
		logger.info("Entered PhcDaoImpl : getMonthlyTrackerForCurrentMonth");
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		try {

			Query query = session.createQuery(
					"from HospitalMonthlyTracker mt where mt.report_date=:reportDate and mt.hospital.id=:hospitalId");
			query.setDate("reportDate", getReportDate(-1));
			query.setLong("hospitalId", hospitalId);

			List<HospitalMonthlyTracker> hospitalMonthlyTrackers = query.list();

			if (hospitalMonthlyTrackers != null && !hospitalMonthlyTrackers.isEmpty()) {
				hospitalMonthlyTracker = hospitalMonthlyTrackers.get(0);
			}

		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in  PhcDaoImpl : getMonthlyTrackerForCurrentMonth:"+ e.getMessage());
			throw new Exception("Exception : " + e.getMessage());

		}
		logger.info("Exited PhcDaoImpl : getMonthlyTrackerForCurrentMonth");
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
//	private HospitalMonthlyTracker getMonthlyTrackerForTrend(Long hospitalId, int currentMonth) throws Exception {
//
//		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
//		HospitalMonthlyTracker hospitalMonthlyTracker = null;
//		try {
//
//			Query query = session.createQuery(
//					"from HospitalMonthlyTracker mt where mt.reportMonth=:month and mt.hospital.id=:hospitalId");
//			query.setLong("month", currentMonth);
//			query.setLong("hospitalId", hospitalId);
//
//			List<HospitalMonthlyTracker> hospitalMonthlyTrackers = query.list();
//
//			if (hospitalMonthlyTrackers != null && !hospitalMonthlyTrackers.isEmpty()) {
//				hospitalMonthlyTracker = hospitalMonthlyTrackers.get(0);
//			}
//
//		} catch (HibernateException e) {
//			
//			throw new HibernateException("Hibernate Exception : " + e.getMessage());
//		} catch (Exception e) {
//			
//			throw new Exception("Exception : " + e.getMessage());
//
//		}
//		return hospitalMonthlyTracker;
//	}
//	
	private Date getReportDate(int value)  throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, value);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		
		
		
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.AM_PM);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		
//		SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String dmy = dmyFormat.format(calendar.getTime());
//		return dmyFormat.parse(dmy);
		
		
	
		return calendar.getTime();
	}

	// for dashboard
	// coding done// testing pending
	public List<MonthlyDataFhcChc> getPhcDynamicDataForDashboard(String hospitalId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();

		List<MonthlyDataFhcChc> dataFhcChcs = null;
		try {

			String strQuery = "select forenoon_op_tot,afternoon_op_tot,total_precheck,patient_lab_test,total_lab_test,total_attendee,"
					+ "housevisit_mo,housevisit_hs,housevisit_phns,housevisit_hi,housevisit_phn,housevisit_jhi,housevisit_jphn,"
					+ "housevisit_asha,regular_sc_clinic from monthlydata_fhc_chc md inner join hospital_monthlytracker mt "
					+ " on md.hospmonthlytrack_id  = mt.id inner join  hospital_master hm  on mt.hospital_id=hm.gid where "
					+ "hm.hospital_code=:hospitalCode and mt.report_date=:reportDate";

			

			Query query = session.createSQLQuery(strQuery);
			query.setString("hospitalCode", hospitalId);

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


			}
			
		} catch (HibernateException e) {
			
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Exception : " + e.getMessage());

		}
		return dataFhcChcs;
	}

	public List<MonthlyDataFhcChc> getPhcDynamicDataTrend(String hospitalId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();

		List<MonthlyDataFhcChc> dataFhcChcs = null;
	
		try {
			Calendar today = Calendar.getInstance();
			HospitalMonthlyTracker trackerForCurrentMonth = getMonthlyTrackerForCurrentMonth(new Long(hospitalId));

			Long trackerid = 0L;

			for (int i = 0; i < Constants.trendPeriod; i++) {
				today.add(Calendar.MONTH, -1);
			}

			List<Date> dates = new ArrayList<Date>();

			if (trackerForCurrentMonth == null) {
				// to do

			} else {
				
				String strQuery = "select forenoon_op_tot,afternoon_op_tot,total_precheck,patient_lab_test,total_lab_test,total_attendee,"
						+ "housevisit_mo,housevisit_hs,housevisit_phns,housevisit_hi,housevisit_phn,housevisit_jhi,housevisit_jphn,"
						+ "housevisit_asha,regular_sc_clinic,hm.subcenter_count from monthlydata_fhc_chc md inner join hospital_monthlytracker mt "
						+ " on md.hospmonthlytrack_id  = mt.id inner join  hospital_master hm  on mt.hospital_id=hm.gid  inner join category_details cd"
						+ " on cd.hospital_id=hm.gid where "
						+ "hm.nin=:hospitalCode and mt.report_date=to_date(:reportDate,'yyyy-mm-dd') and mt.final_submit_done=true";				
				
				

				String query = "select forenoonOpTotal,afternoonOpTotal,totalPrecheck,patientLabTest,totallabTest,housevisitMo,housevisitHs,"
						+ "housevisitPhns,housevisitHi,housevisitPhl,housevisitJhi,housevisitJphn from MonthlyDataFhcChc where hospitalMonthlyTracker.reportMonth in ()";

				trackerid = trackerForCurrentMonth.getId();

				Criteria cr = session.createCriteria(MonthlyDataFhcChc.class, "MonthlyDataFhcChc")
						.createCriteria("MonthlyDataFhcChc.hospitalMonthlyTracker", "trackerTable")
						// .add(Restrictions.in(propertyName, values)("trackerTable.id", trackerid))
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
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Exception : " + e.getMessage());

		}
		return dataFhcChcs;
	}

	public Map<String,String> getDataForMap(Long hospitalId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
		 Map<String,String> resultMap = new HashMap<String,String>();
		try {
			

			String strQuery = "select (forenoon_op_tot+afternoon_op_tot) as opCount,total_attendee,"
					+ "(iec_healthpromo_activities+tot_outreach) as healthActivities,hm.hospital_name from monthlydata_fhc_chc md inner join hospital_monthlytracker mt "
					+ " on md.hospmonthlytrack_id  = mt.id inner join  hospital_master hm  on mt.hospital_id=hm.gid where "
					+ "hm.nin=:hospitalId and mt.report_date=to_date(:reportDate,'yyyy-mm-dd') and final_submit_done=true";
			Query query = session.createSQLQuery(strQuery);
			query.setDate("reportDate", getReportDate(-1));
			query.setLong("hospitalId", hospitalId);

			List resultSet = query.list();
		
			 
			 
			 if(resultSet.isEmpty()) {
				 resultMap.put(Constants.DATA_STATUS, Constants.DATA_NOT_AVAILABLE);
				 resultMap.put(Constants.RESULT_STATUS, Constants.SUCCESS_STATUS.toString());
			 }else {
			 
			Iterator iterator = query.list().iterator();
			
			while (iterator.hasNext()) {

				// Map row = (Map) iterator.next();
				Object[] row = (Object[]) iterator.next();
				
				resultMap.put("opCount",row[0].toString());
				resultMap.put("totalPersons", row[1].toString());
				resultMap.put("healthActivities", row[2].toString());
				resultMap.put("hospitalName", row[3].toString());
				resultMap.put(Constants.DATA_STATUS, Constants.DATA_AVAILABLE);
				resultMap.put(Constants.RESULT_STATUS, Constants.SUCCESS_STATUS.toString());

			}
			 }

		} catch (HibernateException e) {
			resultMap.put(Constants.RESULT_STATUS, Constants.FAILURE_STATUS.toString());
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			resultMap.put(Constants.RESULT_STATUS, Constants.FAILURE_STATUS.toString());
			throw new Exception("Exception : " + e.getMessage());

		} 
		return resultMap;
	}
	
	
	public String averageHouseVisits(Long houseVisits,int nonWorkingDays) {
		  
		Long averageVisits = 0L;
		if(houseVisits!=null && nonWorkingDays!=0) {
			averageVisits= houseVisits/nonWorkingDays;
	   }
		return averageVisits.toString();
	}
	
	public int countNonWorkingDays() {
	    Calendar calendar = Calendar.getInstance();
	    // Note that month is 0-based in calendar, bizarrely.
	    
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
	   /// calendar.set(year, month - 1, 1);
	    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	    int count = 1;// 1 for second saturday
	    for (int day = 1; day <= daysInMonth; day++) {
	       // calendar.set(year, month - 1, day);
	    	 calendar.set(Calendar.DAY_OF_MONTH,day);
	        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	        if (dayOfWeek == Calendar.SUNDAY) {// || dayOfweek == Calendar.SATURDAY) {
	            count++;
	           
	        }
	    }
	    return count;
	}
	
	
	
	
	public List<Map<String,String>> getPhcDynamicDataForDistrict(Long districtId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
		Map<String,String> resultMap = new HashMap<String, String>();
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		try {

			for (int i=1;i<=6;i++) {
				
			String strQuery = "	 select sum(forenoon_op_tot),sum(afternoon_op_tot),sum(total_precheck),sum(patient_lab_test),sum(total_lab_test),sum(total_attendee),"
					+ "sum(housevisit_mo),sum(housevisit_hs),sum(housevisit_phns),sum(housevisit_hi),sum(housevisit_phn),sum(housevisit_jhi),sum(housevisit_jphn),"
					+ "sum(housevisit_asha),sum(regular_sc_clinic),sum(hm.subcenter_count) from  hospital_master hm  inner join hospital_monthlytracker mt on mt.hospital_id=hm.gid  inner join "
		+"		 monthlydata_fhc_chc md  on md.hospmonthlytrack_id  = mt.id where mt.report_date=to_date(:reportDate,'yyyy-mm-dd') and hm.hospitaltype_id in (15,16,17,20)  and hm.district_id=:districtId  and mt.final_submit_done=true";
			
			Query query = session.createSQLQuery(strQuery);
			
			
			SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			 query.setString("reportDate", dmyFormat.format(getReportDate(-1*i)));
			 query.setLong("districtId", districtId);

			Iterator iterator = query.list().iterator();
			while (iterator.hasNext()) {

				Object[] row = (Object[]) iterator.next();
				int nonworkingDays=countNonWorkingDays();
				resultMap.put("totalFnOp", castObjectToString(row[0]));
				resultMap.put("totalAnOp", castObjectToString(row[1]));
				resultMap.put("PrecheckByNurses",castObjectToString(row[2]));
				resultMap.put("patientLabTest", castObjectToString(row[3]));
				resultMap.put("totalLabTest", castObjectToString(row[4]));
				resultMap.put("totalAttendee",castObjectToString(row[5]));
				
				resultMap.put("houseVisitAsha", averageHouseVisits(castObjectToLong(row[13]),nonworkingDays));
				resultMap.put("houseVisitHi", averageHouseVisits(castObjectToLong(row[9]),nonworkingDays));
				resultMap.put("houseVisitHs", averageHouseVisits(castObjectToLong(row[7]),nonworkingDays));
				resultMap.put("houseVisitJhi", averageHouseVisits(castObjectToLong(row[11]),nonworkingDays));
				resultMap.put("houseVisitMo", averageHouseVisits(castObjectToLong(row[6]),nonworkingDays));
				resultMap.put("houseVisitPhn", averageHouseVisits(castObjectToLong(row[10]),nonworkingDays));
				resultMap.put("houseVisitJphn", averageHouseVisits(castObjectToLong(row[12]),nonworkingDays));
				resultMap.put("houseVisitPhns", averageHouseVisits(castObjectToLong(row[8]),nonworkingDays));
				Long subCentreCount=castObjectToLong(row[15]);
				if(subCentreCount!=null) {
					resultMap.put("irregularSc",String.valueOf(subCentreCount-castObjectToLong(row[14])));
				}
			
				resultList.add(resultMap);
			}

			}

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());

		} 
		return resultList;
	}
	
	
	public List<Map<String,String>> getPhcDynamicDataForState() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
		Map<String,String> resultMap = new HashMap<String, String>();
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		try {

			for (int i=1;i<=6;i++) {
				
			String strQuery = "	 select sum(forenoon_op_tot),sum(afternoon_op_tot),sum(total_precheck),sum(patient_lab_test),sum(total_lab_test),sum(total_attendee),"
					+ "sum(housevisit_mo),sum(housevisit_hs),sum(housevisit_phns),sum(housevisit_hi),sum(housevisit_phn),sum(housevisit_jhi),sum(housevisit_jphn),"
					+ "sum(housevisit_asha),sum(regular_sc_clinic),sum(hm.subcenter_count) from  hospital_master hm  inner join hospital_monthlytracker mt on mt.hospital_id=hm.gid  inner join "
		+"		 monthlydata_fhc_chc md  on md.hospmonthlytrack_id  = mt.id where mt.report_date=to_date(:reportDate,'yyyy-mm-dd') and hm.hospitaltype_id in (15,16,17,20) and mt.final_submit_done=true";
			
			Query query = session.createSQLQuery(strQuery);
			
			
			SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			 query.setString("reportDate", dmyFormat.format(getReportDate(-1*i)));

			Iterator iterator = query.list().iterator();
			while (iterator.hasNext()) {

				Object[] row = (Object[]) iterator.next();
				int nonworkingDays=countNonWorkingDays();
				resultMap.put("totalFnOp", castObjectToString(row[0]));
				resultMap.put("totalAnOp", castObjectToString(row[1]));
				resultMap.put("PrecheckByNurses",castObjectToString(row[2]));
				resultMap.put("patientLabTest", castObjectToString(row[3]));
				resultMap.put("totalLabTest", castObjectToString(row[4]));
				resultMap.put("totalAttendee",castObjectToString(row[5]));
				
				resultMap.put("houseVisitAsha", averageHouseVisits(castObjectToLong(row[13]),nonworkingDays));
				resultMap.put("houseVisitHi", averageHouseVisits(castObjectToLong(row[9]),nonworkingDays));
				resultMap.put("houseVisitHs", averageHouseVisits(castObjectToLong(row[7]),nonworkingDays));
				resultMap.put("houseVisitJhi", averageHouseVisits(castObjectToLong(row[11]),nonworkingDays));
				resultMap.put("houseVisitMo", averageHouseVisits(castObjectToLong(row[6]),nonworkingDays));
				resultMap.put("houseVisitPhn", averageHouseVisits(castObjectToLong(row[10]),nonworkingDays));
				resultMap.put("houseVisitJphn", averageHouseVisits(castObjectToLong(row[12]),nonworkingDays));
				resultMap.put("houseVisitPhns", averageHouseVisits(castObjectToLong(row[8]),nonworkingDays));
				Long subCentreCount=castObjectToLong(row[15]);
				if(subCentreCount!=null) {
					resultMap.put("irregularSc",String.valueOf(subCentreCount-castObjectToLong(row[14])));
				}
			
				resultList.add(resultMap);
			}

			}

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());

		} 
		return resultList;
	}
	
	
	public List<Map<String,String>> getPhcDynamicDataFromHospitalId(Long hospitalId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
		Map<String,String> resultMap = new HashMap<String, String>();
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		try {

			

			String strQuery = "	 select forenoon_op_tot,afternoon_op_tot,total_precheck,patient_lab_test,total_lab_test,total_attendee,housevisit_mo,"
		+"	 housevisit_hs,housevisit_phns,housevisit_hi,housevisit_phn,housevisit_jhi,housevisit_jphn,housevisit_asha,regular_sc_clinic,"
		+"		 hm.subcenter_count,ST_X((ST_Dump(hm.geometry)).geom), ST_Y((ST_Dump(hm.geometry)).geom) from  hospital_master hm  inner join hospital_monthlytracker mt on mt.hospital_id=hm.gid  inner join "
		+"		 monthlydata_fhc_chc md  on md.hospmonthlytrack_id  = mt.id where hm.nin=:hospitalCode  and  mt.report_date between "
		+"		 to_date(:startReportDate,'yyyy-mm-dd') and  to_date(:endReportDate,'yyyy-mm-dd') and mt.final_submit_done=true";
			
			
//			
//			String strQuery = "	 select sum(forenoon_op_tot) ,sum(afternoon_op_tot),sum(total_precheck),sum(patient_lab_test),sum(total_lab_test),sum(total_attendee),"
//					+ "sum(housevisit_mo),sum(housevisit_hs),sum(housevisit_phns),sum(housevisit_hi),sum(housevisit_phn),sum(housevisit_jhi),sum(housevisit_jphn),"
//					+ "sum(housevisit_asha),sum(regular_sc_clinic),sum(hm.subcenter_count) from  hospital_master hm  inner join hospital_monthlytracker mt on mt.hospital_id=hm.gid  inner join "
//		+"		 monthlydata_fhc_chc md  on md.hospmonthlytrack_id  = mt.id where hm.nin=:hospitalCode  and mt.report_date between "
//		+"		 to_date(:startReportDate,'yyyy-mm-dd') and  to_date(:endReportDate,'yyyy-mm-dd')  and mt.final_submit_done=true";
			

			Query query = session.createSQLQuery(strQuery);
			
			
			SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			 query.setString("endReportDate", dmyFormat.format(getReportDate(-1)));
			 query.setString("startReportDate",  dmyFormat.format(getReportDate(-6)));
			 query.setLong("hospitalCode", hospitalId);

			Iterator iterator = query.list().iterator();
			while (iterator.hasNext()) {

				Object[] row = (Object[]) iterator.next();
				int nonworkingDays=countNonWorkingDays();
				resultMap.put("totalFnOp", castObjectToString(row[0]));
				resultMap.put("totalAnOp", castObjectToString(row[1]));
				resultMap.put("PrecheckByNurses",castObjectToString(row[2]));
				resultMap.put("patientLabTest", castObjectToString(row[3]));
				resultMap.put("totalLabTest", castObjectToString(row[4]));
				resultMap.put("totalAttendee",castObjectToString(row[5]));
				
				resultMap.put("houseVisitAsha", averageHouseVisits(castObjectToLong(row[13]),nonworkingDays));
				resultMap.put("houseVisitHi", averageHouseVisits(castObjectToLong(row[9]),nonworkingDays));
				resultMap.put("houseVisitHs", averageHouseVisits(castObjectToLong(row[7]),nonworkingDays));
				resultMap.put("houseVisitJhi", averageHouseVisits(castObjectToLong(row[11]),nonworkingDays));
				resultMap.put("houseVisitMo", averageHouseVisits(castObjectToLong(row[6]),nonworkingDays));
				resultMap.put("houseVisitPhn", averageHouseVisits(castObjectToLong(row[10]),nonworkingDays));
				resultMap.put("houseVisitJphn", averageHouseVisits(castObjectToLong(row[12]),nonworkingDays));
				resultMap.put("houseVisitPhns", averageHouseVisits(castObjectToLong(row[8]),nonworkingDays));
				Long subCentreCount=castObjectToLong(row[15]);
				if(subCentreCount!=null) {
					resultMap.put("irregularSc",String.valueOf(subCentreCount-castObjectToLong(row[14])));
				}
				resultMap.put("latitude", castObjectToString(row[17]));
				resultMap.put("longitude", castObjectToString(row[16]));
				resultList.add(resultMap);

			}

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());

		} 
		return resultList;
	}

}
