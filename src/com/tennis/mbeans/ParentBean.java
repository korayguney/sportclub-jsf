package com.tennis.mbeans;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.tennis.models.Game;
import com.tennis.models.Player;
import com.tennis.models.User;
import com.tennis.services.GameService;
import com.tennis.services.TournamentService;
import com.tennis.services.UserService;

@ManagedBean
public class ParentBean {
	
	private List<User> users;
	private Player player;
	
	@EJB
	private UserService userService;
	
	@EJB
	private GameService gameService;
	
	@EJB
	private TournamentService tournamentService;
	
	@ManagedProperty(value = "#{sessionScopeBean}")
	SessionScopeBean sessionScopeBean;
	
	@PostConstruct
	public void init() {
		users = userService.getAllUsers();
		player = new Player();
	}
	
	public Player getPlayerOfParent() {
		this.player = userService.getPlayerOfParent(sessionScopeBean.getUser());
		
		return this.player;

	}
	
	public Game getCurrentGame() {
		
		
		return null;
		
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	public SessionScopeBean getSessionScopeBean() {
		return sessionScopeBean;
	}

	public void setSessionScopeBean(SessionScopeBean sessionScopeBean) {
		this.sessionScopeBean = sessionScopeBean;
	}
	
	
	
	
}
