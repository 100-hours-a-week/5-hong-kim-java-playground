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

	@SuppressWarnings("unchecked")
	private <T extends BaseTimeEntity> void dispatch(T entity) {
		EventConsumerDispatch.findFitConsumer(entity.getClass())
			.ifPresentOrElse(
				consumer -> execute((EventConsumer<T>)consumer, entity),
				() -> {
					System.out.println("\n| (async) >> 등록된 컨슈머가 없음 :: " + entity.getClass().getName());
					System.out.println("\n| (async) >> 비동기 이벤트 publish fail");
				}
			);
	}

	private <T extends BaseTimeEntity> void execute(EventConsumer<T> consumer, T entity) {
		System.out.println("\n| (async) >> 비동기 이벤트 publish - command 작업");

		consumer.save(entity);  // 각 도메인 Repository 의 save() 호출

		System.out.println("\n| (async) >> 비동기 작업 완료 - 저장 완료");
	}
}
