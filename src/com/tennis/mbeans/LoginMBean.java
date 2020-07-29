package com.tennis.mbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tennis.models.Login;
import com.tennis.models.Role;
import com.tennis.models.User;
import com.tennis.services.LoginService;

@ManagedBean
public class LoginMBean {

	private Login login;

	@EJB
	private LoginService loginService;

	public String checkUser() {

		User user = loginService.checkUserOnDatabase(login);

		if (user != null) {
			if (user.getRole().equals(Role.ADMIN)) {
				return "main-admin";
			} else if (user.getRole().equals(Role.PARENT)) {
				return "main-parent";
			} else if (user.getRole().equals(Role.PLAYER)) {
				return "main-player";
			}
			return "login";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Email or password is wrong"));
			return "login";
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

	
	
	
}
