package com.turf.owner.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class TurfDetailsDTO {
	

	private Integer turfId;
	private String turfName;
	private String turfAddress;
	private List<String> availableSports; //cricket,football
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	
	
}
