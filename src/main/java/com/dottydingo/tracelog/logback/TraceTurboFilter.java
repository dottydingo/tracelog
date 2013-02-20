package com.dottydingo.tracelog.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.FilterReply;
import com.dottydingo.tracelog.TraceManager;
import com.dottydingo.tracelog.TraceManagerHolder;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 */
public class TraceTurboFilter extends TurboFilter
{
	private TraceManager traceManager;

    public void setTraceManager(TraceManager traceManager)
    {
        this.traceManager = traceManager;
    }

    @Override
	public void start()
	{
        LoggerContext loggerFactory = (LoggerContext) LoggerFactory.getILoggerFactory();
        setContext(loggerFactory);
        loggerFactory.addTurboFilter(this);
        super.start();
	}

	@Override
	public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t)
	{
		if(level.isGreaterOrEqual(logger.getEffectiveLevel()))
			return FilterReply.NEUTRAL;

		if(traceManager.inTrace(logger.getName()))
			return FilterReply.ACCEPT;

		return FilterReply.NEUTRAL;
	}
}
