A module for creating request traces. Currently supports outputting traces to email or to files.

# Usage
## Dependencies
To use add this maven dependency:

```xml
<dependency>
    <groupId>com.dottydingo.tracelog</groupId>
    <artifactId>tracelog</artifactId>
    <version>{current_version}</version>
</dependency>
```

You will also need the following dependencies:
* slf4j-api - 1.6.x or greater
* logback - 1.0.x or greater
* Java activation - 1.1 (if using the email trace)
* Java mail 1.4.x (if using the email trace)

## Configuration
To set up in Spring do the following:

```xml
<!-- create the trace manager and define the top level packages (loggers) that will be traced -->
<bean id="traceManager" class="com.dottydingo.tracelog.DefaultTraceManager">
    <property name="tracePackages">
        <set>
            <value>com.foo</value>
        </set>
    </property>
</bean>

<!-- install the turbo filter -->
<bean class="com.dottydingo.tracelog.logback.TraceTurboFilter" init-method="start">
    <property name="traceManager" ref="traceManager"/>
</bean>

<!-- install the trace appender on the packages specified in the trace manager -->
<bean class="com.dottydingo.tracelog.logback.TraceAppender" init-method="init">
    <property name="traceManager" ref="traceManager"/>
</bean>

<!-- the configuration for email traces  -->
<bean id="emailConfiguration" class="com.dottydingo.tracelog.logback.EmailConfiguration">
    <property name="bufferSize" value="5000"/>
    <property name="subject" value="Service Trace"/>
    <property name="from" value="myservice@somedomain.com"/>
    <property name="smtpHost" value="localhost"/>
    <property name="smtpPort" value="25"/>
    <property name="pattern" value="%d{HH:mm:ss.SSS} %logger{5}: %message%n"/>
</bean>

<!-- the configuration for file traces -->
<bean id="fileConfiguration" class="com.dottydingo.tracelog.logback.FileConfiguration">
    <property name="pattern" value="%d{HH:mm:ss.SSS} %logger{5}: %message%n"/>
    <property name="baseDirectory" value="/var/log/trace/"/>
    <property name="bufferSize" value="5000"/>
</bean>

<!-- the factory for creating trace instances -->
<bean id="traceFactory" class="com.dottydingo.tracelog.logback.LogbackTraceFactory">
    <property name="emailConfiguration" ref="emailConfiguration"/>
    <property name="fileConfiguration" ref="fileConfiguration"/>
</bean>
```

Add the LoggerLevelFilter to the other appenders so prevent a trace from outputting logging events
to other appenders when they would not normally be output: 

```xml
<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
        <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
    <!-- 
    Only log entries that match the configured logger level (so we don't leak to other appenders when a trace
    is active. 
    -->
    <filter class="com.dottydingo.tracelog.logback.LoggerLevelFilter"/>
</appender>
```

## Executing the trace
Generally the trace will be set up in a request handler or Servlet Filter of some kind. The following example
shows how to set up the the trace:

```java
Trace trace = traceFactory.createTrace(TraceType.email,"trace_destination@somedomain.com");

// associate the trace with the current tread
traceManager.startTrace(trace);

... process request

// un-associate the trace from the current thread.
trace = traceManager.endTrace();

// close the trace and send the email
trace.close();
```

