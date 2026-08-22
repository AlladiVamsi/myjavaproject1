package com.API.Athuntication.Profile.dto;

public class UserProfileResponse {


	    private boolean success;

	    private Long userId;

	    private String mobileNumber;

	    private String name;

	    private String activeRole;

	    private boolean buyerRegistered;

	    private boolean supplierRegistered;

	    public UserProfileResponse() {
	    }

	    public boolean isSuccess() {
	        return success;
	    }

	    public void setSuccess(boolean success) {
	        this.success = success;
	    }

	    public Long getUserId() {
	        return userId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

	    public String getMobileNumber() {
	        return mobileNumber;
	    }

	    public void setMobileNumber(String mobileNumber) {
	        this.mobileNumber = mobileNumber;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getActiveRole() {
	        return activeRole;
	    }

	    public void setActiveRole(String activeRole) {
	        this.activeRole = activeRole;
	    }

	    public boolean isBuyerRegistered() {
	        return buyerRegistered;
	    }

	    public void setBuyerRegistered(boolean buyerRegistered) {
	        this.buyerRegistered = buyerRegistered;
	    }

	    public boolean isSupplierRegistered() {
	        return supplierRegistered;
	    }

	    public void setSupplierRegistered(boolean supplierRegistered) {
	        this.supplierRegistered = supplierRegistered;
	    }
	
}
