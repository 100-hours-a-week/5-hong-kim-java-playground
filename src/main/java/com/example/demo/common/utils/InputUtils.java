package com.example.demo.common.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {

	private static final Scanner scanner = new Scanner(System.in);

	/**
	 * 사용자로부터 String 타입 입력값을 받는 함수
	 * <p>입력이 String 타입이 아닌 경우, 오류 메시지를 출력하고 다시 입력을 요청</p>
	 *
	 * @return 사용자가 입력한 정수
	 */
	public static String getStringInput() {
		return scanner.nextLine();
	}

	/**
	 * 사용자로부터 int 타입 입력값을 받는 함수
	 * <p>입력이 int 타입이 아닌 경우, 오류 메시지를 출력하고 다시 입력을 요청</p>
	 *
	 * @return 사용자가 입력한 정수
	 */
	public static int getIntInput() {
		while (true) {
			try {
				int input = scanner.nextInt();
				scanner.nextLine();
				return input;
			} catch (InputMismatchException ex) {
				scanner.nextLine();
				System.out.print("다시 입력해주세요: ");
			}
		}
	}

	/**
	 * 지정된 범위 내에서 사용자로부터 int 타입 입력값을 받는 함수
	 * <p>입력이 int 타입이 아니거나 범위 밖일 경우, 오류 메시지를 출력하고 다시 입력을 요청</p>
	 *
	 * @param min 입력 받을 범위의 최소값
	 * @param max 입력 받을 범위의 최대값
	 * @return 사용자가 입력한 정수
	 */
	public static int getIntInput(int min, int max) {
		while (true) {
			int inputValue = getIntInput();
			if (inputValue >= min && inputValue <= max) {
				return inputValue;
			}
			System.out.print("다시 입력해주세요: ");
		}
	}

	/**
	 * 사용자로부터 Long 타입 입력값을 받는 함수
	 * <p>입력이 Long 타입이 아닌 경우, 오류 메시지를 출력하고 다시 입력을 요청</p>
	 *
	 * @return 사용자가 입력한 정수 (Long)
	 */
	public static Long getLongInput() {
		while (true) {
			try {
				return scanner.nextLong();
			} catch (InputMismatchException ex) {
				scanner.nextLine();
				System.out.print("다시 입력해주세요: ");
			}
		}
	}
}
