package com.ehealth.hmms.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "configuration_master")
public class Configuration implements Serializable{
	
	private static final long serialVersionUID = 387137050385972831L;

	@Id 
	@SequenceGenerator(name="configuration_sequence",sequenceName="configuration_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="configuration_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "key")
	private String key;
	
	@Column(name = "value")
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
