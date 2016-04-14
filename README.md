Summary
===================

Demonstrates the fix so that @Context injection works when a REST resource is using aspects and annotated with @Component.  This is spring-aop specific.

This project uses a simple aspect called *Pointless* which will go around any public method.

----------

## Running

1) Load the project in your IDE and run `ServiceRunner`
OR
2) Run `mvn clean compile exec:java`

The project uses embedded Undertow as the web server, but that should be irrelevant to this.

## Jersey 2.22.2

Run the following:

	curl localhost:8081/api/component
	
The following output will show the aspect being called for both the REST endpoint and the service.  All the @Context fields injected are null.

	Pointless aspect called
	Component uriInfo: null
	Component servletRequest: null
	Component servletResponse: null
	Component basicService: com.mnorris.jersey.springaop.service.BasicService@284edf9
	Pointless aspect called

## Jersey 2.23-SNAPSHOT

Update the POM to use SNAPSHOT, then run same command:

	curl localhost:8081/api/component
	
The output shows that injection happened properly:

	Pointless aspect called
	Component uriInfo: org.glassfish.jersey.server.internal.routing.UriRoutingContext@5d7bbd8a
	Component servletRequest: HttpServletRequestImpl [ GET /api/component ]
	Component servletResponse: io.undertow.servlet.spec.HttpServletResponseImpl@241a0e30
	Component basicService: com.mnorris.jersey.springaop.service.BasicService@529944db
	Pointless aspect called
