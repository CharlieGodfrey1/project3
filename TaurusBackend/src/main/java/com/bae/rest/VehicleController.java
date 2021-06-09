package com.bae.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bae.DTO.CombinationDTO;
import com.bae.DTO.FrontendDTO;
import com.bae.service.TimeService;
import com.bae.service.VehicleService;

@RestController
@CrossOrigin
public class VehicleController {
	private VehicleService service;
	private TimeService timeService;

	public VehicleController(VehicleService service, TimeService timeService) {
		super();
		this.service = service;
		this.timeService = timeService;
	}

	@GetMapping("/getSuspectInfo/{carReg}")
	public ResponseEntity<CombinationDTO> getSuspectInfo(@PathVariable String carReg) {
		return ResponseEntity.ok(this.service.showSusObs(carReg));
	}

	@GetMapping("/findAllInArea/{timestamp}/{streetname}/{timeframe}/{radius}")
	public ResponseEntity<FrontendDTO> findAllInArea(@PathVariable String timestamp, @PathVariable String streetname,
			@PathVariable int timeframe, @PathVariable int radius) {
		return ResponseEntity.ok(this.timeService.findByTime(timestamp, streetname, timeframe, radius));
	}

}
