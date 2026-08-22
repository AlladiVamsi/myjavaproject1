package com.API.Athuntication.Profile.Service;

	import org.springframework.security.core.Authentication;

import com.API.Athuntication.Profile.dto.BuyerRegistrationRequest;
import com.API.Athuntication.Profile.dto.SendOtpRequest;
import com.API.Athuntication.Profile.dto.SupplierRegistrationRequest;
import com.API.Athuntication.Profile.dto.VerifyOtpRequest;

	public interface AuthService {

	    // 1. Send OTP
	    Object sendOtp(SendOtpRequest request);

	    // 2. Verify OTP
	    Object verifyOtp(VerifyOtpRequest request);

	    // 3. Register Buyer
	    Object registerBuyer(
	            BuyerRegistrationRequest request,
	            Authentication authentication
	    );

	    // 4. Register Supplier
	    Object registerSupplier(
	            SupplierRegistrationRequest request,
	            Authentication authentication
	    );

	    // 5. Get Current User
	    Object getCurrentUser(
	            Authentication authentication
	    );

	    // 6. Switch Buyer/Supplier Role
	    Object switchRole(
	            Authentication authentication
	    );
	    Object registerBuyer1(
	            BuyerRegistrationRequest request,
	            Authentication authentication
	    );
}
