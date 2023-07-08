package com.ssafy.trip.path.model;

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
public class Path {
	int sequence;
	Integer pathId;
	long contentId;
	String userId, title;
}
