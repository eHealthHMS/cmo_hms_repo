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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	final static Logger logger = Logger.getLogger(ThDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public Boolean saveOrUpdateDeptWiseIpOpDetails(List<DepartmentWiseOpIp> departmentWiseOpIpList,
			HospitalMonthlyTracker hospitalMonthlyTracker) throws Exception {
		logger.info("Entered ThDaoImpl:saveOrUpdateDeptWiseIpOpDetails");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			if ((hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() != null
					&& hospitalMonthlyTracker.getId() == -1)
					|| (hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() == null)) {
				Long id = (Long) session.save(hospitalMonthlyTracker);
				hospitalMonthlyTracker.setId(id);
			}
			for (DepartmentWiseOpIp departmentWiseOpIp : departmentWiseOpIpList) {
				if (departmentWiseOpIp.getId() != null && departmentWiseOpIp.getId() == -1) {
					departmentWiseOpIp.setId(null);
				}
				hospitalMonthlyTracker.setLastModified(new Date());
				departmentWiseOpIp.setHospitalMonthlyTrackerId(hospitalMonthlyTracker);
				session.saveOrUpdate(departmentWiseOpIp);
			}
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl:saveOrUpdateDeptWiseIpOpDetails: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());

		}
		logger.info("Exited ThDaoImpl:saveOrUpdateDeptWiseIpOpDetails");
		return true;
	}

	public Boolean saveOrUpdateSurgeryDetails(List<SurgeryDetailsThDhGh> surgeryDetailsList,
			HospitalMonthlyTracker hospitalMonthlyTracker) throws Exception {
		logger.info("Entered ThDaoImpl: saveOrUpdateSurgeryDetails");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			if ((hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() != null
					&& hospitalMonthlyTracker.getId() == -1)
					|| (hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() == null)) {
				Long id = (Long) session.save(hospitalMonthlyTracker);
				hospitalMonthlyTracker.setId(id);
			}
			for (SurgeryDetailsThDhGh surgeryDetailsThDhGh : surgeryDetailsList) {
				if (surgeryDetailsThDhGh.getId() != null && surgeryDetailsThDhGh.getId() == -1) {
					surgeryDetailsThDhGh.setId(null);
				}
				hospitalMonthlyTracker.setLastModified(new Date());
				surgeryDetailsThDhGh.setHospitalMonthlyTracker(hospitalMonthlyTracker);
				session.saveOrUpdate(surgeryDetailsThDhGh);
			}
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl:saveOrUpdateSurgeryDetails: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: saveOrUpdateSurgeryDetails");
		return true;
	}

	public Boolean saveOrUpdateIdilingEquipment(List<IdlingMajorEquipment> IdlingMajorEquipmentList,
			HospitalMonthlyTracker hospitalMonthlyTracker) throws Exception {
		logger.info("Entered ThDaoImpl: saveOrUpdateIdilingEquipment");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			if ((hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() != null
					&& hospitalMonthlyTracker.getId() == -1)
					|| (hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() == null)) {
				Long id = (Long) session.save(hospitalMonthlyTracker);
				hospitalMonthlyTracker.setId(id);
			}
			for (IdlingMajorEquipment idlingMajorEquipment : IdlingMajorEquipmentList) {
				if (idlingMajorEquipment.getId() != null && idlingMajorEquipment.getId() == -1) {
					idlingMajorEquipment.setId(null);
				}
				hospitalMonthlyTracker.setLastModified(new Date());
				idlingMajorEquipment.setHospitalMonthlyTracker(hospitalMonthlyTracker);
				session.saveOrUpdate(idlingMajorEquipment);
			}
			logger.info("Exited ThDaoImpl: saveOrUpdateIdilingEquipment");
			return true;
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: saveOrUpdateIdilingEquipment: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
	}

	public Map<String, String> getThalukBasicData(Long nin) {
		logger.info("Entered ThDaoImpl:getThalukBasicData");
		Session session = this.sessionFactory.getCurrentSession();
		Object[] opIpDetails = null;
		Object[] serviceAreasOthers = null;
		Object[] diaLysisDetails = null;

		HospitalMaster hospitalMaster = (HospitalMaster) session.get(HospitalMaster.class, nin);
		if (hospitalMaster != null) {

			Query query = session.createQuery(
					"from HospitalMonthlyTracker hmt where hmt.hospital.id=:hospital_id and hmt.report_date=:report_date and hmt.finalSubmitDone=true");
			HospitalMonthlyTracker hospitalMonthlyTracker = (HospitalMonthlyTracker) query
					.setParameter("hospital_id", hospitalMaster.getId()).setParameter("report_date", getReportDate())
					.uniqueResult();
			if (hospitalMonthlyTracker != null) {
				opIpDetails = (Object[]) session
						.createQuery(
								"select opipdet.forenoonOpTotal+opipdet.afternoonOpTotal,opipdet.emrPatinetAttended, "
										+ "opipdet.ipAdmissionsTotal from OpIpDetails opipdet "
										+ "where opipdet.hospitalMonthlyTracker.id=:hospmonthlytrack_id")
						.setParameter("hospmonthlytrack_id", hospitalMonthlyTracker.getId()).uniqueResult();
				serviceAreasOthers = (Object[]) session
						.createQuery("select sao.ogLscsCount+sao.ogNormalDeliveryCount " + "from ServiceAreaOthers sao "
								+ "where sao.hospitalMonthlyTracker.id=:hospmonthlytrack_id")
						.setParameter("hospmonthlytrack_id", hospitalMonthlyTracker.getId()).uniqueResult();
				diaLysisDetails = (Object[]) session
						.createQuery("select dia.diaTotalCount " + "from LabDialysis dia "
								+ "where dia.hospitalMonthlyTracker.id=:hospmonthlytrack_id")
						.setParameter("hospmonthlytrack_id", hospitalMonthlyTracker.getId()).uniqueResult();
			}
		}
		Map<String, String> basicData = new HashMap<String, String>();
		basicData.put("totalOPCount", (opIpDetails[0] == null ? "no value" : String.valueOf(opIpDetails[0])));
		basicData.put("emrPatientsAdmitted", (opIpDetails[1] == null ? "no value" : String.valueOf(opIpDetails[1])));
		basicData.put("ipAdmissionsTotal", (opIpDetails[2] == null ? "no value" : String.valueOf(opIpDetails[2])));
		basicData.put("totalDeliveryCount",
				(serviceAreasOthers == null ? "no value" : String.valueOf(serviceAreasOthers[0])));
		basicData.put("hospitalName",
				hospitalMaster.getHospitalName() == null ? "no value" : hospitalMaster.getHospitalName());
		basicData.put("totalDiaCount", (diaLysisDetails == null ? "no value" : String.valueOf(diaLysisDetails[0])));
		logger.info("Exited ThDaoImpl:getThalukBasicData");
		return basicData;
	}

	public Boolean saveOrUpdateServiceAreaOthers(ServiceAreaOthers serviceAreaOthers,
			HospitalMonthlyTracker hospitalMonthlyTracker) throws Exception {
		logger.info("Entered ThDaoImpl:saveOrUpdateServiceAreaOthers");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			if ((hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() != null
					&& hospitalMonthlyTracker.getId() == -1)
					|| (hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() == null)) {
				Long id = (Long) session.save(hospitalMonthlyTracker);
				hospitalMonthlyTracker.setId(id);
			}
			if (serviceAreaOthers.getId() != null && serviceAreaOthers.getId() == -1) {
				serviceAreaOthers.setId(null);
			}
			hospitalMonthlyTracker.setLastModified(new Date());
			serviceAreaOthers.setHospitalMonthlyTracker(hospitalMonthlyTracker);
			session.saveOrUpdate(serviceAreaOthers);
			logger.info("Exited ThDaoImpl: saveOrUpdateServiceAreaOthers");
			return true;
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: saveOrUpdateServiceAreaOthers: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}

	}

	public Boolean saveOrUpdateFundExpenditure(FundExpenditure fundExpenditure,
			HospitalMonthlyTracker hospitalMonthlyTracker) throws Exception {
		logger.info("Entered ThDaoImpl:saveOrUpdateFundExpenditure");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			if ((hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() != null
					&& hospitalMonthlyTracker.getId() == -1)
					|| (hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() == null)) {
				Long id = (Long) session.save(hospitalMonthlyTracker);
				hospitalMonthlyTracker.setId(id);
			}
			if (fundExpenditure.getId() != null && fundExpenditure.getId() == -1) {
				fundExpenditure.setId(null);
			}
			hospitalMonthlyTracker.setLastModified(new Date());
			fundExpenditure.setHospitalMonthlyTracker(hospitalMonthlyTracker);
			session.saveOrUpdate(fundExpenditure);

			logger.info("Exited ThDaoImpl: saveOrUpdateFundExpenditure");
			return true;
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: saveOrUpdateFundExpenditure: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
	}

	// Saving or updating the OP and IP details of taluk hospital.

	public Boolean saveAndUpdateOpIpDetails(OpIpDetails opIpDetails, HospitalMonthlyTracker hospitalMonthlyTracker)
			throws Exception {
		logger.info("Entered ThDaoImpl:saveAndUpdateOpIpDetails");
		Session session = this.sessionFactory.getCurrentSession();

		Boolean resultFlag = false;

		try {
			if ((hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() != null
					&& hospitalMonthlyTracker.getId() == -1)
					|| (hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() == null)) {
				Long id = (Long) session.save(hospitalMonthlyTracker);
				hospitalMonthlyTracker.setId(id);
			}
			if (opIpDetails.getId() != null && opIpDetails.getId() == -1) {
				opIpDetails.setId(null);
			}
			hospitalMonthlyTracker.setLastModified(new Date());
			opIpDetails.setHospitalMonthlyTracker(hospitalMonthlyTracker);

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
			Long hospTrackId = hospitalMonthlyTracker.getId();

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
				return true;
			}

		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: saveAndUpdateOpIpDetails: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: saveAndUpdateOpIpDetails");
		return resultFlag;

	}

	// Saving or updating the speciality clinics of taluk hospital.

	public Boolean saveAndUpdateSpecialityClinicData(List<SpecialityClinicData> specialityClinicDataList,
			HospitalMonthlyTracker hospitalMonthlyTracker) throws Exception {
		logger.info("Entered ThDaoImpl:saveAndUpdateSpecialityClinicData");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			if ((hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() != null
					&& hospitalMonthlyTracker.getId() == -1)
					|| (hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() == null)) {
				Long id = (Long) session.save(hospitalMonthlyTracker);
				hospitalMonthlyTracker.setId(id);
			}
			for (SpecialityClinicData specialityClinicData : specialityClinicDataList) {
				if (specialityClinicData.getId() != null && specialityClinicData.getId() == -1) {
					specialityClinicData.setId(null);
				}
				hospitalMonthlyTracker.setLastModified(new Date());
				specialityClinicData.setHospitalMonthlyTracker(hospitalMonthlyTracker);
				session.saveOrUpdate(specialityClinicData);
			}

			logger.info("Exited ThDaoImpl: saveAndUpdateSpecialityClinicData");
			return true;
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: saveAndUpdateSpecialityClinicData: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
	}

	public Boolean saveAndUpdateLabDialysis(LabDialysis labDialysis, HospitalMonthlyTracker hospitalMonthlyTracker)
			throws Exception {
		logger.info("Entered ThDaoImpl:saveAndUpdateLabDialysis");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			if ((hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() != null
					&& hospitalMonthlyTracker.getId() == -1)
					|| (hospitalMonthlyTracker != null && hospitalMonthlyTracker.getId() == null)) {
				Long id = (Long) session.save(hospitalMonthlyTracker);
				hospitalMonthlyTracker.setId(id);
			}
			if (labDialysis.getId() != null && labDialysis.getId() == -1) {
				labDialysis.setId(null);
			}
			hospitalMonthlyTracker.setLastModified(new Date());
			labDialysis.setHospitalMonthlyTracker(hospitalMonthlyTracker);
			session.saveOrUpdate(labDialysis);
			logger.info("Exited ThDaoImpl: saveAndUpdateLabDialysis");
			return true;
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: saveAndUpdateLabDialysis: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
	}

	public Boolean saveAndUpdateThTransactionalData(MonthlyDataTh monthlyDataTh) throws Exception {
		logger.info("Entered ThDaoImpl: saveAndUpdateThTransactionalData");

		try {

			HospitalMonthlyTracker hospitalMonthlyTracker = new HospitalMonthlyTracker();
			hospitalMonthlyTracker = monthlyDataTh.getHospitalMonthlyTracker();

			List<DepartmentWiseOpIp> departmentWiseOpIpList = new ArrayList<DepartmentWiseOpIp>();
			departmentWiseOpIpList = monthlyDataTh.getDepartmentWiseOpIp();
			saveOrUpdateDeptWiseIpOpDetails(departmentWiseOpIpList, hospitalMonthlyTracker);

			if (monthlyDataTh.isIdlingEquipment()) {
				List<IdlingMajorEquipment> idlingMajorEquipments = new ArrayList<IdlingMajorEquipment>();
				idlingMajorEquipments = monthlyDataTh.getIdlingMajorEquipments();
				saveOrUpdateIdilingEquipment(idlingMajorEquipments, hospitalMonthlyTracker);
			}

			List<SpecialityClinicData> specialityClinicDatas = new ArrayList<SpecialityClinicData>();
			specialityClinicDatas = monthlyDataTh.getSpecialityClinicData();
			saveAndUpdateSpecialityClinicData(specialityClinicDatas, hospitalMonthlyTracker);

			List<SurgeryDetailsThDhGh> surgeryDetailsThDhGhs = new ArrayList<SurgeryDetailsThDhGh>();
			surgeryDetailsThDhGhs = monthlyDataTh.getSurgeryDetailsThDhGh();
			saveOrUpdateSurgeryDetails(surgeryDetailsThDhGhs, hospitalMonthlyTracker);

			LabDialysis talukFacilityDetails = new LabDialysis();
			talukFacilityDetails = monthlyDataTh.getTalukFacilityDetails();
			saveAndUpdateLabDialysis(talukFacilityDetails, hospitalMonthlyTracker);

			FundExpenditure talukFundDetails = new FundExpenditure();
			talukFundDetails = monthlyDataTh.getTalukFundDetails();
			saveOrUpdateFundExpenditure(talukFundDetails, hospitalMonthlyTracker);

			OpIpDetails talukOpIpDetails = new OpIpDetails();
			talukOpIpDetails = monthlyDataTh.getTalukOpIpDetails();
			saveAndUpdateOpIpDetails(talukOpIpDetails, hospitalMonthlyTracker);

			ServiceAreaOthers talukOtherServiceDetails = new ServiceAreaOthers();
			talukOtherServiceDetails = monthlyDataTh.getTalukOtherServiceDetails();
			saveOrUpdateServiceAreaOthers(talukOtherServiceDetails, hospitalMonthlyTracker);

		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: saveAndUpdateThTransactionalData: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: saveAndUpdateThTransactionalData");
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
		logger.info("Entered ThDaoImpl:saveHospitalMonthlyTracker");
		Session session = this.sessionFactory.getCurrentSession();// HibernatePersistence.getSessionFactory().openSession();
		Long trackerId = 0L;
		try {
			HospitalMonthlyTracker trackerForCurrentMonth = new HospitalMonthlyTracker();
			HospitalMaster hospitalMaster = new HospitalMaster();
			hospitalMaster.setId(hospitalId);
			trackerForCurrentMonth.setHospital(hospitalMaster);
			trackerForCurrentMonth.setLastModified(Calendar.getInstance().getTime());
			trackerForCurrentMonth.setReport_date(getReportDate());// setReportMonth(new
																	// Long(Calendar.getInstance().get(Calendar.MONTH)));
			trackerId = (Long) session.save(trackerForCurrentMonth);

		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: saveHospitalMonthlyTracker: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: saveHospitalMonthlyTracker");
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
		logger.info("Entered ThDaoImpl:fetchOpIpDetails");
		Session session = this.sessionFactory.getCurrentSession();
		OpIpDetails opIpDetails = new OpIpDetails();

		try {
			String sql = "select op.forenoon_op_male, op.afternoon_op_male, op.ip_patients_discharged, op.ip_patients_expired, op.ip_patients_referred, op.emr_patinet_attended, op.emr_patient_admited, op.emr_patient_referred, op.emr_rta_trauma, op.forenoon_op_female, op.forenoon_op_tg, op.forenoon_op_total, op.afternoon_op_female, op.afternoon_op_tg, op.afternoon_op_total, op.ip_admissions_male, op.ip_admissions_female, op.ip_admissions_tg, op.ip_admissions_total,op.hospmonthlytrack_id,op.id from op_ip_th_gh_dh op\r\n"
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
					opIpDetails.setId(castObjectToLong(row[20]));
				}
			}
		} catch (HibernateException e) {
			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: fetchOpIpDetails: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: fetchOpIpDetails");
		return opIpDetails;
	}

	public List<DepartmentWiseOpIp> fetchDeptOpIpDetails(Long hospitalId) throws Exception {
		logger.info("Entered ThDaoImpl:fetchDeptOpIpDetails");
		Session session = this.sessionFactory.getCurrentSession();
		DepartmentWiseOpIp departmentWiseOpIp = new DepartmentWiseOpIp();
		List<DepartmentWiseOpIp> deptOpIpListUpdated = new ArrayList<DepartmentWiseOpIp>();
		try {
			String sql = "select d.category_id, d.total_op_count, d.total_ip_count,d.hospital_track_id,d.id from department_wise_op_ip d inner join hospital_monthlytracker h on h.id = d.hospital_track_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			List<DepartmentWiseOpIp> thDeptOpIpList = query.list();
			if (thDeptOpIpList != null && !thDeptOpIpList.isEmpty()) {
				Iterator iterator = thDeptOpIpList.iterator();
				while (iterator.hasNext()) {
					departmentWiseOpIp = new DepartmentWiseOpIp();
					Object[] row = (Object[]) iterator.next();
					departmentWiseOpIp.setCategoryMasterId(castObjectToCategryMastr(row[0]));
					departmentWiseOpIp.setTotalIpCount(castObjectToLong(row[1]));
					departmentWiseOpIp.setTotalOpCount(castObjectToLong(row[2]));
					departmentWiseOpIp.setHospitalMonthlyTrackerId(castObjectToHmt(row[3]));
					departmentWiseOpIp.setId(castObjectToLong(row[4]));
					deptOpIpListUpdated.add(departmentWiseOpIp);
				}
			} else {
				DepartmentWiseOpIp departmentWiseOpIp1 = new DepartmentWiseOpIp();
				departmentWiseOpIp1.setCategoryMasterId(castObjectToCategryMastr(4));
				deptOpIpListUpdated.add(departmentWiseOpIp1);

				DepartmentWiseOpIp departmentWiseOpIp2 = new DepartmentWiseOpIp();
				departmentWiseOpIp2.setCategoryMasterId(castObjectToCategryMastr(5));
				deptOpIpListUpdated.add(departmentWiseOpIp2);

				DepartmentWiseOpIp departmentWiseOpIp3 = new DepartmentWiseOpIp();
				departmentWiseOpIp3.setCategoryMasterId(castObjectToCategryMastr(8));
				deptOpIpListUpdated.add(departmentWiseOpIp3);

				DepartmentWiseOpIp departmentWiseOpIp4 = new DepartmentWiseOpIp();
				departmentWiseOpIp4.setCategoryMasterId(castObjectToCategryMastr(9));
				deptOpIpListUpdated.add(departmentWiseOpIp4);

				DepartmentWiseOpIp departmentWiseOpIp5 = new DepartmentWiseOpIp();
				departmentWiseOpIp5.setCategoryMasterId(castObjectToCategryMastr(10));
				deptOpIpListUpdated.add(departmentWiseOpIp5);

				DepartmentWiseOpIp departmentWiseOpIp6 = new DepartmentWiseOpIp();
				departmentWiseOpIp6.setCategoryMasterId(castObjectToCategryMastr(6));
				deptOpIpListUpdated.add(departmentWiseOpIp6);

				DepartmentWiseOpIp departmentWiseOpIp7 = new DepartmentWiseOpIp();
				departmentWiseOpIp7.setCategoryMasterId(castObjectToCategryMastr(7));
				deptOpIpListUpdated.add(departmentWiseOpIp7);

				DepartmentWiseOpIp departmentWiseOpIp8 = new DepartmentWiseOpIp();
				departmentWiseOpIp8.setCategoryMasterId(castObjectToCategryMastr(8));
				deptOpIpListUpdated.add(departmentWiseOpIp8);

				DepartmentWiseOpIp departmentWiseOpIp9 = new DepartmentWiseOpIp();
				departmentWiseOpIp9.setCategoryMasterId(castObjectToCategryMastr(9));
				deptOpIpListUpdated.add(departmentWiseOpIp9);
			}

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: fetchDeptOpIpDetails: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: fetchDeptOpIpDetails");
		return deptOpIpListUpdated;
	}

	public List<SurgeryDetailsThDhGh> fetchSurgeryDetailsThDhGh(Long hospitalId) throws Exception {
		logger.info("Entered ThDaoImpl:fetchSurgeryDetailsThDhGh");
		Session session = this.sessionFactory.getCurrentSession();
		SurgeryDetailsThDhGh surgeryDetails = new SurgeryDetailsThDhGh();
		List<SurgeryDetailsThDhGh> surgeryDetailsUpdated = new ArrayList<SurgeryDetailsThDhGh>();
		try {
			String sql = "select s.category_id , s.majorsurgery, s.minorsurgery,s.hosp_monthly_trackid, s.id from surgerydetails_thghdh s inner join hospital_monthlytracker h on h.id = s.hosp_monthly_trackid where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId\r\n";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			List<SurgeryDetailsThDhGh> surgeryDetailsThDhGh = query.list();

			if (surgeryDetailsThDhGh != null && !surgeryDetailsThDhGh.isEmpty()) {
				Iterator iterator = surgeryDetailsThDhGh.iterator();
				while (iterator.hasNext()) {
					Object[] row = (Object[]) iterator.next();
					surgeryDetails = new SurgeryDetailsThDhGh();
					surgeryDetails.setCategoryMaster(castObjectToCategryMastr(row[0]));
					surgeryDetails.setMajorSurgery(castObjectToLong(row[1]));
					surgeryDetails.setMinorSurgery(castObjectToLong(row[2]));
					surgeryDetails.setHospitalMonthlyTracker(castObjectToHmt(row[3]));
					surgeryDetails.setId(castObjectToLong(row[4]));
					surgeryDetailsUpdated.add(surgeryDetails);
				}
			} else {
				SurgeryDetailsThDhGh surgeryDetailsThDhGh1 = new SurgeryDetailsThDhGh();
				surgeryDetailsThDhGh1.setCategoryMaster(castObjectToCategryMastr(5));
				surgeryDetailsUpdated.add(surgeryDetailsThDhGh1);

				SurgeryDetailsThDhGh surgeryDetailsThDhGh2 = new SurgeryDetailsThDhGh();
				surgeryDetailsThDhGh2.setCategoryMaster(castObjectToCategryMastr(8));
				surgeryDetailsUpdated.add(surgeryDetailsThDhGh2);

				SurgeryDetailsThDhGh surgeryDetailsThDhGh3 = new SurgeryDetailsThDhGh();
				surgeryDetailsThDhGh3.setCategoryMaster(castObjectToCategryMastr(9));
				surgeryDetailsUpdated.add(surgeryDetailsThDhGh3);

				SurgeryDetailsThDhGh surgeryDetailsThDhGh4 = new SurgeryDetailsThDhGh();
				surgeryDetailsThDhGh4.setCategoryMaster(castObjectToCategryMastr(10));
				surgeryDetailsUpdated.add(surgeryDetailsThDhGh4);

				SurgeryDetailsThDhGh surgeryDetailsThDhGh5 = new SurgeryDetailsThDhGh();
				surgeryDetailsThDhGh5.setCategoryMaster(castObjectToCategryMastr(21));
				surgeryDetailsUpdated.add(surgeryDetailsThDhGh5);

				SurgeryDetailsThDhGh surgeryDetailsThDhGh6 = new SurgeryDetailsThDhGh();
				surgeryDetailsThDhGh6.setCategoryMaster(castObjectToCategryMastr(6));
				surgeryDetailsUpdated.add(surgeryDetailsThDhGh6);
			}

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: fetchSurgeryDetailsThDhGh: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: fetchSurgeryDetailsThDhGh");
		return surgeryDetailsUpdated;
	}

	public List<SpecialityClinicData> fetchSpecialityClinicData(Long hospitalId) throws Exception {
		logger.info("Entered ThDaoImpl:fetchSpecialityClinicData");
		Session session = this.sessionFactory.getCurrentSession();
		SpecialityClinicData specialityClinicData = new SpecialityClinicData();
		List<SpecialityClinicData> specialityClinicDataUpdated = new ArrayList<SpecialityClinicData>();

		try {
			String sql = "select sc.specialityclinic_id, sc.malecount, sc.femalecount, sc.total, sc.tgcount,sc.hosp_track_id,sc.id from specialityclinic_data sc inner join hospital_monthlytracker h on h.id = sc.hosp_track_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			List<SpecialityClinicData> thSpecialityClinicData = query.list();

			if (thSpecialityClinicData != null && !thSpecialityClinicData.isEmpty()) {
				Iterator iterator = thSpecialityClinicData.iterator();

				while (iterator.hasNext()) {
					Object[] row = (Object[]) iterator.next();
					specialityClinicData = new SpecialityClinicData();
					specialityClinicData.setSpecialityClinic(castObjectToSpecialityClinic(row[0]));
					specialityClinicData.setMaleCount(castObjectToLong(row[1]));
					specialityClinicData.setFemalCount(castObjectToLong(row[2]));
					specialityClinicData.setTotal(castObjectToLong(row[3]));
					specialityClinicData.setTgCount(castObjectToLong(row[4]));
					specialityClinicData.setHospitalMonthlyTracker(castObjectToHmt(row[5]));
					specialityClinicData.setId(castObjectToLong(row[6]));
					specialityClinicDataUpdated.add(specialityClinicData);
				}
			} else {
				SpecialityClinicData specialityClinicData1 = new SpecialityClinicData();
				specialityClinicData1.setSpecialityClinic(castObjectToSpecialityClinic(1));
				specialityClinicDataUpdated.add(specialityClinicData1);

				SpecialityClinicData specialityClinicData2 = new SpecialityClinicData();
				specialityClinicData2.setSpecialityClinic(castObjectToSpecialityClinic(2));
				specialityClinicDataUpdated.add(specialityClinicData2);

				SpecialityClinicData specialityClinicData3 = new SpecialityClinicData();
				specialityClinicData3.setSpecialityClinic(castObjectToSpecialityClinic(3));
				specialityClinicDataUpdated.add(specialityClinicData3);

				SpecialityClinicData specialityClinicData4 = new SpecialityClinicData();
				specialityClinicData4.setSpecialityClinic(castObjectToSpecialityClinic(4));
				specialityClinicDataUpdated.add(specialityClinicData4);

				SpecialityClinicData specialityClinicData5 = new SpecialityClinicData();
				specialityClinicData5.setSpecialityClinic(castObjectToSpecialityClinic(5));
				specialityClinicDataUpdated.add(specialityClinicData5);

				SpecialityClinicData specialityClinicData6 = new SpecialityClinicData();
				specialityClinicData6.setSpecialityClinic(castObjectToSpecialityClinic(6));
				specialityClinicDataUpdated.add(specialityClinicData6);

				SpecialityClinicData specialityClinicData7 = new SpecialityClinicData();
				specialityClinicData7.setSpecialityClinic(castObjectToSpecialityClinic(7));
				specialityClinicDataUpdated.add(specialityClinicData7);
			}

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: fetchSpecialityClinicData: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: fetchSpecialityClinicData");
		return specialityClinicDataUpdated;
	}

	public LabDialysis fetchLabDialysis(Long hospitalId) throws Exception {
		logger.info("Entered ThDaoImpl:fetchLabDialysis");
		Session session = this.sessionFactory.getCurrentSession();
		LabDialysis labDialysis = new LabDialysis();

		try {
			String sql = "select lb.lab_patients_tested, lb.lab_total_tests, lb.lab_usg_func_machines, lb.lab_usg_count, lb.lab_ecg_count, lb.dia_shift_functioning, lb.dia_patient_count, lb.dia_total_count, lb.dia_patient_waiting, lb.xray_units_functioning, lb.total_xray_taken, lb.ph_arv_availability, lb.ph_asv_availability, lb.blood_bank, lb.blood_storage_unit, lb.ph_shortage_details, lb.drug_availability, lb.functional_ambulance, lb.hospmonthlytrack_id, lb.id from lab_dialysis_xray_pharmacy lb inner join hospital_monthlytracker h on h.id = lb.hospmonthlytrack_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
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
					labDialysis.setId(castObjectToLong(row[19]));
				}
			}
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: fetchLabDialysis: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: fetchLabDialysis");
		return labDialysis;
	}

	public FundExpenditure fetchFundExpenditure(Long hospitalId) throws Exception {
		logger.info("Entered ThDaoImpl:fetchFundExpenditure");
		Session session = this.sessionFactory.getCurrentSession();
		FundExpenditure fundExpenditure = new FundExpenditure();

		try {
			String sql = "select f.fund_hmc, f.fund_nhm, f.fund_rsby, f.fund_dept_plan, f.fund_lsgi, f.exp_hmc, f.exp_nhm, f.exp_rsby, f.exp_dept_plan, f.exp_lsgi, f.ongoing_construction, f.progress_asper_plan, f.delay_reason,f.hospital_tracker_id, f.id from fund_expenditure f \r\n"
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
					fundExpenditure.setId(castObjectToLong(row[14]));

				}
			}
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: fetchFundExpenditure: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: fetchFundExpenditure");
		return fundExpenditure;
	}

	public ServiceAreaOthers fetchServiceAreaOthers(Long hospitalId) throws Exception {
		logger.info("Entered ThDaoImpl:fetchServiceAreaOthers");
		Session session = this.sessionFactory.getCurrentSession();
		ServiceAreaOthers serviceAreaOthers = new ServiceAreaOthers();

		try {
			String sql = "select so.dental_procedures, so.og_normal_delivery_count, so.og_lscs_count, so.og_maternal_death_count, so.last_hmc_meeting, so.og_referred_cases_count, so.major_surgery_count, so.sc_high_dependency_unit, so.sc_patients_treated_count, so.other_relevant_info,so.hosp_tracker_id, so.id from servicearea_others so inner join hospital_monthlytracker h on h.id = so.hosp_tracker_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
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
					serviceAreaOthers.setId(castObjectToLong(row[11]));
				}
			}
		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: fetchServiceAreaOthers: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: fetchServiceAreaOthers");
		return serviceAreaOthers;
	}

	public List<IdlingMajorEquipment> fetchIdlingMajorEquipment(Long hospitalId) throws Exception {
		logger.info("Entered ThDaoImpl:fetchIdlingMajorEquipment");
		Session session = this.sessionFactory.getCurrentSession();
		IdlingMajorEquipment idlingMajorEquipment = new IdlingMajorEquipment();
		List<IdlingMajorEquipment> idlingMajorEquipmentUpdated = new ArrayList<IdlingMajorEquipment>();
		try {
			String sql = "select maj.equipment_name, maj.acquisition_date, maj.hosp_track_id,maj.id from idling_major_equipment maj inner join hospital_monthlytracker h on h.id = maj.hosp_track_id where h.report_date =to_date(:date,'yyyy-mm-dd') and h.hospital_id =:hospitalId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("hospitalId", hospitalId);
			query.setParameter("date", getReportDate());
			List<IdlingMajorEquipment> thIdlingMajorEquipment = query.list();

			if (thIdlingMajorEquipment != null && !thIdlingMajorEquipment.isEmpty()) {
				Iterator iterator = thIdlingMajorEquipment.iterator();

				while (iterator.hasNext()) {
					Object[] row = (Object[]) iterator.next();
					idlingMajorEquipment = new IdlingMajorEquipment();
					idlingMajorEquipment.setEquipmentName(castObjectToString(row[0]));
					idlingMajorEquipment.setAcquisitionDate((Date) (row[1]));
					idlingMajorEquipment.setHospitalMonthlyTracker(castObjectToHmt(row[2]));
					idlingMajorEquipment.setId(castObjectToLong(row[3]));
					idlingMajorEquipmentUpdated.add(idlingMajorEquipment);
				}
			}

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: fetchIdlingMajorEquipment: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl : fetchIdlingMajorEquipment");
		return idlingMajorEquipmentUpdated;
	}

	public MonthlyDataTh fetchMonthlyDataTh(Long hospitalId) throws Exception {
		logger.info("Entered ThDaoImpl:fetchMonthlyDataTh");
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

			HospitalMonthlyTracker hospitalMonthlyTracker = opIpDetails.getHospitalMonthlyTracker();
			if (hospitalMonthlyTracker != null) {
				Long hospMonthTrackId = opIpDetails.getHospitalMonthlyTracker().getId();
				hospitalMonthlyTracker.setId(hospMonthTrackId);
			}
			monthlyDataTh.setHospitalMonthlyTracker(hospitalMonthlyTracker);

		} catch (HibernateException e) {

			throw new HibernateException("Hibernate Exception : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in ThDaoImpl: fetchMonthlyDataTh: " + e.getMessage());
			throw new Exception("Exception : " + e.getMessage());
		}
		logger.info("Exited ThDaoImpl: fetchMonthlyDataTh");
		return monthlyDataTh;
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

	private CategoryMaster castObjectToCategryMastr(Object object) {
		CategoryMaster categoryMaster = new CategoryMaster();
		categoryMaster.setId(new Long((Integer) ((object != null) ? object : 0)));
		return categoryMaster;
	}

	private SpecialityClinic castObjectToSpecialityClinic(Object object) {
		SpecialityClinic specialityClinic = new SpecialityClinic();
		specialityClinic.setId(new Long((Integer) ((object != null) ? object : 0)));
		return specialityClinic;
	}

	private HospitalMonthlyTracker castObjectToHmt(Object object) {
		HospitalMonthlyTracker hospMonTrack = new HospitalMonthlyTracker();
		hospMonTrack.setId(new Long((Integer) ((object != null) ? object : 0)));
		return hospMonTrack;
	}

	/*
	 * public HospitalMonthlyTracker fetchHospitalMonthlyTrackId(Long hospitalId)
	 * throws Exception {
	 * logger.info("Entered ThDaoImpl: fetchHospitalMonthlyTrackId"); Session
	 * session = this.sessionFactory.getCurrentSession(); HospitalMonthlyTracker
	 * hospitalMonthlyTracker1 = new HospitalMonthlyTracker();
	 * 
	 * try { String sql =
	 * "select id from hospital_monthlytracker where hospital_id=:hospitalId"; Query
	 * query = session.createSQLQuery(sql); query.setParameter("hospitalId",
	 * hospitalId); Object thHospitalMonthlyTrackerId = query.uniqueResult();
	 * 
	 * if (thHospitalMonthlyTrackerId != null) {
	 * hospitalMonthlyTracker1.setId(thHospitalMonthlyTrackerId); }
	 * 
	 * } catch (HibernateException e) {
	 * 
	 * throw new HibernateException("Hibernate Exception : " + e.getMessage()); }
	 * catch (Exception e) {
	 * 
	 * throw new Exception("Exception : " + e.getMessage()); }
	 * logger.info("Exited ThDaoImpl:fetchServiceAreaOthers"); return
	 * serviceAreaOthers; }
	 */

}
