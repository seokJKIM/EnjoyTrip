package com.ssafy.trip.path.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.trip.path.model.Path;

public interface PathService {
	int insertPath(List<Path> routes) throws SQLException;
	List<Path> listPath(String userId) throws SQLException;
	int deletePath(String userId, int pathId) throws SQLException;
}
