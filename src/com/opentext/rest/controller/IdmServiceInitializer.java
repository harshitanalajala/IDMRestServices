package com.opentext.rest.controller;

import com.opentext.rest.services.AppServices;
import com.opentext.rest.services.DocServices;
import com.opentext.rest.services.LibServices;
import com.opentext.rest.services.QueueServices;
import com.opentext.rest.services.SystemServices;

public class IdmServiceInitializer {

	SystemServices sysServices;
	AppServices appServices;
	LibServices libServices;
	QueueServices queueServices;
	DocServices docServices;
	
	private static java.util.logging.Logger logger = java.util.logging.Logger
			.getLogger("com.opentext.rest.IdmServiceInitializer");


	public IdmServiceInitializer() {

		try {
			logger.severe("Came Here");
			sysServices = new SystemServices();
			appServices = new AppServices();
			libServices = new LibServices();
			queueServices=new QueueServices();
			docServices=new DocServices();
		} catch (Exception e) {
			logger.severe(e.toString());
		}

	}
}
