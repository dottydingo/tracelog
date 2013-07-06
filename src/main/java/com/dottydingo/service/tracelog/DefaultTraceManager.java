package com.dottydingo.service.tracelog;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The default implementation of a TraceManager. Traces are associated with the current(or specified) thread.
 */
public class DefaultTraceManager<E> implements TraceManager<E>
{
	private Map<Thread,Trace<E>> threadMap = new ConcurrentHashMap<Thread, Trace<E>>();
	private Set<String> tracePackages = new HashSet<String>();

    /**
     * {@inheritDoc}
     */
	@Override
	public void associateTrace(Trace<E> trace)
	{
		associateTrace(trace, Thread.currentThread());
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void associateTrace(Trace<E> trace, Thread thread)
	{
		threadMap.put(thread,trace);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Trace<E> getTrace()
	{
		return getTrace(Thread.currentThread());
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Trace<E> getTrace(Thread thread)
	{
		return threadMap.get(thread);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Trace<E> disassociateTrace()
	{
		return disassociateTrace(Thread.currentThread());
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Trace<E> disassociateTrace(Thread thread)
	{
		return threadMap.remove(thread);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Set<String> getTracePackages()
	{
		return tracePackages;
	}

    /**
     * {@inheritDoc}
     */
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

    /**
     * Set the top level loggers (packages) to trace. Any loggers below the specified ones will also be covered
     * by an active trace.
     * @param tracePackages The packages to trace
     */
	public void setTracePackages(Set<String> tracePackages)
	{
		this.tracePackages = tracePackages;
	}
}
