package com.dottydingo.tracelog.logback;

/**
 * Configuration for a File trace
 */
public class FileConfiguration
{
	private String baseDirectory;
	private String pattern;
	private int bufferSize;

    /**
     * Return the base directory to write the trace files to
     * @return the directory
     */
	public String getBaseDirectory()
	{
		return baseDirectory;
	}

    /**
     * Set the base directory to write the trace files to
     * @param baseDirectory the directory
     */
	public void setBaseDirectory(String baseDirectory)
	{
		this.baseDirectory = baseDirectory;
	}

    /**
     * Return the pattern to use for the log entries in the file. This should be a valid pattern for a PatternLayout.
     * @return the pattern
     */
    public String getPattern()
    {
        return pattern;
    }

    /**
     * Set the patternb to use for the log entries in the file. This should be a valid pattern for a PatternLayout.
     * @param pattern The pattern
     */
    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

    /**
     * Return the buffer size for the file. This controls the number of log entries that will be written to the trace file.
     * @return The buffer size.
     */
    public int getBufferSize()
    {
        return bufferSize;
    }

    /**
     * Set the buffer size for the file. This controls the number of log entries that will be written to the trace file.
     * @param bufferSize the buffer size
     */
    public void setBufferSize(int bufferSize)
    {
        this.bufferSize = bufferSize;
    }
}
