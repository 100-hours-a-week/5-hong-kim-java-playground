package com.example.demo.common.utils;

import java.util.Arrays;

public class EnumUtils {

	public static <T extends Enum<T>> T getTypeByInputString(Class<T> clazz) {
		while (true) {
			try {
				String input = InputUtils.getStringInput();

				return Arrays.stream(clazz.getEnumConstants())
					.filter(e -> e.name().equals(input))
					.findFirst()
					.orElseThrow(() -> new RuntimeException("없는 타입"));
			} catch (Exception ex) {
				System.out.print("다시 입력해주세요 : ");
			}
		}
	}
}

