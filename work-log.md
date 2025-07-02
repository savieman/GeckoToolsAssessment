# logged hours
## june 27

10:30 10:40 reading 

10:40 11:00 made notes

11:01 11:30 Created spring boot project and Have all the general expected endpoints ready

13:00 13:15 Reading about test containers and installing docker

13:15 14:30 Trying to setup test containers and fixing an exception 
```
java.lang.AbstractMethodError: Receiver class ch.qos.logback.classic.PatternLayout does not define or inherit an implementation of the resolved method 'abstract java.util.Map getDefaultConverterSupplierMap()' of abstract class ch.qos.logback.core.pattern.PatternLayoutBase.
	at ch.qos.logback.core.pattern.PatternLayoutBase.getEffectiveConverterMap(PatternLayoutBase.java:79)
	at ch.qos.logback.core.pattern.PatternLayoutBase.start(PatternLayoutBase.java:143)
	at ch.qos.logback.classic.encoder.PatternLayoutEncoder.start(PatternLayoutEncoder.java:28)
	at org.springframework.boot.logging.logback.LogbackConfigurator.start(LogbackConfigurator.java:111)
	at org.springframework.boot.logging.logback.DefaultLogbackConfiguration.createAppender(DefaultLogbackConfiguration.java:155)
	at org.springframework.boot.logging.logback.DefaultLogbackConfiguration.consoleAppender(DefaultLogbackConfiguration.java:137)
	at org.springframework.boot.logging.logback.DefaultLogbackConfiguration.apply(DefaultLogbackConfiguration.java:86)
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.lambda$loadDefaults$1(LogbackLoggingSystem.java:241)
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.withLoggingSuppressed(LogbackLoggingSystem.java:481)
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.loadDefaults(LogbackLoggingSystem.java:230)
	at org.springframework.boot.logging.AbstractLoggingSystem.initializeWithConventions(AbstractLoggingSystem.java:84)
	at org.springframework.boot.logging.AbstractLoggingSystem.initialize(AbstractLoggingSystem.java:61)
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.initialize(LogbackLoggingSystem.java:195)
	at org.springframework.boot.context.logging.LoggingApplicationListener.initializeSystem(LoggingApplicationListener.java:332)
	at org.springframework.boot.context.logging.LoggingApplicationListener.initialize(LoggingApplicationListener.java:298)
	at org.springframework.boot.context.logging.LoggingApplicationListener.onApplicationEnvironmentPreparedEvent(LoggingApplicationListener.java:246)
	at org.springframework.boot.context.logging.LoggingApplicationListener.onApplicationEvent(LoggingApplicationListener.java:223)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:185)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:178)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:156)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:138)
	at org.springframework.boot.context.event.EventPublishingRunListener.multicastInitialEvent(EventPublishingRunListener.java:136)
	at org.springframework.boot.context.event.EventPublishingRunListener.environmentPrepared(EventPublishingRunListener.java:81)
	at org.springframework.boot.SpringApplicationRunListeners.lambda$environmentPrepared$2(SpringApplicationRunListeners.java:64)
	at java.base/java.lang.Iterable.forEach(Iterable.java:75)
	at org.springframework.boot.SpringApplicationRunListeners.doWithListeners(SpringApplicationRunListeners.java:118)
	at org.springframework.boot.SpringApplicationRunListeners.doWithListeners(SpringApplicationRunListeners.java:112)
	at org.springframework.boot.SpringApplicationRunListeners.environmentPrepared(SpringApplicationRunListeners.java:63)
	at org.springframework.boot.SpringApplication.prepareEnvironment(SpringApplication.java:353)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:313)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1361)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1350)
	at com.example.RecipeService.RecipeServiceApplication.main(RecipeServiceApplication.java:10)
13:24:01,049 |-WARN in Logger[org.springframework.boot.SpringApplication] - No appenders present in context [default] for logger [org.springframework.boot.SpringApplication].
```

14:30 14:50 Creating sql tables that fit the entity that is created

14:50 15:00  Injecting RecipeRepository in the controller

15:00 15:30  Creating unit tests

15:40 16:35 Working through Issues with entity and sql

16:35 16:45 Asked question in detail in the channel about it

16:57 17:06 Thought of a solutions for the ingredients and their amounts

17:06 17:27 implemented feedback of the backenders

17:27 17:46 fixed project not running because of wrong properties

17:46 18:02 trying to fix org.hibernate.StaleObjectStateException Row was updated or deleted by another transaction (or unsaved-
value mapping was incorrect): [com.example.RecipeService.Model.Recipe#0]

18:02 18:20 Created a working docker-compose file to make running a docker-container with the service easier

18:20 18:30 Comitting all the files in singles commits that I hadn't committed yet

## June 30
12:30 12:35 setting things up

12:35 13:30 fixing staleObjectexception and transcientObjectException

13:45 14:00 Got all endpoints working to it's basic level except search

14:00 14:12 added Exceptioncontroller to handle when entities are not found instead of getting an internal server error

14:15 14:43 working on search / querying

14:43 15:00 fix relationship between recipe and ingredient

15:00 16:00 fixed search query

16:00 16:17 made search query also list all the recipes when no queries are filled in

16:30 16:50 implementing testcontainers 

16:50 17:05 test containers working by adding missing postgresql dependency

17:30 17:50 Added more swagger documentation

## July 1
09:45 10:49 Created unit tests for RecipeService.java

10:49 11:05 Setup the necessities for unit tests for the controller

13:15 14:00 Setting up unit tests for the RecipeController

14:15 14:20 Setup a base for the front-end

14:20 15:30 Solved all unit tests for the controller with help for some issues

15:40 16:15 Fixed listing recipes in the front-end

22:30 22:56 Have the front-end be able to create recipes

22:56 22:05 Fix issue where ingredients with the same name were not possible

22:05 23:30 Have the front-end use signals for listing the recipes

# July 2
12:52 13:10 read about integration testing online and ask about it in the teams channel

13:10 13:29 Have integration tests mostly working

13:29 13:54 Fixed last issues I had with two integration tests

14:10 14:15 Giving an update in the teams chat and making small improvements in the code

14:15 14:30 Making more small improvements to the project

14:45 15:20 Work on documentation

15:20 15:25 Added unsubscribe in angular to prevent memory leaks

15:30 16:20 Added documentation to README.md

21:10 21:41 Editing documentation after a second glance
