package com.ssafy.trip.place.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.place.model.GugunDto;
import com.ssafy.trip.place.model.Place;
import com.ssafy.trip.place.model.SidoDto;
import com.ssafy.trip.place.model.service.PlaceService;
import com.ssafy.trip.user.model.User;

@CrossOrigin("*")
@RestController
@RequestMapping("/place")
public class PlaceController {
	
	@Autowired
	PlaceService placeService;
	
	@GetMapping("/sido")
	public ArrayList<SidoDto> getSido() throws Exception {
		return placeService.getSido();
	}
	
	@GetMapping("/gugun")
	public ArrayList<GugunDto> getGugun(@RequestParam int code) throws Exception{
		return placeService.getGugun(code);
	}
	
	@GetMapping("/searchPlace")
	public ArrayList<Place> getPlace(@RequestParam int sidoCode, @RequestParam int gugunCode, @RequestParam int contentTypeId, @RequestParam String keyword) throws SQLException{
		return placeService.searchPlace(sidoCode, gugunCode, contentTypeId, keyword);
	}
	
}
