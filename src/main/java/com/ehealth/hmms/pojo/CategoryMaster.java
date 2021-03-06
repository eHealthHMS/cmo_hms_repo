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
@Table(name = "category_master")
public class CategoryMaster implements Serializable {
	
	private static final long serialVersionUID = -2868805966510003418L;

	@Id 
	@SequenceGenerator(name="category_master_sequence",sequenceName="category_master_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="category_master_sequence")
	@Column(name="id", unique=true, nullable=false)
	private Long id;

	@Column(name = "category_name")
	private String categoryName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
