package com.example.demo.shop.presentation;

import java.util.Optional;

import com.example.demo.common.auth.AuthContext;
import com.example.demo.common.utils.InputUtils;
import com.example.demo.member.domain.Member;
import com.example.demo.shop.domain.EquipmentType;

public class ShopFacadeController {

	private static final StringBuilder stringBuilder = new StringBuilder();

	private final OrderController orderController;
	private final ReviewController reviewController;

	public ShopFacadeController(OrderController orderController, ReviewController reviewController) {
		this.orderController = orderController;
		this.reviewController = reviewController;
	}

	public void process() {
		Optional<Member> currentMember = AuthContext.getInstance().getCurrentMember();
		if (currentMember.isEmpty()) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		while (true) {
			System.out.println("[1] 주문  [2] 리뷰  [0] 뒤로가기");

			System.out.print("선택 : ");
			int intInput = InputUtils.getIntInput(0, 2);

			switch (intInput) {
				case 1:
					processOrder();
					break;
				case 2:
					processReview(currentMember.get());
					break;
				case 0:
					return;
			}
		}
	}

	private void processReview(Member currentMember) {
		while (true) {
			System.out.println("[1] 리뷰작성및수정  [2] 내리뷰보기  [3] 전체리뷰보기  [0] 뒤로가기");
			System.out.print("선택 : ");
			int intInput = InputUtils.getIntInput(0, 3);

			switch (intInput) {
				case 1:
					reviewController.writeReview(currentMember);
					break;
				case 2:
					reviewController.showOwnReview(currentMember);
					break;
				case 3:
					reviewController.showAllReview();
					break;
				case 0:
					return;
			}
		}
	}

	private void processOrder() {
		while (true) {
			EquipmentType[] equipmentTypes = EquipmentType.getEquipmentTypes();
			for (int i = 0; i < equipmentTypes.length; i++) {
				stringBuilder.append("[")
					.append(i + 1)
					.append("] ")
					.append(equipmentTypes[i].getDescription())
					.append("  ");
			}
			stringBuilder.append("[4] 주문내역 보기  [0] 뒤로가기");
			System.out.println(stringBuilder);
			stringBuilder.setLength(0);

			System.out.print("선택 : ");
			int intInput = InputUtils.getIntInput(0, 4);
			switch (intInput) {
				case 1:
					orderController.createMonitorOrder();
					break;
				case 2:
					orderController.createLaptopOrder();
					break;
				case 3:
					orderController.createKeyboardOrder();
					break;
				case 4:
					orderController.viewMyOrders();
					break;
				case 0:
					return;
			}
		}
	}
}
