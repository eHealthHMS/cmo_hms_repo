package com.ehealth.hmms.pojo;

public class Result {

	
	

	private Object value;
	
	private Character status;//login status
	
	private String hospitalName;

	
	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}


	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}


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


//	/**
//	 * @return the userName
//	 */
//	public String getUserName() {
//		return hospitalName;
//	}
//
//
//	/**
//	 * @param userName the userName to set
//	 */
//	public void setUserName(String userName) {
//		this.hospitalName = userName;
//	}


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
	

	
}
