package com.ssafy.trip.charger.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.charger.model.ChargerDto;

@Mapper
public interface ChargerMapper {
	
	List<ChargerDto> getCharger(Map map) throws SQLException;
	
}
