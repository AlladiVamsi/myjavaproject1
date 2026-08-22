package com.API.Athuntication.Profile.enums.Entity;

	import jakarta.persistence.*;
	import lombok.*;

	@Entity
	@Table(name = "suppliers")
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class Supplier {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String businessName;

	    private String email;

	    private String gstNumber;

	    private String businessType;

	    private String address;

	    private String pincode;

	    @OneToOne
	    @JoinColumn(name = "user_id")
	    private User user;
	    
	    public Supplier()
	    {
	    	super();
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Supplier(Long id, String businessName, String email, String gstNumber, String businessType,
				String address, String pincode, User user) {
			super();
			this.id = id;
			this.businessName = businessName;
			this.email = email;
			this.gstNumber = gstNumber;
			this.businessType = businessType;
			this.address = address;
			this.pincode = pincode;
			this.user = user;
		}
	    
}
