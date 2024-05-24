package com.example.demo.shop.domain.laptop;

public enum OSType {
	MACOS("MacOs", 3_000_000L),
	WINDOWS("Windows", 2_000_000L),
	LINUX("Linux", 1_700_000L),
	;

	private final String description;
	private final Long price;

	OSType(String description, Long price) {
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public Long getPrice() {
		return price;
	}

	// public static Optional<OSType> findTypeByName(String name) {
	// 	return Arrays.stream(OSType.values())
	// 		.filter(memberType -> memberType.name().equals(name))
	// 		.findFirst();
	// }
}
