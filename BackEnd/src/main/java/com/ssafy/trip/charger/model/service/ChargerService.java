package com.ssafy.trip.charger.model.service;

import java.util.List;

import com.ssafy.trip.charger.model.ChargerDto;

public interface ChargerService {

	public List<ChargerDto> getCharger(Double lat, Double lng) throws Exception;
	
}
