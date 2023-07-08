package com.ssafy.trip.alarm.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.alarm.model.Alarm;

public interface AlarmService {
	int insert(Alarm alarm) throws SQLException;

	int getCount(String userId) throws SQLException;

	int getNoteCount(String userId) throws SQLException;
	
	List<Alarm> selectAlarm(String userId) throws SQLException;

	int deleteCommentAlarm(String articleId) throws SQLException;

	int deleteNoteAlarm(String userid) throws SQLException;
}
