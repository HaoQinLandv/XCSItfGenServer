package com.foxhis.itf.service;

/**
 * 新增接口泛型，来匹配需要做的任务handler
 * @author Administrator
 *
 * @param <T>
 */
public interface IDoTask<T> {

	public void doTask(T t);
}
