package com.tonto.hms.common.container;

/**
 * 容器接口
 * @author TontoZhou
 *
 */
public interface Container<T> {
	
	/**
	 * 放入元素
	 * @param ts
	 */
	@SuppressWarnings("unchecked")
	public void push(T... ts);
	
	/**
	 * 获取元素
	 * @return
	 */
	public T pop();
	
}
