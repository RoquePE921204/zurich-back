package com.zurich.insurance_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zurich.insurance_management.entities.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
	
	public List<Insurance> findAllByClientId(Long clientId);

}
