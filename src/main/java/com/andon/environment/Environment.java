package com.andon.environment;

/**
 * 根据不同环境执行不同方法。
 * @author TM Tse
 *
 */
public interface Environment {
	public Object handle(Object obj);
	public Object handleForDevelop(Object obj);
	public Object handleForTest(Object obj);
	public Object handleForPreproduction(Object obj);
	public Object handleForProduct(Object obj);
	public Object handleForDefault(Object obj);
}
