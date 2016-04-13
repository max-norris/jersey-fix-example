package com.mnorris.jersey.springaop.service;

import javax.inject.Named;
import javax.inject.Singleton;

import com.mnorris.jersey.springaop.aspect.Pointless;

/**
 * Basic service to show injection working
 *
 */

@Named
@Singleton
@Pointless
public class BasicService {

	public String ping() {
		return "ping";
	}
}
