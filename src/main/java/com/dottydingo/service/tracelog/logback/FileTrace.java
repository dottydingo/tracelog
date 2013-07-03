package com.dottydingo.service.tracelog.logback;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.helpers.CyclicBuffer;
import com.dottydingo.service.tracelog.Trace;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A Trace that collects log events in a cyclic buffer and writes them to a file when the trace is closed.
 */
public class FileTrace implements Trace<ILoggingEvent>
{
    private CyclicBuffer<ILoggingEvent> buffer;
    private PatternLayout layout = new PatternLayout();
    private File file;

    /**
     * Create a new trace using the supplied parameters. The specified file will be written to the base directory.
     * Care should be taken to validate the file name if this can be specified from a request.
     * @param configuration The configuration
     * @param fileName The name of the trace file.
     */
    public FileTrace(FileConfiguration configuration, String fileName)
    {

        layout.setPattern(configuration.getPattern());
        layout.setContext((Context) LoggerFactory.getILoggerFactory());
        layout.start();

        buffer = new CyclicBuffer<ILoggingEvent>(configuration.getBufferSize());
        file = new File(configuration.getBaseDirectory(), fileName);
        if (file.isDirectory())
        {
            throw new RuntimeException(String.format("%s is a directory.", file.getName()));
        }
        try
        {
            file.createNewFile();
        }
        catch (IOException e)
        {
            throw new RuntimeException(String.format("Error creating file %s", file.getName()));
        }

        if (!file.canWrite())
        {
            throw new RuntimeException(String.format("%s is not writable.", file.getName()));
        }
    }


    /**
     * Add the supplied event to the buffer
     * @param event the event
     */
    @Override
    public void addEvent(ILoggingEvent event)
    {
        event.prepareForDeferredProcessing();
        buffer.add(event);
    }

    /**
     * Close the trace and write the file
     * @throws Exception if an error occurs
     */
    @Override
    public void close() throws Exception
    {
        BufferedWriter writer = null;
        try
        {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());

            writer = new BufferedWriter(fw);

            String header = layout.getFileHeader();
            if (header != null)
            {
                writer.append(header);
            }
            String presentationHeader = layout.getPresentationHeader();
            if (presentationHeader != null)
            {
                writer.append(presentationHeader);
            }
            int len = buffer.length();
            for (int i = 0; i < len; i++)
            {
                ILoggingEvent event = buffer.get();
                writer.append(layout.doLayout(event));
            }

            String presentationFooter = layout.getPresentationFooter();
            if (presentationFooter != null)
            {
                writer.append(presentationFooter);
            }
            String footer = layout.getFileFooter();
            if (footer != null)
            {
                writer.append(footer);
            }
        }
        finally
        {
            if(writer != null)
            {
                writer.flush();
                writer.close();
            }
        }
    }
}
