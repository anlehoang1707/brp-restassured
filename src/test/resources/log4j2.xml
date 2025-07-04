<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" packages="commons">
    <Properties>
        <Property name="random">$${date:dd-MM-YYYY_HH-mm-ss}</Property>
        <Property name="pattern">[%d{dd-MM-YYYY HH:mm:ss.SSS} %level %logger{36}] - %msg%n</Property>
    </Properties>

    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${pattern}" />
        </Console>

        <File name="FixedFile" fileName="logs/Fixed.log" append="true">
            <PatternLayout pattern="${pattern}" />
        </File>

        <File name="RandomFile" fileName="logs/Random.${random}.log" immediateFlush="false" append="true">
            <PatternLayout pattern="${pattern}" />
        </File>

        <TestNGAppender name="TestAppender">
            <PatternLayout pattern="${pattern}" />
        </TestNGAppender>
    </Appenders>

    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="FixedFile"/>
            <AppenderRef ref="RandomFile"/>
            <AppenderRef ref="TestAppender" />
        </Root>
    </Loggers>
</Configuration>