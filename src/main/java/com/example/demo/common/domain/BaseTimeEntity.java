package com.example.demo.common.domain;

import java.time.LocalDateTime;

import com.example.demo.common.utils.FormatUtils;

public class BaseTimeEntity {

	private LocalDateTime createdAt;

	public BaseTimeEntity(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedTimeToString() {
		return FormatUtils.formatDateTime(createdAt);
		// return createdAt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	}
}
