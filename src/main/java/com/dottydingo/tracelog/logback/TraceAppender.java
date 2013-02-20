package com.dottydingo.tracelog.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.dottydingo.tracelog.Trace;
import com.dottydingo.tracelog.TraceManager;
import org.slf4j.LoggerFactory;


/**
 */
public class TraceAppender extends AppenderBase<ILoggingEvent>
{
	private TraceManager<ILoggingEvent> traceManager;

	public void setTraceManager(TraceManager<ILoggingEvent> traceManager)
	{
		this.traceManager = traceManager;
	}

	public void init()
	{
		for (String packageName : traceManager.getTracePackages())
		{
			Logger logger = (Logger) LoggerFactory.getLogger(packageName);
			logger.addAppender(this);
		}
		start();
	}

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
