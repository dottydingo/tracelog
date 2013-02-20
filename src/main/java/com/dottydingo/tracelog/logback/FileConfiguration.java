package com.dottydingo.tracelog.logback;

/**
 */
public class FileConfiguration
{
	private String baseDirectory;
	private String pattern;
	private int bufferSize;

	public String getBaseDirectory()
	{
		return baseDirectory;
	}

	public void setBaseDirectory(String baseDirectory)
	{
		this.baseDirectory = baseDirectory;
	}

	public String getPattern()
	{
		return pattern;
	}

	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}

	public int getBufferSize()
	{
		return bufferSize;
	}

	public void setBufferSize(int bufferSize)
	{
		this.bufferSize = bufferSize;
	}
}
