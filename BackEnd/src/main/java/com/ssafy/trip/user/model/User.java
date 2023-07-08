package com.ssafy.trip.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {
	private String userId, userName, userPass, userEmail, token;
	private Integer pathCnt;
}
