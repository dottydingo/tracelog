package com.dottydingo.tracelog;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 */
public class DefaultTraceManager<E> implements TraceManager<E>
{
	private Map<Thread,Trace<E>> threadMap = new ConcurrentHashMap<Thread, Trace<E>>();
	private Set<String> tracePackages = new HashSet<String>();

	@Override
	public void startTrace(Trace<E> trace)
	{
		startTrace(trace, Thread.currentThread());
	}

	@Override
	public void startTrace(Trace<E> trace, Thread thread)
	{
		threadMap.put(thread,trace);
	}

	@Override
	public Trace<E> getTrace()
	{
		return getTrace(Thread.currentThread());
	}

	@Override
	public Trace<E> getTrace(Thread thread)
	{
		return threadMap.get(thread);
	}

	@Override
	public Trace<E> endTrace()
	{
		return endTrace(Thread.currentThread());
	}

	@Override
	public Trace<E> endTrace(Thread thread)
	{
		return threadMap.remove(thread);
	}

	@Override
	public Set<String> getTracePackages()
	{
		return tracePackages;
	}

	@Override
	public boolean inTrace(String logger)
	{
		if(getTrace() == null)
			return false;

		for (String tracePackage : tracePackages)
		{
			if(logger.startsWith(tracePackage))
				return true;
		}

		return false;
	}

	public void setTracePackages(Set<String> tracePackages)
	{
		this.tracePackages = tracePackages;
	}
}
