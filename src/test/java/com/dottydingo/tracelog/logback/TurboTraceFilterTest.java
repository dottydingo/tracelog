package com.dottydingo.tracelog.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.FilterReply;
import com.dottydingo.tracelog.TraceManager;
import com.dottydingo.tracelog.TraceManagerHolder;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;

/**
 */
public class TurboTraceFilterTest
{
	private TraceManager traceManager;
	private TraceTurboFilter turboFilter;

	@Before
	public void setUp() throws Exception
	{
		traceManager = Mockito.mock(TraceManager.class);
		turboFilter = new TraceTurboFilter();
        turboFilter.setTraceManager(traceManager);
	}

	@Test
	public void testInLogLevel()
	{
		Logger logger = (Logger) LoggerFactory.getLogger("Foo");
		logger.setLevel(Level.WARN);

		Assert.assertEquals(FilterReply.NEUTRAL,turboFilter.decide(null,logger,Level.WARN,null,null,null));
		Assert.assertEquals(FilterReply.NEUTRAL,turboFilter.decide(null,logger,Level.ERROR,null,null,null));

		Mockito.verifyZeroInteractions(traceManager);
	}

	@Test
	public void testNotInLogLevel()
	{
		Logger logger = (Logger) LoggerFactory.getLogger("Foo");
		logger.setLevel(Level.WARN);

		Assert.assertEquals(FilterReply.NEUTRAL,turboFilter.decide(null,logger,Level.INFO,null,null,null));
		Assert.assertEquals(FilterReply.NEUTRAL,turboFilter.decide(null,logger,Level.DEBUG,null,null,null));
		Assert.assertEquals(FilterReply.NEUTRAL,turboFilter.decide(null,logger,Level.TRACE,null,null,null));

		Mockito.verify(traceManager,Mockito.times(3)).inTrace("Foo");
	}

	@Test
	public void testInTraceLevel()
	{
		Mockito.when(traceManager.inTrace("Foo")).thenReturn(true);
		Logger logger = (Logger) LoggerFactory.getLogger("Foo");
		logger.setLevel(Level.WARN);

		Assert.assertEquals(FilterReply.NEUTRAL,turboFilter.decide(null,logger,Level.WARN,null,null,null));
		Assert.assertEquals(FilterReply.ACCEPT,turboFilter.decide(null,logger,Level.INFO,null,null,null));
		Assert.assertEquals(FilterReply.ACCEPT,turboFilter.decide(null,logger,Level.DEBUG,null,null,null));
		Assert.assertEquals(FilterReply.ACCEPT,turboFilter.decide(null,logger,Level.TRACE,null,null,null));

		Mockito.verify(traceManager,Mockito.times(3)).inTrace("Foo");
	}
}
