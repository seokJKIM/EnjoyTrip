package com.ssafy.trip.place.model;

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
@ToString
public class Place {
	long id;
	int type;
	String title;
	String addr1;
	String first_image;
	int sidoCode;
	String overview;
	double latitude;
	double longitude;
}
