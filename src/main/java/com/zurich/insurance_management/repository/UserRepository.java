package com.zurich.insurance_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zurich.insurance_management.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
	public Optional<User> findByCredentials(@Param("username") String username, @Param("password") String password);

}
