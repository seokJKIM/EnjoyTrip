package com.ssafy.trip.place.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.place.model.GugunDto;
import com.ssafy.trip.place.model.Place;
import com.ssafy.trip.place.model.SidoDto;

@Mapper
public interface PlaceMapper {
	ArrayList<SidoDto> getSido() throws SQLException;
	ArrayList<GugunDto> getGugun(int sido) throws SQLException;
	ArrayList<Place> searchPlace(Map<String, Object> map) throws SQLException;
	Place getPlace(long id) throws SQLException;
}
