package com.bae.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.domain.AnprLocation;

@Repository
public interface CameraRepo extends JpaRepository<AnprLocation, Long> {

	AnprLocation findByStreetName(String streetname);

}
