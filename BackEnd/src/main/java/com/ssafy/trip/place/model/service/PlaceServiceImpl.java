package com.ssafy.trip.place.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.place.model.GugunDto;
import com.ssafy.trip.place.model.Place;
import com.ssafy.trip.place.model.SidoDto;
import com.ssafy.trip.place.model.mapper.PlaceMapper;

@Service
public class PlaceServiceImpl implements PlaceService{

	PlaceMapper mapper;
	
	@Autowired
	public PlaceServiceImpl(PlaceMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public ArrayList<SidoDto> getSido() throws SQLException {
		return mapper.getSido();
	}

	@Override
	public ArrayList<GugunDto> getGugun(int sidoCode) throws SQLException {
		return mapper.getGugun(sidoCode);
	}

	@Override
	public ArrayList<Place> searchPlace(int sido, int gugun, int type, String keyword) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("sidoCode", sido);
		map.put("gugunCode", gugun);
		map.put("type", type);
		map.put("keyword", keyword);
		
		return mapper.searchPlace(map);
	}

	@Override
	public Place getPlace(long id) throws SQLException {
		return mapper.getPlace(id);
	}

}
