package com.ssafy.trip.path.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.path.model.Path;
import com.ssafy.trip.path.model.service.PathService;
import com.ssafy.trip.place.model.Place;
import com.ssafy.trip.place.model.service.PlaceService;
import com.ssafy.trip.user.model.User;
import com.ssafy.trip.user.model.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/path")
public class PathController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	PathService pathService;
	@Autowired
	UserService userService;
	@Autowired
	PlaceService placeService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertPath(@RequestBody List<Path> routes, HttpServletRequest request) throws Exception{
		//1. 서버에 userId를 통해서 해당 user의 path_cnt 가져옴 - db
		int pathCnt = userService.userInfo(routes.get(0).getUserId()).getPathCnt();
		//2. 서버의 userId에 해당하는 해당 user의 path_cnt를 +1 update - db
		User u = User.builder().userId(routes.get(0).getUserId()).pathCnt(pathCnt+1).build();
		int result = userService.updatePathCnt(u);
		//3. 현재 routes를 돌면서 pathId를 path_cnt로 update
		for(int i=0; i<routes.size(); i++) {
			routes.get(i).setPathId(pathCnt);
		}
		//4. 이후 수정된 routes를 통해서 insert
		result = pathService.insertPath(routes);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(result==1) {
			status = HttpStatus.ACCEPTED;
			resultMap.put("message", SUCCESS);
		}else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			resultMap.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/list")
	public ResponseEntity<?> listPath(@RequestBody User user, HttpServletRequest request) throws Exception{
		//1. DB에 userId를 통해서 해당 user의 모든 path 가져옴
		List<Path> routes = pathService.listPath(user.getUserId());
		//2. routes를 pathId별로 따로 분리
		List<List<Path>> sortedRoutes = new ArrayList<>();
		List<List<Place>> sortedPlaces = new ArrayList<>();
		sortedRoutes.add(new ArrayList<>());
		sortedPlaces.add(new ArrayList<>());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(routes==null) {
			resultMap.put("message", "empty");
		}else {
			int pathCnt = routes.get(0).getPathId();
			for(int i=0; i<routes.size(); i++) {
				if(routes.get(i).getPathId()==pathCnt) {
					sortedRoutes.get(sortedRoutes.size()-1).add(routes.get(i));
					sortedPlaces.get(sortedPlaces.size()-1).add(placeService.getPlace(routes.get(i).getContentId()));
				}else {
					sortedRoutes.add(new ArrayList<Path>());
					sortedPlaces.add(new ArrayList<Place>());
					sortedRoutes.get(sortedRoutes.size()-1).add(routes.get(i));
					sortedPlaces.get(sortedPlaces.size()-1).add(placeService.getPlace(routes.get(i).getContentId()));
					pathCnt = routes.get(i).getPathId();
				}
			}
			resultMap.put("path", sortedRoutes);
			resultMap.put("info", sortedPlaces);
			resultMap.put("message", SUCCESS);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@DeleteMapping("/delete")
	public int delete(@RequestParam String userId, @RequestParam int pathId) throws SQLException{
		return pathService.deletePath(userId, pathId);
	}
	
}
