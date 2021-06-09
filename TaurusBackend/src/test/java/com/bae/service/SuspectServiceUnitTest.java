package com.bae.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.DTO.CombinationDTO;
import com.bae.DTO.FrontendDTO;
import com.bae.DTO.LocationDTO;
import com.bae.DTO.ObservationDTO;
import com.bae.DTO.PersonDTO;
import com.bae.DTO.SuspectDTO;
import com.bae.DTO.TransactionsDTO;
import com.bae.DTO.VehicleDTO;
import com.bae.DTO.WithdrawalsDTO;
import com.bae.domain.AccountHolder;
import com.bae.domain.AnprLocation;
import com.bae.domain.AtmPoint;
import com.bae.domain.AtmTransaction;
import com.bae.domain.BankCard;
import com.bae.domain.EposTerminal;
import com.bae.domain.EposTransaction;
import com.bae.domain.Observations;
import com.bae.domain.Vehicle;
import com.bae.domain.VehicleRegistration;
import com.bae.repo.AccountHolderRepo;
import com.bae.repo.CardRepo;
import com.bae.repo.ObservationRepo;
import com.bae.repo.TransactionsRepo;
import com.bae.repo.VehicleRegistrationRepo;
import com.bae.repo.VehicleRepo;
import com.bae.repo.WithdrawalsRepo;

@SpringBootTest
@ActiveProfiles("test")
public class SuspectServiceUnitTest {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private VehicleService service;

	@MockBean
	private VehicleRepo repo;

	@MockBean
	private VehicleRegistrationRepo regRepo;

	@MockBean
	private ObservationRepo obsRepo;

	@MockBean
	private TransactionsRepo transactionRepo;

	@MockBean
	private WithdrawalsRepo withdrawalsRepo;

	@MockBean
	private CardRepo cardRepo;

	@MockBean
	private AccountHolderRepo accountHolderRepo;

	@Autowired
	private TimeService timeService;

