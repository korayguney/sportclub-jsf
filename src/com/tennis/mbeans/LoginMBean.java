package com.tennis.mbeans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.tennis.models.Admin;
import com.tennis.models.Login;
import com.tennis.models.Parent;
import com.tennis.models.Player;
import com.tennis.models.Role;
import com.tennis.models.User;
import com.tennis.services.LoginService;

@ManagedBean
public class LoginMBean {

	private Login login;
	
	@PostConstruct
	private void init() {
		login = new Login();
	}
	
	@ManagedProperty(value = "#{sessionScopeBean}")
	SessionScopeBean sessionScopeBean;
	
	@EJB
	private LoginService loginService;

	public String checkUser() {
		System.out.println("inside checkuser");
		User user = loginService.checkUserOnDatabase(login);
		System.out.println("USER : " + user );
		
		if (user != null) {
			
			sessionScopeBean.setUser(user);
			
			if (user instanceof Admin) {
				return "secure/main-admin";
			} else if (user instanceof Parent) {
				return "secure/main-parent";
			} else if (user instanceof Player) {
				return "secure/main-player";
			}
			return "login";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Email or password is wrong"));
			return "login";
		}

	}

	public void logout() {
		
		FacesContext context = FacesContext.getCurrentInstance();
    	context.getExternalContext().invalidateSession();
        try {
			context.getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public SessionScopeBean getSessionScopeBean() {
		return sessionScopeBean;
	}

	public void setSessionScopeBean(SessionScopeBean sessionScopeBean) {
		this.sessionScopeBean = sessionScopeBean;
	}

	
	
	
}
