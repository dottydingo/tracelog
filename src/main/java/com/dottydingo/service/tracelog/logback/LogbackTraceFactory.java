package com.dottydingo.service.tracelog.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.dottydingo.service.tracelog.Trace;
import com.dottydingo.service.tracelog.TraceFactory;
import com.dottydingo.service.tracelog.TraceType;

/**
 * A Logback specific trace factory. Applies the configured trace configurations to the newly created Trace
 * instances.
 */
public class LogbackTraceFactory implements TraceFactory<ILoggingEvent>
{
	private EmailConfiguration emailConfiguration;
	private FileConfiguration fileConfiguration;

    /**
     * Set the email configuration to use on each Email Trace created.
     * @param emailConfiguration The email configuration.
     */
	public void setEmailConfiguration(EmailConfiguration emailConfiguration)
	{
		this.emailConfiguration = emailConfiguration;
	}

    /**
     * Set the file configuration to use on each File Trace created.
     * @param fileConfiguration The file configuration.
     */
	public void setFileConfiguration(FileConfiguration fileConfiguration)
	{
		this.fileConfiguration = fileConfiguration;
	}

    /**
     * {@inheritDoc}
     */
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
