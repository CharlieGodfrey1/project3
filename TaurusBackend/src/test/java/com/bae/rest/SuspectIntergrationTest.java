package com.bae.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.DTO.CombinationDTO;
import com.bae.DTO.FrontendDTO;
import com.bae.DTO.LocationDTO;
import com.bae.DTO.ObservationDTO;
import com.bae.DTO.PersonDTO;
import com.bae.DTO.SuspectDTO;
import com.bae.DTO.TransactionsDTO;
import com.bae.DTO.VehicleDTO;
import com.bae.repo.TransactionsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:suspect-schema.sql",
		"classpath:suspect-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class SuspectIntergrationTest {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private MockMvc mock;
	@Autowired
	private ObjectMapper mapper;

	@Test
	void testRead() throws Exception {
		// Create suspect DTO
		VehicleDTO vehicle = new VehicleDTO("AI51 EYW", "Ford", "Focus", "white");

		Set<VehicleDTO> vehicles = Set.of(vehicle);
		SuspectDTO suspect = new SuspectDTO("Christine", "Donald", "17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ",
				"DONAL958270C99ZJ 12", LocalDate.of(1990, 8, 27), vehicles);

		ObservationDTO obs = new ObservationDTO("Camden Road, A503", 51.54978729523243, -0.12798197853524934,
				LocalDateTime.parse("2015-05-01 09:08:51", formatter), "AI51 EYW");
		ObservationDTO obs2 = new ObservationDTO("M65", 53.71601366362006, -2.570011337921277,
				LocalDateTime.parse("2015-05-01 09:08:52", formatter), "AI51 EYW");

		Set<ObservationDTO> setObs = Set.of(obs, obs2);

		CombinationDTO comDTO = new CombinationDTO(suspect, setObs);

		String suspectAsJson = mapper.writeValueAsString(comDTO);
		// Run get request with the REG

		RequestBuilder mockRequest = get("/getSuspectInfo/" + "AI51 EYW");

		// Check status

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkContent = content().json(suspectAsJson);

		// Check response body is as expected

		mock.perform(mockRequest).andExpect(checkStatus).andExpect(checkContent);

	}

	@Autowired
	private TransactionsRepo repo;

	@Test
	void testGetFromTime() throws Exception {
		System.out.println("=======================================================");
		System.out.println(repo.findAll());
		LocationDTO location = new LocationDTO("Camden Road, A503", 51.54978729523243, -0.12798197853524934,
				LocalDateTime.parse("2015-05-01 09:08:51", formatter), "Christine", "Donald",
				"17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ", LocalDate.of(1990, 8, 27), "AI51 EYW");
		Set<LocationDTO> locationSet = Set.of(location);

		TransactionsDTO transactions = new TransactionsDTO("Camden Road, A503", 51.54978729523243, -0.12798197853524934,
				LocalDateTime.parse("2015-05-01 09:10:00", formatter), 2944149858882718L);
		Set<TransactionsDTO> transactionSet = Set.of(transactions);

		PersonDTO person = new PersonDTO("Ryan", "Forsyth", "53 RIPON HALL AVENUE, BURY, BL0 9RE", "1946-01-29",
				transactionSet);

		Set<PersonDTO> setPerson = Set.of(person);

		FrontendDTO combined = new FrontendDTO(locationSet, setPerson);

		String locationAsJson = mapper.writeValueAsString(combined);

		RequestBuilder mockRequest = get("/findAllInArea/2015-05-01 09:08:00/Camden Road, A503/10/10");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkContent = content().json(locationAsJson);

		mock.perform(mockRequest).andExpect(checkStatus).andExpect(checkContent);

	}

}
