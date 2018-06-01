package com.ehealth.hmms.pojo;

import java.awt.Point;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hospital_master")
public class HospitalMaster implements Serializable {

		private static final long serialVersionUID = -7788619177798333712L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "gid")
		private Long id;
		
		@Column(name = "place")
		private String place;
		
		@Column(name = "hospital_name")
		private String hospitalName;
		
		@Column(name = "local_body")
		private String localBody;

		@Column(name = "lac")
		private String lac;
		
		@Column(name = "geometry")
		private transient Point geometry;
		
		@Column(name = "hospital_code")
		private String hospitalCode;
		
		@Column(name = "operation_theatre_count")
		private Long operationtheatreCount;
		
		@Column(name = "dialysis_machines_count")
		private Long dialysisMachinesCount;
		
		@Column(name = "ward_count")
		private Long wardCount;
		
		@Column(name = "subcenter_count")
		private Long subCenterCount;
		
		@Column(name = "houshold_gramapanchyt_count")
		private Long housholdGpCount;
		
		@Column(name = "nin")
		private Long nin;
		
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "hospitaltype_id",referencedColumnName="id")
		private HospitalTypeMaster hospitalTypeMaster;
		
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "district_id",referencedColumnName="id")
		private DistrictMaster districtMaster;
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getPlace() {
			return place;
		}

		public void setPlace(String place) {
			this.place = place;
		}

		public String getHospitalName() {
			return hospitalName;
		}

		public void setHospitalName(String hospitalName) {
			this.hospitalName = hospitalName;
		}

		public String getLocalBody() {
			return localBody;
		}

		public void setLocalBody(String localBody) {
			this.localBody = localBody;
		}

		public String getLac() {
			return lac;
		}

		public void setLac(String lac) {
			this.lac = lac;
		}

		public Point getGeometry() {
			return geometry;
		}

		public void setGeometry(Point geometry) {
			this.geometry = geometry;
		}

		public String getHospitalCode() {
			return hospitalCode;
		}

		public void setHospitalCode(String hospitalCode) {
			this.hospitalCode = hospitalCode;
		}

		public Long getNin() {
			return nin;
		}
		public void setNin(Long nin) {
			this.nin = nin;
		}
		public DistrictMaster getDistrictMaster() {
			return districtMaster;
		}

		public void setDistrictMaster(DistrictMaster districtMaster) {
			this.districtMaster = districtMaster;
		}

		/**
		 * @return the operationtheatreCount
		 */
		public Long getOperationtheatreCount() {
			return operationtheatreCount;
		}

		/**
		 * @param operationtheatreCount the operationtheatreCount to set
		 */
		public void setOperationtheatreCount(Long operationtheatreCount) {
			this.operationtheatreCount = operationtheatreCount;
		}

		/**
		 * @return the dialysisMachinesCount
		 */
		public Long getDialysisMachinesCount() {
			return dialysisMachinesCount;
		}

		/**
		 * @param dialysisMachinesCount the dialysisMachinesCount to set
		 */
		public void setDialysisMachinesCount(Long dialysisMachinesCount) {
			this.dialysisMachinesCount = dialysisMachinesCount;
		}

		/**
		 * @return the wardCount
		 */
		public Long getWardCount() {
			return wardCount;
		}

		/**
		 * @param wardCount the wardCount to set
		 */
		public void setWardCount(Long wardCount) {
			this.wardCount = wardCount;
		}

		/**
		 * @return the housholdGpCount
		 */
		public Long getHousholdGpCount() {
			return housholdGpCount;
		}

		/**
		 * @param housholdGpCount the housholdGpCount to set
		 */
		public void setHousholdGpCount(Long housholdGpCount) {
			this.housholdGpCount = housholdGpCount;
		}

		/**
		 * @return the subCenterCount
		 */
		public Long getSubCenterCount() {
			return subCenterCount;
		}

		/**
		 * @param subCenterCount the subCenterCount to set
		 */
		public void setSubCenterCount(Long subCenterCount) {
			this.subCenterCount = subCenterCount;
		}

		public HospitalTypeMaster getHospitalTypeMaster() {
			return hospitalTypeMaster;
		}

		public void setHospitalTypeMaster(HospitalTypeMaster hospitalTypeMaster) {
			this.hospitalTypeMaster = hospitalTypeMaster;
		}

}
