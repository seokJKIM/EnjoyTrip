package com.ssafy.trip.board.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.board.model.SearchInfo;
import com.ssafy.trip.board.model.service.BoardService;

@CrossOrigin("*")
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private BoardService service;

	public BoardController(BoardService service) {
		super();
		this.service = service;
	}
	
	
	@PostMapping("/write")
	public String write(@RequestParam String boardid, Model model) {
		model.addAttribute("boardid", boardid);
		return "board/boardInsert";
	}
	
	@ResponseBody
	@PostMapping("/insert")
	public Board insert(@RequestBody Board board) throws SQLException {
		service.writeBoard(board);
		return board;
	}
	
//	@GetMapping("/modify")
//	public String modify(@RequestParam int num, Model model) throws SQLException {
//		Board b = service.searchBoard(num);
//		model.addAttribute("article", b);
//		return "board/boardModify";
//	}
	
	@ResponseBody
	@PutMapping("/modify")
	public Board modify(@RequestBody Board board) throws SQLException {
		service.updateBoard(board);
		return board;
	}
	@ResponseBody
	@GetMapping("/list")
	public List<Board> list(@RequestParam int boardId) throws SQLException{
		return service.selectBoard(boardId);
	}
	
	@ResponseBody
	@PostMapping("/search")
	public List<Board> list(@RequestBody SearchInfo searchInfo) throws SQLException{
		int boardId = searchInfo.getBoardId();
		int condition = searchInfo.getCondition();
		String keyword = searchInfo.getKeyword();
		return service.searchBoard(boardId, condition, keyword);
	}

	@ResponseBody
	@GetMapping("/mypage")
	public List<Board> list(@RequestParam String userId) throws SQLException{
		return service.searchBoardUser(userId);
	}
	
	@ResponseBody
	@DeleteMapping("/delete")
	public int delete(@RequestParam int num) throws SQLException{
		return service.deleteBoard(num);
	}
	
	@ResponseBody
	@GetMapping("/detail")
	public Board detail(@RequestParam int num) throws SQLException {
		Board b = service.searchBoard(num);
		int hits = b.getHits()+1;
		b.setHits(hits);
		service.updateHits(b);
		return service.searchBoard(num);
	}
	
}
