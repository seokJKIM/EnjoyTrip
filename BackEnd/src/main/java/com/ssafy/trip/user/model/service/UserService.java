package com.ssafy.trip.user.model.service;

import java.sql.SQLException;

import com.ssafy.trip.user.model.User;


public interface UserService {
	
	public User login(User user) throws Exception;
	
	public User userInfo(String userid) throws Exception;
	
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	
	public Object getRefreshToken(String userid) throws Exception;
	
	public void deleRefreshToken(String userid) throws Exception;
	
	//여기까지가 새롭게 추가
	
	
	public User getUser(User user) throws SQLException;

	public int registUser(User user) throws SQLException;

	public int deleteUser(User user) throws SQLException;

	public int updateUser(User user) throws SQLException;
	
	public int changePass(User user) throws SQLException;
	
	public int updatePathCnt(User user) throws SQLException;

	public User userPassInfo(String userid) throws SQLException;
}
