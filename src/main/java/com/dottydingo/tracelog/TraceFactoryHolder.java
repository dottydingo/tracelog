package com.dottydingo.tracelog;

/**
 */
public class TraceFactoryHolder
{
	private static TraceFactory instance;

	public static TraceFactory getInstance()
	{
		return instance;
	}

	public void setInstance(TraceFactory instance)
	{
		TraceFactoryHolder.instance = instance;
	}
}
