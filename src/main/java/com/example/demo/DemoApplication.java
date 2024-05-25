package com.example.demo;

import com.example.demo.async.AsyncConfig;
import com.example.demo.common.utils.InputUtils;
import com.example.demo.member.MemberContainer;
import com.example.demo.shop.ShopContainer;

public class DemoApplication {

	public static void main(String[] args) {
		AsyncConfig.enableAsync();

		while (true) {
			System.out.println("[1] 회원기능  [2] 상점기능  [0] 시스템종료");
			System.out.print("선택 : ");

			int intInput = InputUtils.getIntInput(0, 2);
			switch (intInput) {
				case 1 -> MemberContainer.memberFacadeController().process();
				case 2 -> ShopContainer.orderFacadeController().process();
				case 0 -> {
					System.out.println("바이바이");
					System.exit(0);
				}
			}
		}
	}
}
