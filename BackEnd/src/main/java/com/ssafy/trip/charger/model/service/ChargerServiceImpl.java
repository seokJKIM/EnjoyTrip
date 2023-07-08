package com.ssafy.trip.charger.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trip.charger.model.ChargerDto;
import com.ssafy.trip.charger.model.mapper.ChargerMapper;

@Service
public class ChargerServiceImpl implements ChargerService{
	
	ChargerMapper mapper;
	
	public ChargerServiceImpl(ChargerMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public List<ChargerDto> getCharger(Double lat, Double lng) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("latitude", lat);
		map.put("longitude", lng);		
		return mapper.getCharger(map);
	}

}
