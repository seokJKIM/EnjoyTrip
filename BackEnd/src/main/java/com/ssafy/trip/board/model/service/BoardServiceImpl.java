package com.ssafy.trip.board.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	BoardMapper mapper;
	
	public BoardServiceImpl(BoardMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public int writeBoard(Board board) throws SQLException {
		return mapper.insert(board);
	}

	@Override
	public List<Board> selectBoard(int boardId) throws SQLException {
		return mapper.selectBoard(boardId);
	}

	@Override
	public Board searchBoard(int num) throws SQLException {
		return mapper.searchBoard(num);
	}

	@Override
	public List<Board> searchBoard(int boardId, int condition, String keyword) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardId", boardId);
		map.put("condition", condition);
		map.put("keyword", keyword);
		return mapper.searchBoardWithCondition(map);
	}
	
	@Override
	public int deleteBoard(int num) throws SQLException {
		return mapper.deleteBoard(num);
	}

	@Override
	public int updateBoard(Board board) throws SQLException {
		return mapper.updateBoard(board);
	}

	@Override
	public List<Board> searchBoardUser(String userId) throws SQLException {
		return mapper.searchBoardUser(userId);
	}

	@Override
	public int updateHits(Board board) throws SQLException {
		return mapper.updateHits(board);
	}

}
