package com.tennis.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.tennis.models.Role;
import com.tennis.models.User;
import com.tennis.services.UserService;

@ManagedBean
@ViewScoped
public class UpdateUserBean {

	private User user;
	private List<SelectItem> roles;
	private String previousEmail;
	
	@EJB
	UserService userService;

	@PostConstruct
	public void init() {

		roles = new ArrayList<SelectItem>();

		roles.add(new SelectItem(Role.ADMIN));
		roles.add(new SelectItem(Role.PARENT));
		roles.add(new SelectItem(Role.PLAYER));

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		int userId = Integer.parseInt(req.getParameter("userId"));
		user = userService.getUser(userId);
		previousEmail = user.getEmail();
	}

	public User getUser() {
		return user;
	}

	public String updateUser() {
		userService.updateUser(user, this.previousEmail);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "User is updated"));
		return "secure/updateuser";
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<SelectItem> getRoles() {
		return roles;
	}

	public void setRoles(List<SelectItem> roles) {
		this.roles = roles;
	}

	public String getPreviousEmail() {
		return previousEmail;
	}

	public void setPreviousEmail(String previousEmail) {
		this.previousEmail = previousEmail;
	}

	
}
