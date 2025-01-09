package com.zurich.insurance_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zurich.insurance_management.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.userId = :id")
    Optional<Client> findByUserId(@Param("id") Long id);

	boolean existsByEmail(String email);

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
			"FROM Client c WHERE c.email = :email AND c.id != :id")
	boolean existsByEmailAndNotId(@Param("email") String email, @Param("id") Long id);

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
			"FROM Client c WHERE c.userId IS NULL AND c.id = :id")
	boolean existsWithoutUser(@Param("id") Long id);

}
