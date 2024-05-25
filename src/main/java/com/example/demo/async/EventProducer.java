package com.example.demo.async;

import com.example.demo.common.domain.BaseTimeEntity;

public class EventProducer extends AsyncConfig {

	public static void publish(BaseTimeEntity entity) {
		try {
			queue.put(entity);
		} catch (InterruptedException ex) {
			System.out.println("큐에 넣기 실패 - 비동기 작업 시작 실패");
		}
	}
}
