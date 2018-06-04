package com.ehealth.hmms.pojo;

public class Result {

	private Object value;
	
	private Character status;//login status
	
	private HospitalMaster hospitalMaster;
	
	private Boolean editable;// view mode or edit mode

	
	private Long hospitalType;

	private String errorMessage;


	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}


	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}


	/**
	 * @return the status
	 */
	public Character getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(Character status) {
		this.status = status;
	}


	public Boolean getEditable() {
		return editable;
	}


	public void setEditable(Boolean editable) {
		this.editable = editable;
	}


	public HospitalMaster getHospitalMaster() {
		return hospitalMaster;
	}


	public void setHospitalMaster(HospitalMaster hospitalMaster) {
		this.hospitalMaster = hospitalMaster;
	}


	/**
	 * @return the hospitalType
	 */
	public Long getHospitalType() {
		return hospitalType;
	}


	/**
	 * @param hospitalType the hospitalType to set
	 */
	public void setHospitalType(Long hospitalType) {
		this.hospitalType = hospitalType;
	}

	

}
