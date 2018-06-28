package com.ehealth.hmms.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "idling_major_equipment")
public class IdlingMajorEquipment implements Serializable{

	private static final long serialVersionUID = -3688096262820658250L;

	@Id 
	@SequenceGenerator(name="idling_major_equipment_sequence",sequenceName="idling_major_equipment_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="idling_major_equipment_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "equipment_name")
	private String equipmentName;
	
	@Column(name = "acquisition_date")
	private Date acquisitionDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "hosp_track_id", referencedColumnName="id")
	private HospitalMonthlyTracker hospitalMonthlyTrackerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Date getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	public HospitalMonthlyTracker getHospitalMonthlyTracker() {
		return hospitalMonthlyTrackerId;
	}

	public void setHospitalMonthlyTracker(HospitalMonthlyTracker hospitalMonthlyTrackerId) {
		this.hospitalMonthlyTrackerId = hospitalMonthlyTrackerId;
	}
	
	
}
