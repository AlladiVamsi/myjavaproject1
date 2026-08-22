package com.API.Athuntication.Profile.dto;

public class SupplierRegistrationRequest {

	    private String businessName;

	    private String email;

	    private String gstNumber;

	    private String businessType;

	    private String address;

	    private String pincode;

	    public SupplierRegistrationRequest() {
	    }

	    public String getBusinessName() {
	        return businessName;
	    }

	    public void setBusinessName(String businessName) {
	        this.businessName = businessName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getGstNumber() {
	        return gstNumber;
	    }

	    public void setGstNumber(String gstNumber) {
	        this.gstNumber = gstNumber;
	    }

	    public String getBusinessType() {
	        return businessType;
	    }

	    public void setBusinessType(String businessType) {
	        this.businessType = businessType;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getPincode() {
	        return pincode;
	    }

	    public void setPincode(String pincode) {
	        this.pincode = pincode;
	    }
	
}
