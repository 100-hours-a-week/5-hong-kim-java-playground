package com.example.demo.common.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTimeEntity {

	private LocalDateTime localDateTime;

	public BaseTimeEntity(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public String getDateTimeToString() {
		return localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	}
}
