package com.example.demo.async;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.example.demo.common.domain.BaseTimeEntity;

public class AsyncConfig {

	protected static final BlockingQueue<BaseTimeEntity> queue = new LinkedBlockingQueue<>();
	// 싱글 스레드의 MQ
	protected static final ExecutorService executorService = Executors.newSingleThreadExecutor();

	public static void enableAsync() {
		executorService.submit(new EventDispatcher(queue));
	}
}
