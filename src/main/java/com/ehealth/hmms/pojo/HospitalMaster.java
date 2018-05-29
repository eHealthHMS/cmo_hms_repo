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

		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "id")
		public DistrictMaster getDistrictMaster() {
			return districtMaster;
		}

		public void setDistrictMaster(DistrictMaster districtMaster) {
			this.districtMaster = districtMaster;
		}

}
