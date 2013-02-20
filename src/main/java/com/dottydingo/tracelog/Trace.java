package com.dottydingo.tracelog;

/**
 */
public interface Trace<E>
{
	void addEvent(E event);
	void close() throws Exception;
}
