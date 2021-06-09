package com.bae.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bae.DTO.CombinationDTO;
import com.bae.DTO.ObservationDTO;
import com.bae.DTO.SuspectDTO;
import com.bae.DTO.VehicleDTO;
import com.bae.domain.Observations;
import com.bae.domain.Vehicle;
import com.bae.domain.VehicleRegistration;
import com.bae.repo.VehicleRegistrationRepo;
import com.bae.repo.VehicleRepo;

@Service
public class VehicleService {
	private VehicleRepo repo;
	private VehicleRegistrationRepo regRepo;

	public VehicleService(VehicleRepo repo, VehicleRegistrationRepo repoReg) {
		super();
		this.repo = repo;
		this.regRepo = repoReg;
	}

	public SuspectDTO showSuspect(String reg) {
		SuspectDTO suspectDTO = null;

		Optional<Vehicle> found = Optional.ofNullable(repo.findByvehicleRegistrationNumber(reg));

		if (found.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find vehicle");
		}

		for (VehicleRegistration i : found.get().getVehicleRegs()) {
			suspectDTO = mapToDTO(i);
		}

		return suspectDTO;
	}

	public Set<VehicleDTO> findAllVehicles(String licence) {
		Set<VehicleRegistration> found = regRepo.findBydriverLicenceId(licence);

		Set<VehicleDTO> dtos = new HashSet<>();
		for (VehicleRegistration i : found) {
			VehicleDTO dto = mapToDTO2(i);
			dtos.add(dto);
		}

		return dtos;

	}

	public Set<ObservationDTO> showObs(String reg) {
		Vehicle found = repo.findByvehicleRegistrationNumber(reg);

		Set<ObservationDTO> dtos = new HashSet<>();

		for (Observations i : found.getObservations()) {
			ObservationDTO dto = mapToDTO(i);
			dtos.add(dto);
		}

		return dtos;

	}

	public CombinationDTO showSusObs(String reg) {
		CombinationDTO dto = new CombinationDTO();

		dto.setSuspect(showSuspect(reg));
		dto.setObs(showObs(reg));

		return dto;

	}

	public ObservationDTO mapToDTO(Observations obs) {
		ObservationDTO dto = new ObservationDTO();

		dto.setVehicleRegistrationNumber(obs.getVehicleRegistrationNumber());
		dto.setStreetName(obs.getAnprLocal().getStreetName());
		dto.setLongitude(obs.getAnprLocal().getLongitude());
		dto.setLatitude(obs.getAnprLocal().getLatitude());
		dto.setTimestamp(obs.getTimestamp());

		return dto;

	}

	public SuspectDTO mapToDTO(VehicleRegistration vehReg) {
		SuspectDTO dto = new SuspectDTO();

		dto.setForenames(vehReg.getForenames());
		dto.setSurname(vehReg.getSurname());
		dto.setAddress(vehReg.getAddress());
		dto.setDateOfBirth(vehReg.getDateOfBirth());
		dto.setDriverLicenceId(vehReg.getDriverLicenceID());
		dto.setVehicles(findAllVehicles(vehReg.getDriverLicenceID()));

		return dto;
	}

	public VehicleDTO mapToDTO2(VehicleRegistration veh) {
		VehicleDTO dto = new VehicleDTO();

		dto.setVehicleRegistrationNumber(veh.getVehicleRegistrationNo());
		dto.setMake(veh.getMake());
		dto.setModel(veh.getModel());
		dto.setColour(veh.getColour());

		return dto;
	}

}
