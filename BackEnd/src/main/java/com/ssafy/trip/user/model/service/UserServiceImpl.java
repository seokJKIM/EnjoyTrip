package com.ssafy.trip.user.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trip.user.model.User;
import com.ssafy.trip.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	UserMapper mapper;
	
	public UserServiceImpl(UserMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public User getUser(User user) throws SQLException {
		User u = mapper.getUser(user.getUserId());
		if(u != null && u.getUserPass().equals(user.getUserPass())) {
			return u;
		}
		else {
			return null;
		}
	}

	@Override
	public int registUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return mapper.registUser(user);
	}

	@Override
	public int deleteUser(User user) throws SQLException {
		return mapper.deleteUser(user.getUserId());
	}

	@Override
	public int updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return mapper.updateUser(user);
	}
	
	@Override
	public int updatePathCnt(User user) throws SQLException {
		return mapper.updatePathCnt(user);
	}
	
	@Override
	public int changePass(User user) throws SQLException {
		// TODO Auto-generated method stub
		return mapper.changePass(user);
	}
	
	@Override
	public User login(User user) throws Exception {
		if (user.getUserId() == null || user.getUserPass() == null)
			return null;
		return mapper.login(user);
	}

	@Override
	public User userInfo(String userid) throws Exception {
		return mapper.userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userid);
		map.put("token", refreshToken);
		mapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return mapper.getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userid);
		map.put("token", null);
		mapper.deleteRefreshToken(map);
	}

	@Override
	public User userPassInfo(String userid) throws SQLException {
		return mapper.userPassInfo(userid);
	}


}
