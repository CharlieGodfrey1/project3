package com.bae.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.domain.VehicleRegistration;

@Repository
public interface VehicleRegistrationRepo extends JpaRepository<VehicleRegistration, Long> {

	Set<VehicleRegistration> findBydriverLicenceId(String licenceID);

	VehicleRegistration findByvehicleRegistrationNo(String vehReg);

}
