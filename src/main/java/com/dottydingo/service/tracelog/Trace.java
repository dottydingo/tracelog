package com.dottydingo.service.tracelog;

/**
 * Interface for an active trace. Events are specific to the logging library being used.
 */
public interface Trace<E>
{
    /**
     * Add a logging event
     * @param event the event
     */
	void addEvent(E event);

    /**
     * Close the trace and finalize processing.
     * @throws Exception If an error occurs
     */
	void close() throws Exception;
}
