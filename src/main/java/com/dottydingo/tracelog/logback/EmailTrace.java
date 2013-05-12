package com.dottydingo.tracelog.logback;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.helpers.CyclicBuffer;
import com.dottydingo.tracelog.Trace;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * A Trace that collects log events in a cyclic buffer and sends an email containing the buffered
 * events when the trace is closed.
 */
public class EmailTrace implements Trace<ILoggingEvent>
{
    private CyclicBuffer<ILoggingEvent> buffer;
    private MimeMessage mimeMsg;
    private PatternLayout layout = new PatternLayout();

    /**
     * Create a Trace using the supplied parameters
     * @param configuration The configuration
     * @param toAddress the email address to send the mail to
     */
    public EmailTrace(EmailConfiguration configuration, String toAddress)
    {

        layout.setPattern(configuration.getPattern());
        layout.setContext((Context) LoggerFactory.getILoggerFactory());
        layout.start();
        buffer = new CyclicBuffer<ILoggingEvent>(configuration.getBufferSize());
        Session session = buildSessionFromProperties(configuration);

        mimeMsg = new MimeMessage(session);
        try
        {
            mimeMsg.setSubject(configuration.getSubject());
            mimeMsg.setFrom(new InternetAddress(configuration.getFrom()));
            mimeMsg.setRecipients(Message.RecipientType.TO, toAddress);
        }
        catch (MessagingException e)
        {
            throw new RuntimeException("Could not activate SMTPAppender options.", e);
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
     * Close the trace and send the email
     * @throws Exception if an error occurs
     */
    @Override
    public void close() throws Exception
    {

        MimeBodyPart part = new MimeBodyPart();

        StringBuilder sb = new StringBuilder();


        int len = buffer.length();
        for (int i = 0; i < len; i++)
        {
            ILoggingEvent event = buffer.get();
            sb.append(layout.doLayout(event));
        }

        part.setText(sb.toString(), "UTF-8", "plain");

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(part);
        mimeMsg.setContent(mp);

        mimeMsg.setSentDate(new Date());
        Transport.send(mimeMsg);

    }


    private Session buildSessionFromProperties( EmailConfiguration configuration)
    {
        Properties props = new Properties();
        if (configuration.getSmtpHost() != null)
        {
            props.put("mail.smtp.host", configuration.getSmtpHost());
        }
        props.put("mail.smtp.port", Integer.toString(configuration.getSmtpPort()));

        LoginAuthenticator loginAuthenticator = null;

        if (configuration.getUsername() != null)
        {
            loginAuthenticator = new LoginAuthenticator(configuration.getUsername(), configuration.getPassword());
            props.put("mail.smtp.auth", "true");
        }

        return Session.getInstance(props, loginAuthenticator);
    }

    private class LoginAuthenticator extends Authenticator
    {

        String username;
        String password;

        LoginAuthenticator(String username, String password)
        {
            this.username = username;
            this.password = password;
        }

        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(username, password);
        }
    }
}
