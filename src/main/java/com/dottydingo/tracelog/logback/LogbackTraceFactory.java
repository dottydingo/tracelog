package com.dottydingo.tracelog.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.dottydingo.tracelog.Trace;
import com.dottydingo.tracelog.TraceFactory;
import com.dottydingo.tracelog.TraceType;

/**
 */
public class LogbackTraceFactory implements TraceFactory<ILoggingEvent>
{
	private EmailConfiguration emailConfiguration;
	private FileConfiguration fileConfiguration;

	public void setEmailConfiguration(EmailConfiguration emailConfiguration)
	{
		this.emailConfiguration = emailConfiguration;
	}

	public void setFileConfiguration(FileConfiguration fileConfiguration)
	{
		this.fileConfiguration = fileConfiguration;
	}

	@Override
	public Trace<ILoggingEvent> createTrace(TraceType traceType, String... args)
	{
		switch (traceType)
		{
			case email:
				return new EmailTrace(emailConfiguration,args[0]);
			case file:
				return new FileTrace(fileConfiguration,args[0]);
			default:
				throw new RuntimeException(String.format("Unknown traceType: %s",traceType));
		}

	}
}
