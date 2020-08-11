package com.tennis.mbeans;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tennis.models.Player;
import com.tennis.models.User;
import com.tennis.services.UserService;

@ManagedBean
public class UserListBean {

	private List<User> users;
	private List<Player> players;
	
	@EJB
	private UserService userService;
	
	@PostConstruct
	public void init() {
		users = userService.getAllUsers();
		players = userService.getAllPlayers();
	}
	
	public String deleteUser(User user) {
		userService.deleteUser(user);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Succesfully deleted"));
		return "secure/userlist?faces-redirect=true";

	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	
	
	
}
