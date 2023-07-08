package com.ssafy.trip.note.model;

import java.sql.Date;

import com.ssafy.trip.board.model.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
	int num;
	String title;
	String receiver;
	String sender;
	String content;
	Date createtime;
	int open;
}
