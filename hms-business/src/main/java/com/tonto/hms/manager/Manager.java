package com.tonto.hms.manager;

public interface Manager {
	/**
	 * 项目启动完毕后运行
	 * @return
	 */
	public boolean init();
	
	/**
	 * 所有{@link Manager}的实例的{@code init()}方法执行后执行
	 * @return
	 */
	public boolean afterInit();
}
