package com.dottydingo.tracelog;

/**
 * A factory for creating instances of a Trace.
 */
public interface TraceFactory<E>
{
    /**
     * Create a trace of the specified type using the supplied arguments
     * @param traceType The trace type
     * @param args The arguments
     * @return a configured Trace
     */
	Trace<E> createTrace(TraceType traceType,String... args);
}
