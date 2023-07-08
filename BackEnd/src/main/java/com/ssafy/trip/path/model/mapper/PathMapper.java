package com.ssafy.trip.path.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.path.model.Path;

@Mapper
public interface PathMapper {
	
	int insertPath(Path path) throws SQLException;
	List<Path> listPath(String userId) throws SQLException;
	int deletePath(Path path) throws SQLException;
	
}
