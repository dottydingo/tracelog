package com.dottydingo.tracelog.logback;

/**
 * The configuration to use for Email Traces
 */
public class EmailConfiguration
{
	private String from;
	private String subject = "Trace";
	private String smtpHost = "localhost";
	private int smtpPort = 25;
	private String username;
	private String password;

	private int bufferSize;
	private String pattern;

    /**
     * Return the "from" address for the trace email.
     * @return the address
     */
	public String getFrom()
	{
		return from;
	}

    /**
     * Set the "from" address for the trace email.
     * @param from the address
     */
	public void setFrom(String from)
	{
		this.from = from;
	}

    /**
     * Return the "subject" for the trace email. Defaults to "Trace".
     * @return the subject
     */
	public String getSubject()
	{
		return subject;
	}

    /**
     * Set the "subject" for the trace email. Defaults to "Trace" if not set.
     * @param subject the subject
     */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}

    /**
     * Return the smtp host to use for sending emails. Defaults to "localhost".
     * @return the host
     */
	public String getSmtpHost()
	{
		return smtpHost;
	}

    /**
     * Set the smtp host to use for sending emails. Defaults to "localhost" if not set.
     * @param smtpHost the host
     */
	public void setSmtpHost(String smtpHost)
	{
		this.smtpHost = smtpHost;
	}

    /**
     * Return the smtp port to use for sending emails. Defaults to "25".
     * @return the port
     */
	public int getSmtpPort()
	{
		return smtpPort;
	}

    /**
     * Set the smtp port to use for sending emails. Defaults to "25" if not set.
     * @param smtpPort the port
     */
	public void setSmtpPort(int smtpPort)
	{
		this.smtpPort = smtpPort;
	}

    /**
     * Return the username to use for the smtp host.
     * @return the username
     */
	public String getUsername()
	{
		return username;
	}

    /**
     * Set the username to use for the smtp host. This is optional and only needs to be set if
     * the smtp server requires authentication.
     * @param username the username.
     */
	public void setUsername(String username)
	{
		this.username = username;
	}

    /**
     * Return the password to use for the smtp host.
     * @return the password.
     */
	public String getPassword()
	{
		return password;
	}

    /**
     * Set the password to use for the smtp host. This is optional and only needs to be set if
     * the smtp server requires authentication.
     * @param password the password
     */
	public void setPassword(String password)
	{
		this.password = password;
	}

    /**
     * Return the buffer size for the email. This controls the number of log entries that will be returned
     * in the email trace.
     * @return The buffer size.
     */
	public int getBufferSize()
	{
		return bufferSize;
	}

    /**
     * Set the buffer size for the email. This controls the number of log entries that will be returned
     * in the email trace.
     * @param bufferSize the buffer size
     */
	public void setBufferSize(int bufferSize)
	{
		this.bufferSize = bufferSize;
	}

    /**
     * Return the pattern to use for the log entries in the email. This should be a valid pattern for a PatternLayout.
     * @return the pattern
     */
	public String getPattern()
	{
		return pattern;
	}

    /**
     * Set the patternb to use for the log entries in the email. This should be a valid pattern for a PatternLayout.
     * @param pattern The pattern
     */
	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}
}
