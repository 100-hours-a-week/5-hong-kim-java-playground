package com.example.demo.order.domain.monitor;

public enum MonitorType {
	FHD("FHD", 300_000L),
	QHD("QHD", 500_000L),
	UHD("UHD", 700_000L),
	;

	private final String description;
	private final Long price;

	MonitorType(String description, Long price) {
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public Long getPrice() {
		return price;
	}

	// public static Optional<MonitorType> findTypeByName(String name) {
	// 	return Arrays.stream(MonitorType.values())
	// 		.filter(memberType -> memberType.name().equals(name))
	// 		.findFirst();
	// }
}
