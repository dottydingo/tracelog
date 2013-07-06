package com.dottydingo.service.tracelog;


import java.util.Set;

/**
 * Interface for managing traces
 */
public interface TraceManager<E>
{
	/**
	 * Associate the supplied trace to the current thread
	 * @param trace The trace
	 */
	public void associateTrace(Trace<E> trace);

	/**
	 * Associate the supplied trace on the supplied thread
	 * @param trace The trace
	 * @param thread The thread to associate the trace to
	 */
	public void associateTrace(Trace<E> trace, Thread thread);


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
	 * Disassociate the trace from the current tread.
	 * @return The current trace or null if no trace is associated with the current thread
	 */
	public Trace<E> disassociateTrace();

	/**
	 * Disassociate the trace associated with the supplied thread
	 * @param thread The thread
	 * @return The trace or null if no trace is associated with the supplied thread
	 */
	public Trace<E> disassociateTrace(Thread thread);

	/**
	 * Return the packages to trace
	 * @return The packages to trace
	 */
	public Set<String> getTracePackages();


    /**
     * Check if the supplied logger is being traced
     * @param logger The logger
     * @return true if a trace is active that covers the supplied logger, false otherwise
     */
	public boolean inTrace(String logger);
}
