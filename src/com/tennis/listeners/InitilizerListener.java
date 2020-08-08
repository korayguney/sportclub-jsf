package com.tennis.listeners;


import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tennis.services.InitService;


@WebListener
public class InitilizerListener implements ServletContextListener{

	@EJB
	InitService initService;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		initService.saveInitilizedUser();
		initService.saveInitilizedTournament();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
