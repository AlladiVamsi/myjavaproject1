package com.API.Athuntication.Profile.Repository;


	import org.springframework.data.jpa.repository.JpaRepository;

import com.API.Athuntication.Profile.enums.Entity.OtpVerification;

import java.util.Optional;

	public interface OtpVerificationRepository
	        extends JpaRepository<OtpVerification, Long> {

	    Optional<OtpVerification>
	    findTopByMobileNumberOrderByIdDesc(String mobileNumber);
	
}
