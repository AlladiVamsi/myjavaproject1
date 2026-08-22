package com.API.Athuntication.Profile.Repository;



	import org.springframework.data.jpa.repository.JpaRepository;

import com.API.Athuntication.Profile.enums.Entity.Supplier;
import com.API.Athuntication.Profile.enums.Entity.User;

import java.util.Optional;

	public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	    Optional<Supplier> findByUser(User user);
	
}
