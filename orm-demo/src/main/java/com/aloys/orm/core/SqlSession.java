package com.aloys.orm.core;

public interface SqlSession {

	
	<T> int insert(T entity);
}
