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
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ehealth.hmms.dao.ThDao;
import com.ehealth.hmms.pojo.CategoryMaster;
import com.ehealth.hmms.pojo.DepartmentWiseOpIp;
import com.ehealth.hmms.pojo.FundExpenditure;
import com.ehealth.hmms.pojo.HospitalMaster;
import com.ehealth.hmms.pojo.HospitalMonthlyTracker;
import com.ehealth.hmms.pojo.IdlingMajorEquipment;
import com.ehealth.hmms.pojo.LabDialysis;
import com.ehealth.hmms.pojo.MonthlyDataTh;
import com.ehealth.hmms.pojo.OpIpDetails;
import com.ehealth.hmms.pojo.ServiceAreaOthers;
import com.ehealth.hmms.pojo.SpecialityClinic;
import com.ehealth.hmms.pojo.SpecialityClinicData;
import com.ehealth.hmms.pojo.SurgeryDetailsThDhGh;

@Repository
@Transactional
public class ThDaoImpl implements ThDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Boolean saveOrUpdateDeptWiseIpOpDetails(List<DepartmentWiseOpIp> departmentWiseOpIpList) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		for (DepartmentWiseOpIp departmentWiseOpIp : departmentWiseOpIpList) {
			if (departmentWiseOpIp.getHospitalMonthlyTrackerId().getId() != null) {
				hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class,
						departmentWiseOpIp.getHospitalMonthlyTrackerId().getId());
			}
			if (hospitalMonthlyTracker == null) {
				hospitalMonthlyTracker = departmentWiseOpIp.getHospitalMonthlyTrackerId();
				// hospitalMonthlyTracker.setId(id)
				hospitalMonthlyTracker.setLastModified(new Date());
				hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
				session.saveOrUpdate(hospitalMonthlyTracker);
				departmentWiseOpIp.setHospitalMonthlyTrackerId(hospitalMonthlyTracker);
			} else {
				hospitalMonthlyTracker.setLastModified(new Date());
				session.saveOrUpdate(hospitalMonthlyTracker);
				departmentWiseOpIp.setHospitalMonthlyTrackerId(hospitalMonthlyTracker);
			}
			session.saveOrUpdate(departmentWiseOpIp);
		}
		return true;
	}

	public Map<String,String> getThalukBasicData(Long nin){
		Session session = this.sessionFactory.getCurrentSession();
		Object[] opIpDetails = null;
		Object[] serviceAreasOthers = null;
		Object[] diaLysisDetails = null;
		
		HospitalMaster hospitalMaster = (HospitalMaster) session.get(HospitalMaster.class, nin);
		if(hospitalMaster!=null) {
			
			Query query = session.createQuery("from HospitalMonthlyTracker hmt where hmt.hospital.id=:hospital_id and hmt.report_date=:report_date and hmt.finalSubmitDone=true" );
			HospitalMonthlyTracker hospitalMonthlyTracker = (HospitalMonthlyTracker) query.setParameter("hospital_id", hospitalMaster.getId())
																						   .setParameter("report_date", getReportDate()).uniqueResult();
			if(hospitalMonthlyTracker != null) {
				opIpDetails = (Object[]) session.createQuery("select opipdet.forenoonOpTotal+opipdet.afternoonOpTotal,opipdet.emrPatinetAttended, "
									+ "opipdet.ipAdmissionsTotal from OpIpDetails opipdet "
									+ "where opipdet.hospitalMonthlyTracker.id=:hospmonthlytrack_id")
									.setParameter("hospmonthlytrack_id", hospitalMonthlyTracker.getId())
									.uniqueResult();
				serviceAreasOthers = (Object[])session.createQuery("select sao.ogLscsCount+sao.ogNormalDeliveryCount "
						+ "from ServiceAreaOthers sao "
						+ "where sao.hospitalMonthlyTracker.id=:hospmonthlytrack_id")
						.setParameter("hospmonthlytrack_id", hospitalMonthlyTracker.getId())
						.uniqueResult();
				diaLysisDetails = (Object[])session.createQuery("select dia.diaTotalCount "
						+ "from LabDialysis dia "
						+ "where dia.hospitalMonthlyTracker.id=:hospmonthlytrack_id")
						.setParameter("hospmonthlytrack_id", hospitalMonthlyTracker.getId())
						.uniqueResult();
			}
			
			
			//HospitalMonthlyTracker hospitalMonthlyTracker = session.get(HospitalMonthlyTracker.class, hospitalMaster.)
		}
		Map<String, String> basicData = new HashMap<String,String>();
		basicData.put("totalOPCount",(opIpDetails[0]==null?"no value":String.valueOf(opIpDetails[0])));
		basicData.put("emrPatientsAdmitted",(opIpDetails[1]==null?"no value":String.valueOf(opIpDetails[1])));
		basicData.put("ipAdmissionsTotal",(opIpDetails[2]==null?"no value":String.valueOf(opIpDetails[2])));
		basicData.put("totalDeliveryCount", (serviceAreasOthers==null?"no value":String.valueOf(serviceAreasOthers[0])));
		basicData.put("hospitalName", hospitalMaster.getHospitalName()==null?"no value":hospitalMaster.getHospitalName());
		basicData.put("totalDiaCount", (diaLysisDetails==null?"no value":String.valueOf(diaLysisDetails[0])));
		return basicData;
	}

	public Boolean saveOrUpdateServiceAreaOthers(ServiceAreaOthers serviceAreaOthers) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		if (serviceAreaOthers.getHospitalMonthlyTracker().getId() != null) {
			hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class,
					serviceAreaOthers.getHospitalMonthlyTracker().getId());
		}
		if (hospitalMonthlyTracker == null) {
			hospitalMonthlyTracker = serviceAreaOthers.getHospitalMonthlyTracker();
			hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
			serviceAreaOthers.setHospitalMonthlyTracker(hospitalMonthlyTracker);
		} else {
			hospitalMonthlyTracker.setLastModified(new Date());
			serviceAreaOthers.setHospitalMonthlyTracker(hospitalMonthlyTracker);
		}
		session.saveOrUpdate(serviceAreaOthers);
		return true;
	}

	public Boolean saveOrUpdateFundExpenditure(FundExpenditure fundExpenditure) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		if (fundExpenditure.getHospitalMonthlyTracker().getId() != null) {
			hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class,
					fundExpenditure.getHospitalMonthlyTracker().getId());
		}
		if (hospitalMonthlyTracker == null) {
			hospitalMonthlyTracker = fundExpenditure.getHospitalMonthlyTracker();
			hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
			fundExpenditure.setHospitalMonthlyTracker(hospitalMonthlyTracker);

		} else {
			hospitalMonthlyTracker.setLastModified(new Date());
			fundExpenditure.setHospitalMonthlyTracker(hospitalMonthlyTracker);
		}
		session.saveOrUpdate(fundExpenditure);
		return true;
	}

	// Saving or updating the OP and IP details of taluk hospital.

	public Boolean saveAndUpdateOpIpDetails(OpIpDetails opIpDetails) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();

		Boolean resultFlag = false;

		try {

			Long forenoonOpMale = opIpDetails.getForenoonOpMale();
			Long forenoonOpFemale = opIpDetails.getForenoonOpFemale();
			Long forenoonOpTg = opIpDetails.getForenoonOpTg();
			Long forenoonOpTotal = opIpDetails.getForenoonOpTotal();
			Long afternoonOpMale = opIpDetails.getAfternoonOpMale();
			Long afternoonOpFemale = opIpDetails.getAfternoonOpFemale();
			Long afternoonOpTg = opIpDetails.getAfternoonOpTg();
			Long afternoonOpTotal = opIpDetails.getAfternoonOpTotal();
			Long ipPatientsDischarged = opIpDetails.getIpPatientsDischarged();
			Long ipPatientsExpired = opIpDetails.getIpPatientsExpired();
			Long ipPatientsReferred = opIpDetails.getIpPatientsReferred();
			Long ipAdmissionsMale = opIpDetails.getIpAdmissionsMale();
			Long ipAdmissionsFemale = opIpDetails.getIpAdmissionsFemale();
			Long ipAdmissionsTotal = opIpDetails.getIpAdmissionsTotal();
			Long ipAdmissionsTg = opIpDetails.getIpAdmissionsTg();
			Long emrPatientReferred = opIpDetails.getEmrPatientReferred();
			Long emrRtaTrauma = opIpDetails.getEmrRtaTrauma();
			Long emrPatinetAttended = opIpDetails.getEmrPatinetAttended();
			Long emrPatientAdmited = opIpDetails.getEmrPatientAdmited();
			Long hospTrackId = opIpDetails.getHospitalMonthlyTracker().getId();

			String sql = "select * from op_ip_th_gh_dh op where op.hospmonthlytrack_id =:hospTrackId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospTrackId", hospTrackId);
			List<OpIpDetails> opIpQueryDetails = query.list();

			if (opIpQueryDetails != null && !opIpQueryDetails.isEmpty()) {
				if (opIpQueryDetails.get(0) != null) {
					String updateSql = "update op_ip_th_gh_dh set forenoon_op_male =:forenoonOpMale,forenoon_op_female=:forenoonOpFemale,"
							+ "forenoon_op_tg=:forenoonOpTg,forenoon_op_total=:forenoonOpTotal,afternoon_op_male=:afternoonOpMale,afternoon_op_tg=:afternoonOpTg,"
							+ "afternoon_op_total=:afternoonOpTotal,afternoon_op_female=:afternoonOpFemale,ip_admissions_male=:ipAdmissionsMale,"
							+ "ip_admissions_female=:ipAdmissionsFemale,ip_admissions_tg=:ipAdmissionsTg,ip_admissions_total=:ipAdmissionsTotal,"
							+ "ip_patients_discharged=:ipPatientsDischarged,ip_patients_expired=:ipPatientsExpired ,ip_patients_referred =:ipPatientsReferred,"
							+ "emr_patinet_attended =:emrPatinetAttended,emr_patient_admited =:emrPatientAdmited,emr_patient_referred =:emrPatientReferred,"
							+ "emr_rta_trauma =:emrRtaTrauma where hospmonthlytrack_id =:hospTrackId";
					Query updateQuery = session.createSQLQuery(updateSql);
					updateQuery.setParameter("forenoonOpMale", forenoonOpMale);
					updateQuery.setParameter("forenoonOpFemale", forenoonOpFemale);
					updateQuery.setParameter("forenoonOpTotal", forenoonOpTotal);
					updateQuery.setParameter("forenoonOpTg", forenoonOpTg);
					updateQuery.setParameter("afternoonOpMale", afternoonOpMale);
					updateQuery.setParameter("afternoonOpFemale", afternoonOpFemale);
					updateQuery.setParameter("afternoonOpTg", afternoonOpTg);
					updateQuery.setParameter("afternoonOpTotal", afternoonOpTotal);
					updateQuery.setParameter("ipPatientsDischarged", ipPatientsDischarged);
					updateQuery.setParameter("ipPatientsExpired", ipPatientsExpired);
					updateQuery.setParameter("ipPatientsReferred", ipPatientsReferred);
					updateQuery.setParameter("ipAdmissionsMale", ipAdmissionsMale);
					updateQuery.setParameter("ipAdmissionsFemale", ipAdmissionsFemale);
					updateQuery.setParameter("ipAdmissionsTotal", ipAdmissionsTotal);
					updateQuery.setParameter("ipAdmissionsTg", ipAdmissionsTg);
					updateQuery.setParameter("emrPatientReferred", emrPatientReferred);
					updateQuery.setParameter("emrRtaTrauma", emrRtaTrauma);
					updateQuery.setParameter("emrPatinetAttended", emrPatinetAttended);
					updateQuery.setParameter("emrPatientAdmited", emrPatientAdmited);
					updateQuery.setParameter("hospTrackId", hospTrackId);
					updateQuery.executeUpdate();
					resultFlag = true;
				}
			} else {
				session.save(opIpDetails);
				resultFlag = true;
			}

		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Exception : " + e.getMessage());
		}
		return resultFlag;

	}

	// Saving or updating the speciality clinics of taluk hospital.

	public Boolean saveAndUpdateSpecialityClinicData(List<SpecialityClinicData> specialityClinicDataList)
			throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		for (SpecialityClinicData specialityClinicData : specialityClinicDataList) {
			HospitalMonthlyTracker hospitalMonthlyTracker = null;
			if (specialityClinicData.getHospitalMonthlyTracker().getId() != null) {
				hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class,
						specialityClinicData.getHospitalMonthlyTracker().getId());
			}
			if (hospitalMonthlyTracker == null) {
				hospitalMonthlyTracker = specialityClinicData.getHospitalMonthlyTracker();
				hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
				specialityClinicData.setHospitalMonthlyTracker(hospitalMonthlyTracker);

			} else {
				hospitalMonthlyTracker.setLastModified(new Date());
				specialityClinicData.setHospitalMonthlyTracker(hospitalMonthlyTracker);
			}
			session.saveOrUpdate(specialityClinicData);
		}
		return true;
	}

	public Boolean saveAndUpdateLabDialysis(LabDialysis labDialysis) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		HospitalMonthlyTracker hospitalMonthlyTracker = null;
		if (labDialysis.getHospitalMonthlyTracker().getId() != null) {
			hospitalMonthlyTracker = (HospitalMonthlyTracker) session.get(HospitalMonthlyTracker.class,
					labDialysis.getHospitalMonthlyTracker().getId());
		}
		if (hospitalMonthlyTracker == null) {
			hospitalMonthlyTracker = labDialysis.getHospitalMonthlyTracker();
			hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
			labDialysis.setHospitalMonthlyTracker(hospitalMonthlyTracker);

		} else {
			hospitalMonthlyTracker.setLastModified(new Date());
			labDialysis.setHospitalMonthlyTracker(hospitalMonthlyTracker);
		}
		session.saveOrUpdate(labDialysis);
		return true;
	}

	/*
	 * private Date getFirstDateOfMonth() throws ParseException { Calendar c =
	 * Calendar.getInstance(); // this takes current date
	 * c.set(Calendar.DAY_OF_MONTH, 1); SimpleDateFormat dmyFormat = new
	 * SimpleDateFormat("yyyy-MM-dd"); String dmy = dmyFormat.format(c.getTime());
	 * Date reportDate=dmyFormat.parse(dmy); return reportDate; }
	 */

	/*
	 * public Boolean saveAndUpdateSpecialityClinicData(SpecialityClinicData
	 * specialityClinicData) throws Exception {
	 * 
	 * Session session = this.sessionFactory.getCurrentSession(); Boolean resultFlag
	 * = false; HospitalMonthlyTracker hospitalMonthlyTracker =
	 * specialityClinicData.getHospitalMonthlyTracker(); Long hospitalId =
	 * hospitalMonthlyTracker.getHospital().getId(); Long hospmonthlytrackId =
	 * specialityClinicData.getHospitalMonthlyTracker().getId(); if
	 * (hospmonthlytrackId == null) { hospmonthlytrackId =
	 * saveHospitalMonthlyTracker(hospitalId); }
	 * 
	 * try { Long maleCount = specialityClinicData.getMaleCount(); Long femalCount =
	 * specialityClinicData.getFemalCount(); Long tgcount =
	 * specialityClinicData.getTgCount(); Long total =
	 * specialityClinicData.getTotal(); Long specialityClinicId =
	 * specialityClinicData.getSpecialityClinic().getId();
	 * 
	 * String sql =
	 * "select * from specialityclinic_data sc where sc.specialityclinic_id=:specialityClinicId and sc.hosp_track_id =:hospmonthlytrackId"
	 * ; Query query = session.createSQLQuery(sql);
	 * query.setParameter("specialityClinicId", specialityClinicId);
	 * query.setParameter("hospmonthlytrackId", hospmonthlytrackId);
	 * List<SpecialityClinicData> specialityClinicDetails = query.list();
	 * 
	 * if (specialityClinicDetails != null && !specialityClinicDetails.isEmpty()) {
	 * if (specialityClinicDetails.get(0) != null) { String updateSql =
	 * "update specialityclinic_data set maleCount=:maleCount, femaleCount=:femalCount,tgcount =:tgcount,total=:total where specialityclinic_id=:specialityClinicId and hosp_track_id =:hospmonthlytrackId"
	 * ; Query updateQuery = session.createSQLQuery(updateSql);
	 * updateQuery.setParameter("specialityClinicId", specialityClinicId);
	 * updateQuery.setParameter("maleCount", maleCount);
	 * updateQuery.setParameter("femalCount", femalCount);
	 * updateQuery.setParameter("tgcount", tgcount);
	 * updateQuery.setParameter("total", total);
	 * updateQuery.setParameter("hospmonthlytrackId", hospmonthlytrackId);
	 * updateQuery.executeUpdate(); resultFlag = true; } } else {
	 * session.save(specialityClinicData); resultFlag = true; } } catch
	 * (HibernateException e) { throw new
	 * HibernateException("Hibernate Exception : " + e.getMessage()); } catch
	 * (Exception e) { throw new Exception("Exception : " + e.getMessage());
	 * 
	 * >>>>>>> master } return resultFlag;
	 * 
	 * <<<<<<< HEAD }
	 */
	/*
	 * public Boolean saveOrUpdateSpecialityClinic(SpecialityClinicData
	 * specialityClinicData) throws Exception {
	 * 
	 * Session session = this.sessionFactory.getCurrentSession();
	 * HospitalMonthlyTracker hospitalMonthlyTracker = null; if
	 * (specialityClinicData.getHospitalMonthlyTracker().getId() != null) {
	 * hospitalMonthlyTracker = (HospitalMonthlyTracker)
	 * session.get(HospitalMonthlyTracker.class,
	 * specialityClinicData.getHospitalMonthlyTracker().getId()); } if
	 * (hospitalMonthlyTracker == null) { hospitalMonthlyTracker =
	 * specialityClinicData.getHospitalMonthlyTracker();
	 * hospitalMonthlyTracker.setReport_date(getFirstDateOfMonth());
	 * specialityClinicData.setHospitalMonthlyTracker(hospitalMonthlyTracker);
	 * ======= }
	 */

	private Date getFirstDateOfMonth() throws ParseException {
		Calendar c = Calendar.getInstance(); // this takes current date
		c.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dmy = dmyFormat.format(c.getTime());
		Date reportDate = dmyFormat.parse(dmy);
		return reportDate;
	}

	public Long saveHospitalMonthlyTracker(Long hospitalId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
		Long trackerId = 0L;
		// Transaction transaction = null;
		try {
			// transaction = session.beginTransaction();

			HospitalMonthlyTracker trackerForCurrentMonth = new HospitalMonthlyTracker();
			HospitalMaster hospitalMaster = new HospitalMaster();
			hospitalMaster.setId(hospitalId);
			trackerForCurrentMonth.setHospital(hospitalMaster);
			trackerForCurrentMonth.setLastModified(Calendar.getInstance().getTime());
			trackerForCurrentMonth.setReport_date(getReportDate());// setReportMonth(new
																	// Long(Calendar.getInstance().get(Calendar.MONTH)));
			trackerId = (Long) session.save(trackerForCurrentMonth);

		} catch (HibernateException e) {
			// if (transaction != null) {
			// transaction.rollback();
			// }
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			// if (transaction != null) {
			// transaction.rollback();
			// }
			throw new Exception("Exception : " + e.getMessage());

		}

		return trackerId;
	}

	public Date getReportDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.AM_PM);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}

	// **************************< Fetch monthly record of TH starts
	// >***************************************

	public OpIpDetails fetchOpIpDetails(Long hospitalId) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		OpIpDetails opIpDetails = new OpIpDetails();

		try {
			String sql = "select op.forenoon_op_male, op.afternoon_op_male, op.ip_patients_discharged, op.ip_patients_expired, op.ip_patients_referred, op.emr_patinet_attended, op.emr_patient_admited, op.emr_patient_referred, op.emr_rta_trauma, op.forenoon_op_female, op.forenoon_op_tg, op.forenoon_op_total, op.afternoon_op_female, op.afternoon_op_tg, op.afternoon_op_total, op.ip_admissions_male, op.ip_admissions_female, op.ip_admissions_tg, op.ip_admissions_total,op.hospmonthlytrack_id from op_ip_th_gh_dh op\r\n"
					+ "inner join hospital_monthlytracker h on h.id = op.hospmonthlytrack_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";

			Query query = session.createSQLQuery(sql);

			query.setParameter("hospitalId", hospitalId);

			query.setParameter("date", getReportDate());

			List<OpIpDetails> thOpIpList = query.list();

			if (thOpIpList != null && !thOpIpList.isEmpty()) {
				Iterator iterator = thOpIpList.iterator();

				while (iterator.hasNext()) {
					Object[] row = (Object[]) iterator.next();

					opIpDetails.setForenoonOpMale(castObjectToLong(row[0]));
					opIpDetails.setAfternoonOpMale(castObjectToLong(row[1]));
					opIpDetails.setIpPatientsDischarged(castObjectToLong(row[2]));
					opIpDetails.setIpPatientsExpired(castObjectToLong(row[3]));
					opIpDetails.setIpPatientsReferred(castObjectToLong(row[4]));
					opIpDetails.setEmrPatinetAttended(castObjectToLong(row[5]));
					opIpDetails.setEmrPatientAdmited(castObjectToLong(row[6]));
					opIpDetails.setEmrPatientReferred(castObjectToLong(row[7]));
					opIpDetails.setEmrRtaTrauma(castObjectToLong(row[8]));
					opIpDetails.setForenoonOpFemale(castObjectToLong(row[9]));
					opIpDetails.setForenoonOpTg(castObjectToLong(row[10]));
					opIpDetails.setForenoonOpTotal(castObjectToLong(row[11]));
					opIpDetails.setAfternoonOpFemale(castObjectToLong(row[12]));
					opIpDetails.setAfternoonOpTg(castObjectToLong(row[13]));
					opIpDetails.setAfternoonOpTotal(castObjectToLong(row[14]));
					opIpDetails.setIpAdmissionsMale(castObjectToLong(row[15]));
					opIpDetails.setIpAdmissionsFemale(castObjectToLong(row[16]));
					opIpDetails.setIpAdmissionsTg(castObjectToLong(row[17]));
					opIpDetails.setIpAdmissionsTotal(castObjectToLong(row[18]));
					opIpDetails.setHospitalMonthlyTracker(castObjectToHmt(row[19]));
				}
			}
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());
		}

		return opIpDetails;
	}

	public List<DepartmentWiseOpIp> fetchDeptOpIpDetails(Long hospitalId) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		DepartmentWiseOpIp departmentWiseOpIp = new DepartmentWiseOpIp();
		List<DepartmentWiseOpIp> thDeptOpIpList = null;
		try {
			String sql = "select d.category_id, d.total_op_count, d.total_ip_count,d.hospital_track_id from department_wise_op_ip d inner join hospital_monthlytracker h on h.id = d.hospital_track_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			thDeptOpIpList = query.list();

			/*
			 * if (thDeptOpIpList != null && !thDeptOpIpList.isEmpty()) { Iterator iterator
			 * = thDeptOpIpList.iterator();
			 * 
			 * while (iterator.hasNext()) { Object[] row = (Object[]) iterator.next();
			 * 
			 * departmentWiseOpIp.setCategoryMasterId(castObjectToCategryMastr(row[0]));
			 * departmentWiseOpIp.setTotalIpCount(castObjectToLong(row[1]));
			 * departmentWiseOpIp.setTotalOpCount(castObjectToLong(row[2])); } }
			 */
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());
		}

		return thDeptOpIpList;
	}

	public List<SurgeryDetailsThDhGh> fetchSurgeryDetailsThDhGh(Long hospitalId) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		// SurgeryDetailsThDhGh surgeryDetailsThDhGh = new SurgeryDetailsThDhGh();
		List<SurgeryDetailsThDhGh> surgeryDetailsThDhGh = new ArrayList<SurgeryDetailsThDhGh>();

		try {
			String sql = "select s.category_id , s.majorsurgery, s.minorsurgery,s.hosp_monthly_trackid from surgerydetails_thghdh s inner join hospital_monthlytracker h on h.id = s.hosp_monthly_trackid where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId\r\n";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			surgeryDetailsThDhGh = query.list();

			/*
			 * if (thSurgeryDetails != null && !thSurgeryDetails.isEmpty()) { Iterator
			 * iterator = thSurgeryDetails.iterator();
			 * 
			 * while (iterator.hasNext()) { Object[] row = (Object[]) iterator.next();
			 * 
			 * surgeryDetailsThDhGh.setCategoryMaster(castObjectToCategryMastr(row[0]));
			 * surgeryDetailsThDhGh.setMajorSurgery(castObjectToLong(row[1]));
			 * surgeryDetailsThDhGh.setMinorSurgery(castObjectToLong(row[2])); } }
			 */
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());
		}

		return surgeryDetailsThDhGh;
	}

	public List<SpecialityClinicData> fetchSpecialityClinicData(Long hospitalId) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		// SpecialityClinicData specialityClinicData = new SpecialityClinicData();
		List<SpecialityClinicData> specialityClinicData = new ArrayList<SpecialityClinicData>();

		try {
			String sql = "select sc.specialityclinic_id, sc.malecount, sc.femalecount, sc.total, sc.tgcount,sc.hosp_track_id from specialityclinic_data sc inner join hospital_monthlytracker h on h.id = sc.hosp_track_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			specialityClinicData = query.list();

			/*
			 * if (thSpecialityClinicData != null && !thSpecialityClinicData.isEmpty()) {
			 * Iterator iterator = thSpecialityClinicData.iterator();
			 * 
			 * while (iterator.hasNext()) { Object[] row = (Object[]) iterator.next();
			 * 
			 * specialityClinicData.setSpecialityClinic(castObjectToSpecialityClinic(row[0])
			 * ); specialityClinicData.setMaleCount(castObjectToLong(row[1]));
			 * specialityClinicData.setFemalCount(castObjectToLong(row[2]));
			 * specialityClinicData.setTotal(castObjectToLong(row[3]));
			 * specialityClinicData.setTgCount(castObjectToLong(row[4])); } }
			 */
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());
		}

		return specialityClinicData;
	}

	public LabDialysis fetchLabDialysis(Long hospitalId) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		LabDialysis labDialysis = new LabDialysis();

		try {
			String sql = "select lb.lab_patients_tested, lb.lab_total_tests, lb.lab_usg_func_machines, lb.lab_usg_count, lb.lab_ecg_count, lb.dia_shift_functioning, lb.dia_patient_count, lb.dia_total_count, lb.dia_patient_waiting, lb.xray_units_functioning, lb.total_xray_taken, lb.ph_arv_availability, lb.ph_asv_availability, lb.blood_bank, lb.blood_storage_unit, lb.ph_shortage_details, lb.drug_availability, lb.functional_ambulance, lb.hospmonthlytrack_id from lab_dialysis_xray_pharmacy lb inner join hospital_monthlytracker h on h.id = lb.hospmonthlytrack_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			List<LabDialysis> thLabDialysis = query.list();

			if (thLabDialysis != null && !thLabDialysis.isEmpty()) {
				Iterator iterator = thLabDialysis.iterator();

				while (iterator.hasNext()) {
					Object[] row = (Object[]) iterator.next();

					labDialysis.setLabPatientsTested(castObjectToLong(row[0]));
					labDialysis.setLabTotalTests(castObjectToLong(row[1]));
					labDialysis.setLabUsgFuncMachines(castObjectToBoolean(row[2]));
					labDialysis.setLabUsgCount(castObjectToLong(row[3]));
					labDialysis.setLabEcgCount(castObjectToLong(row[4]));
					labDialysis.setDiaShiftFunctioning(castObjectToLong(row[5]));
					labDialysis.setDiaPatientCount(castObjectToLong(row[6]));
					labDialysis.setDiaTotalCount(castObjectToLong(row[7]));
					labDialysis.setDiaPatientWaiting(castObjectToLong(row[8]));
					labDialysis.setXrayUnitsFunctioning(castObjectToLong(row[9]));
					labDialysis.setTotalXrayTaken(castObjectToLong(row[10]));
					labDialysis.setPhArvAvailability(castObjectToBoolean(row[11]));
					labDialysis.setPhAsvAvailability(castObjectToBoolean(row[12]));
					labDialysis.setBloodBank(castObjectToBoolean(row[13]));
					labDialysis.setBloodStorageUnit(castObjectToBoolean(row[14]));
					labDialysis.setPhShortageDetails(castObjectToString(row[15]));
					labDialysis.setDrugAvailability(castObjectToString(row[16]));
					labDialysis.setFunctionalAmbulance(castObjectToBoolean(row[17]));
					labDialysis.setHospitalMonthlyTracker(castObjectToHmt(row[18]));
				}
			}
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());
		}

		return labDialysis;
	}

	public FundExpenditure fetchFundExpenditure(Long hospitalId) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		FundExpenditure fundExpenditure = new FundExpenditure();

		try {
			String sql = "select f.fund_hmc, f.fund_nhm, f.fund_rsby, f.fund_dept_plan, f.fund_lsgi, f.exp_hmc, f.exp_nhm, f.exp_rsby, f.exp_dept_plan, f.exp_lsgi, f.ongoing_construction, f.progress_asper_plan, f.delay_reason,f.hospital_tracker_id from fund_expenditure f \r\n"
					+ "inner join hospital_monthlytracker h on h.id = f.hospital_tracker_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			List<FundExpenditure> thFundExpenditure = query.list();

			if (thFundExpenditure != null && !thFundExpenditure.isEmpty()) {
				Iterator iterator = thFundExpenditure.iterator();

				while (iterator.hasNext()) {
					Object[] row = (Object[]) iterator.next();

					fundExpenditure.setFundHmc(castObjectToLong(row[0]));
					fundExpenditure.setFundNhm(castObjectToLong(row[1]));
					fundExpenditure.setFundRsby(castObjectToLong(row[2]));
					fundExpenditure.setFundDeptPlan(castObjectToLong(row[3]));
					fundExpenditure.setFundLsgi(castObjectToLong(row[4]));
					fundExpenditure.setExpHmc(castObjectToLong(row[5]));
					fundExpenditure.setExpNhm(castObjectToLong(row[6]));
					fundExpenditure.setExpRsby(castObjectToLong(row[7]));
					fundExpenditure.setExpDeptPlan(castObjectToLong(row[8]));
					fundExpenditure.setExpLsgi(castObjectToLong(row[9]));
					fundExpenditure.setOngoingConstruction(castObjectToBoolean(row[10]));
					fundExpenditure.setProgressAsperPlan(castObjectToBoolean(row[11]));
					fundExpenditure.setDelayReason(castObjectToString(row[12]));
					fundExpenditure.setHospitalMonthlyTracker(castObjectToHmt(row[13]));

				}
			}
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());
		}

		return fundExpenditure;
	}

	public ServiceAreaOthers fetchServiceAreaOthers(Long hospitalId) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		ServiceAreaOthers serviceAreaOthers = new ServiceAreaOthers();

		try {
			String sql = "select so.dental_procedures, so.og_normal_delivery_count, so.og_lscs_count, so.og_maternal_death_count, so.last_hmc_meeting, so.og_referred_cases_count, so.major_surgery_count, so.sc_high_dependency_unit, so.sc_patients_treated_count, so.other_relevant_info,so.hosp_tracker_id from servicearea_others so inner join hospital_monthlytracker h on h.id = so.hosp_tracker_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			List<ServiceAreaOthers> thServiceAreaOthers = query.list();

			if (thServiceAreaOthers != null && !thServiceAreaOthers.isEmpty()) {
				Iterator iterator = thServiceAreaOthers.iterator();

				while (iterator.hasNext()) {
					Object[] row = (Object[]) iterator.next();

					serviceAreaOthers.setDentalProcedures(castObjectToLong(row[0]));
					serviceAreaOthers.setOgNormalDeliveryCount(castObjectToLong(row[1]));
					serviceAreaOthers.setOgLscsCount(castObjectToLong(row[2]));
					serviceAreaOthers.setOgMaternalDeathCount(castObjectToLong(row[3]));
					serviceAreaOthers.setLasthmcMeeting((Date) (row[4]));
					serviceAreaOthers.setOgReferredCasesCount(castObjectToLong(row[5]));
					serviceAreaOthers.setMajorSurgeryCount(castObjectToLong(row[6]));
					serviceAreaOthers.setScHighDependencyUnit(castObjectToBoolean(row[7]));
					serviceAreaOthers.setScPatientsTreatedCount(castObjectToLong(row[8]));
					serviceAreaOthers.setOtherRelevantInfo(castObjectToString(row[9]));
					serviceAreaOthers.setHospitalMonthlyTracker(castObjectToHmt(row[10]));
				}
			}
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());
		}

		return serviceAreaOthers;
	}

	public List<IdlingMajorEquipment> fetchIdlingMajorEquipment(Long hospitalId) throws Exception {

		Session session = this.sessionFactory.getCurrentSession();
		// IdlingMajorEquipment idlingMajorEquipment = new IdlingMajorEquipment();
		List<IdlingMajorEquipment> idlingMajorEquipment = new ArrayList<IdlingMajorEquipment>();
		try {

			String sql = "select maj.equipment_name, maj.acquisition_date, maj.hosp_track_id from idling_major_equipment maj inner join hospital_monthlytracker h on h.id = maj.hosp_track_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			idlingMajorEquipment = query.list();

			/*
			 * if (thIdlingMajorEquipment != null && !thIdlingMajorEquipment.isEmpty()) {
			 * Iterator iterator = thIdlingMajorEquipment.iterator();
			 * 
			 * while (iterator.hasNext()) { Object[] row = (Object[]) iterator.next();
			 * 
			 * idlingMajorEquipment.setEquipmentName(castObjectToString(row[0]));
			 * idlingMajorEquipment.setAcquisitionDate((Date)(row[1])); } }
			 */
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());
		}

		return idlingMajorEquipment;
	}

	public MonthlyDataTh fetchMonthlyDataTh(Long hospitalId) throws Exception {
		MonthlyDataTh monthlyDataTh = new MonthlyDataTh();

		try {
			OpIpDetails opIpDetails = fetchOpIpDetails(hospitalId);
			monthlyDataTh.setTalukOpIpDetails(opIpDetails);

			List<DepartmentWiseOpIp> departmentWiseOpIp = fetchDeptOpIpDetails(hospitalId);
			monthlyDataTh.setDepartmentWiseOpIp(departmentWiseOpIp);

			List<SurgeryDetailsThDhGh> surgeryDetailsThDhGh = fetchSurgeryDetailsThDhGh(hospitalId);
			monthlyDataTh.setSurgeryDetailsThDhGh(surgeryDetailsThDhGh);

			List<SpecialityClinicData> specialityClinicData = fetchSpecialityClinicData(hospitalId);
			monthlyDataTh.setSpecialityClinicData(specialityClinicData);

			LabDialysis talukFacilityDetails = fetchLabDialysis(hospitalId);
			monthlyDataTh.setTalukFacilityDetails(talukFacilityDetails);

			FundExpenditure talukFundDetails = fetchFundExpenditure(hospitalId);
			monthlyDataTh.setTalukFundDetails(talukFundDetails);

			ServiceAreaOthers talukOtherServiceDetails = fetchServiceAreaOthers(hospitalId);
			monthlyDataTh.setTalukOtherServiceDetails(talukOtherServiceDetails);

			List<IdlingMajorEquipment> idlingMajorEquipments = fetchIdlingMajorEquipment(hospitalId);
			if (idlingMajorEquipments.isEmpty()) {
				monthlyDataTh.setIdlingEquipment(false);
			} else {
				monthlyDataTh.setIdlingMajorEquipments(idlingMajorEquipments);
				monthlyDataTh.setIdlingEquipment(true);
			}

			HospitalMonthlyTracker hospitalMonthlyTracker = new HospitalMonthlyTracker();
			hospitalMonthlyTracker.setId(opIpDetails.getHospitalMonthlyTracker().getId());
			monthlyDataTh.setHospitalMonthlyTracker(hospitalMonthlyTracker);

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {

			throw new Exception("Exception : " + e.getMessage());
		}

		return monthlyDataTh;
	}

	public Map<String,String> getDashboardSummaryForThaluk(Long nin) throws Exception {
		
		Session session = this.sessionFactory.getCurrentSession();
		Object[] dashBoardSummary = null;

		Long hosptialId = (Long) session.createQuery("select id from HospitalMaster hm where hm.nin =:nin").setParameter("nin", nin).uniqueResult();
		HospitalMaster hospitalMaster = (HospitalMaster) session.get(HospitalMaster.class, hosptialId);
		if(hospitalMaster!=null) {
			
			Query query = session.createQuery("from HospitalMonthlyTracker hmt where hmt.hospital.id=:hospital_id and hmt.report_date=:report_date and hmt.finalSubmitDone=true" );
			HospitalMonthlyTracker hospitalMonthlyTracker = (HospitalMonthlyTracker) query.setParameter("hospital_id", hospitalMaster.getId())
																						   .setParameter("report_date", getReportDate()).uniqueResult();
			if(hospitalMonthlyTracker != null) {
				dashBoardSummary = (Object[]) session.createSQLQuery("select opip.forenoon_op_total+opip.afternoon_op_total as optotal, opip.ip_admissions_total,"
						+ "sao.og_lscs_count+sao.og_normal_delivery_count as deliverycount, lab.dia_patient_count, lab.lab_patients_tested, "
						+ "lab.drug_availability, sao.major_surgery_count, opip.emr_patinet_attended from op_ip_th_gh_dh opip inner join "
						+ "servicearea_others sao on "
						+ "opip.hospmonthlytrack_id = sao.hosp_tracker_id "
						+ "inner join lab_dialysis_xray_pharmacy lab "
						+ "on lab.hospmonthlytrack_id=sao.hosp_tracker_id "
						+ "where "
						+ "sao.hosp_tracker_id=:hospmonthlytrack_id "
						+ "and opip.hospmonthlytrack_id=:hospmonthlytrack_id "
						+ "and lab.hospmonthlytrack_id=:hospmonthlytrack_id")
						.setParameter("hospmonthlytrack_id", hospitalMonthlyTracker.getId())
						.uniqueResult();
			}
			
			
			//HospitalMonthlyTracker hospitalMonthlyTracker = session.get(HospitalMonthlyTracker.class, hospitalMaster.)
		}
		return null;
	}
	// **************************< Fetch monthly record of TH ends
	// >***************************************
	private Long castObjectToLong(Object object) {

		return new Long((Integer) ((object != null) ? object : 0));

	}

	private Boolean castObjectToBoolean(Object object) {

		return new Boolean((Boolean) ((object != null) ? object : false));

	}

	private String castObjectToString(Object object) {

		return new String((String) ((object != null) ? object : ""));

	}

/*	private CategoryMaster castObjectToCategryMastr(Object object) {
		CategoryMaster categoryMaster = new CategoryMaster();
		categoryMaster.setId(new Long((Integer) ((object != null) ? object : 0)));
		return categoryMaster;
	}

	private SpecialityClinic castObjectToSpecialityClinic(Object object) {
		SpecialityClinic specialityClinic = new SpecialityClinic();
		specialityClinic.setId(new Long((Integer) ((object != null) ? object : 0)));
		return specialityClinic;
	}*/

	private HospitalMonthlyTracker castObjectToHmt(Object object) {
		HospitalMonthlyTracker hospMonTrack = new HospitalMonthlyTracker();
		hospMonTrack.setId(new Long((Integer) ((object != null) ? object : 0)));
		return hospMonTrack;
	}
}
