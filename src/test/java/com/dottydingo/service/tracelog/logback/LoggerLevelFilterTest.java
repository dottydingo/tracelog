package com.dottydingo.service.tracelog.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.spi.FilterReply;
import junit.framework.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;


/**
 */
public class LoggerLevelFilterTest
{
    private LoggerLevelFilter filter = new LoggerLevelFilter();

    @Test
    public void testDecide()
    {
        Logger logger = (Logger) LoggerFactory.getLogger("foo.bar");
        logger.setLevel(Level.INFO);

        ILoggingEvent event = new LoggingEvent("boo.bar",logger,Level.DEBUG,"",null,null);
        Assert.assertEquals(FilterReply.DENY,filter.decide(event));

        event = new LoggingEvent("boo.bar",logger,Level.INFO,"",null,null);
        Assert.assertEquals(FilterReply.NEUTRAL,filter.decide(event));

        event = new LoggingEvent("boo.bar",logger,Level.ERROR,"",null,null);
        Assert.assertEquals(FilterReply.NEUTRAL,filter.decide(event));
    }
}
