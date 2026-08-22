package com.API.Athuntication.Profile.Repository;

	import org.springframework.data.jpa.repository.JpaRepository;

import com.API.Athuntication.Profile.enums.Entity.User;

import java.util.Optional;

	public interface UserRepository extends JpaRepository<User, Long> {

	    Optional<User> findByMobileNumber(String mobileNumber);
	
}
