package com.dottydingo.tracelog;

/**
 * Convenience class to allow a configured instance of a TraceFactory to be accessed statically
 */
public class TraceFactoryHolder
{
	private static TraceFactory instance;

    /**
     * Return the static instance
     * @return the instance
     */
	public static TraceFactory getInstance()
	{
		return instance;
	}

    /**
     * Set the configured instance
     * @param instance The instance
     */
	public void setInstance(TraceFactory instance)
	{
		TraceFactoryHolder.instance = instance;
	}
}
