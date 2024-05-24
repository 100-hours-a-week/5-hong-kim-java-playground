package com.example.demo.shop.presentation;

import java.util.Optional;

import com.example.demo.common.auth.AuthContext;
import com.example.demo.common.utils.InputUtils;
import com.example.demo.member.domain.Member;
import com.example.demo.shop.domain.EquipmentType;

public class ShopFacadeController {

	private static final StringBuilder stringBuilder = new StringBuilder();

	private final OrderController orderController;

	public ShopFacadeController(OrderController orderController) {
		this.orderController = orderController;
	}

	public void process() {
		Optional<Member> currentMember = AuthContext.getInstance().getCurrentMember();
		if (currentMember.isEmpty()) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		StringBuilder stringBuilder = new StringBuilder();

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
