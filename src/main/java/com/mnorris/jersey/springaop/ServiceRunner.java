package com.mnorris.jersey.springaop;

import javax.servlet.ServletException;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.mnorris.jersey.springaop.config.JerseyApplicationConfig;
import com.mnorris.jersey.springaop.config.SpringApplicationConfig;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ListenerInfo;
import io.undertow.servlet.util.ImmediateInstanceHandle;

public class ServiceRunner {
	public static void main(final String[] args) throws Exception {
		initUndertow();
	}

	public static void initUndertow() throws Exception {
		final AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(SpringApplicationConfig.class);
		final ResourceConfig resourceConfig = new JerseyApplicationConfig();

		try {
			System.out.println("undertow starting");
			final DeploymentInfo servletBuilder = Servlets.deployment()
					.setClassLoader(Application.class.getClassLoader()).setContextPath("/api")
					.setDeploymentName(
							"test")
					.addListener(createContextLoaderListener(appContext))
					.addServlets(Servlets
							.servlet("MyServlet", ServletContainer.class,
									() -> new ImmediateInstanceHandle(new ServletContainer(resourceConfig)))
							.addMapping("/*").setAsyncSupported(true));
			final DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
			manager.deploy();

			final HttpHandler servletHandler = manager.start();
			final PathHandler path = Handlers.path(Handlers.redirect("/api")).addPrefixPath("/api", servletHandler);

			final Undertow server = Undertow.builder().addHttpListener(8081, "localhost")
					.setHandler(Handlers.path().addPrefixPath("/", path)).build();

			server.start();
		} catch (final ServletException e) {
			throw new RuntimeException(e);
		}
	}

	private static ListenerInfo createContextLoaderListener(final AnnotationConfigWebApplicationContext appContext) {
		return new ListenerInfo(ContextLoaderListener.class,
				() -> new ImmediateInstanceHandle(new ContextLoaderListener(appContext)));
	}

}
