package com.API.Athuntication.Profile.enums.Entity;


	import jakarta.persistence.*;
	import lombok.*;

	@Entity
	@Table(name = "buyers")
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class Buyer {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    private String email;

	    private String companyName;

	    private String gstNumber;

	    private String address;

	    private String pincode;

	    @OneToOne
	    @JoinColumn(name = "user_id")
	    private User user;
	    
	    public Buyer()
	    {
	    	super();
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Buyer(Long id, String name, String email, String companyName, String gstNumber, String address,
				String pincode, User user) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.companyName = companyName;
			this.gstNumber = gstNumber;
			this.address = address;
			this.pincode = pincode;
			this.user = user;
		}
	    
}
