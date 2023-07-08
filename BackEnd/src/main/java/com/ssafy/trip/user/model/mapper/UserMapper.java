package com.ssafy.trip.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.user.model.User;

@Mapper
public interface UserMapper {
	
	public User login(User user) throws SQLException;
	public User userInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
	//여기까지가 새롭게 추가한 부분
	
	User getUser(String id) throws SQLException;
	int registUser(User user) throws SQLException;
	int deleteUser(String id) throws SQLException;
	int updateUser(User user) throws SQLException;
	int changePass(User user) throws SQLException;
	int updatePathCnt(User user) throws SQLException;
	
	public String checkPass(User user) throws SQLException;
	public User userPassInfo(String userid) throws SQLException;
}
