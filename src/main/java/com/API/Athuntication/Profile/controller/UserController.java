package com.API.Athuntication.Profile.controller;


	import lombok.RequiredArgsConstructor;

	import org.springframework.http.ResponseEntity;
	import org.springframework.security.core.Authentication;
	import org.springframework.web.bind.annotation.*;

import com.API.Athuntication.Profile.Service.AuthService;

	@RestController
	@RequestMapping("/users")
	@RequiredArgsConstructor
	public class UserController {

	    private final AuthService authService = null;

	    // =====================================================
	    // 6. SWITCH ROLE
	    // PUT /users/switch-role
	    // =====================================================

	    @PutMapping("/switch-role")
	    public ResponseEntity<?> switchRole(
	            Authentication authentication) {

	        return ResponseEntity.ok(
	                authService.switchRole(
	                        authentication
	                )
	        );
	    }
	
}
