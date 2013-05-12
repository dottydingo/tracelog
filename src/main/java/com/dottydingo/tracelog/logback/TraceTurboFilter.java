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
 * A TurboFilter that will force the logging event to be processed if a trace is in progress and the logger is
 * one of the packages being traced.
 */
public class TraceTurboFilter extends TurboFilter
{
	private TraceManager traceManager;

    /**
     * Set the trace manager
     * @param traceManager the trace manager
     */
    public void setTraceManager(TraceManager traceManager)
    {
        this.traceManager = traceManager;
    }

    /**
     * Initialize and add this turbo filter to the loggerFactory.
     */
    @Override
	public void start()
	{
        LoggerContext loggerFactory = (LoggerContext) LoggerFactory.getILoggerFactory();
        setContext(loggerFactory);
        loggerFactory.addTurboFilter(this);
        super.start();
	}

    /**
     * Checks to see if the current event should be logged based on if a relevant trace is active (or it would be logged anyway)
     * @param marker the marker
     * @param logger the logger
     * @param level the level
     * @param format the format
     * @param params the parameters
     * @param t the throwable
     * @return "ACCEPT if a relevant trace is active, "NEUTRAL" otherwise.
     */
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
