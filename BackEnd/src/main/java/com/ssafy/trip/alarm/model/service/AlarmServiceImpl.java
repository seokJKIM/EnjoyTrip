package com.ssafy.trip.alarm.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.alarm.model.Alarm;
import com.ssafy.trip.alarm.model.mapper.AlarmMapper;

@Service
public class AlarmServiceImpl implements AlarmService {

	AlarmMapper mapper;

	public AlarmServiceImpl(AlarmMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public int insert(Alarm alarm) throws SQLException {
		return mapper.insert(alarm);
	}

	public int getCount(String userId) throws SQLException {
		return mapper.getCount(userId);
	}

	@Override
	public List<Alarm> selectAlarm(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return mapper.selectAlarm(userId);
	}

	@Override
	public int deleteCommentAlarm(String articleId) throws SQLException {
		return mapper.deleteCommentAlarm(articleId);
	}

	@Override
	public int deleteNoteAlarm(String userid) throws SQLException {
		return mapper.deleteNoteAlarm(userid);
	}

	@Override
	public int getNoteCount(String userId) throws SQLException {
		return mapper.getNoteCount(userId);
	}


}
