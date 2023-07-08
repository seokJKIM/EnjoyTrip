package com.ssafy.trip.charger.model;

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
public class ChargerDto {
	String statId, chgerType, statNm, addr;
	double lat, lng;
}
