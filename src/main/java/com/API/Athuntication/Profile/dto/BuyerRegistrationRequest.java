package com.API.Athuntication.Profile.dto;

public class BuyerRegistrationRequest {
	    private String name;

	    private String email;

	    private String companyName;

	    private String gstNumber;

	    private String address;

	    private String pincode;

	    public BuyerRegistrationRequest() {
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getCompanyName() {
	        return companyName;
	    }

	    public void setCompanyName(String companyName) {
	        this.companyName = companyName;
	    }

	    public String getGstNumber() {
	        return gstNumber;
	    }

	    public void setGstNumber(String gstNumber) {
	        this.gstNumber = gstNumber;
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
