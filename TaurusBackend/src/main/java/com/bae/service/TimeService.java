package com.bae.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bae.DTO.FrontendDTO;
import com.bae.DTO.LocationDTO;
import com.bae.DTO.PersonDTO;
import com.bae.DTO.TransactionsDTO;
import com.bae.DTO.WithdrawalsDTO;
import com.bae.domain.AccountHolder;
import com.bae.domain.AtmTransaction;
import com.bae.domain.BankCard;
import com.bae.domain.EposTransaction;
import com.bae.domain.Observations;
import com.bae.domain.VehicleRegistration;
import com.bae.repo.AccountHolderRepo;
import com.bae.repo.CardRepo;
import com.bae.repo.ObservationRepo;
import com.bae.repo.TransactionsRepo;
import com.bae.repo.VehicleRegistrationRepo;
import com.bae.repo.WithdrawalsRepo;

@Service
public class TimeService {
	private double latitude;
	private double longitude;

	private ObservationRepo repo;
	private VehicleRegistrationRepo regRepo;
	private TransactionsRepo transactionRepo;
	private AccountHolderRepo personRepo;
	private CardRepo cardRepo;
	private WithdrawalsRepo withdrawalsRepo;

	public TimeService(ObservationRepo repo, VehicleRegistrationRepo regRepo, TransactionsRepo transactionRepo,
			AccountHolderRepo personRepo, CardRepo cardRepo, WithdrawalsRepo withdrawalsRepo) {
		super();
		this.repo = repo;
		this.regRepo = regRepo;
		this.transactionRepo = transactionRepo;
		this.personRepo = personRepo;
		this.cardRepo = cardRepo;
		this.withdrawalsRepo = withdrawalsRepo;
	}

	public FrontendDTO findAllInArea(Set<LocationDTO> vehicle, Set<PersonDTO> person) {
		FrontendDTO dto = new FrontendDTO();
		dto.setVehicle(vehicle);
		dto.setTransaction(person);
		return dto;
	}

