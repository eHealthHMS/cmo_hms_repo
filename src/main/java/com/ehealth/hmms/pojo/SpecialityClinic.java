package com.ehealth.hmms.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "speciality_clinic")
public class SpecialityClinic  implements Serializable{

	private static final long serialVersionUID = -171207112203674417L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ncd_clinic")
	private Long ncdClinic;
	
	@Column(name = "swas_clinic")
	private Long swasClinic;
	
	@Column(name = "adolescent_clinic")
	private Long adolescentClinic;
	
	@Column(name = "aswas_clinic")
	private Long aswasClinic;
	
	@Column(name = "palliative_clinic")
	private Long palliativeClinic;
	
	@Column(name = "geriatric_clinic")
	private Long geriatricClinic;
	
	@Column(name = "an_clinic")
	private Long anClinic;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNcdClinic() {
		return ncdClinic;
	}

	public void setNcdClinic(Long ncdClinic) {
		this.ncdClinic = ncdClinic;
	}

	public Long getSwasClinic() {
		return swasClinic;
	}

	public void setSwasClinic(Long swasClinic) {
		this.swasClinic = swasClinic;
	}

	public Long getAdolescentClinic() {
		return adolescentClinic;
	}

	public void setAdolescentClinic(Long adolescentClinic) {
		this.adolescentClinic = adolescentClinic;
	}

	public Long getAswasClinic() {
		return aswasClinic;
	}

	public void setAswasClinic(Long aswasClinic) {
		this.aswasClinic = aswasClinic;
	}

	public Long getPalliativeClinic() {
		return palliativeClinic;
	}

	public void setPalliativeClinic(Long palliativeClinic) {
		this.palliativeClinic = palliativeClinic;
	}

	public Long getGeriatricClinic() {
		return geriatricClinic;
	}

	public void setGeriatricClinic(Long geriatricClinic) {
		this.geriatricClinic = geriatricClinic;
	}

	public Long getAnClinic() {
		return anClinic;
	}

	public void setAnClinic(Long anClinic) {
		this.anClinic = anClinic;
	}

		
}
