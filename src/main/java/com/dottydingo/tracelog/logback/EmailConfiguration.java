package com.dottydingo.tracelog.logback;

/**
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

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getSmtpHost()
	{
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost)
	{
		this.smtpHost = smtpHost;
	}

	public int getSmtpPort()
	{
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort)
	{
		this.smtpPort = smtpPort;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getBufferSize()
	{
		return bufferSize;
	}

	public void setBufferSize(int bufferSize)
	{
		this.bufferSize = bufferSize;
	}

	public String getPattern()
	{
		return pattern;
	}

	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}
}