	@Test
	void testShowSusObvs() {
		AnprLocation testANPR = new AnprLocation(5538L, "Camden Road, A503", 51.54978729523243, -0.12798197853524934);
		testANPR.setAnprId(5538L);
		Observations testObvs = new Observations(5538L, LocalDateTime.parse("2015-05-01 09:08:51", formatter),
				"AI51 EYW", testANPR);
		Set<Observations> testObvsSet = Set.of(testObvs);
		VehicleRegistration testReg = new VehicleRegistration(131213L, LocalDate.of(2013, 1, 12), "AI51 EYW", "Ford",
				"Focus", "white", "Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				LocalDate.of(1990, 8, 26), "DONAL958270C99ZJ 12");
		Set<VehicleRegistration> testRegsSet = Set.of(testReg);
		Vehicle testVehicle = new Vehicle("AI51 EYW", "Ford", "Focus", "white", testRegsSet, testObvsSet);

		Mockito.when(this.repo.findByvehicleRegistrationNumber("AI51 EYW")).thenReturn(testVehicle);
		VehicleDTO vehicleDTO = new VehicleDTO("AI51 EYW", "Ford", "Focus", "white");
		Set<VehicleDTO> vehicleDTOs = Set.of(vehicleDTO);

		Mockito.when(this.regRepo.findBydriverLicenceId("DONAL958270C99ZJ 12")).thenReturn(testRegsSet);

		SuspectDTO testSuspectDTO = new SuspectDTO("Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				"DONAL958270C99ZJ 12", LocalDate.of(1990, 8, 26), vehicleDTOs);

		ObservationDTO testObservationDTO = new ObservationDTO("Camden Road, A503", 51.54978729523243,
				-0.12798197853524934, LocalDateTime.parse("2015-05-01 09:08:51", formatter), "AI51 EYW");
		Set<ObservationDTO> testObservationDTOs = Set.of(testObservationDTO);

		CombinationDTO testCombinationDTO = new CombinationDTO(testSuspectDTO, testObservationDTOs);
		assertThat(this.service.showSusObs("AI51 EYW")).isEqualTo(testCombinationDTO);
	}

	@Test
	void testShowSuspect() {
		AnprLocation testANPR = new AnprLocation(5538L, "Camden Road, A503", 51.54978729523243, -0.12798197853524934);
		Observations testObvs = new Observations(5538L, LocalDateTime.parse("2015-05-01 09:08:51", formatter),
				"AI51 EYW", testANPR);
		Set<Observations> testObvsSet = Set.of(testObvs);
		VehicleRegistration testReg = new VehicleRegistration(131213L, LocalDate.of(2013, 1, 12), "AI51 EYW", "Ford",
				"Focus", "white", "Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				LocalDate.of(1990, 8, 26), "DONAL958270C99ZJ 12");
		Set<VehicleRegistration> testRegsSet = Set.of(testReg);
		Vehicle testVehicle = new Vehicle("AI51 EYW", "Ford", "Focus", "white", testRegsSet, testObvsSet);

		Mockito.when(this.repo.findByvehicleRegistrationNumber("AI51 EYW")).thenReturn(testVehicle);

		VehicleDTO vehicleDTO = new VehicleDTO("AI51 EYW", "Ford", "Focus", "white");
		Set<VehicleDTO> vehicleDTOs = Set.of(vehicleDTO);

		Mockito.when(this.regRepo.findBydriverLicenceId("DONAL958270C99ZJ 12")).thenReturn(testRegsSet);

		SuspectDTO testSuspectDTO = new SuspectDTO("Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				"DONAL958270C99ZJ 12", LocalDate.of(1990, 8, 26), vehicleDTOs);

		assertThat(this.service.showSuspect("AI51 EYW")).isEqualTo(testSuspectDTO);
	}

	@Test
	void testShowObvs() {
		AnprLocation testANPR = new AnprLocation(5538L, "Camden Road, A503", 51.54978729523243, -0.12798197853524934);
		Observations testObvs = new Observations(5538L, LocalDateTime.parse("2015-05-01 09:08:51", formatter),
				"AI51 EYW", testANPR);
		Set<Observations> testObvsSet = Set.of(testObvs);

		VehicleRegistration testReg = new VehicleRegistration(131213L, LocalDate.of(2013, 1, 12), "AI51 EYW", "Ford",
				"Focus", "white", "Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				LocalDate.of(1990, 8, 26), "DONAL958270C99ZJ 12");
		Set<VehicleRegistration> testRegsSet = Set.of(testReg);

		Vehicle testVehicle = new Vehicle("AI51 EYW", "Ford", "Focus", "white", testRegsSet, testObvsSet);

		Mockito.when(this.repo.findByvehicleRegistrationNumber("AI51 EYW")).thenReturn(testVehicle);

		ObservationDTO testObvsDTO = new ObservationDTO("Camden Road, A503", 51.54978729523243, -0.12798197853524934,
				LocalDateTime.parse("2015-05-01 09:08:51", formatter), "AI51 EYW");
		Set<ObservationDTO> returnDTOs = Set.of(testObvsDTO);

		assertThat(this.service.showObs("AI51 EYW")).isEqualTo(returnDTOs);
	}

	@Test
	void testFindByvehicleRegistrationNumber() {
		Vehicle testVehicle = new Vehicle("AI51 EYW", "Ford", "Focus", "white");
		Mockito.when(this.repo.findByvehicleRegistrationNumber("AI51 EYW")).thenReturn(testVehicle);
		assertThat(this.repo.findByvehicleRegistrationNumber("AI51 EYW")).isEqualTo(testVehicle);
	}

	@Test
	void testFindBydriverLicenceId() {
		VehicleRegistration testReg = new VehicleRegistration(131213L, LocalDate.of(2013, 1, 12), "AI51 EYW", "Ford",
				"Focus", "white", "Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				LocalDate.of(1990, 8, 26), "DONAL958270C99ZJ 12");
		Set<VehicleRegistration> testRegsSet = Set.of(testReg);
		Mockito.when(this.regRepo.findBydriverLicenceId("DONAL958270C99ZJ 12")).thenReturn(testRegsSet);
		assertThat(this.regRepo.findBydriverLicenceId("DONAL958270C99ZJ 12")).isEqualTo(testRegsSet);
	}

	@Test
	void testFindAllVehicles() {
		VehicleRegistration testReg = new VehicleRegistration(131213L, LocalDate.of(2013, 1, 12), "AI51 EYW", "Ford",
				"Focus", "white", "Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				LocalDate.of(1990, 8, 26), "DONAL958270C99ZJ 12");
		Set<VehicleRegistration> testRegsSet = Set.of(testReg);

		Mockito.when(this.regRepo.findBydriverLicenceId("DONAL958270C99ZJ 12")).thenReturn(testRegsSet);

		VehicleDTO vehicleDTO = new VehicleDTO("AI51 EYW", "Ford", "Focus", "white");
		Set<VehicleDTO> vehicleDTOs = Set.of(vehicleDTO);

		assertThat(this.service.findAllVehicles("DONAL958270C99ZJ 12")).isEqualTo(vehicleDTOs);
	}

	@Test
	void testVehicleDoesNotExist() throws IllegalArgumentException {
		Mockito.when(this.repo.findByvehicleRegistrationNumber("1")).thenThrow(IllegalArgumentException.class);
		assertThrows(IllegalArgumentException.class, () -> service.showSuspect("1"));
	}

//	@Test
//	void testGetByTime() {
//		LocalDateTime time1 = LocalDateTime.parse("2015-05-01 09:08:51", formatter);
//		LocalDateTime time2 = LocalDateTime.parse("2015-05-01 09:08:51", formatter);
//		AnprLocation testANPR = new AnprLocation(5538L, "Camden Road, A503", 51.54978729523243, -0.12798197853524934);
//		Observations testObvs = new Observations(5538L, LocalDateTime.parse("2015-05-01 09:08:51", formatter),
//				"AI51 EYW", testANPR);
//		Set<Observations> testObvsSet = Set.of(testObvs);
//
//		VehicleRegistration testReg = new VehicleRegistration(131213L, LocalDate.of(2013, 1, 12), "AI51 EYW", "Ford",
//				"Focus", "white", "Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
//				LocalDate.of(1990, 8, 27), "DONAL958270C99ZJ 12");
//
//		Mockito.when(this.obsRepo.findAllBytimestampBetweenAndAnprPointIdGreaterThan(time1, time2, 0L))
//				.thenReturn(testObvsSet);
//		Mockito.when(this.regRepo.findByvehicleRegistrationNo("AI51 EYW")).thenReturn(testReg);
//
//		LocationDTO location = new LocationDTO("Camden Road, A503", 51.54978729523243, -0.12798197853524934,
//				LocalDateTime.parse("2015-05-01 09:08:51", formatter), "Christine", "Donald",
//				"17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ", LocalDate.of(1990, 8, 27), "AI51 EYW");
//		Set<LocationDTO> setLocation = Set.of(location);
//
//		assertThat(timeService.findByTime("2015-05-01 09:08:51", "Camden Road, A503", 0, 5)).isEqualTo(setLocation);
//
//	}

	@Test
	void testFindAllInAreaEpos() {
		LocalDateTime time1 = LocalDateTime.parse("2015-05-01 09:08:51", formatter);
		LocalDateTime time2 = LocalDateTime.parse("2015-05-01 09:08:51", formatter);
		AnprLocation testANPR = new AnprLocation(5538L, "Camden Road, A503", 51.54978729523243, -0.12798197853524934);
		Observations testObvs = new Observations(5538L, LocalDateTime.parse("2015-05-01 09:08:51", formatter),
				"AI51 EYW", testANPR);
		Set<Observations> testObvsSet = Set.of(testObvs);

		Mockito.when(this.obsRepo.findAllBytimestampBetweenAndAnprPointIdGreaterThan(time1, time2, 0L))
				.thenReturn(testObvsSet);

		VehicleRegistration testReg = new VehicleRegistration(131213L, LocalDate.of(2013, 1, 12), "AI51 EYW", "Ford",
				"Focus", "white", "Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				LocalDate.of(1990, 8, 27), "DONAL958270C99ZJ 12");

		Mockito.when(this.regRepo.findByvehicleRegistrationNo("AI51 EYW")).thenReturn(testReg);

		LocationDTO location = new LocationDTO("Camden Road, A503", 51.54978729523243, -0.12798197853524934,
				LocalDateTime.parse("2015-05-01 09:08:51", formatter), "Christine", "Donald",
				"17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ", LocalDate.of(1990, 8, 27), "AI51 EYW");
		Set<LocationDTO> setLocation = Set.of(location);

		EposTerminal terminal = new EposTerminal(12345L, "Test Vendor", "Camden Road, A503", "Test Postcode",
				51.54978729523243, -0.12798197853524934);

		EposTransaction transaction = new EposTransaction(LocalDateTime.parse("2015-05-01 09:08:51", formatter), 12345L,
				12345L, 12345L, 50.34, terminal);
		Set<EposTransaction> setTransactions = Set.of(transaction);

		Mockito.when(this.transactionRepo.findAllBytimestampBetweenAndEposIdGreaterThan(time1, time2, 0L))
				.thenReturn(setTransactions);

		BankCard card = new BankCard(12345L, 12345L, "12-34-56", 12345L, 12345L, "Test Bank");

		Mockito.when(this.cardRepo.findAllByCardNumber(12345L)).thenReturn(card);

		AccountHolder person = new AccountHolder(12345L, 12345L, "Test Bank", "Christine", "Donald", "1990-8-27",
				"17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ");

		Mockito.when(this.accountHolderRepo.findByBankAccountId(12345L)).thenReturn(person);

		TransactionsDTO transactionDTO = new TransactionsDTO("Camden Road, A503", 51.54978729523243,
				-0.12798197853524934, LocalDateTime.parse("2015-05-01 09:08:51", formatter), 12345L);
		Set<TransactionsDTO> setTransactionDTO = Set.of(transactionDTO);

		PersonDTO personDTO = new PersonDTO("Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				"1990-8-27", setTransactionDTO);
		Set<PersonDTO> transactions = Set.of(personDTO);

		FrontendDTO dto = new FrontendDTO(setLocation, transactions);

		assertThat(timeService.findByTime("2015-05-01 09:08:51", "Camden Road, A503", 0, 5)).isEqualTo(dto);
	}

	@Test
	void testFindAllInAreaAtm() {
		LocalDateTime time1 = LocalDateTime.parse("2015-05-01 09:08:51", formatter);
		LocalDateTime time2 = LocalDateTime.parse("2015-05-01 09:08:51", formatter);
		AnprLocation testANPR = new AnprLocation(5538L, "Camden Road, A503", 51.54978729523243, -0.12798197853524934);
		Observations testObvs = new Observations(5538L, LocalDateTime.parse("2015-05-01 09:08:51", formatter),
				"AI51 EYW", testANPR);
		Set<Observations> testObvsSet = Set.of(testObvs);

		Mockito.when(this.obsRepo.findAllBytimestampBetweenAndAnprPointIdGreaterThan(time1, time2, 0L))
				.thenReturn(testObvsSet);

		VehicleRegistration testReg = new VehicleRegistration(131213L, LocalDate.of(2013, 1, 12), "AI51 EYW", "Ford",
				"Focus", "white", "Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				LocalDate.of(1990, 8, 27), "DONAL958270C99ZJ 12");

		Mockito.when(this.regRepo.findByvehicleRegistrationNo("AI51 EYW")).thenReturn(testReg);

		LocationDTO location = new LocationDTO("Camden Road, A503", 51.54978729523243, -0.12798197853524934,
				LocalDateTime.parse("2015-05-01 09:08:51", formatter), "Christine", "Donald",
				"17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ", LocalDate.of(1990, 8, 27), "AI51 EYW");
		Set<LocationDTO> setLocation = Set.of(location);

		AtmPoint terminal = new AtmPoint(12345L, "Test Operator", "Camden Road, A503", "Test Postcode",
				51.54978729523243, -0.12798197853524934);

		AtmTransaction transaction = new AtmTransaction(12345L, LocalDateTime.parse("2015-05-01 09:08:51", formatter),
				12345L, 12345L, "Withdrawal", 50.34, terminal);
		Set<AtmTransaction> setTransactions = Set.of(transaction);

		Mockito.when(this.withdrawalsRepo.findAllBytimestampBetweenAndAtmIdGreaterThan(time1, time2, 0L))
				.thenReturn(setTransactions);

		BankCard card = new BankCard(12345L, 12345L, "12-34-56", 12345L, 12345L, "Test Bank");

		Mockito.when(this.cardRepo.findAllByCardNumber(12345L)).thenReturn(card);

		AccountHolder person = new AccountHolder(12345L, 12345L, "Test Bank", "Christine", "Donald", "1990-8-27",
				"17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ");

		Mockito.when(this.accountHolderRepo.findByBankAccountId(12345L)).thenReturn(person);

		WithdrawalsDTO withdrawalDTO = new WithdrawalsDTO("Camden Road, A503", 51.54978729523243, -0.12798197853524934,
				LocalDateTime.parse("2015-05-01 09:08:51", formatter), 12345L);
		Set<WithdrawalsDTO> setWithdrawalDTO = Set.of(withdrawalDTO);

		PersonDTO personDTO = new PersonDTO("Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				"1990-8-27", setWithdrawalDTO);
		Set<PersonDTO> transactions = Set.of(personDTO);

		FrontendDTO dto = new FrontendDTO(setLocation, transactions);

		assertThat(timeService.findByTime("2015-05-01 09:08:51", "Camden Road, A503", 0, 5)).isEqualTo(dto);
	}

	@Test
	void gettersAndSetters() {
		AnprLocation testANPR = new AnprLocation();
		testANPR.setAnprId(5538L);
		testANPR.setStreetName("Camden Road, A503");
		testANPR.setLongitude(51.54978729523243);
		testANPR.setLatitude(-0.12798197853524934);

		Observations testObvs = new Observations();
		testObvs.setAnprPointId(5538L);
		testObvs.setTimestamp(LocalDateTime.parse("2015-05-01 09:08:51", formatter));
		testObvs.setVehicleRegistrationNumber("AI51 EYW");
		testObvs.setAnprLocal(testANPR);

		Set<Observations> testObvsSet = Set.of(testObvs);

		VehicleRegistration testReg = new VehicleRegistration();
		testReg.setRegistrationID(131213L);
		testReg.setDateOfBirth(LocalDate.of(2013, 1, 12));
		testReg.setVehicleRegistrationNo("AI51 EYW");
		testReg.setMake("Ford");
		testReg.setModel("Focus");
		testReg.setColour("white");
		testReg.setForenames("Christine");
		testReg.setSurname("Donald");
		testReg.setAddress("17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ");
		testReg.setDateOfBirth(LocalDate.of(1990, 8, 26));
		testReg.setDriverLicenceID("DONAL958270C99ZJ 12");

		Set<VehicleRegistration> testRegsSet = Set.of(testReg);

		Vehicle testVehicle = new Vehicle();

		testVehicle.setMake("AI51 EYW");
		testVehicle.setMake("Ford");
		testVehicle.setModel("Focus");
		testVehicle.setVehicleRegs(testRegsSet);
		testVehicle.setObservations(testObvsSet);

		VehicleDTO vehicleDTO = new VehicleDTO("AI51 EYW", "Ford", "Focus", "white");

		vehicleDTO.setVehicleRegistrationNumber("AI51 EYW");
		vehicleDTO.setMake("Ford");
		vehicleDTO.setModel("Focus");
		vehicleDTO.setColour("white");

		Set<VehicleDTO> vehicleDTOs = Set.of(vehicleDTO);

		SuspectDTO testSuspectDTO = new SuspectDTO("Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				"DONAL958270C99ZJ 12", LocalDate.of(1990, 8, 26), vehicleDTOs);

		testSuspectDTO.setVehicles(vehicleDTOs);
		testSuspectDTO.setForenames("Christine");
		testSuspectDTO.setSurname("Donald");
		testSuspectDTO.setAddress("17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ");
		testSuspectDTO.setDriverLicenceId("DONAL958270C99ZJ 12");
		testSuspectDTO.setDateOfBirth(LocalDate.of(1999, 8, 26));

		ObservationDTO testObservationDTO = new ObservationDTO("Camden Road, A503", 51.54978729523243,
				-0.12798197853524934, LocalDateTime.parse("2015-05-01 09:08:51", formatter), "AI51 EYW");

		testObservationDTO.setStreetName("Camden Road, A503");
		testObservationDTO.setLongitude(-0.12798197853524934);
		testObservationDTO.setLatitude(51.54978729523243);
		testObservationDTO.setTimestamp(LocalDateTime.parse("2015-05-01 09:08:51", formatter));
		testObservationDTO.setVehicleRegistrationNumber("AI51 EYW");

		assertThat(testObservationDTO).isEqualTo(new ObservationDTO("Camden Road, A503", 51.54978729523243,
				-0.12798197853524934, LocalDateTime.parse("2015-05-01 09:08:51", formatter), "AI51 EYW"));

	}

	@Test
	void testTransactionGettersAndSetters() {
		EposTerminal eposTerminal = new EposTerminal();
		eposTerminal.setId(1234L);
		eposTerminal.setLatitude(51.54978729523243);
		eposTerminal.setLongitude(-0.12798197853524934);
		eposTerminal.setPostcode("NW1 2TH");
		eposTerminal.setStreetName("Camden Road, A503");
		eposTerminal.setVendor("Test vendor");

		EposTransaction transaction = new EposTransaction();
		transaction.setAccountNumber(1234L);
		transaction.setAmount(50.34);
		transaction.setBankCardNumber(1234L);
		transaction.setCardNumber(1234L);
		transaction.setCardNumber(1234L);
		transaction.setEposId(1234L);
		transaction.setEposTerminal(eposTerminal);
		transaction.setPayeeAccount(1234L);
		transaction.setTimestamp(LocalDateTime.parse("2015-05-01 09:08:51", formatter));
		Set<EposTransaction> transactions = Set.of(transaction);
		eposTerminal.setTransactions(transactions);

		AccountHolder person = new AccountHolder();
		person.setAccountId(1234L);
		person.setAccountNumber(1234L);
		person.setBank("Test bank");
		person.setDateOfBirth("12-03-1990");
		person.setForenames("Christine");
		person.setSurname("Donald");
		person.setHomeAddress("17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ");

		AtmPoint atmPoint = new AtmPoint();
		atmPoint.setId(1234L);
		atmPoint.setLatitude(51.54978729523243);
		atmPoint.setLongitude(-0.12798197853524934);
		atmPoint.setOperator("Test operator");
		atmPoint.setPostcode("NW1 2TH");
		atmPoint.setStreetName("17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ");

		AtmTransaction atmTransaction = new AtmTransaction();
		atmTransaction.setAmount(50.34);
		atmTransaction.setAtm(atmPoint);
		atmTransaction.setAtmId(1234L);
		atmTransaction.setBankCardNumber(1234L);
		atmTransaction.setId(1234L);
		atmTransaction.setTimestamp(LocalDateTime.parse("2015-05-01 09:08:51", formatter));
		atmTransaction.setType("withdrawal");

		BankCard card = new BankCard();
		card.setAccountId(1234L);
		card.setAccountNumber(1234L);
		card.setBank("Test bank");
		card.setBankAccountId(1234L);
		card.setBankCardId(1234L);
		card.setCardId(1234L);
		card.setCardNumber(1234L);
		card.setPerson(person);
		card.setSortCode("12-32-43");
		card.setTransactions(transactions);

		TransactionsDTO transactionDTO = new TransactionsDTO();
		transactionDTO.setBankCardNumber(1234L);
		transactionDTO.setLatitude(51.54978729523243);
		transactionDTO.setLongitude(-0.12798197853524934);
		transactionDTO.setStreetName("Camden Road, A503");
		transactionDTO.setTimestamp(LocalDateTime.parse("2015-05-01 09:08:51", formatter));
		Set<TransactionsDTO> transactionDTOs = Set.of(transactionDTO);

		WithdrawalsDTO withdrawalDTO = new WithdrawalsDTO();
		withdrawalDTO.setBankCardNumber(1234L);
		withdrawalDTO.setLatitude(51.54978729523243);
		withdrawalDTO.setLongitude(-0.12798197853524934);
		withdrawalDTO.setStreetName("Camden Road, A503");
		withdrawalDTO.setTimestamp(LocalDateTime.parse("2015-05-01 09:08:51", formatter));

		PersonDTO personDTO = new PersonDTO();
		personDTO.setDateOfBirth("26-08-1990");
		personDTO.setForenames("Christine");
		personDTO.setSurname("Donald");
		personDTO.setHomeAddress("17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ");
		personDTO.setTransaction(transactionDTOs);

		assertThat(transactionDTO).isEqualTo(new TransactionsDTO("Camden Road, A503", 51.54978729523243,
				-0.12798197853524934, LocalDateTime.parse("2015-05-01 09:08:51", formatter), 1234L));

	}
}
