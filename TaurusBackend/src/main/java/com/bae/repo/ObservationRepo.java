package com.bae.repo;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.domain.Observations;

@Repository
public interface ObservationRepo extends JpaRepository<Observations, Long> {

	Set<Observations> findAllBytimestampBetweenAndAnprPointIdGreaterThan(LocalDateTime time1, LocalDateTime time2,
			Long anprPointId);

	Set<Observations> findAllBytimestamp(LocalDateTime time);

}
