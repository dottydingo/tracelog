package com.dottydingo.tracelog;

/**
 */
public class TraceManagerHolder
{
	private static TraceManager instance;

	public static TraceManager getInstance()
	{
		return instance;
	}

	public void setInstance(TraceManager instance)
	{
		TraceManagerHolder.instance = instance;
	}
}
