package com.example.demo.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatUtils {

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String formatWithCommas(long number) {
		return String.format("%,d", number);
	}

	public static String formatDateTime(LocalDateTime time) {
		return time.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
	}
}
