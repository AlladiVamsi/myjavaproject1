package com.API.Athuntication.Profile.Service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.API.Athuntication.Profile.Repository.BuyerRepository;
import com.API.Athuntication.Profile.Repository.OtpVerificationRepository;
import com.API.Athuntication.Profile.Repository.SupplierRepository;
import com.API.Athuntication.Profile.Repository.UserRepository;
import com.API.Athuntication.Profile.dto.AuthResponse;
import com.API.Athuntication.Profile.dto.BuyerRegistrationRequest;
import com.API.Athuntication.Profile.dto.SendOtpRequest;
import com.API.Athuntication.Profile.dto.SupplierRegistrationRequest;
import com.API.Athuntication.Profile.dto.VerifyOtpRequest;
import com.API.Athuntication.Profile.enums.UserRole;
import com.API.Athuntication.Profile.enums.Entity.Buyer;
import com.API.Athuntication.Profile.enums.Entity.OtpVerification;
import com.API.Athuntication.Profile.enums.Entity.Supplier;
import com.API.Athuntication.Profile.enums.Entity.User;

@Service
public  class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BuyerRepository buyerRepository;
    private final SupplierRepository supplierRepository;
    private final OtpVerificationRepository otpRepository;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            BuyerRepository buyerRepository,
            SupplierRepository supplierRepository,
            OtpVerificationRepository otpRepository,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.buyerRepository = buyerRepository;
        this.supplierRepository = supplierRepository;
        this.otpRepository = otpRepository;
        this.jwtService = jwtService;
    }

    // =====================================================
    // 1. SEND OTP
    // =====================================================

    @Override
    public Object sendOtp(SendOtpRequest request) {

        String mobileNumber = request.getMobileNumber();

        if (mobileNumber == null || mobileNumber.isBlank()) {
            throw new RuntimeException("Mobile number is required");
        }

        String otp = String.format(
                "%06d",
                new Random().nextInt(1000000)
        );

        OtpVerification verification = new OtpVerification();

        verification.setMobileNumber(mobileNumber);
        verification.setOtp(otp);
        verification.setVerified(false);
        verification.setExpiresAt(
                LocalDateTime.now().plusMinutes(5)
        );

        otpRepository.save(verification);

        // Development only
        System.out.println(
                "OTP for " + mobileNumber + " = " + otp
        );

        return "OTP sent successfully";
    }

    // =====================================================
    // 2. VERIFY OTP
    // =====================================================

    @Override
    public Object verifyOtp(VerifyOtpRequest request) {

        OtpVerification verification =
                otpRepository
                        .findTopByMobileNumberOrderByIdDesc(
                                request.getMobileNumber()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "OTP not found"
                                )
                        );

        if (verification.isVerified()) {
            throw new RuntimeException(
                    "OTP already used"
            );
        }

        if (verification.getExpiresAt()
                .isBefore(LocalDateTime.now())) {

            throw new RuntimeException(
                    "OTP expired"
            );
        }

        if (!verification.getOtp()
                .equals(request.getOtp())) {

            throw new RuntimeException(
                    "Invalid OTP"
            );
        }

        verification.setVerified(true);

        otpRepository.save(verification);

        // Find existing user
        // OR create new user

        User user =
                userRepository
                        .findByMobileNumber(
                                request.getMobileNumber()
                        )
                        .orElseGet(() -> {

                            User newUser = new User();

                            newUser.setMobileNumber(
                                    request.getMobileNumber()
                            );

                            newUser.setMobileVerified(true);
                            newUser.setBuyerRegistered(false);
                            newUser.setSupplierRegistered(false);

                            newUser.setActiveRole(
                                    UserRole.BUYER
                            );

                            return userRepository.save(
                                    newUser
                            );
                        });

        user.setMobileVerified(true);

        userRepository.save(user);

        String role =
                user.getActiveRole() != null
                        ? user.getActiveRole().name()
                        : UserRole.BUYER.name();

        // Access Token

        String accessToken =
                jwtService.generateToken(
                        user.getId(),
                        user.getMobileNumber(),
                        role
                );

        // Refresh Token
        //
        // IMPORTANT:
        // Ideally this should use a separate
        // refresh-token method.

        String refreshToken =
                jwtService.generateToken(
                        user.getId(),
                        user.getMobileNumber(),
                        role
                );

        return new AuthResponse(
                true,
                "OTP verified successfully",
                accessToken,
                refreshToken,
                user.getId(),
                user.getMobileNumber(),
                role
        );
    }

    // =====================================================
    // 3. REGISTER BUYER
    // =====================================================

    @Override
    public Object registerBuyer(
            BuyerRegistrationRequest request,
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        if (!user.isMobileVerified()) {
            throw new RuntimeException(
                    "Please verify mobile number first"
            );
        }

        if (user.isBuyerRegistered()) {
            throw new RuntimeException(
                    "Buyer already registered"
            );
        }

        Buyer buyer = new Buyer();

        buyer.setUser(user);
        buyer.setName(request.getName());
        buyer.setEmail(request.getEmail());
        buyer.setCompanyName(request.getCompanyName());
        buyer.setGstNumber(request.getGstNumber());
        buyer.setAddress(request.getAddress());
        buyer.setPincode(request.getPincode());

        buyerRepository.save(buyer);

        user.setBuyerRegistered(true);
        user.setActiveRole(UserRole.BUYER);

        userRepository.save(user);

        return "Buyer registered successfully";
    }

    // =====================================================
    // 4. REGISTER SUPPLIER
    // =====================================================

    @Override
    public Object registerSupplier(
            SupplierRegistrationRequest request,
            Authentication authentication) {

        User user =
                getAuthenticatedUser(authentication);

        if (!user.isMobileVerified()) {

            throw new RuntimeException(
                    "Please verify mobile number first"
            );
        }

        if (user.isSupplierRegistered()) {

            throw new RuntimeException(
                    "Supplier already registered"
            );
        }

        Supplier supplier = new Supplier();

        supplier.setUser(user);

        supplier.setBusinessName(
                request.getBusinessName()
        );

        supplier.setEmail(
                request.getEmail()
        );

        supplier.setGstNumber(
                request.getGstNumber()
        );

        supplier.setBusinessType(
                request.getBusinessType()
        );

        supplier.setAddress(
                request.getAddress()
        );

        supplier.setPincode(
                request.getPincode()
        );

        supplierRepository.save(supplier);

        user.setSupplierRegistered(true);

        user.setActiveRole(
                UserRole.SUPPLIER
        );

        userRepository.save(user);

        return "Supplier registered successfully";
    }

    // =====================================================
    // 5. GET CURRENT USER
    // =====================================================

    @Override
    public Object getCurrentUser(
            Authentication authentication) {

        User user =
                getAuthenticatedUser(authentication);

        return user;
    }

    // =====================================================
    // 6. SWITCH ROLE
    // =====================================================

    @Override
    public Object switchRole(
            Authentication authentication) {

        User user =
                getAuthenticatedUser(authentication);

        if (user.getActiveRole() == UserRole.BUYER) {

            if (!user.isSupplierRegistered()) {

                throw new RuntimeException(
                        "Supplier profile is not registered"
                );
            }

            user.setActiveRole(
                    UserRole.SUPPLIER
            );

        } else {

            if (!user.isBuyerRegistered()) {

                throw new RuntimeException(
                        "Buyer profile is not registered"
                );
            }

            user.setActiveRole(
                    UserRole.BUYER
            );
        }

        userRepository.save(user);

        return "Role switched to "
                + user.getActiveRole().name();
    }

    // =====================================================
    // COMMON METHOD
    // =====================================================

    private User getAuthenticatedUser(
            Authentication authentication) {

        if (authentication == null
                || !authentication.isAuthenticated()) {

            throw new RuntimeException(
                    "User is not authenticated"
            );
        }

        String mobileNumber =
                authentication.getName();

        return userRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );
    }

	@Override
	public Object registerBuyer1(BuyerRegistrationRequest request, Authentication authentication) {
		// TODO Auto-generated method stub
		return null;
	}

}