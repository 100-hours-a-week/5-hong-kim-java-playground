package com.example.demo.common.domain;

public interface BaseTimeEntity {

	String getCreatedTimeToString();
}

// 리팩토링 사유 - createdAt 이나 updatedAt 이 필요한 엔티티 클래스에서 구현함으로 써 객체의 유연성 확장
// 추상 클래스를 비추천하는 이유 - 현재는 createdAt, updateDt중 createdAt 만 사용한다면 updatedAt 기능은 쓸모 없는 기능이 된다.
// SRP 위반의 사유
// public class BaseTimeEntity {
//
// 	private LocalDateTime createdAt;
//
// 	public BaseTimeEntity(LocalDateTime createdAt) {
// 		this.createdAt = createdAt;
// 	}
//
// 	public String getCreatedTimeToString() {
// 		return FormatUtils.formatDateTime(createdAt);
// 		// return createdAt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
// 	}
// }


