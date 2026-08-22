package com.API.Athuntication.Profile.enums.Entity;


	import jakarta.persistence.*;
	import lombok.*;

	import java.time.LocalDateTime;

	@Entity
	@Table(name = "otp_verifications")
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class OtpVerification {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String mobileNumber;

	    private String otp;

	    private LocalDateTime expiresAt;

	    private boolean verified;

		public OtpVerification(Long id, String mobileNumber, String otp, LocalDateTime expiresAt, boolean verified) {
			super();
			this.id = id;
			this.mobileNumber = mobileNumber;
			this.otp = otp;
			this.expiresAt = expiresAt;
			this.verified = verified;
		}
		public OtpVerification()
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

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}

		public LocalDateTime getExpiresAt() {
			return expiresAt;
		}

		public void setExpiresAt(LocalDateTime expiresAt) {
			this.expiresAt = expiresAt;
		}

		public boolean isVerified() {
			return verified;
		}

		public void setVerified(boolean verified) {
			this.verified = verified;
		}
	    
	    
}
