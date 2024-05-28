package com.example.demo.member.presentation;

import com.example.demo.common.utils.FormatUtils;
import com.example.demo.common.utils.InputUtils;
import com.example.demo.member.application.MemberService;
import com.example.demo.member.application.dto.OwnInfoResponse;

public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	public void signUp() {
		while (true) {
			try {
				System.out.print("사용하실 아이디를 입력해주세요 : ");
				String username = InputUtils.getStringInput();
				System.out.print("사용하실 비밀번호를 입력해주세요 : ");
				String password = InputUtils.getStringInput();
				System.out.print("가입하실 권한을 입력해주세요 (고객/관리자) : ");
				String memberType = InputUtils.getStringInput();

				memberService.createNewMember(username, password, memberType);

				System.out.println("회원가입 성공!");
				break;
			} catch (RuntimeException ex) {
				System.out.println("회원가입에 실패했습니다.\n뒤로 가시겠습니까?");
				System.out.print("[0] 예  [1] 아니오 :");

				int choice = InputUtils.getIntInput(0, 1);
				if (choice == 0) {
					break;
				}
			}
		}
	}

	public void login() {
		while (true) {
			try {
				System.out.print("아이디를 입력해주세요 : ");
				String username = InputUtils.getStringInput();
				System.out.print("비밀번호를 입력해주세요 : ");
				String password = InputUtils.getStringInput();

				memberService.loginMember(username, password);

				System.out.println(username + "님 반갑습니다");
				break;
			} catch (RuntimeException ex) {
				System.out.println("로그인에 실패했습니다.\n뒤로 가시겠습니까?");
				System.out.print("[0] 예  [1] 아니오 : ");

				int choice = InputUtils.getIntInput(0, 1);
				if (choice == 0) {
					break;
				}
			}
		}
	}

	public void getOwnInfo() {
		StringBuilder stringBuilder = new StringBuilder();

		OwnInfoResponse currentMemberInfo = memberService.getCurrentMemberInfo();
		stringBuilder.append(currentMemberInfo.username())
			.append("님의 정보입니다")
			.append("\n| >> 생성일 : ")
			.append(currentMemberInfo.createdAt())
			.append("\n| >> 잔액 : ")
			.append(FormatUtils.formatWithCommas(currentMemberInfo.balance()));

		System.out.println(stringBuilder);
		stringBuilder.setLength(0);
	}

	public void recharge() {
		System.out.print("충전할 금액을 입력해주세요 : ");
		Long balance = InputUtils.getLongInput();

		memberService.rechargeBalance(balance);

		Long nowBalance = memberService.getCurrentMemberBalance();
		System.out.println("충전 후 잔액은 " + nowBalance + "원 입니다.");
	}
}
