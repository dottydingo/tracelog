package com.dottydingo.tracelog;


import java.util.Set;

/**
 */
public interface TraceManager<E>
{
	/**
	 * Start a trace on the current thread using the supplied trace
	 * @param trace The trace
	 */
	public void startTrace(Trace<E> trace);

	/**
	 * Start a trace on the supplied thread using the supplied trace
	 * @param trace The trace
	 * @param thread The thread to associate the trace to
	 */
	public void startTrace(Trace<E> trace, Thread thread);


	/**
	 * Get the trace associated with the current tread.
	 * @return The trace or null if no trace is associated with the current thread
	 */
	public Trace<E> getTrace();

	/**
	 * Get the trace associated with the supplied thread
	 * @param thread The thread
	 * @return The trace or null if no trace is associated with the supplied thread
	 */
	public Trace<E> getTrace(Thread thread);

	/**
	 * End the trace associated with the current tread.
	 * @return The current trace or null if no trace is associated with the current thread
	 */
	public Trace<E> endTrace();

	/**
	 * End the trace associated with the supplied thread
	 * @param thread The thread
	 * @return The trace or null if no trace is associated with the supplied thread
	 */
	public Trace<E> endTrace(Thread thread);

	/**
	 * Return the packages to trace
	 * @return The packages to trace
	 */
	public Set<String> getTracePackages();

	public boolean inTrace(String logger);
}
