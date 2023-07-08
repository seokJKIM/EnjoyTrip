package com.ssafy.trip.place.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.trip.place.model.GugunDto;
import com.ssafy.trip.place.model.Place;
import com.ssafy.trip.place.model.SidoDto;

public interface PlaceService {
	ArrayList<SidoDto> getSido() throws SQLException;
	ArrayList<GugunDto> getGugun(int sidoCode) throws SQLException;
	ArrayList<Place> searchPlace(int sido, int gugun, int type, String keyword) throws SQLException;
	Place getPlace(long id) throws SQLException;
}
