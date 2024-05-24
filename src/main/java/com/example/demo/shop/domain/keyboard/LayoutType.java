package com.example.demo.shop.domain.keyboard;

public enum LayoutType {
	FULL_SIZE("104키", 200_000L),
	TEN_KEYLESS("87키", 190_000L),
	COMPACT("68키", 140_000L),
	;

	private final String description;
	private final Long price;

	LayoutType(String description, Long price) {
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public Long getPrice() {
		return price;
	}

	// public Optional<LayoutType> findTypeByName(String name) {
	// 	return Arrays.stream(LayoutType.values())
	// 		.filter(memberType -> memberType.name().equals(name))
	// 		.findFirst();
	// }
}
