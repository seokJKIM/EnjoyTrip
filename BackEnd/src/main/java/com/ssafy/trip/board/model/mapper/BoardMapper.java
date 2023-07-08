package com.ssafy.trip.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.board.model.Board;

@Mapper
public interface BoardMapper {
	
	int insert(Board board) throws SQLException;
	
	List<Board> selectBoard(int boardId) throws SQLException;
	
	List<Board> searchBoardUser(String userId) throws SQLException;

	Board searchBoard(int num) throws SQLException;
	
	List<Board> searchBoardWithCondition(Map map) throws SQLException;
	
	int updateBoard(Board board) throws SQLException;
	
	int deleteBoard(int num) throws SQLException;
	
	int updateHits(Board board) throws SQLException;
}
