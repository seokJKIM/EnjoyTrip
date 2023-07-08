package com.ssafy.trip.alarm.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alarm {
	int num;
	int sep;
	Date createTime;
	String articleId;
	String userId;
}
