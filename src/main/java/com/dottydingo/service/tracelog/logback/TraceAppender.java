package com.dottydingo.service.tracelog.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.dottydingo.service.tracelog.Trace;
import com.dottydingo.service.tracelog.TraceManager;
import org.slf4j.LoggerFactory;


/**
 * An Appender added to the packages that can be traced. Logging events are only processed if a trace is currently in process.
 */
public class TraceAppender extends AppenderBase<ILoggingEvent>
{
	private TraceManager<ILoggingEvent> traceManager;

    /**
     * Set the trace manager
     * @param traceManager the trace manager
     */
	public void setTraceManager(TraceManager<ILoggingEvent> traceManager)
	{
		this.traceManager = traceManager;
	}

    /**
     * Adds this appender to the traced packages (from the TraceManager)
     */
	public void init()
	{
		for (String packageName : traceManager.getTracePackages())
		{
			Logger logger = (Logger) LoggerFactory.getLogger(packageName);
			logger.addAppender(this);
		}
		start();
	}

    /**
     * Appends the logging event to the trace if one is currently in progress
     * @param eventObject the event
     */
	@Override
	protected void append(ILoggingEvent eventObject)
	{
		Trace<ILoggingEvent> trace = traceManager.getTrace();
		if(trace != null)
		{
			trace.addEvent(eventObject);
		}
	}
}
