package com.ssafy.trip.charger.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.charger.model.ChargerDto;
import com.ssafy.trip.charger.model.service.ChargerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/charger")
public class ChargerController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private ChargerService service;

	public ChargerController(ChargerService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/list")
	public ResponseEntity<Map<String, Object>> getCharger(@RequestBody ChargerDto charger){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<ChargerDto> chargers = service.getCharger(charger.getLat(), charger.getLng());
			resultMap.put("chargers", chargers);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
