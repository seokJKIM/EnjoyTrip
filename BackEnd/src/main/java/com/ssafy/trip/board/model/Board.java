package com.ssafy.trip.board.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
	int num;
	Date createTime;
	String title;
	String content;
	String writerId;
	int hits;
	int boardId;
}
