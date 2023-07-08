package com.ssafy.trip.path.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.path.model.Path;
import com.ssafy.trip.path.model.mapper.PathMapper;

@Service
public class PathServiceImpl implements PathService{
	
	PathMapper mapper;
	
	@Autowired
	public PathServiceImpl(PathMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public int insertPath(List<Path> routes) throws SQLException {
		int result = 0;
		for(int i=0; i<routes.size(); i++) {
			result = mapper.insertPath(routes.get(i));
		}
		return result;
	}

	@Override
	public List<Path> listPath(String userId) throws SQLException {
		return mapper.listPath(userId);
	}

	@Override
	public int deletePath(String userId, int pathId) throws SQLException {
		Path path = Path.builder().userId(userId).pathId(pathId).build();
		return mapper.deletePath(path);
	}

}
