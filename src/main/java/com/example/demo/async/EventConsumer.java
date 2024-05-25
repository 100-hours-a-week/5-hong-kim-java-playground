package com.example.demo.async;

import com.example.demo.common.domain.BaseTimeEntity;

// 간단한 시스템을 위해서 Consumer 역할을 Repository 자체로 구현
public interface EventConsumer<T extends BaseTimeEntity> {

	void save(T t);
}
