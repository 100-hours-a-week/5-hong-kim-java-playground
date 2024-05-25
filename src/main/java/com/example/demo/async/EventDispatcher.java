package com.example.demo.async;

import java.util.concurrent.BlockingQueue;

import com.example.demo.common.domain.BaseTimeEntity;

public class EventDispatcher implements Runnable {

	private final BlockingQueue<BaseTimeEntity> queue;

	public EventDispatcher(BlockingQueue<BaseTimeEntity> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				BaseTimeEntity payload = queue.take();
				dispatch(payload);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
				break;
			}
		}
	}

	private <T extends BaseTimeEntity> void dispatch(T entity) {
		EventConsumer<T> consumer = (EventConsumer<T>)EventConsumerDispatch.findFitConsumer(entity.getClass());
		if (consumer != null) {
			System.out.println("\n| (async) >> 비동기 이벤트 publish - command 작업");

			consumer.save(entity);  // 각 도메인 Repository 의 save() 호출

			System.out.println("\n| (async) >> 비동기 작업 완료 - 저장 완료");
			return;
		}

		System.out.println("\n| (async) >> 등록된 컨슈머가 없음 :: " + entity.getClass().getName());
		System.out.println("\n| (async) >>비동기 이벤트 publish fail");
	}
}