	public FrontendDTO findByTime(String timestamp, String streetname, int timeframe, int radius) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);

		Set<Observations> found = repo.findAllBytimestampBetweenAndAnprPointIdGreaterThan(
				dateTime.minusMinutes(timeframe), dateTime.plusMinutes(timeframe), 0L);

		Set<LocationDTO> dtos = new HashSet<>();
		for (Observations i : found) {
			if (i.getAnprLocal().getStreetName().equals(streetname)) {
				this.latitude = i.getAnprLocal().getLatitude();
				this.longitude = i.getAnprLocal().getLongitude();
			}
		}

		LocalDateTime time1 = dateTime.minusMinutes(timeframe);
		LocalDateTime time2 = dateTime.plusMinutes(timeframe);

		Set<PersonDTO> people = new HashSet<>();

		Set<EposTransaction> eposTransactions = transactionRepo.findAllBytimestampBetweenAndEposIdGreaterThan(time1,
				time2, 0L);

		for (EposTransaction t : eposTransactions) {
			if (t.getEposTerminal().getStreetName().equals(streetname)) {
				this.latitude = t.getEposTerminal().getLatitude();
				this.longitude = t.getEposTerminal().getLongitude();
			}
		}

		Set<AtmTransaction> atmTransactions = withdrawalsRepo.findAllBytimestampBetweenAndAtmIdGreaterThan(time1, time2,
				0L);

		for (AtmTransaction t : atmTransactions) {
			if (t.getAtm().getStreetName().equals(streetname)) {
				this.latitude = t.getAtm().getLatitude();
				this.longitude = t.getAtm().getLongitude();
			}
		}

		for (AtmTransaction t : atmTransactions) {
			if (t.getAtm().getStreetName().equals(streetname)) {
				BankCard card = this.cardRepo.findAllByCardNumber(t.getBankCardNumber());
				PersonDTO dto = mapToPersonDTO(t, card, time1, time2);
				people.add(dto);
			} else if (calculateDistanceInKilometer(latitude, longitude, t.getAtm().getLatitude(),
					t.getAtm().getLongitude()) <= radius) {
				BankCard card = this.cardRepo.findAllByCardNumber(t.getBankCardNumber());
				PersonDTO dto = mapToPersonDTO(t, card, time1, time2);
				people.add(dto);
			}
		}

		for (Observations i : found) {
			if (i.getAnprLocal().getStreetName().equals(streetname)) {
				VehicleRegistration reg = regRepo.findByvehicleRegistrationNo(i.getVehicleRegistrationNumber());
				LocationDTO dto = mapToDTO(i, reg);
				dtos.add(dto);
			} else if (calculateDistanceInKilometer(latitude, longitude, i.getAnprLocal().getLatitude(),
					i.getAnprLocal().getLongitude()) <= radius) {
				VehicleRegistration reg = regRepo.findByvehicleRegistrationNo(i.getVehicleRegistrationNumber());
				LocationDTO dto = mapToDTO(i, reg);
				dtos.add(dto);
			}
		}

		for (EposTransaction t : eposTransactions) {
			if (t.getEposTerminal().getStreetName().equals(streetname)) {
				BankCard card = this.cardRepo.findAllByCardNumber(t.getBankCardNumber());
				PersonDTO dto = mapToPersonDTO(t, card, time1, time2);
				people.add(dto);
			} else if (calculateDistanceInKilometer(latitude, longitude, t.getEposTerminal().getLatitude(),
					t.getEposTerminal().getLongitude()) <= radius) {
				BankCard card = this.cardRepo.findAllByCardNumber(t.getBankCardNumber());
				PersonDTO dto = mapToPersonDTO(t, card, time1, time2);
				people.add(dto);
			}
		}

		return findAllInArea(dtos, people);
	}

	public LocationDTO mapToDTO(Observations obs, VehicleRegistration reg) {
		LocationDTO dto = new LocationDTO();

		dto.setTimestamp(obs.getTimestamp());
		dto.setForename(reg.getForenames());
		dto.setSurname(reg.getSurname());
		dto.setAddress(reg.getAddress());
		dto.setDateOfBirth(reg.getDateOfBirth());
		dto.setVehicleRegNo(reg.getVehicleRegistrationNo());
		dto.setStreetName(obs.getAnprLocal().getStreetName());
		dto.setLongitude(obs.getAnprLocal().getLongitude());
		dto.setLatitude(obs.getAnprLocal().getLatitude());

		return dto;
	}

	public int calculateDistanceInKilometer(double userLat, double userLng, double venueLat, double venueLng) {
		double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
		double latDistance = Math.toRadians(userLat - venueLat);
		double lngDistance = Math.toRadians(userLng - venueLng);

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(userLat))
				* Math.cos(Math.toRadians(venueLat)) * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
	}

	public PersonDTO mapToPersonDTO(EposTransaction t, BankCard card, LocalDateTime time1, LocalDateTime time2) {
		AccountHolder person = this.personRepo.findByBankAccountId(card.getBankAccountId());
		PersonDTO dto = new PersonDTO();
		dto.setForenames(person.getForenames());
		dto.setSurname(person.getSurname());
		dto.setHomeAddress(person.getHomeAddress());
		dto.setDateOfBirth(person.getDateOfBirth());
		dto.setTransaction(mapToTransactionsDTO(t));
		return dto;
	}

	public PersonDTO mapToPersonDTO(AtmTransaction t, BankCard card, LocalDateTime time1, LocalDateTime time2) {
		AccountHolder person = this.personRepo.findByBankAccountId(card.getBankAccountId());
		PersonDTO dto = new PersonDTO();
		dto.setForenames(person.getForenames());
		dto.setSurname(person.getSurname());
		dto.setHomeAddress(person.getHomeAddress());
		dto.setDateOfBirth(person.getDateOfBirth());
		dto.setTransaction(mapToWithdrawalsDTO(t));
		return dto;
	}

	public Set<TransactionsDTO> mapToTransactionsDTO(EposTransaction t) {

		Set<TransactionsDTO> dtos = new HashSet<>();

		TransactionsDTO dto = new TransactionsDTO();
		dto.setTimestamp(t.getTimestamp());
		dto.setLatitude(t.getEposTerminal().getLatitude());
		dto.setLongitude(t.getEposTerminal().getLongitude());
		dto.setStreetName(t.getEposTerminal().getStreetName());
		dto.setBankCardNumber(t.getBankCardNumber());
		dtos.add(dto);

		return dtos;
	}

	public Set<WithdrawalsDTO> mapToWithdrawalsDTO(AtmTransaction t) {

		Set<WithdrawalsDTO> dtos = new HashSet<>();

		WithdrawalsDTO dto = new WithdrawalsDTO();
		dto.setTimestamp(t.getTimestamp());
		dto.setLatitude(t.getAtm().getLatitude());
		dto.setLongitude(t.getAtm().getLongitude());
		dto.setStreetName(t.getAtm().getStreetName());
		dto.setBankCardNumber(t.getBankCardNumber());
		dtos.add(dto);

		return dtos;

	}

}
