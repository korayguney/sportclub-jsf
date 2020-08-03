package com.tennis.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import com.tennis.models.Player;
import com.tennis.models.Role;
import com.tennis.models.User;
import com.tennis.services.UserService;

@ManagedBean(name = "register")
public class RegisterBean {

	private User user;
	private Player player;
	private List<SelectItem> roles;
	private boolean disableAge;

	@PostConstruct
	public void init() {
		disableAge = true;
		user = new User();
		roles = new ArrayList<SelectItem>();
		roles.add(new SelectItem(Role.ADMIN));
		roles.add(new SelectItem(Role.PARENT));
		roles.add(new SelectItem(Role.PLAYER));

	}

	@EJB
	UserService userService;

	public String saveUser() {
		userService.saveUser(this.user);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "", "User is saved "));
		return "register";
	}

	public void enableInputText(ValueChangeEvent event) {
		System.out.println(event.getNewValue());
		Role role = (Role) event.getNewValue();

		if (role.equals(Role.PLAYER)) {
			setDisableAge(false);
		} else if (role.equals(Role.ADMIN)) {
			setDisableAge(true);
		} else if (role.equals(Role.PARENT)) {
			setDisableAge(true);
		}

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isDisableAge() {
		return disableAge;
	}

	public void setDisableAge(boolean disableAge) {
		this.disableAge = disableAge;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SelectItem> getRoles() {
		return roles;
	}

	public void setRoles(List<SelectItem> roles) {
		this.roles = roles;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
