package com.ssafy.trip.comment.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
	int num;
	int articleno;
	int contentno;
	String writerid;
	String content;
	Date createtime;
}
