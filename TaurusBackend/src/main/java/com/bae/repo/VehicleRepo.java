package com.bae.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.domain.Vehicle;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

	Vehicle findByvehicleRegistrationNumber(String reg);

}
