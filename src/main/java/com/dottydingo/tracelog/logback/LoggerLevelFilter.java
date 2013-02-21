package com.dottydingo.tracelog.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.LoggerFactory;

/**
 */
public class LoggerLevelFilter extends Filter<ILoggingEvent>
{
    private LoggerContext loggerFactory = (LoggerContext) LoggerFactory.getILoggerFactory();

    @Override
    public FilterReply decide(ILoggingEvent event)
    {
        Logger logger = loggerFactory.getLogger(event.getLoggerName());
        if(event.getLevel().isGreaterOrEqual(logger.getEffectiveLevel()))
            return FilterReply.NEUTRAL;

        return FilterReply.DENY;
    }
}
