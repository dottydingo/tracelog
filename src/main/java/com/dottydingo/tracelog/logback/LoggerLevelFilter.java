package com.dottydingo.tracelog.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.LoggerFactory;

/**
 * A Filter used on standard appenders to prevent logging events that would not normally be sent to an appender from
 * being appended during a trace.
 */
public class LoggerLevelFilter extends Filter<ILoggingEvent>
{
    private LoggerContext loggerFactory = (LoggerContext) LoggerFactory.getILoggerFactory();

    /**
     * Checks the log level of the event against the effective log level of the Logger referenced in the event.
     * @param event the event
     * @return Returns "NEUTRAL" if the event level is greater than or equal to the loggers effective log level, "DENY"
     * otherwise.
     */
    @Override
    public FilterReply decide(ILoggingEvent event)
    {
        Logger logger = loggerFactory.getLogger(event.getLoggerName());
        if(event.getLevel().isGreaterOrEqual(logger.getEffectiveLevel()))
            return FilterReply.NEUTRAL;

        return FilterReply.DENY;
    }
}
