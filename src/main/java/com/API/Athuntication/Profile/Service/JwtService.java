package com.API.Athuntication.Profile.Service;


	import io.jsonwebtoken.Claims;
	import io.jsonwebtoken.Jwts;
	import io.jsonwebtoken.io.Decoders;
	import io.jsonwebtoken.security.Keys;

	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.stereotype.Service;

	import javax.crypto.SecretKey;
	import java.util.Date;
	import java.util.function.Function;

	@Service
	public class JwtService {

	    @Value("${jwt.secret}")
	    private String secret;

	    @Value("${jwt.expiration:3600000}")
	    private long expiration;

	    // Create signing key
	    private SecretKey getSigningKey() {

	        byte[] keyBytes = Decoders.BASE64.decode(secret);

	        return Keys.hmacShaKeyFor(keyBytes);
	    }

	    // Generate JWT token
	    public String generateToken(
	            Long userId,
	            String mobileNumber,
	            String role) {

	        return Jwts.builder()
	                .subject(String.valueOf(userId))

	                .claim("mobile", mobileNumber)
	                .claim("role", role)

	                .issuedAt(new Date())

	                .expiration(
	                        new Date(
	                                System.currentTimeMillis()
	                                        + expiration
	                        )
	                )

	                .signWith(getSigningKey())

	                .compact();
	    }

	    // Get user ID from token
	    public String extractUserId(String token) {

	        return extractClaim(
	                token,
	                Claims::getSubject
	        );
	    }

	    // Get mobile number from token
	    public String extractMobile(String token) {

	        return extractAllClaims(token)
	                .get("mobile", String.class);
	    }

	    // Get role from token
	    public String extractRole(String token) {

	        return extractAllClaims(token)
	                .get("role", String.class);
	    }

	    // Check token validity
	    public boolean isTokenValid(String token) {

	        try {

	            extractAllClaims(token);

	            return true;

	        } catch (Exception e) {

	            return false;
	        }
	    }

	    // Extract specific claim
	    private <T> T extractClaim(
	            String token,
	            Function<Claims, T> resolver) {

	        return resolver.apply(
	                extractAllClaims(token)
	        );
	    }

	    // Extract all claims
	    private Claims extractAllClaims(String token) {

	        return Jwts.parser()
	                .verifyWith(getSigningKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }
	
}
