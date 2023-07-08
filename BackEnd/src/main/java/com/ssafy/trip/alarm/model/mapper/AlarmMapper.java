package com.ssafy.trip.alarm.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.alarm.model.Alarm;

@Mapper
public interface AlarmMapper {
	
	int insert(Alarm alarm) throws SQLException;
	
	int getCount(String userId) throws SQLException;
	
	int getNoteCount(String userId) throws SQLException;
	
	List<Alarm> selectAlarm(String userId) throws SQLException;
	
	int deleteCommentAlarm(String articleId) throws SQLException;
	
	int deleteNoteAlarm(String userid) throws SQLException;

}
