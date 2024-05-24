package com.example.demo.shop.presentation;

import java.util.List;

import com.example.demo.common.utils.EnumUtils;
import com.example.demo.common.utils.FormatUtils;
import com.example.demo.common.utils.InputUtils;
import com.example.demo.member.application.MemberService;
import com.example.demo.member.domain.Member;
import com.example.demo.shop.application.OrderService;
import com.example.demo.shop.application.dto.OrderInfoResponse;
import com.example.demo.shop.domain.Order;
import com.example.demo.shop.domain.keyboard.Keyboard;
import com.example.demo.shop.domain.keyboard.LayoutType;
import com.example.demo.shop.domain.laptop.Laptop;
import com.example.demo.shop.domain.laptop.OSType;
import com.example.demo.shop.domain.monitor.Monitor;
import com.example.demo.shop.domain.monitor.MonitorType;
import com.example.demo.shop.exception.OrderException;

public class OrderController {

	private final OrderService orderService;
	private final MemberService memberService;

	public OrderController(OrderService orderService, MemberService memberService) {
		this.orderService = orderService;
		this.memberService = memberService;
	}

	public void viewMyOrders(Member loginMember) {
		String username = loginMember.getUsername();

		try {
			List<OrderInfoResponse> orderListByMember = orderService.getOrderListByMember(loginMember);

			System.out.println(username + "님의 주문 내역");
			for (OrderInfoResponse order : orderListByMember) {
				System.out.println("| >> 상품명 : " + order.itemName());
				System.out.println("| >> 주문일 : " + order.orderDate());
				System.out.println("| >> 배송지 : " + order.location());
				System.out.println("| >> 상품 가격 : " + FormatUtils.formatWithCommas(order.price()));
				System.out.println("| >> 선택 수량 : " + FormatUtils.formatWithCommas(order.quantity()));
				System.out.println("| >> 결제 금액 : " + FormatUtils.formatWithCommas(order.totalPrice()));
				System.out.println("| ---------------------------");
			}
		} catch (OrderException ex) {
			System.out.println(username + "님의 주문 내역이 없습니다");
		}
	}

	public void createMonitorOrder(Member loginMember) {
		MonitorType monitorType = getOrderType(MonitorType.class, "구매하실 모니터 타입을 입력해주세요");
		Monitor keyboard = new Monitor.Builder()
			.username(loginMember.getUsername())
			.quantity(0)
			.location(null)
			.monitorType(monitorType)
			.build();
		createOrder(loginMember.getUsername(), keyboard);
	}

	public void createLaptopOrder(Member loginMember) {
		OSType osType = getOrderType(OSType.class, "구매하실 운영 체제를 입력해주세요");
		Laptop keyboard = new Laptop.Builder()
			.username(loginMember.getUsername())
			.quantity(0)
			.location(null)
			.osType(osType)
			.build();
		createOrder(loginMember.getUsername(), keyboard);
	}

	public void createKeyboardOrder(Member loginMember) {
		LayoutType layoutType = getOrderType(LayoutType.class, "구매하실 키보드 타입을 입력해주세요");
		Keyboard keyboard = new Keyboard.Builder()
			.username(loginMember.getUsername())
			.quantity(0)
			.location(null)
			.layoutType(layoutType)
			.build();
		createOrder(loginMember.getUsername(), keyboard);
	}

	private <T extends Enum<T>> T getOrderType(Class<T> enumType, String message) {
		StringBuilder stringBuilder = new StringBuilder();

		T[] values = enumType.getEnumConstants();
		for (int i = 0; i < values.length; i++) {
			stringBuilder.append(values[i].toString());
			if (i != values.length - 1)
				stringBuilder.append("/");
		}

		System.out.print(message + " (" + stringBuilder + "): ");
		return EnumUtils.getTypeByInputString(enumType);
	}

	private void createOrder(String username, Order order) {
		System.out.print("구매하실 수량을 입력해주세요: ");
		int quantity = InputUtils.getIntInput();

		System.out.print("배송지를 입력해주세요: ");
		String location = InputUtils.getStringInput();

		order.setQuantity(quantity);
		order.setLocation(location);

		Long orderPrice = orderService.getOrderPrice(order);
		Long beforeBalance = memberService.getCurrentMemberBalance();
		String formattedOrderPrice = FormatUtils.formatWithCommas(orderPrice);
		String formattedBeforeBalance = FormatUtils.formatWithCommas(beforeBalance);

		System.out.println(username + "님의 현재 잔액은 " + formattedBeforeBalance + "원 입니다.");
		System.out.println(formattedOrderPrice + "원 결제를 진행합니다.");

		try {
			orderService.orderedItem(order);
			System.out.println("주문이 완료되었습니다");
		} catch (OrderException ex) {
			System.out.println(ex.getMessage());
		}

		Long afterBalance = memberService.getCurrentMemberBalance();
		String formattedAfterBalance = FormatUtils.formatWithCommas(afterBalance);
		System.out.println(username + "님의 현재 잔액: " + formattedAfterBalance);
	}
}
