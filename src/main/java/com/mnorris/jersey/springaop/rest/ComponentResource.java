package com.mnorris.jersey.springaop.rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import com.mnorris.jersey.springaop.aspect.Pointless;
import com.mnorris.jersey.springaop.service.BasicService;

@Path("/component")
@Component
@Pointless
public class ComponentResource {
	@Context
	private UriInfo uriInfo;
	@Context
	private HttpServletRequest servletRequest;
	@Context
	private HttpServletResponse servletResponse;
	@Inject
	private BasicService basicService;

	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String ping() {
		System.out.println("Component uriInfo: " + uriInfo);
		System.out.println("Component servletRequest: " + servletRequest);
		System.out.println("Component servletResponse: " + servletResponse);
		System.out.println("Component basicService: " + basicService);
		return basicService.ping();
	}

}
