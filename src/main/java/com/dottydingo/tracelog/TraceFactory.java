package com.dottydingo.tracelog;

/**
 */
public interface TraceFactory<E>
{
	Trace<E> createTrace(TraceType traceType,String... args);
}
