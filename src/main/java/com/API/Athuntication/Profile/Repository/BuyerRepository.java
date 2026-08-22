package com.API.Athuntication.Profile.Repository;

	import org.springframework.data.jpa.repository.JpaRepository;

import com.API.Athuntication.Profile.enums.Entity.Buyer;
import com.API.Athuntication.Profile.enums.Entity.User;

import java.util.Optional;

	public interface BuyerRepository extends JpaRepository<Buyer, Long> {

	    Optional<Buyer> findByUser(User user);
	
}
