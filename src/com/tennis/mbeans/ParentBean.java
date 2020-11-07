package com.tennis.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.tennis.models.Match;
import com.tennis.models.MatchScore;
import com.tennis.models.Player;
import com.tennis.models.Tournament;
import com.tennis.models.User;
import com.tennis.services.GameService;
import com.tennis.services.TournamentService;
import com.tennis.services.UserService;

@ManagedBean
public class ParentBean {
	
	private List<User> users;
	private Player player;
	private Tournament tournament;
	private MatchScore gameSet;
	
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
		tournament = new Tournament();
		gameSet = new MatchScore();
	}
	
	public Player getPlayerOfParent() {
		this.player = userService.getPlayerOfParent(sessionScopeBean.getUser());
		return this.player;
	}
	
	public Tournament getTournamentOfPlayer() {
		this.tournament = userService.getTournamentOfPlayer(getPlayerOfParent());
		return this.tournament;
	}
	
	public MatchScore getGameSetOfPlayer() {
		this.gameSet = userService.getGameSetOfPlayer(getPlayerOfParent());
		return this.gameSet;
	}
	
	public Match getCurrentGame() {
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

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public MatchScore getGameSet() {
		return gameSet;
	}

	public void setGameSet(MatchScore gameSet) {
		this.gameSet = gameSet;
	}
	
	
}
