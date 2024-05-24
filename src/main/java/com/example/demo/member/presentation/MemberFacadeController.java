package com.example.demo.member.presentation;

import java.util.Optional;

import com.example.demo.common.auth.AuthContext;
import com.example.demo.common.utils.InputUtils;
import com.example.demo.member.domain.Member;

public class MemberFacadeController {

	private final MemberController memberController;

	public MemberFacadeController(MemberController memberController) {
		this.memberController = memberController;
	}

	public void process() {
		while (true) {
			Optional<Member> currentMember = AuthContext.getInstance().getCurrentMember();
			displayMenu(currentMember.isPresent());

			int intInput = InputUtils.getIntInput(0, getMaxChoice(currentMember.isPresent()));
			switch (intInput) {
				case 1 -> memberController.signUp();
				case 2 -> memberController.login();
				case 3 -> memberController.getOwnInfo();
				case 4 -> memberController.recharge();
				case 0 -> {
					return;
				}
			}
		}
	}

	private void displayMenu(boolean isLoggedIn) {
		if (isLoggedIn) {
			System.out.println("[1] 회원가입  [2] 로그인  [3] 내정보보기  [4] 잔액충전  [0] 뒤로가기");
		} else {
			System.out.println("[1] 회원가입  [2] 로그인  [0] 뒤로가기");
		}
		System.out.print("선택 : ");
	}

	private int getMaxChoice(boolean isLoggedIn) {
		return isLoggedIn ? 4 : 2;
	}
}
