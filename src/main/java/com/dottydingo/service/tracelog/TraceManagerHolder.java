package com.dottydingo.service.tracelog;

/**
 * Convenience class to allow a configured instance of a TraceManager to be accessed statically
 */
public class TraceManagerHolder
{
	private static TraceManager instance;

    /**
     * Return the static instance
     * @return the trace manager
     */
	public static TraceManager getInstance()
	{
		return instance;
	}

    /**
     * Set the configured instance
     * @param instance The instance
     */
	public void setInstance(TraceManager instance)
	{
		TraceManagerHolder.instance = instance;
	}
}
