package com.API.Athuntication.Profile.enums.Entity;

	import com.API.Athuntication.Profile.enums.UserRole;

import jakarta.persistence.*;
	import lombok.*;

	@Entity
	@Table(name = "users")
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(unique = true, nullable = false)
	    private String mobileNumber;

	    private String name;

	    private boolean mobileVerified;

	    private boolean buyerRegistered;

	    private boolean supplierRegistered;

	    @Enumerated(EnumType.STRING)
	    private UserRole activeRole;

		public User(Long id, String mobileNumber, String name, boolean mobileVerified, boolean buyerRegistered,
				boolean supplierRegistered, UserRole activeRole) {
			super();
			this.id = id;
			this.mobileNumber = mobileNumber;
			this.name = name;
			this.mobileVerified = mobileVerified;
			this.buyerRegistered = buyerRegistered;
			this.supplierRegistered = supplierRegistered;
			this.activeRole = activeRole;
		}
		public User()
		{
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public boolean isMobileVerified() {
			return mobileVerified;
		}

		public void setMobileVerified(boolean mobileVerified) {
			this.mobileVerified = mobileVerified;
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

		public UserRole getActiveRole() {
			return activeRole;
		}

		public void setActiveRole(UserRole activeRole) {
			this.activeRole = activeRole;
		}
		public void setMobileVerified1(boolean mobileVerified2) {
			// TODO Auto-generated method stub
			
		}
		public void setMobileNumber1(String mobileNumber2) {
			// TODO Auto-generated method stub
			
		}
	    
	    
}
